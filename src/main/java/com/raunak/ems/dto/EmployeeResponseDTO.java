package com.raunak.ems.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDTO {

    private Long id;
    private String name;
    private String email;
    private double salary;

    // getters and setters
}
