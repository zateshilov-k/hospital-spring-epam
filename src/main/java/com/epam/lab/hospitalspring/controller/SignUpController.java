package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.form.PersonalForm;
import com.epam.lab.hospitalspring.model.Personal;
import com.epam.lab.hospitalspring.service.SignUpService;
import com.epam.lab.hospitalspring.service.impl.SignUpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Controller
public class SignUpController {



    @GetMapping("/signUp")
    public String getSignUpPage(Personal personal) {
        System.out.println("signUp get");
        return "signUp";
    }

    @Autowired
    SignUpService signUpService;

    @PostMapping("/signUp")
    public String signUp(@Valid Personal personal, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return "signUp";
        }
        System.out.println(personal.getFirstName() + " " + personal.getLastName() + " "
        + personal.getPassword() + " " + personal.getLogin() + " " + personal.getRole());
        /*try {
            signUpService.signUp(personalForm);
        } catch (SignUpServiceImpl.LoginAlreadyUsed e) {
            return "redirect:/signUp?loginAleadyUsed";
        }catch (SignUpServiceImpl.LoginNotValid e) {
            return "redirect:/signUp?loginNotValid";
        }*/
        return "redirect:/personals";
    }
}
