package com.epam.lab.hospitalspring.repository;

import com.epam.lab.hospitalspring.model.Personal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Test repository for pagination (/employees page)
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Personal, Long>, PagingAndSortingRepository<Personal, Long> {

    Page<Personal> findPersonalByFirstNameAfterOrLastNameOrLogin(String firstName, String lastName, String login, Pageable pageable);
}