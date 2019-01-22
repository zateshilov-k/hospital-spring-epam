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

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @Override
    public Prescription addPrescription(Prescription prescription) {
        Prescription savedPersonal = prescriptionRepository.saveAndFlush(prescription);
        return savedPersonal;
    }

    @Override
    public void update(Prescription prescription) {
        Prescription savedPersonal = prescriptionRepository.saveAndFlush(prescription);
    }

    @Override
    public Prescription getById(Long id) {
        return prescriptionRepository.findPrescriptionById(id);
    }

    @Override
    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    @Override
    public List<Prescription> findPrescriptionsForDiagnosisByDiagnosisId(Long diagnosisId) {
        return prescriptionRepository.findPrescriptionsForDiagnosisByDiagnosisId(diagnosisId);
    }

    @Override
    public String findPrescriptionsForDiagnosisByDiagnosisIdOrderByIdAsc(Long diagnosisId, Locale locale) {
        List<Prescription> prescriptions =
                prescriptionRepository.findPrescriptionsForDiagnosisByDiagnosisIdOrderByIdAsc(diagnosisId);
        Gson gson = GsonFactory.buildGson(locale);
        return gson.toJson(prescriptions);
    }

    @Override
    public void doPrescription(Long prescriptionId) {
        Optional<Prescription> currentPrescription = prescriptionRepository.findById(prescriptionId);
        try {
            currentPrescription.ifPresent(prescription -> {
                if (!prescription.getDone()) {
                    prescription.setDone(true);
                    prescriptionRepository.saveAndFlush(prescription);
                    System.out.println("Do prescription");
                } else {
                    throw new IllegalArgumentException("Trying to do prescription that already done");
                }
            });
            currentPrescription.orElseThrow(IllegalArgumentException::new);
        } catch (Exception e) {
            // TODO: add log
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addPrescription(long diagnosisId, String prescriptionDescription, String prescriptionType) {

        try {
            Optional<Diagnosis> diagnosis = diagnosisRepository.findById(diagnosisId);
            diagnosis.orElseThrow(IllegalArgumentException::new);
            PrescriptionType type = PrescriptionType.valueOf(prescriptionType);
            Prescription prescription = new Prescription(null,prescriptionDescription,
                    diagnosis.get(),false,LocalDateTime.now(),type);
            prescriptionRepository.saveAndFlush(prescription);
        } catch (Exception e) {
            //TODO Log
            throw new RuntimeException(e);
        }
    }
}