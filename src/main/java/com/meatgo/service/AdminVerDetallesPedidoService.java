package com.meatgo.service;

import com.meatgo.dto.VerDetallesPedidoRequestDTO;
import com.meatgo.dto.VerDetallesPedidoResponseDTO;
import com.meatgo.dto.VerDetallesPedidoResponseDTO.DetallePedidoDTO;
import com.meatgo.model.DetallePedido;
import com.meatgo.model.Pedido;
import com.meatgo.model.Sesion;
import com.meatgo.model.Usuario;
import com.meatgo.repository.DetallePedidoRepository;
import com.meatgo.repository.PedidoRepository;
import com.meatgo.repository.SesionRepository;
import com.meatgo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminVerDetallesPedidoService {

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    public VerDetallesPedidoResponseDTO verDetallesPedido(VerDetallesPedidoRequestDTO request) {
        Optional<Sesion> sesionOpt = sesionRepository.findByToken(request.getToken());
        if (sesionOpt.isEmpty()) {
            throw new RuntimeException("Token inválido");
        }

        Usuario usuario = sesionRepository.findByToken(request.getToken())
                .map(Sesion::getUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (usuario == null || usuario.getRol() == null || usuario.getRol().getId() != 1) {
            throw new RuntimeException("No tienes permisos para acceder como admin");
        }

        Optional<Pedido> pedidoOpt = pedidoRepository.findById(request.getIdPedido());
        if (pedidoOpt.isEmpty()) {
            throw new RuntimeException("Pedido no encontrado");
        }

        List<DetallePedido> detalles = detallePedidoRepository.findByPedidoId(request.getIdPedido());

        List<DetallePedidoDTO> detallesResponse = new ArrayList<>();

        for (DetallePedido detalle : detalles) {
            int productoId = detalle.getProducto().getId();
            int cantidad = detalle.getCantidad().intValue();
            double precio = detalle.getPrecio().doubleValue();
            double subtotal = detalle.getSubtotal().doubleValue();

            DetallePedidoDTO detalleDTO = new DetallePedidoDTO(productoId, cantidad, precio, subtotal);
            detallesResponse.add(detalleDTO);
        }

        return new VerDetallesPedidoResponseDTO("ok", detallesResponse);
    }
}
