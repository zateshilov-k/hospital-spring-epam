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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * Test controller for pagination (/employees page)
 */
@Controller
public class EmployeeController {
    private String filter = null;
    @Autowired
    EmployeeService employeeService;

    // выдает страницы с сотрудниками
    @GetMapping("/employees")
    public String getEmployees(@PageableDefault(size = 2) Pageable pageable,
                               Model model, Authentication authentication) {
        Page<Personal> page = null;
        if (filter == null || filter =="") {
            page = employeeService.findAll(pageable);

        } else {
            page = employeeService.newFinder(filter, pageable);
        }
        Long totalElements = page.getTotalElements();
        model.addAttribute("page", page);
        PersonalDetailsImpl personalDetailsService = (PersonalDetailsImpl) authentication.getPrincipal();
        model.addAttribute("currentRole", personalDetailsService.getPersonal().getRole());
        model.addAttribute("firstName", personalDetailsService.getPersonal().getFirstName());
        model.addAttribute("lastName", personalDetailsService.getPersonal().getLastName());
        model.addAttribute("totalElements", totalElements);
        return "employee-page";
    }

    //отрабатывает поиск по вхождению search в поле имя или фамилия
    @PostMapping("/employees")
    public String search(Model model, Authentication authentication, String search, @PageableDefault(size = 2) Pageable pageable) {
        filter = search;
        Page<Personal> page = employeeService.newFinder(search, pageable);
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
