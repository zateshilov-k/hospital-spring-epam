package com.epam.lab.hospitalspring.service;

import com.epam.lab.hospitalspring.form.PatientForm;
import com.epam.lab.hospitalspring.model.Patient;
import com.epam.lab.hospitalspring.model.Personal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientService {
    boolean validateData(PatientForm patientForm);

    void addPatient(Patient patient);

    void updatePatient(Patient patient);

    Patient getPatientById(Long id);

    Page<Patient> getNotDeletedPatients(Pageable pageable);

    List<Patient> getDeletedPatients();

    boolean discharge(Personal personal, Long id);
    Page<Patient> newFinder(String searchString, Pageable pageable);

}
