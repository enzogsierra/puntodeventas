package com.example.pdv.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pdv.model.Venta;


@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> 
{
    
}
