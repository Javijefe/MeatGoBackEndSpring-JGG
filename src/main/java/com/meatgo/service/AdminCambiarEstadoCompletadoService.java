package com.meatgo.service;

import com.meatgo.dto.AdminCambiarEstadoCompletadoRequestDTO;
import com.meatgo.dto.AdminCambiarEstadoCompletadoResponseDTO;
import com.meatgo.model.*;
import com.meatgo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AdminCambiarEstadoCompletadoService {

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public AdminCambiarEstadoCompletadoResponseDTO cambiarEstado(AdminCambiarEstadoCompletadoRequestDTO request) {

        Sesion sesion = sesionRepository.findByToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        Usuario usuario = usuarioRepository.findById(sesion.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (usuario.getRol() == null || usuario.getRol().getId() != 1) {
            throw new RuntimeException("No tienes permisos para acceder como admin");
        }

        Pedido pedido = pedidoRepository.findById(request.getId_pedido())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        String estado = pedido.getEstado();
        if (!estado.equals("solicitud") && !estado.equals("pendiente")) {
            throw new RuntimeException("El estado del pedido no es 'solicitud' ni 'pendiente'");
        }

        pedido.setEstado("completado");
        pedidoRepository.save(pedido);

        List<DetallePedido> detalles = detallePedidoRepository.findByPedidoId(pedido.getId());

        for (DetallePedido detalle : detalles) {
            Producto producto = detalle.getProducto();
            if (producto != null) {
                BigDecimal reservadoActual = producto.getReservadoCestas() != null ? producto.getReservadoCestas() : BigDecimal.ZERO;
                BigDecimal enPreparacionActual = producto.getEnPreparacion() != null ? producto.getEnPreparacion() : BigDecimal.ZERO;

                BigDecimal cantidadDetalle = detalle.getCantidad();

                BigDecimal nuevoReservado = reservadoActual.subtract(cantidadDetalle);
                BigDecimal nuevoEnPreparacion = enPreparacionActual.add(cantidadDetalle);

                producto.setReservadoCestas(nuevoReservado.max(BigDecimal.ZERO));
                producto.setEnPreparacion(nuevoEnPreparacion);

                productoRepository.save(producto);
            }
        }

        return new AdminCambiarEstadoCompletadoResponseDTO(
                "ok",
                "El estado del pedido " + pedido.getId() + " ha sido cambiado a 'completado' y productos actualizados a 'en_preparacion'."
        );
    }
}
