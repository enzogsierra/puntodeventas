package com.example.pdv.controller;

import javax.validation.Valid;

import com.example.pdv.model.Producto;
import com.example.pdv.service.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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


    // Añadir producto
    @GetMapping("/crear")
    public String crear(Model model)
    {
        model.addAttribute("titulo", "Nuevo producto");
        model.addAttribute("producto", new Producto());
        return "productos/form";
    }

    // Editar producto
    @GetMapping("/editar/{id}")
    public String editar(Model model, @PathVariable Integer id)
    {
        Producto producto = productoService.findById(id).get();
    
        model.addAttribute("titulo", "Editar producto");
        model.addAttribute("producto", producto);
        return "productos/form";
    }

    // Guardar producto (nuevo o editado)
    @PostMapping("/guardar")
    public String guardar(@Valid Producto producto, BindingResult result, RedirectAttributes redirect, Model model)
    {
        // Verificar si hay errores
        if(result.hasErrors())
        {
            return "productos/form";
        }
        
        // Datos correctos
        productoService.save(producto);
        
        redirect.addFlashAttribute("successMsg", (producto.getId() == null || producto.getId() == 0) ? "Producto creado correctamente" : "Producto editado correctamente");
        return "redirect:/productos/";
    }

    // Eliminar (archivar) producto
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirect)
    {
        Producto producto = productoService.findById(id).get();
        producto.setActivo(false); // Archivar - Desactivar el producto de manera lógica
        productoService.save(producto);

        redirect.addFlashAttribute("dangerMsg", "Producto archivado correctamente");
        return "redirect:/productos/";
    }
}
