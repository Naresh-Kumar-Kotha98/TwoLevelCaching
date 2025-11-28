package com.twocacheing.config;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

public class TwoLevelCache implements Cache {

  private static final Logger log = LoggerFactory.getLogger(TwoLevelCache.class);

    private final Cache caffeineCache;
    private final Cache redisCache;

    public TwoLevelCache(Cache caffeineCache, Cache redisCache) {
        this.caffeineCache = caffeineCache;
        this.redisCache = redisCache;
    }

    @Override
    public String getName() {
        return redisCache.getName();
    }

    @Override
    public Object getNativeCache() {
        return redisCache.getNativeCache();
    }

    @Override
    public ValueWrapper get(Object key) {
        // First check L1 (Caffeine)
        ValueWrapper value = caffeineCache.get(key);
        if (value != null) {
          log.info("Cache HIT (L1 - Caffeine) for key: {}", key);
            return value;
        }

        // If not found, check L2 (Redis)
        value = redisCache.get(key);
        if (value != null) {
          log.info("Cache HIT (L2 - Redis) for key: {}", key);

            // Populate L1 for faster next access
            caffeineCache.put(key, value.get());
        }
        return value;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        ValueWrapper wrapper = get(key);
        return (wrapper != null ? (T) wrapper.get() : null);
    }

    @Override
    public void put(Object key, Object value) {
      log.info("Cache PUT for key: {}", key);

        caffeineCache.put(key, value);
        redisCache.put(key, value);
    }

    @Override
    public void evict(Object key) {
      log.info("Cache EVICT for key: {}", key);
        caffeineCache.evict(key);
        redisCache.evict(key);
    }

    @Override
    public void clear() {
      log.info("Cache CLEAR called");

        caffeineCache.clear();
        redisCache.clear();
    }
//    

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
      // TODO Auto-generated method stub
      return null;
    }
}
