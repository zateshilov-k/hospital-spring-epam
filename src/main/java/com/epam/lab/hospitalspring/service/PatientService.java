package com.epam.lab.hospitalspring.service;

import com.epam.lab.hospitalspring.form.PatientForm;
import com.epam.lab.hospitalspring.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientService {
    boolean validateData(PatientForm patientForm);

    void addPatient(Patient patient);

    void updatePatient(Patient patient);

    Patient getPatientById(Long id);

//    List<Patient> getAllPatients();

    Page<Patient> getNotDeletedPatients(Pageable pageable);

    List<Patient> getDeletedPatients();

    Page<Patient> newFinder(String searchString, Pageable pageable);

}
