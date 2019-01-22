package com.epam.lab.hospitalspring;

import com.epam.lab.hospitalspring.model.Diagnosis;
import com.epam.lab.hospitalspring.model.Patient;
import com.epam.lab.hospitalspring.model.Personal;
import com.epam.lab.hospitalspring.repository.DiagnosisRepository;
import com.epam.lab.hospitalspring.repository.PatientRepository;
import com.epam.lab.hospitalspring.repository.PersonalRepository;
import com.epam.lab.hospitalspring.service.DiagnosisService;
import com.epam.lab.hospitalspring.service.impl.DiagnosisServiceImpl;
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
public class DiagnosisServiceTest {
    @Autowired
    DiagnosisService diagnosisService;
    @MockBean
    DiagnosisRepository diagnosisRepository;
    @MockBean
    private PersonalRepository personalRepository;
    @MockBean
    private PatientRepository patientRepository;

    @Before
    public void setUp() {
        Optional<Diagnosis> emptyDiagnosis = Optional.empty();
        Diagnosis closedDiagnosis = new Diagnosis();
        closedDiagnosis.setOpened(false);
        Mockito.when(diagnosisRepository.findById(1l)).thenReturn(emptyDiagnosis);
        Mockito.when(diagnosisRepository.findById(2l)).thenReturn(Optional.of(closedDiagnosis));

        Mockito.when(patientRepository.findById(2l)).thenReturn(Optional.empty());
        Personal personal = new Personal();
        Mockito.when(personalRepository.findById(2l)).thenReturn(Optional.of(personal));

        Mockito.when(personalRepository.findById(1l)).thenReturn(Optional.empty());
        Patient patient = new Patient();
        Mockito.when(patientRepository.findById(1l)).thenReturn(Optional.of(patient));

    }

    @Test(expected = IllegalArgumentException.class)
    public void closeDiagnosisThatNotInDB() {
        diagnosisService.closeDiagnosis(1l);
    }

    @Test(expected = IllegalArgumentException.class)
    public void closeDiagnosisThatAlreadyClosed() {
        diagnosisService.closeDiagnosis(2l);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addDiagnosisToPatientThatNotInDB() {
        diagnosisService.addDiagnosis(2l, 2l, "Decsription");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addDiagnosisToPersonalThatNotInDB() {
        diagnosisService.addDiagnosis(1l, 1l, "Decsription");
    }

    @TestConfiguration
    static class DiagnosisServiceImplTestContextConfiguration {
        @Bean
        public DiagnosisService diagnosisService() {
            return new DiagnosisServiceImpl();
        }
    }
}
