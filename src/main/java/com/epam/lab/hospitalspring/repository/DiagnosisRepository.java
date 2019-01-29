package com.epam.lab.hospitalspring.repository;

import com.epam.lab.hospitalspring.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {

    Optional<Diagnosis> findById(Long id);

    List<Diagnosis> findDiagnosisByPatientId(Long id);

}