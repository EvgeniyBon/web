package com.web.project.firstWeb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {
    @GetMapping(value = "/contacts")
    public String contacts(Model model){
        model.addAttribute("contacts","There is our contacts page");
        return "contacts";
    }
}
