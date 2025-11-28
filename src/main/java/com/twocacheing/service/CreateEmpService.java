package com.twocacheing.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.twocacheing.model.dto.EmployeeDto;

@Service
public interface CreateEmpService {
  
  @Caching(evict = { @CacheEvict(cacheNames = "employeesCache", allEntries = true) })
  public EmployeeDto process(EmployeeDto emp);

}
