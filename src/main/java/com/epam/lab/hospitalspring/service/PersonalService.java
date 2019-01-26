package com.epam.lab.hospitalspring.service;

import com.epam.lab.hospitalspring.form.PersonalForm;
import com.epam.lab.hospitalspring.model.Personal;

import java.util.List;

public interface PersonalService {

    boolean update(PersonalForm personalForm, Long id);

    Personal getById(Long id);

    List<Personal> getAll();

    void delete(Long id);

}
