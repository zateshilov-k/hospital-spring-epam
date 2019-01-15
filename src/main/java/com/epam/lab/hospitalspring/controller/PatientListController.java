package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.model.Diagnosis;
import com.epam.lab.hospitalspring.model.Patient;
import com.epam.lab.hospitalspring.repository.DiagnosisRepository;
import com.epam.lab.hospitalspring.repository.PatientRepository;
import com.epam.lab.hospitalspring.service.DiagnosisService;
import com.epam.lab.hospitalspring.service.PatientService;
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
public class PatientListController {

    @Autowired
    PatientService patientService;
    @Autowired
    DiagnosisService diagnosisService;
    @Autowired
    DiagnosisRepository diagnosisRepository;
    LocalDateTime today = LocalDateTime.now();
    // test data
    List<Patient> patientList = new ArrayList<Patient>() {
        {
            add(new Patient(1L, "patient1", "secondName1",  false, true));
            add(new Patient(2L, "patient2", "secondName2", false, true));
            add(new Patient(3L, "patient3", "secondName3", false, true));
        }
    };

    List<Diagnosis> diagnosisList = new ArrayList<Diagnosis>() {
        {
            add(new Diagnosis(1L, "dsfsdf", patientList.get(1), true, today));
            add(new Diagnosis(2L, "dsfsdf", patientList.get(1), true, today));
            add(new Diagnosis(3L, "dsfsdf", patientList.get(1), true, today));
            add(new Diagnosis(4L, "dsfsdf", patientList.get(2), true, today));
        }
    };

    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    public String getAllPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
//        model.addAttribute("patients", patientList);
        return "patients";
    }

    @RequestMapping(value = "/patients/{id}", method = RequestMethod.GET)
    public String getPatient(@PathVariable("id") Long id, Model model) {
        model.addAttribute("patient", patientService.getPatientById(id));
        model.addAttribute("diagnosisList", diagnosisService.findDiagnosisByPatientId(id));
//        model.addAttribute("patient", patientList.get(Math.toIntExact(id)));
//        model.addAttribute("diagnosisList", getListDiagnosisByPatientId(id));
//        System.out.println(patientService.getPatientById(id));
//        getListDiagnosisByPatientId(id);
        return "patientDiagnosisCard";
    }

    private List<Diagnosis> getListDiagnosisByPatientId(Long id) {
        Patient patient = patientService.getPatientById(id);
//        List<Diagnosis> diagnosisList = diagnosisService.getDiagnosisByPatient(patient);
        List<Diagnosis> list = new ArrayList<>();
        for (int i = 0; i < diagnosisList.size(); i++) {
            if (id == diagnosisList.get(i).getPatient().getId()) {
                list.add(diagnosisList.get(i));
            }
        }
        return list;
    }


   /* @RequestMapping("/patients")
    public String showPatientsList(Model model) {
        // записываем тестовый набор пользователей
        for (Patient patient : patientList) {
            patientService.addPatient(patient);
        }

        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);

//        List<Diagnosis> diagnoses = diagnosisService.getAll();
//        model.addAttribute("diagnosis", diagnoses);
        return "patients"; // //path/name of the view in resources/templates
    }*/

    @GetMapping("/test")
    public void addTestData(){
        //diagnosisList.forEach(diagnosis -> diagnosisRepository.save(diagnosis));

        List<Diagnosis> all = diagnosisRepository.findAll();
        List<Patient> allPatients = patientService.getAllPatients();
        all.forEach(System.out::println);
        allPatients.forEach(System.out::println);
    }



}
