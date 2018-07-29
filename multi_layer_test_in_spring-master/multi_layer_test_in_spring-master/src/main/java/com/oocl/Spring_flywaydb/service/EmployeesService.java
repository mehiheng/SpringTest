package com.oocl.Spring_flywaydb.service;

import com.oocl.Spring_flywaydb.controller.dto.oneToMany.EmployeesDTO;
import com.oocl.Spring_flywaydb.entities.oneToMany.Employees;
import com.oocl.Spring_flywaydb.repositories.oneToMany.EmployeesReository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeesService {
    @Autowired
    private EmployeesReository EmployeesReository;


    public List<EmployeesDTO> getAllEmployees(){
        return EmployeesReository.findAll().stream().map(item->
                new EmployeesDTO(item)).collect(Collectors.toList());
    }

    public List<EmployeesDTO> getAllEmployeesByPage(int page,int pageSize){

        return EmployeesReository.findAll(PageRequest.of(page,pageSize)).stream().map(item->
                new EmployeesDTO(item)).collect(Collectors.toList());
    }

    public boolean addEmployees(Employees Employees){
        Employees save = EmployeesReository.save(Employees);
        return save!=null;
    }


    public boolean updateEmployees(Employees Employees){
        Employees save = EmployeesReository.save(Employees);
        return save!=null;
    }


    public EmployeesDTO getById(Long id){
        Employees Employees = EmployeesReository.findById(id).get();
        return new EmployeesDTO(Employees);
    }


    public boolean deleteById(Long id){
        int deleteId = EmployeesReository.deleteEmployeesById(id);
        return deleteId!=0;
    }


}
