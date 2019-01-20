package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.form.PersonalForm;
import com.epam.lab.hospitalspring.model.Personal;
import com.epam.lab.hospitalspring.model.enums.Role;
import com.epam.lab.hospitalspring.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class PersonalsListController {
    @Autowired
    PersonalService personalService;


    @GetMapping("/personals")
    public String showPersonalsList(Model model) {
        List<Personal> personals = personalService.getAll();
        model.addAttribute("personals", personals);
        return "personals"; // //path/name of the view in resources/templates
    }

    @GetMapping("/personal/{id}")
    public String getPersonalPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("personal", personalService.getById(id));
        return "/personal";
    }

    @GetMapping("/addPersonal")
    public String showSyPersonal(PersonalForm personalForm) {
        return "signUp";
    }

    @GetMapping("/updatePersonal")
    public String getUpdatePersonalPage() {

        System.out.println("чудо");
        return "personal";
    }

    @PostMapping("/updatePersonal")
    public String updatePersonal(PersonalForm personalForm) {
        String goNextPage = "";
        if (personalService.update(personalForm)) {
            goNextPage= "redirect:/personals";
        } else {
            System.out.println("НЕ успешное обновление ");
//TODO вернуться на предыдущую страницу /personal/id с ошибкой
        }
        return "redirect:/personals";

    }
}
