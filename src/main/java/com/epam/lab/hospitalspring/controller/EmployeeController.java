package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.model.Personal;
import com.epam.lab.hospitalspring.security.details.PersonalDetailsImpl;
import com.epam.lab.hospitalspring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * Test controller for pagination (/employees page)
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping("/employees")
    public String getEmployees(@PageableDefault(size = 2) Pageable pageable,
                               Model model, Authentication authentication) {
        Page<Personal> page = employeeService.getAll(pageable);
        Long totalElements = page.getTotalElements();
        model.addAttribute("page", page);
        PersonalDetailsImpl personalDetailsService = (PersonalDetailsImpl) authentication.getPrincipal();
        model.addAttribute("currentRole", personalDetailsService.getPersonal().getRole());
        model.addAttribute("firstName", personalDetailsService.getPersonal().getFirstName());
        model.addAttribute("lastName", personalDetailsService.getPersonal().getLastName());
        model.addAttribute("totalElements", totalElements);
        return "employee-page";
    }

    @PostMapping("/employees")
    public String search(Model model, Authentication authentication, String search, @PageableDefault(size = 2) Pageable pageable){

        Page<Personal> page = employeeService.finder(search, search, search, pageable);
        Long totalElements = page.getTotalElements();
        model.addAttribute("page", page);
        PersonalDetailsImpl personalDetailsService = (PersonalDetailsImpl) authentication.getPrincipal();
        model.addAttribute("currentRole", personalDetailsService.getPersonal().getRole());
        model.addAttribute("firstName", personalDetailsService.getPersonal().getFirstName());
        model.addAttribute("lastName", personalDetailsService.getPersonal().getLastName());
        model.addAttribute("totalElements", totalElements);
        return "employee-page";



    }
}
