package com.example.pdv.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.pdv.model.LineaVenta;
import com.example.pdv.model.Producto;
import com.example.pdv.model.Venta;
import com.example.pdv.service.IProductoService;
import com.example.pdv.service.IVentaService;


@Controller
@RequestMapping("/ventas")
@SessionAttributes("venta")
public class VentaController 
{
    @Autowired
    private IVentaService ventaService;

    @Autowired
    private IProductoService productoService;


    @GetMapping(value = {"", "/"})
    public String home(Model model)
    {
        model.addAttribute("title", "Listado de ventas");
        model.addAttribute("ventas", ventaService.all());
        return "ventas/index";
    }


    @GetMapping("/nuevo")
    public String nuevo(Model model)
    {
        Venta venta = new Venta();

        model.addAttribute("venta", venta);
        return "ventas/form";
    }

    @PostMapping("/nuevo")
    public String nuevo_POST
    (
        @Valid Venta venta, 
        BindingResult result, 
        @RequestParam(name = "item_id[]", required = true) List<String> items, 
        @RequestParam(name = "cantidad[]", required = true) List<String> cantidad, 
        Model model, 
        RedirectAttributes flash, 
        SessionStatus status)
    {
        // Verificar errores
        if(result.hasErrors())
        {
            model.addAttribute("dangerMsg", "Verifica los campos");
            return "ventas/form";
        }

        if(items == null || items.size() == 0)
        {
            model.addAttribute("warningMsg", "Debes añadir productos a la venta");
            return "ventas/form";
        }

        // Si no hay errores
        LineaVenta linea;
        Producto producto;

        // Crear las líneas de la venta
        for(int i = 0; i < items.size(); i++)
        {
            linea = new LineaVenta();
            Integer id = Integer.parseInt(items.get(i));
            Integer amount = Integer.parseInt(cantidad.get(i));
            producto = productoService.findById(id).get();

            linea.setProducto(producto);
            linea.setCantidad(amount);
            venta.agregarLinea(linea);
        }

        ventaService.save(venta);
        status.setComplete();

        flash.addFlashAttribute("successMsg", "Venta registrada");
        return "redirect: /ventas";
    }

    @GetMapping(value = "/buscar/{query}", produces = {"application/json"})
    public @ResponseBody List<Producto> buscarProductos(@PathVariable("query") String query)
    {
        return productoService.search(query);
    }
}
