package com.epam.lab.hospitalspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestPageAlekseiController {
    @GetMapping("/testmainAleksei")
    public String getTestPage(){
        return "testPageAleksei";
    }
}
