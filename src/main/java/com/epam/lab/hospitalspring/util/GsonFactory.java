package com.epam.lab.hospitalspring.util;

import com.epam.lab.hospitalspring.model.Diagnosis;
import com.epam.lab.hospitalspring.model.Prescription;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Locale;

public class GsonFactory {
    public static Gson buildGson(Locale locale) {
        GsonBuilder builder = new GsonBuilder();
        DiagnosisSerializer diagnosisSerializer = new DiagnosisSerializer();
        diagnosisSerializer.setLocale(locale);
        PrescriptionSerializer prescriptionSerializer = new PrescriptionSerializer();
        prescriptionSerializer.setLocale(locale);
        builder.registerTypeAdapter(Diagnosis.class, diagnosisSerializer);
        builder.registerTypeAdapter(Prescription.class, prescriptionSerializer);
        return builder.excludeFieldsWithoutExposeAnnotation().create();
    }
}
