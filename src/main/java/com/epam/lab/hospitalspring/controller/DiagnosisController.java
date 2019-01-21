package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.model.Personal;
import com.epam.lab.hospitalspring.security.details.PersonalDetailsImpl;
import com.epam.lab.hospitalspring.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DiagnosisController {
    @Autowired
    DiagnosisService diagnosisService;

    @ResponseBody
    @GetMapping("/patientDiagnosisCard/{patientId}/diagnosis/")
    String getDiagnoses(@PathVariable("patientId") long patientId) {
        String diagnosesJson = diagnosisService.findDiagnosisByPatientId(patientId);
        return diagnosesJson;
    }

    @ResponseBody
    @PostMapping("/patientDiagnosisCard/{patientId}/closeDiagnosis/{diagnosisId}")
    void closeDiagnosis(@PathVariable("diagnosisId") long diagnosisId){
        diagnosisService.closeDiagnosis(diagnosisId);
    }

    @ResponseBody
    @PostMapping("/patientDiagnosisCard/{patientId}/addDiagnosis/descr={diagnosisDescription}")
    void addDiagnosis(@PathVariable("diagnosisDescription") String diagnosisDescription,
                      @PathVariable("patientId") Long patientId,
                      Authentication authentication) {
        PersonalDetailsImpl personalDetailsService = (PersonalDetailsImpl) authentication.getPrincipal();
        Personal personal = personalDetailsService.getPersonal();
        Long personalId = personal.getId();

        diagnosisService.addDiagnosis(patientId,personalId,diagnosisDescription);
    }
}
