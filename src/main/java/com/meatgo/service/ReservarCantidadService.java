package com.meatgo.service;

import com.meatgo.dto.ReservarCantidadRequestDTO;
import com.meatgo.model.*;
import com.meatgo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class ReservarCantidadService {

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Transactional
    public ReservarCantidadResponse reservarCantidad(ReservarCantidadRequestDTO request) {

        Sesion sesion = sesionRepository.findByToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        Integer usuarioId = sesion.getUsuario().getId();

        Producto producto = productoRepository.findById(request.getProducto_id())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        BigDecimal reservado = producto.getReservadoCestas() != null ? producto.getReservadoCestas() : BigDecimal.ZERO;
        BigDecimal enPreparacion = producto.getEnPreparacion() != null ? producto.getEnPreparacion() : BigDecimal.ZERO;
        BigDecimal disponible = producto.getStock().subtract(reservado).subtract(enPreparacion);

        BigDecimal cantidadDecimal = BigDecimal.valueOf(request.getCantidad());

        if (disponible.compareTo(cantidadDecimal) < 0) {
            throw new RuntimeException("Stock insuficiente. Disponible: " + disponible.doubleValue());
        }

        Pedido pedido = pedidoRepository.findByUsuarioIdAndEstado(usuarioId, "solicitud")
                .orElseGet(() -> {
                    Pedido nuevoPedido = new Pedido();
                    nuevoPedido.setUsuario(sesion.getUsuario());
                    nuevoPedido.setEstado("solicitud");
                    nuevoPedido.setTotal(BigDecimal.ZERO);
                    return pedidoRepository.save(nuevoPedido);
                });

        BigDecimal subtotal = cantidadDecimal.multiply(producto.getPrecio());

        DetallePedido detalle = new DetallePedido();
        detalle.setPedido(pedido);
        detalle.setProducto(producto);
        detalle.setCantidad(cantidadDecimal); 
        detalle.setPrecio(producto.getPrecio());
        detalle.setSubtotal(subtotal);
        detalle.setFecha(LocalDateTime.now());
        detallePedidoRepository.save(detalle);

        producto.setStock(producto.getStock().subtract(cantidadDecimal));
        producto.setReservadoCestas(reservado.add(cantidadDecimal));

        pedido.setTotal(pedido.getTotal().add(subtotal));
        pedidoRepository.save(pedido);

        return new ReservarCantidadResponse(
                "ok",
                producto.getNombre(),
                producto.getStock().doubleValue(),
                producto.getReservadoCestas().doubleValue(),
                subtotal.doubleValue(),
                pedido.getId()
        );
    }

    public static class ReservarCantidadResponse {
        private String status;
        private String producto;
        private double stock_restante;
        private double reservado_cestas;
        private double subtotal;
        private Integer pedido_id;

        public ReservarCantidadResponse(String status, String producto, double stock_restante,
                                        double reservado_cestas, double subtotal, Integer pedido_id) {
            this.status = status;
            this.producto = producto;
            this.stock_restante = stock_restante;
            this.reservado_cestas = reservado_cestas;
            this.subtotal = subtotal;
            this.pedido_id = pedido_id;
        }

        public String getStatus() { return status; }
        public String getProducto() { return producto; }
        public double getStock_restante() { return stock_restante; }
        public double getReservado_cestas() { return reservado_cestas; }
        public double getSubtotal() { return subtotal; }
        public Integer getPedido_id() { return pedido_id; }

        public void setStatus(String status) { this.status = status; }
        public void setProducto(String producto) { this.producto = producto; }
        public void setStock_restante(double stock_restante) { this.stock_restante = stock_restante; }
        public void setReservado_cestas(double reservado_cestas) { this.reservado_cestas = reservado_cestas; }
        public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
        public void setPedido_id(Integer pedido_id) { this.pedido_id = pedido_id; }
    }
}

