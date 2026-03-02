package com.raunak.ems.dto;

import jakarta.validation.constraints.*;

public class UpdateEmployeeRequestDTO {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @Positive
    private double salary;

    // getters and setters
}
