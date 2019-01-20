package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.form.PatientForm;
import com.epam.lab.hospitalspring.model.Patient;
import com.epam.lab.hospitalspring.model.enums.Role;
import com.epam.lab.hospitalspring.repository.DiagnosisRepository;
import com.epam.lab.hospitalspring.repository.PatientRepository;
import com.epam.lab.hospitalspring.repository.PrescriptionRepository;
import com.epam.lab.hospitalspring.security.details.PersonalDetailsImpl;
import com.epam.lab.hospitalspring.service.DiagnosisService;
import com.epam.lab.hospitalspring.service.PatientService;
import com.epam.lab.hospitalspring.service.PrescriptionService;
import com.epam.lab.hospitalspring.transfer.PersonalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    //list of patients
    @GetMapping(value = "/patients")
    public String getAllPatients(Model model, Authentication authentication) {
        PersonalDetailsImpl personalDetailsService = (PersonalDetailsImpl) authentication.getPrincipal();
        Role currentRole = personalDetailsService.getPersonal().getRole();
        System.out.println(currentRole);
        List<Patient> patients = new ArrayList<>();
        if (currentRole == Role.DOCTOR) {
            patients = patientService.getAllPatients();
        } else if (currentRole == Role.NURSE) {
            patients = patientService.getNotDeletedPatients();
        }
        model.addAttribute("patients", patients);
        model.addAttribute("currentRole", currentRole);
        patients.forEach(System.out::println);
        return "patients";
    }

    // Getting for patinet his diagnoises and prescriptions
    @GetMapping(value = "/patientDiagnosisCard/{id}")
    public String getPatient(@PathVariable("id") Long id, Model model) {
        model.addAttribute("patient", patientService.getPatientById(id));
        return "patientDiagnosisCard";
    }

    @GetMapping(value = "/addPatient")
    public String getAddPatientPage() {
        return "patient";
    }

    @PostMapping(value = "/addPatient")
    public String addPatient(PatientForm patientForm, Model model) {
        Patient patient = patientService.addPatient(patientForm);
        patientService.updatePatient(patient);
        if (patient != null) {
            return "redirect:/patients";
        } else {
            return "redirect:/error/errorMessage";
        }
    }

    @GetMapping(value = "/patients/{id}")
    public String showPatientProfile(@PathVariable("id") Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        return "patientUpdateForm";
    }

    @PostMapping(value="/patients/updatePatient/{id}")
    public String updatePatientProfile(Patient patient) {
        patientService.updatePatient(patient);
        return "redirect:/patients";
    }

}