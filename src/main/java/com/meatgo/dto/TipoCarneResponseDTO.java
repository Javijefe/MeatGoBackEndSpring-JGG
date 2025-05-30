package com.meatgo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TipoCarneResponseDTO {
     
    @JsonProperty("tipos_carne")
    private List<TipoCarneDTO> tipos_carne;

    public TipoCarneResponseDTO() {}

    public TipoCarneResponseDTO(List<TipoCarneDTO> tipos_carne) {
        this.tipos_carne = tipos_carne;
    }   

    public List<TipoCarneDTO> getTipos_carne() {
        return tipos_carne;
    }   

    public void setTipos_carne(List<TipoCarneDTO> tipos_carne) {
        this.tipos_carne = tipos_carne;
    }
}
