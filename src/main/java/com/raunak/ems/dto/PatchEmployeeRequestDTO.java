package com.raunak.ems.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatchEmployeeRequestDTO {

    private String name;

    @Email
    private String email;

    @Positive
    private Double salary;

}