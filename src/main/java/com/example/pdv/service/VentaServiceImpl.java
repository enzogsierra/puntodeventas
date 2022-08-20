package com.example.pdv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pdv.model.Venta;
import com.example.pdv.repository.VentaRepository;


@Service
public class VentaServiceImpl implements IVentaService 
{
    @Autowired
    private VentaRepository ventaRepository;
    
    
    @Override
    @Transactional(readOnly = true)
    public List<Venta> all() 
    {
        return ventaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Venta> findById(Long id)
    {
        return ventaRepository.findById(id);
    }

    @Override
    public void save(Venta venta) 
    {
        ventaRepository.save(venta);
    }
}
