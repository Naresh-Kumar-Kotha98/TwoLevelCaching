package com.twocacheing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twocacheing.model.model.Employee;

public interface EmpRepository extends JpaRepository<Employee, Integer> {

}
