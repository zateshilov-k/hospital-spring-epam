package com.epam.lab.hospitalspring.controller;

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
            add(new Personal(1L, "admin@epam.com", "admin", "Сергей", "Шнуров", false, Role.ADMIN));
            add(new Personal(2L, "doctor@epam.com", "doctor", "Николай", "Басков", false, Role.DOCTOR));
            add(new Personal(3L, "nurse@epam.com", "nurse", "Верка", "Сердючка", false, Role.NURSE));
        }
    };

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
}
