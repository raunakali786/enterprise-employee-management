package com.raunak.ems.service;
import com.raunak.ems.dto.CreateEmployeeRequestDTO;
import com.raunak.ems.dto.EmployeeResponseDTO;
import com.raunak.ems.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface EmployeeService {

//    Employee saveEmployee(Employee employee);

//    List<Employee> getAllEmployees();

//    Employee getEmployeeById(Long id);

//    void deleteEmployee(Long id);

//    Employee updateEmployee(Long id, Employee employee);

    EmployeeResponseDTO saveEmployee(CreateEmployeeRequestDTO dto);

//    List<EmployeeResponseDTO> getAllEmployees();

//    Page<EmployeeResponseDTO> getAllEmployees(Pageable pageable);

    Page<?> getAllEmployees (
            String name,
            String email,
            Double minSalary,
            Pageable pageable
    );

    EmployeeResponseDTO getEmployeeById(Long id);

    void deleteEmployee(Long id);

    EmployeeResponseDTO updateEmployee(Long id, CreateEmployeeRequestDTO dto);

}
