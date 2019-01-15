package com.epam.lab.hospitalspring.repository;

import com.epam.lab.hospitalspring.model.Diagnosis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
    Diagnosis findDiagnosisById(@Param("id") Long id);
}