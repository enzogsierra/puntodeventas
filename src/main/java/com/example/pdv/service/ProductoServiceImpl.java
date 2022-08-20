package com.example.pdv.service;

import java.util.List;
import java.util.Optional;

import com.example.pdv.model.Producto;
import com.example.pdv.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductoServiceImpl implements IProductoService 
{
    @Autowired
    private ProductoRepository productoRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Producto> all() {
        return productoRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> findById(Integer id) {
        return productoRepository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Producto> search(String criteria) 
    {
        return productoRepository.findByCriteria(criteria);
    }

    @Override
    public void save(Producto producto) {
        productoRepository.save(producto);
    }
    
    @Override
    public void update(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void delete(Producto producto) {
        productoRepository.delete(producto);
    }
}
