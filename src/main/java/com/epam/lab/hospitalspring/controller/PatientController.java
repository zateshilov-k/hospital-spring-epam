package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.repository.DiagnosisRepository;
import com.epam.lab.hospitalspring.repository.PatientRepository;
import com.epam.lab.hospitalspring.repository.PrescriptionRepository;
import com.epam.lab.hospitalspring.service.DiagnosisService;
import com.epam.lab.hospitalspring.service.PatientService;
import com.epam.lab.hospitalspring.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

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
    //list of patients
    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    public String getAllPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patients";
    }

    // Getting for patinet his diagnoises and prescriptions
    @RequestMapping(value = "/patientDiagnosisCard/{id}", method = RequestMethod.GET)
    public String getPatient(@PathVariable("id") Long id, Model model) {
        model.addAttribute("patient", patientService.getPatientById(id));
        return "patientDiagnosisCard";
    }


    @RequestMapping(value = "/addPatient", method = RequestMethod.GET)
    public String addPatient(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return "patients";
    }
}