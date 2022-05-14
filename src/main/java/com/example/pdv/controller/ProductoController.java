package com.example.pdv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/productos")
public class ProductoController 
{
    @GetMapping(value = {"", "/"})
    public String home(Model model)
    {
        model.addAttribute("titulo", "Listado de Productos");
        return "productos/listado";
    }


    @GetMapping("/nuevo")
    public String nuevo(Model model)
    {
        model.addAttribute("titulo", "Nuevo Producto");
        return "productos/nuevo";
    }
}
