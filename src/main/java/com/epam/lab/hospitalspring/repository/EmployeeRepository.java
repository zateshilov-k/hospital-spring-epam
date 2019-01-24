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

    //    Page<Personal> findPersonalByFirstNameContainsOrLastNameContainsOrLoginContains(String firstName, Pageable pageable);
//    Page<Personal> findPersonalByFirstNameContains(String firstName, Pageable pageable); // работает поиск по имени
//    Page<Personal> findPersonalByFirstNameContainsOrLastNameContains(String firstName, Pageable pageable); // не работает
//    Page<Personal> findPersonalByFirstNameIsContainingOrLastNameIsContaining(String firstName, Pageable pageable); // не работает поиск
//    Page<Personal> findPersonalByFirstNameIsContainingOrLastNameIsContaining(String firstName, String lastName, Pageable pageable); // не работает поиск

//    @Query(nativeQuery = true, value = "select * from personal where first_name like '%:word%'  or last_name like '%:word%'")
//    Page<Personal> findByParameter(@Param("word") String word, Pageable pageable);

//    @Query(value = "SELECT * FROM personal WHERE first_name LIKE '%сер%' ORDER BY id \n-- #pageable\n", nativeQuery = true) //работает
    @Query(value = "SELECT * FROM personal WHERE first_name LIKE '%'+:word+'%' OR last_name LIKE '%'+:word+'%' ORDER BY id \n-- #pageable\n",
            countQuery = "SELECT count(*) FROM personal WHERE first_name LIKE '%'+:word+'%' OR last_name LIKE '%'+:word+'%'",
            nativeQuery = true)
    Page<Personal> findAllPersonalsWithPagination(@Param("word") String word, Pageable pageable);

}