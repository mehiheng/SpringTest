package com.oocl.Spring_flywaydb.repositories;

import com.oocl.Spring_flywaydb.entities.Companies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CompaniesReository extends JpaRepository<Companies,Long> {
    @Transactional
    int deleteCompaniesById(@Param("id") Long id);

}
