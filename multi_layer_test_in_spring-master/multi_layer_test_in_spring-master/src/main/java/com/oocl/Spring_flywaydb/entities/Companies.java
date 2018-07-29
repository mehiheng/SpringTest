package com.oocl.Spring_flywaydb.entities;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Companies {
    @Id
    private Long id;
    private String name;
    @CreatedDate
    private ZonedDateTime createdDate = ZonedDateTime.now();



    @OneToMany(cascade = CascadeType.ALL,mappedBy = "companies",fetch = FetchType.LAZY)
    private List<Employees> employees = new ArrayList<>();

    public Companies() {
    }

    public Companies(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Companies(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public List<Employees> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employees> employees) {
        this.employees = employees;
    }
}
