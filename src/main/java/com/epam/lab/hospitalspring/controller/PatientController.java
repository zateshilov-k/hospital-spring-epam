package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.form.PatientForm;
import com.epam.lab.hospitalspring.model.Patient;
import com.epam.lab.hospitalspring.model.Prescription;
import com.epam.lab.hospitalspring.model.enums.PrescriptionType;
import com.epam.lab.hospitalspring.model.enums.Role;
import com.epam.lab.hospitalspring.repository.DiagnosisRepository;
import com.epam.lab.hospitalspring.repository.PatientRepository;
import com.epam.lab.hospitalspring.repository.PrescriptionRepository;
import com.epam.lab.hospitalspring.security.details.PersonalDetailsImpl;
import com.epam.lab.hospitalspring.service.DiagnosisService;
import com.epam.lab.hospitalspring.service.PatientService;
import com.epam.lab.hospitalspring.service.PrescriptionService;
import com.epam.lab.hospitalspring.util.GsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping(value = "/patients")
    public String getAllPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patients";
    }

    @GetMapping(value = "/patientDiagnosisCard/{id}")
    public String getPatient(@PathVariable("id") Long id, Model model, Authentication authentication) {
        Patient currentPatient= patientService.getPatientById(id);
        model.addAttribute("patient", currentPatient);
        Gson gson = GsonFactory.buildGson();
        model.addAttribute("diagnoses",gson.toJson(currentPatient.getDiagnosisList()));
        model.addAttribute("prescriptions",gson.toJson(
                currentPatient.getDiagnosisList().get(0).getPrescriptions()));

        PersonalDetailsImpl personalDetailsService = (PersonalDetailsImpl) authentication.getPrincipal();
        Role role = personalDetailsService.getPersonal().getRole();
        model.addAttribute("role",role);
        return "patientDiagnosisCard";
    }


    @GetMapping(value = "/addPatient")
    public String getAddPatientPage() {
        return "patient";
    }

    @PostMapping(value = "/addPatient")
    public String addPatient(PatientForm patientForm, Model model) {

        Patient patient = patientService.addPatient(patientForm);
        if (patient != null) {
            return "redirect:/patients";
        } else {
            return "redirect:/error/errorMessage";
        }
    }

    @GetMapping(value = "/patient/{id}")
    public String showError(@PathVariable("id") Long id, Model model) {
        model.addAttribute(patientService.getPatientById(id));
        //TODO передать на фронт имя и фамилию
        return "patient";
    }
}