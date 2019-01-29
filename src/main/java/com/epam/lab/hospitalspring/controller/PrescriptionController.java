package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

/**
 * Contains all controllers that handle all request related to Prescription.
 */
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
                prescriptionService.findPrescriptionsForDiagnosisByDiagnosisIdOrderByIdAsc(diagnosisId, locale);
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
                         @RequestParam("prescriptionType") String prescriptionType) {
        prescriptionService.addPrescription(diagnosisId, prescriptionDescription, prescriptionType);
    }

}
