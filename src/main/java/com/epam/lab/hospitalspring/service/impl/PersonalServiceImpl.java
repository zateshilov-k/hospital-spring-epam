package com.epam.lab.hospitalspring.service.impl;

import com.epam.lab.hospitalspring.form.PersonalForm;
import com.epam.lab.hospitalspring.model.Personal;
import com.epam.lab.hospitalspring.model.enums.Role;
import com.epam.lab.hospitalspring.repository.PersonalRepository;
import com.epam.lab.hospitalspring.service.PersonalService;
import com.epam.lab.hospitalspring.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class PersonalServiceImpl implements PersonalService {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    @Autowired //аннотация которая позволит Spring инициализировать наш сервис
    private PersonalRepository personalRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public boolean update(PersonalForm personalForm) {
        boolean result=false;
        if(!VALID_EMAIL_ADDRESS_REGEX .matcher(personalForm.getLogin()).find()) {
            return  false;
        }
        Optional<Personal> oPersonal = personalRepository.findOneByLogin(personalForm.getLogin());
        System.out.println(personalRepository.findOneByLogin(personalForm.getLogin()).get());
        if(oPersonal.isPresent()) {
           Personal personal  = oPersonal.get();
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
    public List<Personal> getAll() {
        return personalRepository.findAll();
    }

}
