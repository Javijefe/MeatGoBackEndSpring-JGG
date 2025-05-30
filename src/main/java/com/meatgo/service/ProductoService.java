package com.meatgo.service;

import com.meatgo.dto.ProductoDTO;
import com.meatgo.model.Producto;
import com.meatgo.model.TipoCarne;
import com.meatgo.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<ProductoDTO> obtenerTodosDTO() {
        List<Producto> productos = productoRepository.findAll();
        List<ProductoDTO> productosDTO = new ArrayList<>();

        for (Producto p : productos) {
            ProductoDTO dto = new ProductoDTO();
            dto.setId(p.getId());
            dto.setNombre(p.getNombre());
            dto.setPrecio(p.getPrecio());
            dto.setStock(p.getStock());
            dto.setReservadoCestas(p.getReservadoCestas());
            dto.setEnPreparacion(p.getEnPreparacion());
            dto.setFoto(p.getFoto());

            TipoCarne tipo = p.getTipo();
            if (tipo != null) {
                dto.setTipoCarneId(tipo.getId());
                dto.setTipoCarneNombre(tipo.getNombre());
            }

            productosDTO.add(dto);
        }

        return productosDTO;
    }
}
