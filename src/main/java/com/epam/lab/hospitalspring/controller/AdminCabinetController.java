package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.model.Personal;
import com.epam.lab.hospitalspring.model.enums.Role;
import com.epam.lab.hospitalspring.service.PersonalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminCabinetController {
    PersonalService personalService;
    List<Personal> personals = new ArrayList<Personal>() {
        {
            add(new Personal(1L, "admin@epam.com", "admin", "Сергей", "Шнур", false, Role.ADMIN));
            add(new Personal(1L, "doctor@epam.com", "doctor", "Николай", "Басков", false, Role.DOCTOR));
            add(new Personal(1L, "nurse@epam.com", "nurse", "Верка", "Сердючка", false, Role.NURSE));
        }
    };

    @RequestMapping("/personals")
    public String showPersonalsList(Model model) {
        // записываем тестовый набор пользователей
        for (Personal personal : personals) {
            personalService.addPersonal(personal);
        }
        List<Personal> personals = personalService.getAll();
        model.addAttribute(personals);
        return "personals"; // //path/name of the view in resources/templates
    }
}
