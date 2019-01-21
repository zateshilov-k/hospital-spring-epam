package com.epam.lab.hospitalspring.service;

import com.epam.lab.hospitalspring.form.PersonalForm;
import com.epam.lab.hospitalspring.model.Personal;

import java.util.List;

public interface PersonalService {

    boolean update(PersonalForm personalForm, Long id); // for update and mark as deleted

    Personal getById(Long id);

    List<Personal> getAll();

}
