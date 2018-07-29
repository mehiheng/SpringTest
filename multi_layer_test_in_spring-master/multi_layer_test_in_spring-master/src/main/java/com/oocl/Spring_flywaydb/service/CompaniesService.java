package com.oocl.Spring_flywaydb.service;

import com.oocl.Spring_flywaydb.controller.dto.oneToMany.CompaniesDTO;
import com.oocl.Spring_flywaydb.entities.oneToMany.Companies;
import com.oocl.Spring_flywaydb.repositories.oneToMany.CompaniesReository;
import com.oocl.Spring_flywaydb.repositories.oneToMany.EmployeesReository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompaniesService {
    @Autowired
    private CompaniesReository companiesReository;

    @Autowired
    private EmployeesReository employeesReository;


    public List<CompaniesDTO> getAllCompanies(){
        return companiesReository.findAll().stream().map(item->
            new CompaniesDTO(item)).collect(Collectors.toList());
    }

    public List<CompaniesDTO> getAllCompaniesByPage(int page,int pageSize){
        return companiesReository.findAll(PageRequest.of(page,pageSize)).stream().map(item->
                new CompaniesDTO(item)).collect(Collectors.toList());
    }

    public boolean addCompanies(Companies companies){
        companies.getEmployees().stream().forEach(employees -> {
            employees.setCompanies(companies);
        });
            Companies save = companiesReository.save(companies);
        return save!=null;
    }


    public boolean updateCompanies(Companies companies){
        companies.getEmployees().stream().filter(employees -> employees.getCompanies()==null).forEach(employees -> {
            employees.setCompanies(companies);
        });
        Companies save = companiesReository.save(companies);
        return save!=null;
    }


    public CompaniesDTO getById(Long id){
        Companies companies = companiesReository.findById(id).get();
        return new CompaniesDTO(companies);
    }


    public boolean deleteById(Long id){
      int deleteId = companiesReository.deleteCompaniesById(id);
        return deleteId!=0;
    }



}
