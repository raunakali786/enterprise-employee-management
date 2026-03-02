package com.raunak.ems.repository;

import com.raunak.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

//public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//}

// New JPA Repository with specification which alows
//Dynamic filtering
//✔ Multiple optional filters
//✔ Clean service layer
//✔ Scalable APIs

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeRepository
        extends JpaRepository<Employee, Long>,
        JpaSpecificationExecutor<Employee> {
}