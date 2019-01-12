package com.epam.lab.hospitalspring.service;

import com.epam.lab.hospitalspring.model.Personal;

import java.util.List;

public interface PersonalService {

    Personal addPersonal(Personal personal);

    void update(Personal personal); // for update and mark as deleted

    Personal getById(Long id);

    List<Personal> getAll();

}
