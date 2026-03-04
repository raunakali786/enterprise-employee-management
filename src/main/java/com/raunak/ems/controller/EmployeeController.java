package com.raunak.ems.controller;

import com.raunak.ems.common.ApiResponse;
import com.raunak.ems.common.PagedResponse;
import com.raunak.ems.dto.CreateEmployeeRequestDTO;
import com.raunak.ems.dto.EmployeeResponseDTO;
import com.raunak.ems.dto.UpdateEmployeeRequestDTO;
import com.raunak.ems.entity.Employee;
import com.raunak.ems.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(
            @Valid @RequestBody CreateEmployeeRequestDTO dto) {

        EmployeeResponseDTO saved = employeeService.saveEmployee(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

//    @GetMapping
//    public List<EmployeeResponseDTO> getAllEmployees() {
//        return employeeService.getAllEmployees();
//    }

    @GetMapping
//    public ResponseEntity<Page<EmployeeResponseDTO>> getAllEmployees
    public ResponseEntity<ApiResponse<PagedResponse<?>>> getAllEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Double minSalary,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id,asc") String sort
    ) {
        String[] sortParams = sort.split(",");

        String sortField = sortParams[0];
        String sortDirection = sortParams.length > 1 ? sortParams[1] : "asc";
        Sort sortObj = Sort.by(Sort.Direction.fromString(sortDirection), sortField);

        Pageable pageable = PageRequest.of(page, size, sortObj);
//        Page<EmployeeResponseDTO> employees = employeeService.getAllEmployees(pageable);

//        Page<EmployeeResponseDTO> employees =
//                employeeService.getAllEmployees(name, email, minSalary, pageable);

        Page<?> pageResult =
                employeeService.getAllEmployees(name, email, minSalary, pageable);

        PagedResponse<?> pagedResponse =
                new PagedResponse<>(
                        pageResult.getContent(),
                        pageResult.getNumber(),
                        pageResult.getSize(),
                        pageResult.getTotalElements(),
                        pageResult.getTotalPages(),
                        pageResult.isLast()
                );

        ApiResponse<PagedResponse<?>> response = response =
                new ApiResponse<>(
                        true,
                        "Employees fetched successfully",
                        pagedResponse
                );

        return ResponseEntity.ok(response);

//        return ResponseEntity.ok(employees);
    }


    @GetMapping("/{id}")
    public EmployeeResponseDTO getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
//        return ResponseEntity.ok("Employee deleted successfully");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody CreateEmployeeRequestDTO dto) {

        EmployeeResponseDTO updated = employeeService.updateEmployee(id, dto);
        return ResponseEntity.ok(updated);
    }

    // CREATE
//    @PostMapping
//    public Employee createEmployee(@Valid @RequestBody Employee employee) {
//        return employeeService.saveEmployee(employee);
//    }

//    @PostMapping
//    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
//        Employee saved = employeeService.saveEmployee(employee);
//        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
//    }



//
//    // READ ALL
//    @GetMapping
//    public List<Employee> getAllEmployees() {
//        return employeeService.getAllEmployees();
//    }

    // READ BY ID
//    @GetMapping("/{id}")
//    public Employee getEmployeeById(@PathVariable Long id) {
//        return employeeService.getEmployeeById(id);
//    }



//    // DELETE
//    @DeleteMapping("/{id}")
//    public void deleteEmployee(@PathVariable Long id) {
//        employeeService.deleteEmployee(id);
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Employee> updateEmployee(
//            @PathVariable Long id,
//            @Valid @RequestBody Employee employee) {
//
//        Employee updated = employeeService.updateEmployee(id, employee);
//        return ResponseEntity.ok(updated);
    // Just to test the Branch updation on GitHub
//    }



}
