package com.epam.lab.hospitalspring.service.impl;

import com.epam.lab.hospitalspring.form.PersonalForm;
import com.epam.lab.hospitalspring.model.Personal;
import com.epam.lab.hospitalspring.model.enums.Role;
import com.epam.lab.hospitalspring.repository.PersonalRepository;
import com.epam.lab.hospitalspring.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonalServiceImpl implements PersonalService {
    @Autowired
    private PersonalRepository personalRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public boolean update(PersonalForm personalForm, Long id) {
        boolean result = false;
        String login = personalForm.getLogin();

        if (personalRepository.findOneByLogin(login).isPresent() &&
                !personalRepository.findPersonalById(id).getLogin().equals(login)) {
            return false;
        }

        Optional<Personal> oPersonal = personalRepository.findById(id);
        if (oPersonal.isPresent()) {
            Personal personal = oPersonal.get();
            personal.setFirstName(personalForm.getFirstName());
            personal.setLastName(personalForm.getLastName());
            personal.setLogin(personalForm.getLogin());
            personal.setRole(Role.valueOf(personalForm.getRole()));
            personal.setPassword(passwordEncoder.encode(personalForm.getPassword()));
            personalRepository.saveAndFlush(personal);
            result = true;
        }
        return result;
    }

    @Override
    public Personal getById(Long id) {
        return personalRepository.findPersonalById(id);
    }

    @Override
    public Page<Personal> findAll(Pageable pageable) {
        return personalRepository.findAll(pageable);
    }

    @Override
    public void deleteFromDB(Long id) {
        personalRepository.deleteById(id);
    }

    @Override
    public Page<Personal> newFinder(String searchString, Pageable pageable) {
        Page<Personal> page;
        page = personalRepository.returnPage(searchString, pageable);
        return page;
    }

}
