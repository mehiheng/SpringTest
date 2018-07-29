package com.oocl.Spring_flywaydb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oocl.Spring_flywaydb.entities.Companies;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
public class Employees {
    @Id
    private Long id;
    private String name;
    @CreatedDate
    private ZonedDateTime createdDate = ZonedDateTime.now();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companies_id")
    private Companies companies;

    public Employees(String name) {
        this.name = name;
    }

    public Employees(Long id,String name) {
        this.id = id;
        this.name = name;

    }

    public Employees() {
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

    public Companies getCompanies() {
        return companies;
    }

    public void setCompanies(Companies companies) {
        this.companies = companies;
    }
}
