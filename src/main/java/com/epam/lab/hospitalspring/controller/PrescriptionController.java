package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.model.Prescription;
import com.epam.lab.hospitalspring.repository.PrescriptionRepository;
import com.epam.lab.hospitalspring.service.PrescriptionService;
import com.epam.lab.hospitalspring.util.GsonFactory;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
public class PrescriptionController {
    @Autowired
    PrescriptionService prescriptionService;

    @ResponseBody
    @GetMapping("/patientDiagnosisCard/{patientId}/diagnosis/{diagnosisId}/prescription")
    String getPrescriptions(@PathVariable("diagnosisId") long diagnosisId,
                            @PathVariable("patientId") long patientId,
                            Locale locale) {
        String prescriptionsJson =
                prescriptionService.findPrescriptionsForDiagnosisByDiagnosisIdOrderByIdAsc(diagnosisId,locale);
        return prescriptionsJson;
    }

    @ResponseBody
    @PostMapping("/patientDiagnosisCard/{patientId}/doPrescription/{prescriptionId}")
    void doPrescription(@PathVariable("prescriptionId") long prescriptionId,
                        @PathVariable("patientId") long patientId) {
        prescriptionService.doPrescription(prescriptionId);
    }

    @ResponseBody
    @PostMapping("/patientDiagnosisCard/{patientId}/diagnosis/{diagnosisId}/addPrescription/")
    void addPrescription(@PathVariable("diagnosisId") long diagnosisId,
                         @RequestParam("prescriptionDescription") String prescriptionDescription,
                         @RequestParam("prescriptionType") String prescriptionType){
        prescriptionService.addPrescription(diagnosisId, prescriptionDescription, prescriptionType);
    }
}
