package com.example.pdv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController
{
    @GetMapping(value = {"", "/"})
    public String home(Model model)
    {
        model.addAttribute("title", "Aplicacion Spring Boot");
        model.addAttribute("subtitle", "Punto de Ventas");
        return "home";
    }
}
