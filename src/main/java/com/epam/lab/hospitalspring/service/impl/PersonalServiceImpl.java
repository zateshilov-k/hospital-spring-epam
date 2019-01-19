package com.epam.lab.hospitalspring.service.impl;

import com.epam.lab.hospitalspring.form.PersonalForm;
import com.epam.lab.hospitalspring.model.Personal;
import com.epam.lab.hospitalspring.repository.PersonalRepository;
import com.epam.lab.hospitalspring.service.PersonalService;
import com.epam.lab.hospitalspring.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalServiceImpl implements PersonalService {

    @Autowired //аннотация которая позволит Spring инициализировать наш сервис
    private PersonalRepository personalRepository;

    @Override
    public void update(Personal personal) {
        personalRepository.saveAndFlush(personal);
    }

    @Override
    public Personal getById(Long id) {
        return personalRepository.findPersonalById(id);
    }

    @Override
    public List<Personal> getAll() {
        return personalRepository.findAll();
    }
}
