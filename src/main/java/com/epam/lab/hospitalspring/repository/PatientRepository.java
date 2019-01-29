package com.epam.lab.hospitalspring.repository;

import com.epam.lab.hospitalspring.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findPatientById(@Param("id") Long id);

    List<Patient> findPatientsByDeletedIsTrue();

    Page<Patient> findPatientsByDeletedIsFalseOrderByLastName(Pageable pageable);

    Page<Patient> findPatientsByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseAndDeletedFalseOrderByLastName(
            String firstName,
            String lastName,
            Pageable pageable);

    default Page<Patient> returnPage(String searchString, Pageable pageable) {
        Page<Patient> page = findPatientsByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseAndDeletedFalseOrderByLastName(
                searchString,
                searchString,
                pageable);
        return page;
    }
}