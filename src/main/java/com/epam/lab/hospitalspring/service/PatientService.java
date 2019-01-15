package com.epam.lab.hospitalspring.service;

import com.epam.lab.hospitalspring.model.Patient;

import java.util.List;

public interface PatientService {

    Patient addPatient(Patient patient);

    void updatePatient(Patient patient);

    Patient getPatientById(Long id);

    List<Patient> getAllPatients();

}
