package com.example.pdv.controller;

import javax.validation.Valid;

import com.example.pdv.model.Producto;
import com.example.pdv.service.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/productos")
public class ProductoController 
{
    @Autowired
    private IProductoService productoService;



    //
    @GetMapping(value = {"", "/"})
    public String home(Model model)
    {
        model.addAttribute("titulo", "Listado de Productos");
        model.addAttribute("productos", productoService.all());
        return "productos/index";
    }


    @GetMapping("/crear")
    public String nuevo(Model model)
    {
        model.addAttribute("titulo", "Nuevo Producto");
        model.addAttribute("producto", new Producto());
        return "productos/crear";
    }

    @PostMapping("/crear")
    public String crear(@Valid Producto producto, BindingResult result, RedirectAttributes redirect, Model model)
    {
        // Verificar si hay errores
        if(result.hasErrors())
        {
            return "productos/crear";
        }


        redirect.addFlashAttribute("successMsg", "Producto creado correctamente");
        return "redirect:/productos/";
    }
}
