package com.epam.lab.hospitalspring.service;

import com.epam.lab.hospitalspring.model.Diagnosis;
import com.epam.lab.hospitalspring.model.Prescription;
import com.epam.lab.hospitalspring.repository.DiagnosisRepository;
import com.epam.lab.hospitalspring.repository.PrescriptionRepository;
import com.epam.lab.hospitalspring.service.PrescriptionService;
import com.epam.lab.hospitalspring.service.impl.PrescriptionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
public class PrescriptionServiceTest {
    @Autowired
    PrescriptionService prescriptionService;
    @MockBean
    private PrescriptionRepository prescriptionRepository;
    @MockBean
    private DiagnosisRepository diagnosisRepository;

    @Test(expected = IllegalArgumentException.class)
    public void doPrescriptionThatNotInDB() {
        Mockito.when(prescriptionRepository.findById(1l)).thenReturn(Optional.empty());

        prescriptionService.doPrescription(1l);
    }

    @Test(expected = IllegalArgumentException.class)
    public void doPrescriptionThatAlreadyDone() {
        Prescription prescription = new Prescription();
        prescription.setDone(true);
        Mockito.when(prescriptionRepository.findById(2l)).thenReturn(Optional.of(prescription));

        prescriptionService.doPrescription(2l);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addPrescriptionToDiagnosisThatIsNotInDB() {
        Mockito.when(diagnosisRepository.findById(1L)).thenReturn(Optional.empty());

        prescriptionService.addPrescription(1l, "Description", "OPERATION");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addPrescriptionToDiagnosisWithWrongPrescriptionType() {
        String wrongPrescriptionType = "OP";
        Mockito.when(diagnosisRepository.findById(2L)).thenReturn(Optional.of(new Diagnosis()));

        prescriptionService.addPrescription(2l, "Description", wrongPrescriptionType);
    }

    @TestConfiguration
    static class DiagnosisServiceImplTestContextConfiguration {
        @Bean
        public PrescriptionService prescriptionService() {
            return new PrescriptionServiceImpl();
        }
    }
}
