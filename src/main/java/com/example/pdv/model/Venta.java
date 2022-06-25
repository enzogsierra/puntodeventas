package com.example.pdv.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "ventas")
public class Venta 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int nroFactura;

    @NotNull(message = "La fecha es requerida")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm z")
    private LocalDateTime fecha;

    @Size(max = 64, message = "La descripción no debe superar los 64 caracteres")
    private String descripcion;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ventaId")
    private List<LineaVenta> lineas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;


    public Venta() 
    {
        lineas = new ArrayList<>();
        fecha = LocalDateTime.now();
        descripcion = "-";
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(int nroFactura) {
        this.nroFactura = nroFactura;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<LineaVenta> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaVenta> lineas) {
        this.lineas = lineas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    //
    public void agregarLinea(LineaVenta linea) {
        lineas.add(linea);
    }

    public double getTotal()
    {
        double total = 0.0d;

        for(LineaVenta linea: lineas)
        {
            total += linea.getImporte();
        }
        return total;
    }
}
