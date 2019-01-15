package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.model.Patient;
import com.epam.lab.hospitalspring.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PatientListController {

    @Autowired
    PatientService patientService;
    // test data
    List<Patient> patientList = new ArrayList<Patient>() {
        {
            add(new Patient(1L, "patient1", "secondName1",  false, true));
            add(new Patient(2L, "patient2", "secondName2", false, true));
            add(new Patient(3L, "patient3", "secondName3", false, true));
        }
    };

    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    public String getAllPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patients";
    }

    @RequestMapping(value = "/patients/{id}", method = RequestMethod.GET)
    public String getPatient(@PathVariable("id") Long id, Model model) {
        model.addAttribute("patient", patientService.getPatientById(id));
        System.out.println(patientService.getPatientById(id));
        return "patientProfile";
    }



//    @RequestMapping("/patients")
//    public String showPatientsList(Model model) {
//        // записываем тестовый набор пользователей
//        for (Patient patient : patientList) {
//            patientService.addPatient(patient);
//        }
//        List<Patient> patients = patientService.getAllPatients();
//        model.addAttribute("patients", patients);
//        return "patients"; // //path/name of the view in resources/templates
//    }

}
