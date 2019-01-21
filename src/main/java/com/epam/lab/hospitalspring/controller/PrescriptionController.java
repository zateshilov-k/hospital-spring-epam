package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.model.Prescription;
import com.epam.lab.hospitalspring.repository.PrescriptionRepository;
import com.epam.lab.hospitalspring.service.PrescriptionService;
import com.epam.lab.hospitalspring.util.GsonFactory;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class PrescriptionController {
    @Autowired
    PrescriptionService prescriptionService;

    @ResponseBody
    @GetMapping("/patientDiagnosisCard/{patientId}/diagnosis/{diagnosisId}/prescription")
    String getPrescriptions(@PathVariable("diagnosisId") long diagnosisId,
                            @PathVariable("patientId") long patientId) {
        String prescriptionsJson =
                prescriptionService.findPrescriptionsForDiagnosisByDiagnosisIdOrderByIdAsc(diagnosisId);
        return prescriptionsJson;
    }

    @ResponseBody
    @PostMapping("/patientDiagnosisCard/{patientId}/doPrescription/{prescriptionId}")
    void doPrescription(@PathVariable("prescriptionId") long prescriptionId,
                        @PathVariable("patientId") long patientId){
        prescriptionService.doPrescription(prescriptionId);
    }

    @ResponseBody
    @PostMapping("/patientDiagnosisCard/{patientId}/diagnosis/{diagnosisId}/addPrescription/description={prescriptionDescription}/type={prescriptionType}")
    void addPrescription(@PathVariable("diagnosisId") long diagnosisId,
                         @PathVariable("prescriptionDescription") String prescriptionDescription,
                         @PathVariable("prescriptionType") String prescriptionType){
        prescriptionService.addPrescription(diagnosisId, prescriptionDescription, prescriptionType);
    }
}
