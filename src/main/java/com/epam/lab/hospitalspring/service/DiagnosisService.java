package com.epam.lab.hospitalspring.service;

import java.util.Locale;

public interface DiagnosisService {

    String findDiagnosisByPatientId(Long id, Locale locale);

    boolean closeDiagnosis(Long diagnosisId);

    void addDiagnosis(Long patientId, Long personalId, String description);
}
