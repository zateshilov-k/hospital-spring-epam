package com.epam.lab.hospitalspring.repository;

import com.epam.lab.hospitalspring.model.Diagnosis;

import com.epam.lab.hospitalspring.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
    Diagnosis findDiagnosisById(Long id);

    Optional<Diagnosis> findById(Long id);

    List<Diagnosis> findDiagnosisByPatientId(Long id);

}