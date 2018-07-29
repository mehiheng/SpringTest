package com.oocl.Spring_flywaydb.reository;

import com.oocl.Spring_flywaydb.entities.Companies;
import com.oocl.Spring_flywaydb.repositories.CompaniesReository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CompaniesReositoryTest {
    @Autowired
    private CompaniesReository companiesReository;

    @Autowired
    private TestEntityManager entityManager;

    @After
    public void tearDown(){
        entityManager.clear();
    }

    @Test
    public void findAll() {
        //given
       entityManager.persist(new Companies(1L,"oocl"));
        entityManager.persist(new Companies(2L,"meizu"));

        //when
        List<Companies> companiesList = companiesReository.findAll();

        //then
        assertThat(companiesList.size(), is(2));
        assertThat(companiesList.get(1).getId(), is(2L));
        assertThat(companiesList.get(1).getName(), is("meizu"));

    }

    @Test
    public void findById() {
        //given
        entityManager.persist(new Companies(1L,"oocl"));
        entityManager.persist(new Companies(2L,"meizu"));

        //when
        Companies companies = companiesReository.findById(2L).get();

        //then
        assertThat(companies.getId(), is(2L));
        assertThat(companies.getName(), is("meizu"));

    }

    @Test
    public void addCompanies() {
        //given
        entityManager.persist(new Companies(1L,"oocl"));
        entityManager.persist(new Companies(2L,"meizu"));
        Companies companies = new Companies(3L,"jinshan");

        //when
        Companies save = companiesReository.save(companies);

        //then
        assertThat(companiesReository.findById(3L).get().getId(),is(3L));
        assertThat(companiesReository.findById(3L).get().getName(),is("jinshan"));

    }

    @Test
    public void change() {

        //given
        entityManager.persist(new Companies(1L, "oocl"));

        //when
       companiesReository.save(new Companies(1L,"meizu"));

        //then
        assertThat(companiesReository.findById(1L).get().getName(), is("meizu"));


    }
    @Test
    public void deleteCompanyById() {

        //given
        entityManager.persist(new Companies(1L, "oocl"));

        //when
        companiesReository.deleteCompaniesById(1L);

        //then
        assertThat(companiesReository.findAll().size(), is(0));


    }
}
