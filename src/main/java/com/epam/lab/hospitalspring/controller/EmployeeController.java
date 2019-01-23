package com.epam.lab.hospitalspring.controller;

import com.epam.lab.hospitalspring.model.Personal;
import com.epam.lab.hospitalspring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping("/employees")
    public String getEmployees(@PageableDefault(size = 2) Pageable pageable,
                               Model model) {
        Page<Personal> page = employeeService.getAll(pageable);
        model.addAttribute("page", page);
        return "employee-page";
    }
}
