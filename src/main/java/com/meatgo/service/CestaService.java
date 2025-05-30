package com.meatgo.service;

import com.meatgo.dto.CestaRequestDTO;
import com.meatgo.dto.CestaResponseDTO;
import com.meatgo.dto.CestaResponseDTO.ProductoCestaDTO;
import com.meatgo.dto.EliminarProductoCestaRequestDTO;
import com.meatgo.model.DetallePedido;
import com.meatgo.model.Pedido;
import com.meatgo.model.Producto;
import com.meatgo.model.Sesion;
import com.meatgo.repository.DetallePedidoRepository;
import com.meatgo.repository.PedidoRepository;
import com.meatgo.repository.ProductoRepository;
import com.meatgo.repository.SesionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.*;

import static org.springframework.http.HttpStatus.*;

@Service
public class CestaService {

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public CestaResponseDTO obtenerProductosCesta(CestaRequestDTO request) {
        Optional<Sesion> sesionOpt = sesionRepository.findByToken(request.getToken());
        if (sesionOpt.isEmpty()) {
            throw new ResponseStatusException(UNAUTHORIZED, "Token inválido");
        }

        Integer usuarioId = sesionOpt.get().getUsuario().getId();

        Optional<Pedido> pedidoOpt = pedidoRepository.findByUsuarioIdAndEstado(usuarioId, "solicitud");
        if (pedidoOpt.isEmpty()) {
            return new CestaResponseDTO(new ArrayList<>());
        }

        Pedido pedido = pedidoOpt.get();
        List<DetallePedido> detalles = detallePedidoRepository.findByPedidoId(pedido.getId());

        List<ProductoCestaDTO> productos = new ArrayList<>();
        for (DetallePedido detalle : detalles) {
            ProductoCestaDTO dto = new ProductoCestaDTO(
                    detalle.getId(),
                    detalle.getProducto().getNombre(),
                    detalle.getCantidad().doubleValue(),
                    detalle.getSubtotal().doubleValue()
            );
            productos.add(dto);
        }

        return new CestaResponseDTO(productos);
    }

    @Transactional
    public CestaResponseDTO eliminarProductoCompletoDeCesta(EliminarProductoCestaRequestDTO request) {
        Optional<Sesion> sesionOpt = sesionRepository.findByToken(request.getToken());
        if (sesionOpt.isEmpty()) {
            throw new ResponseStatusException(UNAUTHORIZED, "Token inválido");
        }

        Integer usuarioId = sesionOpt.get().getUsuario().getId();

        List<DetallePedido> detalles = detallePedidoRepository
                .findByIdAndPedidoUsuarioIdAndPedidoEstado(request.getId_detalle(), usuarioId, "solicitud");

        if (detalles.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Producto no está en la cesta");
        }

        BigDecimal cantidadTotal = BigDecimal.ZERO;
        BigDecimal subtotalTotal = BigDecimal.ZERO;
        Integer productoId = null;
        Set<Pedido> pedidosAfectados = new HashSet<>();

        for (DetallePedido detalle : detalles) {
            cantidadTotal = cantidadTotal.add(detalle.getCantidad());
            subtotalTotal = subtotalTotal.add(detalle.getSubtotal());
            productoId = detalle.getProducto().getId();
            pedidosAfectados.add(detalle.getPedido());
            detallePedidoRepository.delete(detalle);
        }

        if (productoId == null) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, "Error al identificar producto");
        }

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Producto no encontrado"));

        producto.setStock(producto.getStock().add(cantidadTotal));
        producto.setReservadoCestas(producto.getReservadoCestas().subtract(cantidadTotal));
        productoRepository.save(producto);

        for (Pedido pedido : pedidosAfectados) {
            pedido.setTotal(pedido.getTotal().subtract(subtotalTotal));
        }

        return new CestaResponseDTO(new ArrayList<>());
    }
}
