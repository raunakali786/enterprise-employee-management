package com.raunak.ems.service;

import com.raunak.ems.dto.CreateEmployeeRequestDTO;
import com.raunak.ems.dto.EmployeeResponseDTO;
import com.raunak.ems.entity.Employee;
import com.raunak.ems.exception.EmployeeNotFoundException;
import com.raunak.ems.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResponseDTO saveEmployee(CreateEmployeeRequestDTO dto) {

        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setSalary(dto.getSalary());

        Employee saved = employeeRepository.save(employee);

        return mapToResponse(saved);
    }

//    @Override
//    public List<EmployeeResponseDTO> getAllEmployees() {
//        return employeeRepository.findAll()
//                .stream()
//                .map(this::mapToResponse)
//                .toList();
//    }

    @Override
    public Page<EmployeeResponseDTO> getAllEmployees(Pageable pageable) {

        Page<Employee> page = employeeRepository.findAll(pageable);

        return page.map(this::mapToResponse);
    }


    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

        return mapToResponse(employee);
    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

        employeeRepository.delete(employee);
    }

    @Override
    public EmployeeResponseDTO updateEmployee(Long id, CreateEmployeeRequestDTO dto) {

        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setSalary(dto.getSalary());

        Employee updated = employeeRepository.save(existing);

        return mapToResponse(updated);
    }

    private EmployeeResponseDTO mapToResponse(Employee employee) {

        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setSalary(employee.getSalary());

        return dto;
    }









//    // Function to map the response to the dto with entity.
//
//    private EmployeeResponseDTO mapToResponse(Employee employee) {
//        EmployeeResponseDTO dto = new EmployeeResponseDTO();
//        dto.setId(employee.getId());
//        dto.setName(employee.getName());
//        dto.setEmail(employee.getEmail());
//        dto.setSalary(employee.getSalary());
//        return dto;
//    }


    // Save method directly communicating with DTO
//    @Override
//    public Employee saveEmployee(Employee employee) {
//        return employeeRepository.save(employee);
//    }

    //Save Method communicating DTO
//
//    public EmployeeResponseDTO saveEmployee(CreateEmployeeRequestDTO dto) {
//
//        Employee employee = new Employee();
//        employee.setName(dto.getName());
//        employee.setEmail(dto.getEmail());
//        employee.setSalary(dto.getSalary());
//
//        Employee saved = employeeRepository.save(employee);
//
//        return mapToResponse(saved);
//    }

//
//    @Override
//    public List<Employee> getAllEmployees() {
//        return employeeRepository.findAll();
//    }
//
//    @Override
//    public Employee getEmployeeById(Long id) {
//        return employeeRepository.findById(id)
//                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
//    }
//
//    @Override
//    public void deleteEmployee(Long id) {
//        employeeRepository.deleteById(id);
//    }
//
//    @Override
//    public Employee updateEmployee(Long id, Employee updatedEmployee) {
//
//        Employee existingEmployee = employeeRepository.findById(id)
//                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
//
//        existingEmployee.setName(updatedEmployee.getName());
//        existingEmployee.setEmail(updatedEmployee.getEmail());
//        existingEmployee.setSalary(updatedEmployee.getSalary());
//
//        return employeeRepository.save(existingEmployee);
//    }

}
