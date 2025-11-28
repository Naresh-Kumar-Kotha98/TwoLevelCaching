package com.twocacheing.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twocacheing.model.dto.EmployeeDto;
import com.twocacheing.model.model.Employee;
import com.twocacheing.repository.EmpRepository;
import com.twocacheing.service.CreateEmpService;
import com.twocacheing.service.GetAllEmpService;

@Service
public class GetAllEmplServiceImpl implements GetAllEmpService {
  
  private final EmpRepository empRepository;
  
  @Autowired
  public GetAllEmplServiceImpl(EmpRepository empRepository) {
    this.empRepository = empRepository;
  }

  @Override
  public List<EmployeeDto> process() {
    System.out.println("got from repo");
    List<Employee> empList = empRepository.findAll();
    
    List<EmployeeDto> empListResp = convertEmpListToRespModel(empList);
    return empListResp;
  }

  private List<EmployeeDto> convertEmpListToRespModel(List<Employee> empList) {
//    List<EmployeeDto> empListResp = new ArrayList<>();
    return empList.stream().map(x-> EmployeeDto.builder().id(x.getId()).name(x.getName()).salary(x.getSalary())
        .build()).toList();
  }

  

}
