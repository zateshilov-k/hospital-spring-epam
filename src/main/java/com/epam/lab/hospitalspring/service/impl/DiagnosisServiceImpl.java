package com.epam.lab.hospitalspring.service.impl;

import com.epam.lab.hospitalspring.model.Diagnosis;
import com.epam.lab.hospitalspring.repository.DiagnosisRepository;
import com.epam.lab.hospitalspring.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DiagnosisServiceImpl implements DiagnosisService {


    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @Override
    public Diagnosis addDiagnosis(Diagnosis diagnosis) {
        Diagnosis savedDiagnosis = diagnosisRepository.saveAndFlush(diagnosis);
        return savedDiagnosis;
    }

    @Override
    public void update(Diagnosis diagnosis) {

    }

    @Override
    public Diagnosis getById(Long id) {
        return null;
    }

    @Override
    public List<Diagnosis> getAll() {
        return null;
    }
}
