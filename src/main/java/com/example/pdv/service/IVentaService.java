package com.example.pdv.service;

import java.util.List;
import java.util.Optional;

import com.example.pdv.model.Venta;


public interface IVentaService 
{
    public List<Venta> all();
    public Optional<Venta> findById(Long id);
    public void save(Venta venta);
}
