package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.model.enums.Role;
import com.epam.lab.hospitalspring.security.details.PersonalDetailsImpl;
import com.epam.lab.hospitalspring.transfer.PersonalDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Index {
    @RequestMapping("/")
    public String showLoginPage(Model model, Authentication authentication) {
        PersonalDetailsImpl personalDetailsService = (PersonalDetailsImpl) authentication.getPrincipal();
        PersonalDto personalDto = PersonalDto.from(personalDetailsService.getPersonal());
        model.addAttribute("personal", personalDto);
        if (personalDetailsService.getPersonal().getRole() == Role.ADMIN) {
            return "redirect:/personals";
        } else {
            return "index";
        }
    }

}