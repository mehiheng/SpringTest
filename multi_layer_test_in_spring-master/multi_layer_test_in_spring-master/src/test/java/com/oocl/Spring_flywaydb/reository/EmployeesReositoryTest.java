package com.oocl.Spring_flywaydb.reository;
import com.oocl.Spring_flywaydb.entities.Employees;
import com.oocl.Spring_flywaydb.repositories.EmployeesReository;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeesReositoryTest {
    @Autowired
    private EmployeesReository employeesReository;

    @Autowired
    private TestEntityManager entityManager;

    @After
    public void tearDown(){
        entityManager.clear();
    }

    @Test
    public void findAll() {
        //given
        entityManager.persist(new Employees(1L,"xiaoming"));
        entityManager.persist(new Employees(2L,"lisi"));

        //when
        List<Employees> employeesList = employeesReository.findAll();

        //then
       assertThat(employeesList.size(),is(2));
       assertThat(employeesList.get(0).getName(),is("xiaoming"));
    }

    @Test
    public void findById() {
        //given
        entityManager.persist(new Employees(1L,"xiaoming"));
        entityManager.persist(new Employees(2L,"lisi"));

        //when
        Employees employees = employeesReository.findById(1L).get();

        //then
        assertThat(employees.getName(),is("xiaoming"));
    }

    @Test
    public void addEmployee() {
        //given
        entityManager.persist(new Employees(1L,"xiaoming"));
        entityManager.persist(new Employees(2L,"lisi"));
        Employees employees = new Employees(3L,"jkson");
        //when
         employeesReository.save(employees);

        //then
        assertThat(employeesReository.findById(3L).get().getName(),is("jkson"));
    }

    @Test
    public void deleteTheEmployeesById() {
        //given
        entityManager.persist(new Employees(1L,"xiaoming"));
        entityManager.persist(new Employees(2L,"jkson"));
        //when
        employeesReository.deleteEmployeesById(1L);

        //then
        assertThat(employeesReository.findAll().size(),is(1));
    }

}
