package com.epam.lab.hospitalspring.repository;

import com.epam.lab.hospitalspring.model.Personal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Test repository for pagination (/employees page)
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Personal, Long>, PagingAndSortingRepository<Personal, Long> {

//    Page<Personal> findPersonalsByFirstNameContainsOrLastNameContains(String firstName, String lastName,Pageable pageable);
//
//    default Page<Personal> returnPage (String firstName, Pageable pageable){
//        Page<Personal> page = findPersonalsByFirstNameContainsOrLastNameContains(firstName, firstName, pageable);
//        return page;
//    }

    // код выше работает.
    /*
    но в MySQL регитронезависимо работает, а в H2 - регистрозависимо
     */

    Page<Personal> findPersonalsByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName,Pageable pageable);

    default Page<Personal> returnPage (String firstName, Pageable pageable){
        Page<Personal> page = findPersonalsByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(firstName, firstName, pageable);
        return page;
    }

}