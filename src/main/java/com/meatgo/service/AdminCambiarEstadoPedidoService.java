package com.meatgo.service;

import com.meatgo.dto.AdminCambiarEstadoPedidoRequestDTO;
import com.meatgo.dto.AdminCambiarEstadoPedidoRequestDTO;
import com.meatgo.dto.AdminCambiarEstadoPedidoResponseDTO;
import com.meatgo.model.Pedido;
import com.meatgo.model.Sesion;
import com.meatgo.model.Usuario;
import com.meatgo.repository.PedidoRepository;
import com.meatgo.repository.SesionRepository;
import com.meatgo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminCambiarEstadoPedidoService {

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public AdminCambiarEstadoPedidoResponseDTO cambiarEstado(AdminCambiarEstadoPedidoRequestDTO request) {

        Sesion sesion = sesionRepository.findByToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        Usuario usuario = usuarioRepository.findById(sesion.getUsuario().getId())   
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (usuario.getRol().getId() != 1) {
            throw new RuntimeException("No tienes permisos para acceder como admin");
        }

        Pedido pedido = pedidoRepository.findById(request.getId_pedido())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        if (!"solicitud".equalsIgnoreCase(pedido.getEstado())) {
            throw new RuntimeException("El estado del pedido no es 'solicitud'");
        }

        pedido.setEstado("pendiente");
        pedidoRepository.save(pedido);

        return new AdminCambiarEstadoPedidoResponseDTO(
                "ok",
                "El estado del pedido " + pedido.getId() + " ha sido cambiado a 'pendiente'."
        );
    }
}
