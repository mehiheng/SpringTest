package com.oocl.Spring_flywaydb.controller.dto;

import com.oocl.Spring_flywaydb.entities.Employees;

public class EmployeesDTO {
    private final Long id;
    private final String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public EmployeesDTO(Employees employees) {
        this.id = employees.getId();
        this.name = employees.getName();
        //this.companyId = employees.getCompanies().getId();
    }
}
