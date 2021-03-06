package com.epam.lab.hospitalspring;

import com.epam.lab.hospitalspring.model.Diagnosis;
import com.epam.lab.hospitalspring.model.Patient;
import com.epam.lab.hospitalspring.model.Personal;
import com.epam.lab.hospitalspring.model.Prescription;
import com.epam.lab.hospitalspring.repository.DiagnosisRepository;
import com.epam.lab.hospitalspring.repository.PatientRepository;
import com.epam.lab.hospitalspring.repository.PersonalRepository;
import com.epam.lab.hospitalspring.repository.PrescriptionRepository;
import com.epam.lab.hospitalspring.service.DiagnosisService;
import com.epam.lab.hospitalspring.service.impl.DiagnosisServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class DiagnosisServiceTest {
    @Autowired
    DiagnosisService diagnosisService;
    @MockBean
    DiagnosisRepository diagnosisRepository;
    @MockBean
    private PersonalRepository personalRepository;
    @MockBean
    private PatientRepository patientRepository;
    @MockBean
    private PrescriptionRepository prescriptionRepository;

    @Test(expected = IllegalArgumentException.class)
    public void closeDiagnosisThatNotInDB() {
        long emptyDiagnosisId = 1l;
        Mockito.when(diagnosisRepository.findById(emptyDiagnosisId)).thenReturn(Optional.empty());

        diagnosisService.closeDiagnosis(emptyDiagnosisId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void closeDiagnosisThatAlreadyClosed() {
        long closedDiagnosisId = 2l;
        Diagnosis closedDiagnosis = new Diagnosis();
        closedDiagnosis.setOpened(false);
        Mockito.when(diagnosisRepository.findById(closedDiagnosisId)).thenReturn(Optional.of(closedDiagnosis));

        diagnosisService.closeDiagnosis(closedDiagnosisId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addDiagnosisToPatientThatNotInDB() {
        Mockito.when(patientRepository.findById(2l)).thenReturn(Optional.empty());
        Personal personal = new Personal();
        Mockito.when(personalRepository.findById(2l)).thenReturn(Optional.of(personal));

        diagnosisService.addDiagnosis(2l, 2l, "Decsription");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addDiagnosisToPersonalThatNotInDB() {
        Mockito.when(personalRepository.findById(1l)).thenReturn(Optional.empty());
        Patient patient = new Patient();
        Mockito.when(patientRepository.findById(1l)).thenReturn(Optional.of(patient));

        diagnosisService.addDiagnosis(1l, 1l, "Decsription");
    }
    @Test
    public void closeDiagnosisWithUndonePrescriptions() {
        Prescription prescription1 = new Prescription();
        prescription1.setDone(true);
        List<Prescription> prescriptions = new ArrayList<>();
        prescriptions.add(prescription1);
        Prescription prescription2 = new Prescription();
        prescription2.setDone(false);
        prescriptions.add(prescription2);

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setId(1l);
        diagnosis.setOpened(true);
        Mockito.when(prescriptionRepository.findPrescriptionsForDiagnosisByDiagnosisId(1l)).thenReturn(prescriptions);
        Mockito.when(diagnosisRepository.findById(1l)).thenReturn(Optional.of(diagnosis));
        Assert.assertFalse(diagnosisService.closeDiagnosis(1l));
    }

    @Test
    public void closeDiagnosisWithDonePrescriptions() {
        Prescription prescription1 = new Prescription();
        prescription1.setDone(true);
        List<Prescription> prescriptions = new ArrayList<>();
        prescriptions.add(prescription1);
        Prescription prescription2 = new Prescription();
        prescription2.setDone(true);
        prescriptions.add(prescription2);

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setId(1l);
        diagnosis.setOpened(true);
        Mockito.when(prescriptionRepository.findPrescriptionsForDiagnosisByDiagnosisId(1l)).thenReturn(prescriptions);
        Mockito.when(diagnosisRepository.findById(1l)).thenReturn(Optional.of(diagnosis));
        Assert.assertTrue(diagnosisService.closeDiagnosis(1l));
    }
    @Test
    public void closeDiagnosisWithoutPrescriptions() {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setId(1l);
        diagnosis.setOpened(true);
        Mockito.when(prescriptionRepository.findPrescriptionsForDiagnosisByDiagnosisId(1l)).thenReturn(new ArrayList<Prescription>());
        Mockito.when(diagnosisRepository.findById(1l)).thenReturn(Optional.of(diagnosis));
        Assert.assertTrue(diagnosisService.closeDiagnosis(1l));
    }


    @TestConfiguration
    static class DiagnosisServiceImplTestContextConfiguration {
        @Bean
        public DiagnosisService diagnosisService() {
            return new DiagnosisServiceImpl();
        }
    }
}
