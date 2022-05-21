package com.example.pdv.service;

import java.util.List;
import java.util.Optional;

import com.example.pdv.model.Producto;

public interface IProductoService 
{
    List<Producto> all();
    Optional<Producto> findById(Integer id);
    List<Producto> search(String search);

    void save(Producto producto);
    void update(Producto producto);
    void delete(Producto producto);
}
