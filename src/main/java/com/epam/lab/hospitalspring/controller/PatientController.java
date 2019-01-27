package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.model.Patient;
import com.epam.lab.hospitalspring.model.enums.Role;
import com.epam.lab.hospitalspring.security.details.PersonalDetailsImpl;
import com.epam.lab.hospitalspring.service.DiagnosisService;
import com.epam.lab.hospitalspring.service.PatientService;
import com.epam.lab.hospitalspring.service.PrescriptionService;
import com.epam.lab.hospitalspring.util.GsonFactory;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Locale;

@Controller
public class PatientController {
    private String filter = null;

    @Autowired
    PatientService patientService;
    @Autowired
    DiagnosisService diagnosisService;
    @Autowired
    PrescriptionService prescriptionService;

    LocalDateTime today = LocalDateTime.now();

    @GetMapping("/patients")
    public String getPatients(@PageableDefault(size = 5) Pageable pageable,
                              Model model, Authentication authentication) {
        PersonalDetailsImpl personalDetailsService = (PersonalDetailsImpl) authentication.getPrincipal();
        Role currentRole = personalDetailsService.getPersonal().getRole();
        if (currentRole != Role.ADMIN) {

            Page<Patient> page = null;
            if (filter == null || filter == "") {
                page = patientService.getNotDeletedPatients(pageable);

            } else {
                page = patientService.newFinder(filter, pageable);
            }
            Long totalElements = page.getTotalElements();
            model.addAttribute("page", page);
            model.addAttribute("page", page);
//            model.addAttribute("patients", patientService.getNotDeletedPatients());
            model.addAttribute("currentRole", currentRole);
            model.addAttribute("firstName", personalDetailsService.getPersonal().getFirstName());
            model.addAttribute("lastName", personalDetailsService.getPersonal().getLastName());
            model.addAttribute("totalElements", totalElements);

            return "patients";
        } else {
            return "redirect:/error/errorMessage";
        }
    }

    @PostMapping("/patients")
    public String search(Model model, Authentication authentication, String search, @PageableDefault(size = 5) Pageable pageable) {
        filter = search;
        Page<Patient> page = patientService.newFinder(search, pageable);
        Long totalElements = page.getTotalElements();
        model.addAttribute("page", page);
        PersonalDetailsImpl personalDetailsService = (PersonalDetailsImpl) authentication.getPrincipal();
        model.addAttribute("currentRole", personalDetailsService.getPersonal().getRole());
        model.addAttribute("firstName", personalDetailsService.getPersonal().getFirstName());
        model.addAttribute("lastName", personalDetailsService.getPersonal().getLastName());
        model.addAttribute("totalElements", totalElements);
        return "patients";

    }

    @GetMapping("/deletedPatients")
    public String getDeletedPatients(Model model, Authentication authentication) {
        PersonalDetailsImpl personalDetailsService = (PersonalDetailsImpl) authentication.getPrincipal();
        Role currentRole = personalDetailsService.getPersonal().getRole();
        model.addAttribute("patients", patientService.getDeletedPatients());
        if (currentRole == Role.DOCTOR) {
            model.addAttribute("currentRole", personalDetailsService.getPersonal().getRole());
            model.addAttribute("firstName", personalDetailsService.getPersonal().getFirstName());
            model.addAttribute("lastName", personalDetailsService.getPersonal().getLastName());
            return "deletedPatients";
        } else {
            return "redirect:/error/errorMessage";
        }
    }

    @GetMapping(value = "/patientDiagnosisCard/{id}")
    public String getPatientDiagnosisCard(@PathVariable("id") Long id,
                                          Model model, Authentication authentication, Locale locale) {
        PersonalDetailsImpl personalDetailsService = (PersonalDetailsImpl) authentication.getPrincipal();
        Role currentRole = personalDetailsService.getPersonal().getRole();

        model.addAttribute("role", currentRole);
        if (currentRole != Role.ADMIN) {
            Patient currentPatient = patientService.getPatientById(id);
            model.addAttribute("patient", currentPatient);
            Gson gson = GsonFactory.buildGson(locale);
            model.addAttribute("diagnoses", gson.toJson(currentPatient.getDiagnosisList()));
            return "patientDiagnosisCard";
        } else {
            return "redirect:/error/errorMessage";
        }
    }

    @GetMapping(value = "/addPatient")
    public String getAddPatientPage(Patient patient) {
        return "patient";
    }

    @PostMapping(value = "/addPatient")
    public String addPatient(@Valid Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "patient";
        }
        patientService.addPatient(patient);
        return "redirect:/patients";
    }

    @GetMapping(value = "/patient/{id}")
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

    @GetMapping(value = "/patients/updatePatient/{id}")
    public String getPatientProfileForUpdate(Patient patient) {
        return "patientUpdateForm";
    }


    @PostMapping(value = "/patients/updatePatient/{id}")
    public String updatePatientProfile(@Valid Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "patientUpdateForm";
        }
        patientService.updatePatient(patient);
        if (!patient.getDeleted()) {
            return "redirect:/patients";
        } else {
            return "redirect:/deletedPatients";
        }
    }

}