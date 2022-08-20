package com.example.pdv.repository;

import com.example.pdv.model.Producto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>
{
    @Query("SELECT p FROM Producto p WHERE p.descripcion LIKE %:criteria% OR p.codigoBarras LIKE %:criteria% AND p.activo = true")
    List<Producto> findByCriteria(@Param("criteria") String criteria);
}
