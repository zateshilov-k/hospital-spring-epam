package com.epam.lab.hospitalspring.service;

import com.epam.lab.hospitalspring.model.Diagnosis;


import java.util.List;
import java.util.Locale;

public interface DiagnosisService {

    Diagnosis addDiagnosis(Diagnosis diagnosis);

    void update(Diagnosis diagnosis);

    Diagnosis getById(Long id);

    List<Diagnosis> getAllDiagnosis();

    String findDiagnosisByPatientId(Long id, Locale locale);

    boolean closeDiagnosis(Long diagnosisId);

    void addDiagnosis(Long patientId, Long personalId, String description);
}
