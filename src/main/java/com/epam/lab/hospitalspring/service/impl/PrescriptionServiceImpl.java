package com.epam.lab.hospitalspring.service.impl;

import com.epam.lab.hospitalspring.model.Prescription;
import com.epam.lab.hospitalspring.repository.PrescriptionRepository;
import com.epam.lab.hospitalspring.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired //аннотация которая позволит Spring инициализировать наш сервис
    private PrescriptionRepository prescriptionRepository;

    @Override
    public Prescription addPrescription(Prescription prescription) {
        Prescription savedPersonal = prescriptionRepository.saveAndFlush(prescription);
        return savedPersonal;
    }



    @Override
    public void update(Prescription prescription) {

    }

    @Override
    public Prescription getById(Long id) {
        return null;
    }

    @Override
    public List<Prescription> getAll() {
        return null;
    }
}