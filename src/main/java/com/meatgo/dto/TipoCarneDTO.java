package com.meatgo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TipoCarneDTO {
    
    private Integer id_tipos_carne;

    private String nombre;

    public TipoCarneDTO() {}

    public TipoCarneDTO(Integer id_tipos_carne, String nombre) {
        this.id_tipos_carne = id_tipos_carne;
        this.nombre = nombre;
    }
    
    @JsonProperty("id_tipos_carne")
    public Integer getIdTiposCarne() {
        return id_tipos_carne;
    }

    public void setIdTiposCarne(Integer id_tipos_carne) {
        this.id_tipos_carne = id_tipos_carne;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
