package com.epam.lab.hospitalspring.service.impl;

import com.epam.lab.hospitalspring.form.PatientForm;
import com.epam.lab.hospitalspring.model.Patient;
import com.epam.lab.hospitalspring.repository.PatientRepository;
import com.epam.lab.hospitalspring.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

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
    public Patient addPatient(PatientForm patientForm) {
        Patient patient = null;
        if (validateData(patientForm)) {
            patient = Patient.builder()
                    .firstName(patientForm.getFirstName())
                    .lastName(patientForm.getLastName())
                    .deleted(false)
                    .discharged(false)
                    .diagnosisList(null)
                    .build();
            patient = patientRepository.saveAndFlush(patient);
        }
        return patient;
    }

    @Override
    public void updatePatient(Patient patient) {
        patientRepository.save(patient);
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findPatientById(id);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> getNotDeletedPatients() {
        return patientRepository.findPatientsByDeleted(false);
    }

}
