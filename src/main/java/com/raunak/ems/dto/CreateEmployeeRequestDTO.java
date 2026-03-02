package com.raunak.ems.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateEmployeeRequestDTO {

    @NotBlank(message = "Name must not be blank")
    private String name;

    @Email
    @NotBlank
    private String email;

    @Positive
    private double salary;

    // getters and setters
}
