package com.example.pdv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PublicController
{
    @GetMapping(value = {"", "/"})
    public String home(Model model)
    {
        model.addAttribute("title", "Gestión de Punto de Ventas - Dashboard");
        return "index";
    }
}
