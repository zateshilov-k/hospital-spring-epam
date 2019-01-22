package com.epam.lab.hospitalspring.service;

import com.epam.lab.hospitalspring.model.Diagnosis;


import java.util.List;
import java.util.Locale;

public interface DiagnosisService {

    Diagnosis addDiagnosis(Diagnosis diagnosis);

    void update(Diagnosis diagnosis); // for update and mark as deleted

    Diagnosis getById(Long id);

    List<Diagnosis> getAllDiagnosis();

    String findDiagnosisByPatientId(Long id, Locale locale);

    void closeDiagnosis(Long diagnosisId);

    void addDiagnosis(Long patientId, Long personalId, String description);
}
