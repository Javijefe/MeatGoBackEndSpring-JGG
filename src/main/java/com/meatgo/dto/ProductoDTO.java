package com.meatgo.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductoDTO {
    private Integer id;
    private String nombre;
    private BigDecimal precio;
    private BigDecimal stock;
    private BigDecimal reservadoCestas;
    private BigDecimal enPreparacion;
    private String foto;
    private Integer tipoCarneId;
    private String tipoCarneNombre;

    public ProductoDTO() {}

    public ProductoDTO(Integer id, String nombre, BigDecimal precio, BigDecimal stock,
                       BigDecimal reservadoCestas, BigDecimal enPreparacion, String foto,
                       Integer tipoCarneId, String tipoCarneNombre) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.reservadoCestas = reservadoCestas;
        this.enPreparacion = enPreparacion;
        this.foto = foto;
        this.tipoCarneId = tipoCarneId;
        this.tipoCarneNombre = tipoCarneNombre;
    }

    @JsonProperty("id_productos")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    @JsonProperty("reservado_cestas")
    public BigDecimal getReservadoCestas() {
        return reservadoCestas;
    }

    public void setReservadoCestas(BigDecimal reservadoCestas) {
        this.reservadoCestas = reservadoCestas;
    }

    @JsonProperty("en_preparacion")
    public BigDecimal getEnPreparacion() {
        return enPreparacion;
    }

    public void setEnPreparacion(BigDecimal enPreparacion) {
        this.enPreparacion = enPreparacion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @JsonProperty("tipo_id")
    public Integer getTipoCarneId() {
        return tipoCarneId;
    }

    public void setTipoCarneId(Integer tipoCarneId) {
        this.tipoCarneId = tipoCarneId;
    }

    public String getTipoCarneNombre() {
        return tipoCarneNombre;
    }

    public void setTipoCarneNombre(String tipoCarneNombre) {
        this.tipoCarneNombre = tipoCarneNombre;
    }
}
