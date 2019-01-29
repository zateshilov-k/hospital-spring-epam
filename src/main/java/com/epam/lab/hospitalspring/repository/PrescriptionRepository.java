package com.epam.lab.hospitalspring.repository;

import com.epam.lab.hospitalspring.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    List<Prescription> findPrescriptionsForDiagnosisByDiagnosisId(Long diagnosisId);

    List<Prescription> findPrescriptionsForDiagnosisByDiagnosisIdOrderByIdAsc(Long diagnosisId);
}
