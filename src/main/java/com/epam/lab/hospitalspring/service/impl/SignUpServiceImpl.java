package com.epam.lab.hospitalspring.service.impl;

import com.epam.lab.hospitalspring.form.PersonalForm;
import com.epam.lab.hospitalspring.model.Personal;
import com.epam.lab.hospitalspring.model.enums.Role;
import com.epam.lab.hospitalspring.repository.PersonalRepository;
import com.epam.lab.hospitalspring.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Class provide signUp functionality
 */
@Service
public class SignUpServiceImpl implements SignUpService {
    public static class LoginAlreadyUsed extends RuntimeException {
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PersonalRepository personalRepository;

    /**
     * When input personalForm login already in DB, exception is thrown
     * and catched in controller to provide error on frontend page.
     *
     * @param  personalForm form from front end
     * @throws LoginAlreadyUsed
     */
    @Override
    public void signUp(PersonalForm personalForm) throws LoginAlreadyUsed {
        String login = personalForm.getLogin();
        if (personalRepository.findOneByLogin(login).isPresent()) {
            throw new LoginAlreadyUsed();
        }
        String passwordHash = passwordEncoder.encode(personalForm.getPassword());
        Personal personal = Personal.builder()
                .firstName(personalForm.getFirstName())
                .lastName(personalForm.getLastName())
                .login(personalForm.getLogin())
                .password(passwordHash)
                .role(Role.valueOf(personalForm.getRole()))
                .deleted(false)
                .build();
        personalRepository.saveAndFlush(personal);
    }
}
