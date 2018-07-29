package com.oocl.Spring_flywaydb.controller;


import com.oocl.Spring_flywaydb.controller.dto.EmployeesDTO;
import com.oocl.Spring_flywaydb.entities.Employees;
import com.oocl.Spring_flywaydb.repositories.EmployeesReository;
import com.oocl.Spring_flywaydb.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeesController {
    @Autowired
    private EmployeesService employeesService;



    @Transactional
    @GetMapping("/Employees")
    public List<EmployeesDTO> getAllEmployees(){
        return employeesService.getAllEmployees();
    }

//    @Transactional
//    @GetMapping("/Employees/page/{page}/pageSize/{pageSize}")
//    public List<EmployeesDTO> getAllEmployees(@PathVariable int page,@PathVariable int pageSize){
//        return EmployeesService;
//    }

    @Transactional
    @PostMapping("/Employees")
    public ResponseEntity addEmployees(@RequestBody Employees Employees){
        if (employeesService.addEmployees(Employees)){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @Transactional
    @PutMapping("/Employees")
    public ResponseEntity updateEmployees(@RequestBody Employees Employees){
        if (employeesService.updateEmployees(Employees)){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Transactional
    @GetMapping("/Employees/{id}")
    public EmployeesDTO getById(@PathVariable("id") Long id){
        return employeesService.getById(id);
    }

    @Transactional
    @DeleteMapping("/Employees/{id}")
    public ResponseEntity delete(@PathVariable("id")Long id){
        if (employeesService.deleteById(id)){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
