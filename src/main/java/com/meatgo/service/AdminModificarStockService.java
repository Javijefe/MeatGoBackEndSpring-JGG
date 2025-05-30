package com.meatgo.service;

import com.meatgo.dto.AumentarStockRequestDTO;
import com.meatgo.dto.AumentarStockResponseDTO;
import com.meatgo.model.Producto;
import com.meatgo.model.Sesion;
import com.meatgo.model.Usuario;
import com.meatgo.repository.ProductoRepository;
import com.meatgo.repository.SesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AdminModificarStockService {

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public AumentarStockResponseDTO aumentarStockProducto(AumentarStockRequestDTO request) {

        Optional<Sesion> sesionOpt = sesionRepository.findByToken(request.getToken());
        if (sesionOpt.isEmpty()) {
            return new AumentarStockResponseDTO("error", "Token inválido", 0);
        }
        Sesion sesion = sesionOpt.get();

        Usuario usuario = sesion.getUsuario();
        if (usuario == null || usuario.getRol() == null || usuario.getRol().getId() != 1) {
            return new AumentarStockResponseDTO("error", "No tienes permisos para acceder como admin", 0);
        }

        Optional<Producto> productoOpt = productoRepository.findById(request.getProductoId());
        if (productoOpt.isEmpty()) {
            return new AumentarStockResponseDTO("error", "Producto no encontrado", 0);
        }
        Producto producto = productoOpt.get();

        BigDecimal cantidad = BigDecimal.valueOf(request.getCantidad());
        BigDecimal nuevoStock = producto.getStock().add(cantidad);

        producto.setStock(nuevoStock);
        productoRepository.save(producto);

        return new AumentarStockResponseDTO(
                "ok",
                "Se aumentó el stock del producto " + producto.getNombre(),
                nuevoStock.doubleValue()  
        );
    }
}
