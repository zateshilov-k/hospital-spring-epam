package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.model.Patient;
import com.epam.lab.hospitalspring.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PatientListController {

    @Autowired
    PatientService patientService;

    List<Patient> patientList = new ArrayList<Patient>() {
        {

            add(new Patient(1L, "dfdsfds", "Family", true, false));
        }
    };

    @RequestMapping("/patients")
    public String showPatientList(Model model) {
        for (Patient patient : patientList) {
            patientService.addPatient(patient);
        }

        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "patients";
    }

}
