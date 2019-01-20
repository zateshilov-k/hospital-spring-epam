package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.model.Personal;
import com.epam.lab.hospitalspring.model.enums.Role;
import com.epam.lab.hospitalspring.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonalsListController {
    @Autowired
    PersonalService personalService;


    @RequestMapping("/personals")
    public String showPersonalsList(Model model) {
        List<Personal> personals = personalService.getAll();
        model.addAttribute("personals", personals);
        return "personals"; // //path/name of the view in resources/templates
    }
}
