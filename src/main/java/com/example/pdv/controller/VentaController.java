package com.example.pdv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/ventas")
public class VentaController 
{
    @GetMapping(value = {"", "/"})
    public String home(Model model)
    {
        model.addAttribute("title", "Listado de ventas");
        return "ventas/listado";
    }    
}
