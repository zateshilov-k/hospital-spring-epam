package com.epam.lab.hospitalspring.service;

import com.epam.lab.hospitalspring.form.PersonalForm;
import com.epam.lab.hospitalspring.model.Personal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonalService {

    boolean update(PersonalForm personalForm, Long id);

    Personal getById(Long id);

    Page<Personal> newFinder(String searchString, Pageable pageable);

    Page<Personal> findAll(Pageable pageable);

    void deleteFromDB(Long id);

}
