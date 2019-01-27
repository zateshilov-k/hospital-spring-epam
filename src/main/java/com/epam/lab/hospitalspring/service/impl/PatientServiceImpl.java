package com.epam.lab.hospitalspring.service.impl;

import com.epam.lab.hospitalspring.form.PatientForm;
import com.epam.lab.hospitalspring.model.Patient;
import com.epam.lab.hospitalspring.repository.PatientRepository;
import com.epam.lab.hospitalspring.service.PatientService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private Logger log;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public boolean validateData(PatientForm patientForm) {
        boolean isValidData = true;
        if (patientForm.getFirstName().equals("") || patientForm.getFirstName() == null
                || patientForm.getLastName().equals("") || patientForm.getLastName() == null) {
            isValidData = false;
        }
        return isValidData;
    }

    @Override
    public void addPatient(Patient patient) {
        Patient currentPatient = Patient.builder()
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .deleted(false)
                .discharged(false)
                .diagnosisList(null)
                .build();
        Patient result = patientRepository.saveAndFlush(currentPatient);
        if (result != null) {
            log.info("LOG: Patient added " + patient);
        }
    }

    @Override
    public void updatePatient(Patient patient) {
        patientRepository.saveAndFlush(patient);
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findPatientById(id);
    }

//    @Override
//    public List<Patient> getAllPatients() {
//        return patientRepository.findAll();
//    }

    @Override
    public Page<Patient> getNotDeletedPatients(Pageable pageable) {
        return patientRepository.findPatientsByDeletedIsFalse(pageable);
    }

    @Override
    public Page<Patient> newFinder(String searchString, Pageable pageable) {
        Page<Patient> page;
        page = patientRepository.returnPage(searchString, pageable);
        return page;
    }

    public List<Patient> getDeletedPatients() {
        return patientRepository.findPatientsByDeletedIsTrue();
    }
}
