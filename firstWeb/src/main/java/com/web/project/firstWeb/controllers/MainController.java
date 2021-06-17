package com.web.project.firstWeb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping(value = "/")
    public String mainPage(Model model){
        model.addAttribute("main","greeting");
        return "mainPage";
    }
}
