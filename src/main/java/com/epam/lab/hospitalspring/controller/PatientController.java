package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.model.Diagnosis;
import com.epam.lab.hospitalspring.model.Patient;
import com.epam.lab.hospitalspring.model.Prescription;
import com.epam.lab.hospitalspring.model.enums.PrescriptionType;
import com.epam.lab.hospitalspring.repository.DiagnosisRepository;
import com.epam.lab.hospitalspring.repository.PatientRepository;
import com.epam.lab.hospitalspring.repository.PrescriptionRepository;
import com.epam.lab.hospitalspring.service.DiagnosisService;
import com.epam.lab.hospitalspring.service.PatientService;
import com.epam.lab.hospitalspring.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PatientController {

    @Autowired
    PatientService patientService;
    @Autowired
    DiagnosisService diagnosisService;
    @Autowired
    PrescriptionService prescriptionService;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DiagnosisRepository diagnosisRepository;
    @Autowired
    PrescriptionRepository prescriptionRepository;

    LocalDateTime today = LocalDateTime.now();

    // test data
//    List<Patient> patientList = new ArrayList<Patient>() {
//        {
//            add(new Patient(1L, "patient1", "secondName1",  false, true));
//            add(new Patient(2L, "patient2", "secondName2", false, true, null));
//            add(new Patient(3L, "patient3", "secondName3", false, true, null));
//        }
//    };

//    List<Diagnosis> diagnosisList = new ArrayList<Diagnosis>() {
//        {
//            add(new Diagnosis(1L, "dsfsdf", patientList.get(1), true, today));
//            add(new Diagnosis(2L, "dsfsdf", patientList.get(1), true, today));
//            add(new Diagnosis(3L, "dsfsdf", patientList.get(1), true, today));
//            add(new Diagnosis(4L, "dsfsdf", patientList.get(2), true, today));
//        }
//    };

//    List<Prescription> prescriptionList = new ArrayList<Prescription>() {
//        {
//            add(new Prescription(1L, "description1 !!!", patientList.get(1), diagnosisList.get(1), false, today, PrescriptionType.PROCEDURE));
//            add(new Prescription(2L, "description2 !!!", patientList.get(1), diagnosisList.get(1), false, today, PrescriptionType.OPERATION));
//            add(new Prescription(3L, "description3 !!!", patientList.get(2), diagnosisList.get(2), false, today, PrescriptionType.DRUG));
//        }
//    };

    //list of patients
    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    public String getAllPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patients";
    }

    // Getting for patinet his diagnoises and prescriptions
    @RequestMapping(value = "/patients/{id}", method = RequestMethod.GET)
    public String getPatient(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("patient", patientService.getPatientById(id));
//        model.addAttribute("diagnosisList", diagnosisService.findDiagnosisByPatientId(id));
//        model.addAttribute("prescriptionList", prescriptionService.findPrescriptionsForDiagnosisByDiagnosisId(id));
        return "patientDiagnosisCard";
    }

   // Init base
//    @GetMapping("/init")
//    public void addTestData(){
//        //init
//        patientList.forEach(patient -> patientRepository.save(patient));
//        diagnosisList.forEach(diagnosis -> diagnosisRepository.save(diagnosis));
//        prescriptionList.forEach(prescription -> prescriptionRepository.save(prescription));
//
//        // getting all
//        List<Patient> allPatients = patientService.getAllPatients();
//        List<Diagnosis> allDiagnosis = diagnosisService.getAllDiagnosis();
//        List<Prescription> allPrescriptions = prescriptionRepository.findAll();
//
//        // sout
//        allPatients.forEach(System.out::println);
//        allDiagnosis.forEach(System.out::println);
//        allPrescriptions.forEach(System.out::println);
//    }

}