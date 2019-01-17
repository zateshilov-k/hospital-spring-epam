package com.epam.lab.hospitalspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class ErrorPageController {

    @RequestMapping(value = "/error/{message}", method = GET)
    public String getErrorPage(@PathVariable("message") String message, Model model) {
        model.addAttribute("errorMessage", message);
        return "error";
    }
}
