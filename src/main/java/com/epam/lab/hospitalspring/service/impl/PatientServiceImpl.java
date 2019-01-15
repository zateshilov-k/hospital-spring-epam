package com.epam.lab.hospitalspring.service.impl;

import com.epam.lab.hospitalspring.model.Patient;
import com.epam.lab.hospitalspring.repository.PatientRepository;
import com.epam.lab.hospitalspring.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient addPatient(Patient patient) {
        return patientRepository.saveAndFlush(patient);
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

}
