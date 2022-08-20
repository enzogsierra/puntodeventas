package com.example.pdv.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Where;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;


@Entity
@Table(name = "productos")
@Where(clause = "activo = true")
public class Producto 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Size(min = 13, max = 13, message = "El código de barras debe ser de 13 caracteres")
    private String codigoBarras;

    @Column(length = 64)
    @NotBlank(message = "La descripción es requerida")
    private String descripcion;

    @NotNull(message = "El precio es requerido")
    @NumberFormat(pattern = "#,##0.00", style = Style.CURRENCY)
    private BigDecimal precio;

    @NotNull(message = "El stock es requerido")
    @Min(value = 1, message = "El stock no puede ser 0")
    private int stock;

    @NotNull(message = "La imagen es requerida")
    private String imagenUrl;

    @Column(columnDefinition = "bit default 1")
    private boolean activo;

    
    public Producto() 
    {
        activo = true;
    }

    public Producto(Integer id, String codigoBarras, String descripcion, BigDecimal precio, int stock, String imagenUrl, boolean activo) {
        this.id = id;
        this.codigoBarras = codigoBarras;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.imagenUrl = imagenUrl;
        this.activo = activo;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }


    @Override
    public String toString() {
        return "Producto [activo=" + activo + ", codigoBarras=" + codigoBarras + ", descripcion=" + descripcion
                + ", id=" + id + ", imagenUrl=" + imagenUrl + ", precio=" + precio + ", stock=" + stock + "]";
    }   
}
