package com.raunak.ems.specification;

import com.raunak.ems.entity.Employee;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {

    public static Specification<Employee> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),
                        "%" + name.toLowerCase() + "%"
                );
    }

    public static Specification<Employee> hasEmail(String email) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("email")),
                        "%" + email.toLowerCase() + "%"
                );
    }

    public static Specification<Employee> salaryGreaterThan(Double salary) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("salary"), salary);
    }
}