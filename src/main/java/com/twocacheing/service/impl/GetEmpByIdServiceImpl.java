package com.twocacheing.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twocacheing.model.dto.EmployeeDto;
import com.twocacheing.model.model.Employee;
import com.twocacheing.repository.EmpRepository;
import com.twocacheing.service.GetEmpByIdService;

@Service
public class GetEmpByIdServiceImpl implements GetEmpByIdService {
  
  private final EmpRepository empRepository;
  
  @Autowired
  public GetEmpByIdServiceImpl(EmpRepository empRepository) {
    this.empRepository = empRepository;
  }

  @Override
  public EmployeeDto process(int id) {
    Optional<Employee> respEmp = empRepository.findById(id);
    EmployeeDto empResp = new EmployeeDto();
    if(respEmp.isPresent()) {
      Employee respEmpGet = respEmp.get();
      empResp = EmployeeDto.builder().id(respEmpGet.getId()).name(respEmpGet.getName()).salary(respEmpGet.getSalary())
          .build();
    }
   
    return empResp;
  }

}
