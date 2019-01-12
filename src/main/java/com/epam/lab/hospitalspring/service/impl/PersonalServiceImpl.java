package com.epam.lab.hospitalspring.service.impl;

import com.epam.lab.hospitalspring.model.Personal;
import com.epam.lab.hospitalspring.repository.PersonalRepository;
import com.epam.lab.hospitalspring.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalServiceImpl implements PersonalService {

    @Autowired //аннотация которая позволит Spring инициализировать наш сервис
    private PersonalRepository personalRepository;

    @Override
    public Personal addPersonal(Personal personal) {
        Personal savedPersonal = personalRepository.saveAndFlush(personal);
        return savedPersonal;
    }

    @Override
    public void update(Personal personal) {

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
