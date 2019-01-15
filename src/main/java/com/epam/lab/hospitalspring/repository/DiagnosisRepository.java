package com.epam.lab.hospitalspring.repository;

import com.epam.lab.hospitalspring.model.Diagnosis;

import com.epam.lab.hospitalspring.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.List;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
    Diagnosis findDiagnosisById(Long id);

   /* @Query(nativeQuery = true, value = "SELECT * FROM diagnosis JOIN patient " + "ON diagnosis" +
            ".patient_id = patient.patient_id JOIN medical_personal " + "ON diagnosis.personal_id = medical_personal" +
            ".personal_id WHERE diagnosis.patient_id = ?;")*/
    List<Diagnosis> findDiagnosisByPatientId(Long id);

}