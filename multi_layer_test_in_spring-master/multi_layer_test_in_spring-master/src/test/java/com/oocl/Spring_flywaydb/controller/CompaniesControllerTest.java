package com.oocl.Spring_flywaydb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.Spring_flywaydb.controller.dto.CompaniesDTO;
import com.oocl.Spring_flywaydb.controller.CompaniesController;
import com.oocl.Spring_flywaydb.controller.EmployeesController;
import com.oocl.Spring_flywaydb.entities.Companies;
import com.oocl.Spring_flywaydb.service.CompaniesService;
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
@WebMvcTest(CompaniesController.class)
public class CompaniesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompaniesService companiesService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void should_get_all_customers_without_any_paramters()throws Exception{
        //given

        Companies companies1 = new Companies(1L,"oocl");
        Companies companies2 = new Companies(2L,"huawei");
        List<CompaniesDTO> companiesList = Arrays.asList(new CompaniesDTO(companies1),new CompaniesDTO(companies2));
        //when
        given(companiesService.getAllCompanies()).willReturn(companiesList);

        //then
        mockMvc.perform(get("/Companies")).andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].name",is("oocl")))
                .andExpect(jsonPath("$[1].id",is(2)))
                .andExpect(jsonPath("$[1].name",is("huawei")));
}
    @Test
    public void should_get_indicated_todo_by_id()throws Exception{
        //given
        Companies companies1 = new Companies(1L,"oocl");
        CompaniesDTO companiesDTO = new CompaniesDTO(companies1);
        //when
        given(companiesService.getById(1L)).willReturn(companiesDTO);
        //then
        mockMvc.perform(get("/Companies/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.name",is(companiesDTO.getName())));
    }

    @Test
    public void should_return_created_when_post_to_create_a_todo()throws Exception{
        //given
        Companies companies1 = new Companies(1L,"oocl");
        //when
        when(companiesService.addCompanies(any(Companies.class))).thenReturn(true);
        ResultActions result = mockMvc.perform(post("/Companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(companies1)));
        //then
        result.andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void should_return_bad_request_when_fail_to_create_a_todo()throws Exception{
        //given
        Companies companies1 = new Companies(1L,"oocl");
        //when
        when(companiesService.addCompanies(any(Companies.class))).thenReturn(false);
        ResultActions result = mockMvc.perform(post("/Companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(companies1)));
        //then
        result.andExpect(status().isBadRequest())
                .andDo(print());
    }
    @Test
    public void should_update_the_1L_id_successful_when_put_company_slash_id_with_body_json_company()throws Exception{
        //given
        Companies companies1 = new Companies(1L,"oocl");
        //when
        when(companiesService.updateCompanies(any(Companies.class))).thenReturn(true);
        ResultActions result = mockMvc.perform(put("/Companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(companies1)));
        //then
        result.andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    public void should_delete_company_when_call_http_delete_call_by_id() throws Exception {

        //given
        Companies companies1 = new Companies(1L,"oocl");
        when(companiesService.deleteById(1L)).thenReturn(true);
        //when
        ResultActions result = mockMvc.perform
                (delete("/Companies/1"));

        //then
        result.andExpect(status().isNoContent())
                .andDo(print());
    }


}
