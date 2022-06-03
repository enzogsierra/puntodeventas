package com.example.pdv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/usuarios")
public class UsuarioController 
{
    @GetMapping(value = {"", "/"})
    public String home(Model model)
    {
        model.addAttribute("title", "Listado de Usuarios");
        return "usuarios/index";
    }
}
