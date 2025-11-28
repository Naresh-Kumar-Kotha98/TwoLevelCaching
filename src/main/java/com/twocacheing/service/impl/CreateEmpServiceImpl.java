package com.twocacheing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twocacheing.model.dto.EmployeeDto;
import com.twocacheing.model.model.Employee;
import com.twocacheing.repository.EmpRepository;
import com.twocacheing.service.CreateEmpService;

@Service
public class CreateEmpServiceImpl implements CreateEmpService {
  
  private final EmpRepository empRepository;
  
  @Autowired
  public CreateEmpServiceImpl(EmpRepository empRepository) {
    this.empRepository = empRepository;
  }

  @Override
  public EmployeeDto process(EmployeeDto empDto) {
    Employee employee = Employee.builder().id(empDto.getId()).name(empDto.getName()).salary(empDto.getSalary()).build();
    Employee respEmp = empRepository.save(employee);
    EmployeeDto empResp = EmployeeDto.builder().id(respEmp.getId()).name(respEmp.getName()).salary(respEmp.getSalary())
        .build();
    return empResp;
  }

}
