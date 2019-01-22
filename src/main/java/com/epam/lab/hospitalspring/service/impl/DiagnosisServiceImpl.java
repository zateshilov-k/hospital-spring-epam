package com.epam.lab.hospitalspring.service.impl;

import com.epam.lab.hospitalspring.model.Diagnosis;
import com.epam.lab.hospitalspring.model.Patient;
import com.epam.lab.hospitalspring.model.Personal;
import com.epam.lab.hospitalspring.repository.DiagnosisRepository;
import com.epam.lab.hospitalspring.repository.PatientRepository;
import com.epam.lab.hospitalspring.repository.PersonalRepository;
import com.epam.lab.hospitalspring.service.DiagnosisService;
import com.epam.lab.hospitalspring.util.GsonFactory;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class DiagnosisServiceImpl implements DiagnosisService {

    @Autowired
    private PersonalRepository personalRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @Override
    public Diagnosis addDiagnosis(Diagnosis diagnosis) {
        return diagnosisRepository.saveAndFlush(diagnosis);
    }

    @Override
    public void update(Diagnosis diagnosis) {
        Diagnosis savedDiagnosis = diagnosisRepository.saveAndFlush(diagnosis);
    }

    @Override
    public Diagnosis getById(Long id) {
        return diagnosisRepository.findDiagnosisById(id);
    }

    @Override
    public List<Diagnosis> getAllDiagnosis() {
        return diagnosisRepository.findAll();
    }

    @Override
    public String findDiagnosisByPatientId(Long id, Locale locale) {
        Gson gson = GsonFactory.buildGson(locale);
        List<Diagnosis> diagnoses = diagnosisRepository.findDiagnosisByPatientId(id);
        return gson.toJson(diagnoses);
    }

    @Override
    public void closeDiagnosis(Long diagnosisId) {
        Optional<Diagnosis> currentDiagnosis = diagnosisRepository.findById(diagnosisId);
        currentDiagnosis.ifPresent(diagnosis -> {
            if (diagnosis.getOpened()) {
                diagnosis.setOpened(false);
                diagnosisRepository.saveAndFlush(diagnosis);
            } else {
                throw new IllegalArgumentException("Trying to close diagnosis that " +
                        "already closed");
            }
        });
        currentDiagnosis.orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void addDiagnosis(Long patientId, Long personalId, String description) {
        Optional<Personal> personal = personalRepository.findById(personalId);
        Optional<Patient> patient = patientRepository.findById(patientId);
        personal.orElseThrow(IllegalArgumentException::new);
        patient.orElseThrow(IllegalArgumentException::new);
        Diagnosis diagnosis = new Diagnosis(null, description, personal.get(), patient.get(),
                true, LocalDateTime.now(), null);
        diagnosisRepository.saveAndFlush(diagnosis);
    }
}
