package com.epam.lab.hospitalspring.service;

import java.util.Locale;

public interface PrescriptionService {

    String findPrescriptionsForDiagnosisByDiagnosisIdOrderByIdAsc(Long diagnosisId, Locale locale);

    void doPrescription(Long prescriptionId);

    void addPrescription(long diagnosisId, String prescriptionDescription, String prescriptionType);
}
