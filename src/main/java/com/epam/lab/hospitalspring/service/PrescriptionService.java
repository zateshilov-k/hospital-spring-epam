package com.epam.lab.hospitalspring.service;

import com.epam.lab.hospitalspring.model.Prescription;

import java.util.List;

public interface PrescriptionService {
    Prescription addPrescription(Prescription prescription);

    void update(Prescription prescription); // for update and mark as deleted

    Prescription getById(Long id);

    List<Prescription> getAllPrescriptions();

}
