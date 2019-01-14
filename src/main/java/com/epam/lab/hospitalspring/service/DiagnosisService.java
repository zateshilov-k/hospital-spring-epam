package com.epam.lab.hospitalspring.service;

import com.epam.lab.hospitalspring.model.Diagnosis;


import java.util.List;

public interface DiagnosisService {

    Diagnosis addDiagnosis(Diagnosis diagnosis);

    void update(Diagnosis diagnosis); // for update and mark as deleted

    Diagnosis getById(Long id);

    List<Diagnosis> getAll();

}
