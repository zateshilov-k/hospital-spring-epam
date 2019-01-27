package com.epam.lab.hospitalspring.service;

import com.epam.lab.hospitalspring.form.PatientForm;
import com.epam.lab.hospitalspring.model.Patient;
import com.epam.lab.hospitalspring.model.Personal;

import java.util.List;

public interface PatientService {
    boolean validateData(PatientForm patientForm);

    void addPatient(Patient patient);

    void updatePatient(Patient patient);

    Patient getPatientById(Long id);

    List<Patient> getAllPatients();

    List<Patient> getNotDeletedPatients();

    List<Patient> getDeletedPatients();

    boolean discharge(Personal personal, Long id);
}
