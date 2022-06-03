package com.example.pdv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clientes")
public class ClienteController 
{
    @GetMapping(value = {"", "/"})
    public String home(Model model)
    {
        return "clientes/index";
    }
}
