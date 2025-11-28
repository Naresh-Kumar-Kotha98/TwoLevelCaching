package com.twocacheing.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.twocacheing.model.dto.EmployeeDto;

@Component
public interface GetAllEmpService {
  
  @Cacheable(value = "employees")
  public List<EmployeeDto> process();

}
