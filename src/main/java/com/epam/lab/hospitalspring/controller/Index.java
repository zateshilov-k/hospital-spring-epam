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
        PersonalDetailsImpl userDetailsService = (PersonalDetailsImpl) authentication.getPrincipal();
        PersonalDto personalDto = PersonalDto.from(userDetailsService.getPersonal());
        model.addAttribute("personal", personalDto);
        if (userDetailsService.getPersonal().getRole() == Role.ADMIN) {
            return "redirect:/personals";
        } else {
            return "index";
        }
    }
}
