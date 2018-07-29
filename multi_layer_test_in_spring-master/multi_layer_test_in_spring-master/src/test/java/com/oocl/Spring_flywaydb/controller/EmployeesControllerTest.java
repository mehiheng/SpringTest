package com.oocl.Spring_flywaydb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.Spring_flywaydb.controller.dto.EmployeesDTO;
import com.oocl.Spring_flywaydb.controller.EmployeesController;
import com.oocl.Spring_flywaydb.entities.Employees;
import com.oocl.Spring_flywaydb.service.EmployeesService;
import com.oocl.Spring_flywaydb.controller.dto.EmployeesDTO;
import com.oocl.Spring_flywaydb.controller.EmployeesController;
import com.oocl.Spring_flywaydb.entities.Employees;
import com.oocl.Spring_flywaydb.service.EmployeesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeesController.class)
public class EmployeesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeesService employeesService;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void should_get_all_employees_without_any_paramters()throws Exception{
        //given

        Employees employees1 = new Employees(1L,"liming");
        Employees employees2 = new Employees(2L,"huawei");
        List<EmployeesDTO> employeesList = Arrays.asList(new EmployeesDTO(employees1),new EmployeesDTO(employees2));
        //when
        given(employeesService.getAllEmployees()).willReturn(employeesList);

        //then
        mockMvc.perform(get("/Employees")).andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].name",is("liming")))
                .andExpect(jsonPath("$[1].id",is(2)))
                .andExpect(jsonPath("$[1].name",is("huawei")));
    }
    @Test
    public void should_get_indicated_todo_by_id()throws Exception{
        //given
        Employees employees1 = new Employees(1L,"liming");
        EmployeesDTO employeesDTO = new EmployeesDTO(employees1);
        //when
        given(employeesService.getById(1L)).willReturn(employeesDTO);
        //then
        mockMvc.perform(get("/Employees/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.name",is(employeesDTO.getName())));
    }

    @Test
    public void should_return_created_when_post_to_create_a_todo()throws Exception{
        //given
        Employees employees1 = new Employees(1L,"liming");
        //when
        when(employeesService.addEmployees(any(Employees.class))).thenReturn(true);
        ResultActions result = mockMvc.perform(post("/Employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employees1)));
        //then
        result.andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void should_update_the_1L_id_successful_when_put_employee_slash_id_with_body_json_employee()throws Exception{
        //given
        Employees employees1 = new Employees(1L,"liming");
        //when
        when(employeesService.updateEmployees(any(Employees.class))).thenReturn(true);
        ResultActions result = mockMvc.perform(put("/Employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employees1)));
        //then
        result.andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    public void should_delete_employee_when_call_http_delete_call_by_id() throws Exception {

        //given
        Employees employees1 = new Employees(1L,"liming");
        when(employeesService.deleteById(1L)).thenReturn(true);
        //when
        ResultActions result = mockMvc.perform
                (delete("/Employees/1"));

        //then
        result.andExpect(status().isNoContent())
                .andDo(print());
    }


}
