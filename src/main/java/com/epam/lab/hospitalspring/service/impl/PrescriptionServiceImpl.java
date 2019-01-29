package com.epam.lab.hospitalspring.service.impl;

import com.epam.lab.hospitalspring.model.Diagnosis;
import com.epam.lab.hospitalspring.model.Prescription;
import com.epam.lab.hospitalspring.model.enums.PrescriptionType;
import com.epam.lab.hospitalspring.repository.DiagnosisRepository;
import com.epam.lab.hospitalspring.repository.PrescriptionRepository;
import com.epam.lab.hospitalspring.service.PrescriptionService;
import com.epam.lab.hospitalspring.util.GsonFactory;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Prescription service provide business logic that works with Prescription entities.
 */
@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @Override
    public String findPrescriptionsForDiagnosisByDiagnosisIdOrderByIdAsc(Long diagnosisId, Locale locale) {
        List<Prescription> prescriptions =
                prescriptionRepository.findPrescriptionsForDiagnosisByDiagnosisIdOrderByIdAsc(diagnosisId);
        Gson gson = GsonFactory.buildGson(locale);
        return gson.toJson(prescriptions);
    }

    /**
     * Method tries to do prescription.
     * When selected prescription is not presented in DB, exception is thrown.
     * When selected prescription is already done, exception is thrown.
     *
     * @param prescriptionId selected prescription id
     */
    @Override
    public void doPrescription(Long prescriptionId) {
        Optional<Prescription> currentPrescription = prescriptionRepository.findById(prescriptionId);
        currentPrescription.ifPresent(prescription -> {
            if (!prescription.getDone()) {
                prescription.setDone(true);
                prescriptionRepository.saveAndFlush(prescription);
            } else {
                throw new IllegalArgumentException("Trying to do prescription that already done");
            }
        });
        currentPrescription.orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void addPrescription(long diagnosisId, String prescriptionDescription, String prescriptionType) {
        Optional<Diagnosis> diagnosis = diagnosisRepository.findById(diagnosisId);
        diagnosis.orElseThrow(IllegalArgumentException::new);
        PrescriptionType type = PrescriptionType.valueOf(prescriptionType);
        Prescription prescription = new Prescription(null, prescriptionDescription,
                diagnosis.get(), false, LocalDateTime.now(), type);
        prescriptionRepository.saveAndFlush(prescription);
    }
}