package com.epam.lab.hospitalspring.repository;

import com.epam.lab.hospitalspring.model.Personal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {

    Personal findPersonalById(Long id);

    Optional<Personal> findOneByLogin(String login);

    Page<Personal> findAllByOrderByLastName(Pageable pageable);

    Page<Personal> findPersonalsByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrderByLastName(
            String firstName,
            String lastName,
            Pageable pageable);

    default Page<Personal> returnPage (String searchString, Pageable pageable){
        Page<Personal> page = findPersonalsByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrderByLastName(
                searchString,
                searchString,
                pageable);
        return page;
    }
}
