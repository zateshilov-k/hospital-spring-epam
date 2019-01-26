package com.epam.lab.hospitalspring.repository;

import com.epam.lab.hospitalspring.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findPatientById(@Param("id") Long id);

    List<Patient> findPatientsByDeleted(Boolean deleted);
}