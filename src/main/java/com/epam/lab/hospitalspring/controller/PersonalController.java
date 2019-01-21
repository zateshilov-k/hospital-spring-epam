package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.form.PersonalForm;
import com.epam.lab.hospitalspring.model.Personal;
import com.epam.lab.hospitalspring.model.enums.Role;
import com.epam.lab.hospitalspring.security.details.PersonalDetailsImpl;
import com.epam.lab.hospitalspring.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class PersonalController {
    @Autowired
    PersonalService personalService;

    @GetMapping("/personals")
    public String showPersonalsList(Model model, Authentication authentication) {
        List<Personal> personals = personalService.getAll();
        model.addAttribute("personals", personals);
        PersonalDetailsImpl personalDetailsService = (PersonalDetailsImpl) authentication.getPrincipal();
        model.addAttribute("currentRole", personalDetailsService.getPersonal().getRole());
        model.addAttribute("firstName", personalDetailsService.getPersonal().getFirstName());
        model.addAttribute("lastName", personalDetailsService.getPersonal().getLastName());
        return "personals";
    }

    @GetMapping("/personal/{id}")
    public String showPersonalPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("personal", personalService.getById(id));
        return "/personal";
    }

    @GetMapping("/addPersonal")
    public String showSignUpPage(PersonalForm personalForm) {
        return "signUp";
    }

    @PostMapping("/updatePersonal/{id}")
    public String updatePersonal(@PathVariable("id") Long id, PersonalForm personalForm) {
        String goNextPage = null;
        if (personalService.update(personalForm, id)) {
            goNextPage = "redirect:/personals";
        } else {
            System.out.println("НЕ успешное обновление ");
//TODO вернуться на предыдущую страницу /personal/id с ошибкой
        }
        return "redirect:/personals";

    }

    @GetMapping("/deletePersonalFromDB/{id}")
    public String deletePersonalFromDB(@PathVariable("id") Long id) {
        personalService.deleteFromDB(id);
        return "redirect:/personals";
    }
}
