package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.model.Patient;
import com.epam.lab.hospitalspring.model.Personal;
import com.epam.lab.hospitalspring.model.enums.Role;
import com.epam.lab.hospitalspring.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PersonalRepository personalRepository;

    List<Personal> personals = new ArrayList<Personal>() {
        {
            add(Personal.builder()
                        .login("admin@epam.com")
                        .password("admin")
                        .firstName("Сергей")
                        .lastName("Шнуров")
                        .isDeleted(false)
                        .role(Role.ADMIN)
                        .build());
            add(Personal.builder()
                    .login("doctor@epam.com")
                    .password("doctor")
                    .firstName("Николай")
                    .lastName("Басков")
                    .isDeleted(false)
                    .role(Role.DOCTOR)
                    .build());
            add(Personal.builder()
                    .login("nurse@epam.com")
                    .password("nurse")
                    .firstName("Верка")
                    .lastName("Сердючка")
                    .isDeleted(false)
                    .role(Role.NURSE)
                    .build());
        }
    };

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
}
