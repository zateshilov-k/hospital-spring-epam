package com.epam.lab.hospitalspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class Index {
    @RequestMapping("/")
    public String showLoginPage() {
        return "index"; // //path/name of the view in resources/templates
    }
}
