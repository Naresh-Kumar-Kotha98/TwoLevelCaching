package com.twocacheing.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Collection;
import java.util.Collections;

/**
 * A custom CacheManager that combines Caffeine (L1) and Redis (L2).
 * It checks Caffeine first, then Redis if not found.
 */
public class TwoLevelCacheManager implements CacheManager {

    private final CacheManager caffeineCacheManager;
    private final CacheManager redisCacheManager;

    public TwoLevelCacheManager(CacheManager caffeineCacheManager, CacheManager redisCacheManager) {
        this.caffeineCacheManager = caffeineCacheManager;
        this.redisCacheManager = redisCacheManager;
    }

    @Override
    public Cache getCache(String name) {
        Cache caffeineCache = caffeineCacheManager.getCache(name);
        Cache redisCache = redisCacheManager.getCache(name);

        if (caffeineCache == null || redisCache == null) {
            return null;
        }

        return new TwoLevelCache(caffeineCache, redisCache);
    }

    @Override
    public Collection<String> getCacheNames() {
        return Collections.unmodifiableCollection(redisCacheManager.getCacheNames());
    }
}