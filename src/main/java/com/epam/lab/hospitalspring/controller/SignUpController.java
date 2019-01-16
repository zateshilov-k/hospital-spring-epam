package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.form.PersonalForm;
import com.epam.lab.hospitalspring.service.SignUpService;
import com.epam.lab.hospitalspring.service.impl.SignUpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class SignUpController {



    @GetMapping("/signUp")
    public String getSignUpPage() {
        System.out.println("signUp get");
        return "signUp";
    }

    @Autowired
    SignUpService signUpService;

    @PostMapping("/signUp")
    public String signUp(PersonalForm personalForm) {
        try {
            signUpService.signUp(personalForm);
        } catch (SignUpServiceImpl.LoginAlreadyUsed e) {
            return "redirect:/signUp?loginAleadyUsed";
        }catch (SignUpServiceImpl.LoginNotValid e) {
            return "redirect:/signUp?loginNotValid";
        }
        return "redirect:/login";
    }
}
