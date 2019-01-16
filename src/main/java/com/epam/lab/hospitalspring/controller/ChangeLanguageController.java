package com.epam.lab.hospitalspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChangeLanguageController {
    @PostMapping("/changeLanguage")
    void changeLocale() {

    }
}
