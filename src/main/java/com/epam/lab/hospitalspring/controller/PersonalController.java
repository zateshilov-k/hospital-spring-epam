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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/addPersonal")
    public String showSignUpPage(Personal personal) {
        return "signUp";
    }

    @GetMapping("/personal/{id}")
    public String showPersonalPage(Personal personal, @PathVariable("id") Long id, Model model) {
        model.addAttribute("personal", personalService.getById(id));
        return "/personal";
    }

    @PostMapping("/personal/{id}")
    public String updatePersonal(@Valid Personal personal, BindingResult bindingResult,
                                 @PathVariable("id") Long id, PersonalForm personalForm) {
        if (bindingResult.hasErrors()) {
            return "/personal";
        }

        if (personalService.update(personalForm, id)) {
            return "redirect:/personals";
        } else {
            return "redirect:/personal/"+id+"?loginAleadyUsed";
        }
    }

    @GetMapping("/deletePersonalFromDB/{id}")
    public String deletePersonalFromDB(@PathVariable("id") Long id) {
        personalService.deleteFromDB(id);
        return "redirect:/personals";
    }
}
