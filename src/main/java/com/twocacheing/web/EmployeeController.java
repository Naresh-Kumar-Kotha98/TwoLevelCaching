package com.twocacheing.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twocacheing.exception.EmployeeNotFoundException;
import com.twocacheing.model.dto.EmployeeDto;
import com.twocacheing.service.CreateEmpService;
import com.twocacheing.service.GetAllEmpService;
import com.twocacheing.service.GetEmpByIdService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("employees")
public class EmployeeController {

  private final CreateEmpService createEmpService;
  
  private final GetAllEmpService getAllEmpService;
  
  private final GetEmpByIdService getByIdService;
  
  @Autowired
  public EmployeeController(CreateEmpService createEmpService, GetAllEmpService getAllEmpService,GetEmpByIdService getByIdService) {
    this.createEmpService = createEmpService;
    this.getAllEmpService = getAllEmpService;
    this.getByIdService =  getByIdService;
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(required = true) int id) {
    EmployeeDto emp = getByIdService.process(id);
    if (emp.getId() == 0) {
      throw new EmployeeNotFoundException("Employee not found with id: " + id);
    }
    return ResponseEntity.ok(emp);
  }
  
  @GetMapping("")
  public ResponseEntity<List<EmployeeDto>> getEmployees() {
    List<EmployeeDto> empList = getAllEmpService.process();
    return new ResponseEntity<>( empList, HttpStatus.OK);
  }
  
  @PostMapping("")
  public ResponseEntity<EmployeeDto> createEmp(@Valid @RequestBody(required = true) EmployeeDto employeeDto) {
    EmployeeDto dto = createEmpService.process(employeeDto);
    return new ResponseEntity<>(dto, HttpStatus.CREATED);
  }
  
}
