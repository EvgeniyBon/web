package com.web.project.firstWeb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FooterLinksController {
    @GetMapping(value = "/about")
    public String about (Model model){
        model.addAttribute("about","About Us Page");
        return "about";
    }
}
