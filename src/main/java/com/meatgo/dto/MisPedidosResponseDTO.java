package com.meatgo.dto;

import com.meatgo.dto.CestaResponseDTO.ProductoCestaDTO;
import java.util.List;

public class MisPedidosResponseDTO {
    private Integer id_pedido;
    private String estado;
    private List<ProductoCestaDTO> productos;
    private Float total;

    public MisPedidosResponseDTO() {
    }

    public MisPedidosResponseDTO(Integer id_pedido, String estado, List<ProductoCestaDTO> productos, Float total) {
        this.id_pedido = id_pedido;
        this.estado = estado;
        this.productos = productos;
        this.total = total;
    }

    public Integer getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<ProductoCestaDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoCestaDTO> productos) {
        this.productos = productos;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
