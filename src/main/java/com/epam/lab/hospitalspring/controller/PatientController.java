package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.form.PatientForm;
import com.epam.lab.hospitalspring.model.Patient;
import com.epam.lab.hospitalspring.model.enums.Role;
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
import com.epam.lab.hospitalspring.transfer.PersonalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
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


    @GetMapping(value = "/patients")
    public String getAllPatients(Model model, Authentication authentication) {
        PersonalDetailsImpl personalDetailsService = (PersonalDetailsImpl) authentication.getPrincipal();
        Role currentRole = personalDetailsService.getPersonal().getRole();
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("currentRole", currentRole);
        return "patients";
    }

    @GetMapping(value = "/deletedPatients")
    public String getDeletedPatients(Model model, Authentication authentication) {
        PersonalDetailsImpl personalDetailsService = (PersonalDetailsImpl) authentication.getPrincipal();
        Role currentRole = personalDetailsService.getPersonal().getRole();
        model.addAttribute("patients", patientService.getAllPatients());
        if (currentRole == Role.DOCTOR) {
            return "deletedPatients";
        } else {
            return "redirect:/error/errorMessage";
        }
    }

    // Getting for patinet his diagnoises and prescriptions
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
        patientService.updatePatient(patient);
        if (patient != null) {
            return "redirect:/patients";
        } else {
            return "redirect:/error/errorMessage";
        }
    }

    @GetMapping(value = "/patients/{id}")
    public String showPatientProfile(@PathVariable("id") Long id, Model model, Authentication authentication) {
        PersonalDetailsImpl personalDetailsService = (PersonalDetailsImpl) authentication.getPrincipal();
        Role currentRole = personalDetailsService.getPersonal().getRole();
        model.addAttribute("patient", patientService.getPatientById(id));
        if (currentRole == Role.DOCTOR) {
            return "patientUpdateForm";
        } else {
            return "redirect:/error/errorMessage";
        }
    }

    @PostMapping(value="/patients/updatePatient/{id}")
    public String updatePatientProfile(Patient patient) {
        patientService.updatePatient(patient);
        if (patient.getDeleted() == false) {
            return "redirect:/patients";
        } else {
            return "redirect:/deletedPatients";
        }
    }

}