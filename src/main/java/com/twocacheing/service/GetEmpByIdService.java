package com.twocacheing.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.twocacheing.model.dto.EmployeeDto;

@Component
public interface GetEmpByIdService {
  
  @Cacheable(value = "employees", key = "#id")
  public EmployeeDto process(int id);

}
