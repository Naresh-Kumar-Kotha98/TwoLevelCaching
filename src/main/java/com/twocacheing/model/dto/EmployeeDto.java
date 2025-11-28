package com.twocacheing.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto extends InputModel {
  
  /**
   * 
   */
  private static final long serialVersionUID = -8585539090216180301L;
  
  private int id;
  
  @NotBlank(message = "Name must not be blank")
  private String name;
  
  @NotNull(message = "Salary is required")
  @Min(value = 1000, message = "Salary must be at least 1000")
  private double salary;

}
