package com.epam.lab.hospitalspring;

import com.epam.lab.hospitalspring.model.Diagnosis;
import com.epam.lab.hospitalspring.model.Prescription;
import com.epam.lab.hospitalspring.repository.DiagnosisRepository;
import com.epam.lab.hospitalspring.repository.PrescriptionRepository;
import com.epam.lab.hospitalspring.service.DiagnosisService;
import com.epam.lab.hospitalspring.service.PrescriptionService;
import com.epam.lab.hospitalspring.service.impl.DiagnosisServiceImpl;
import com.epam.lab.hospitalspring.service.impl.PrescriptionServiceImpl;
import org.junit.Before;
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
    @TestConfiguration
    static class DiagnosisServiceImplTestContextConfiguration {
        @Bean
        public PrescriptionService prescriptionService() {
            return new PrescriptionServiceImpl();
        }
    }
    @Autowired
    PrescriptionService prescriptionService;

    @MockBean
    private PrescriptionRepository prescriptionRepository;

    @MockBean
    private DiagnosisRepository diagnosisRepository;

    @Before
    public void setUp() {
        Mockito.when(prescriptionRepository.findById(1l)).thenReturn(Optional.empty());

        Prescription prescription = new Prescription();
        prescription.setDone(true);
        Mockito.when(prescriptionRepository.findById(2l)).thenReturn(Optional.of(prescription));

        Mockito.when(diagnosisRepository.findById(1L)).thenReturn(Optional.empty());

        Mockito.when(diagnosisRepository.findById(2L)).thenReturn(Optional.of(new Diagnosis()));
    }
    @Test(expected = IllegalArgumentException.class)
    public void doPrescriptionThatNotInDB() {
        prescriptionService.doPrescription(1l);
    }
    @Test(expected = IllegalArgumentException.class)
    public void doPrescriptionThatAlreadyDone() {
        prescriptionService.doPrescription(2l);
    }
    @Test(expected = IllegalArgumentException.class)
    public void addPrescriptionToDiagnosisThatIsNotInDB() {
        prescriptionService.addPrescription(1l,"Description","OPERATION");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addPrescriptionToDiagnosisWithWrongPrescriptionType() {
        prescriptionService.addPrescription(2l,"Description","OP");
    }
}
