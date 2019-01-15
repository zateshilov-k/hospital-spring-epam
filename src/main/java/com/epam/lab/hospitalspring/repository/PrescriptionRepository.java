package com.epam.lab.hospitalspring.repository;

import com.epam.lab.hospitalspring.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    Prescription findPrescriptionById(@Param("id") Long id);
}
