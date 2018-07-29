package com.oocl.Spring_flywaydb.repositories;

import com.oocl.Spring_flywaydb.entities.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface EmployeesReository extends JpaRepository<Employees,Long> {
    @Transactional
    int deleteEmployeesById(@Param("id") Long id);
}
