package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.form.PersonalForm;
import com.epam.lab.hospitalspring.model.Personal;
import com.epam.lab.hospitalspring.service.SignUpService;
import com.epam.lab.hospitalspring.service.impl.SignUpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class SignUpController {


    @Autowired
    SignUpService signUpService;

    @GetMapping("/signUp")
    public String getSignUpPage(Personal personal) {
        System.out.println("signUp get");
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid Personal personal, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signUp";
        }
        try {
            signUpService.signUp(PersonalForm.from(personal));
        } catch (SignUpServiceImpl.LoginAlreadyUsed e) {
            return "redirect:/signUp?loginAleadyUsed";
        }
        return "redirect:/personals";
    }
}
