package com.meatgo.service;

import com.meatgo.dto.AdminTodosPedidosRequestDTO;
import com.meatgo.dto.AdminTodosPedidosResponseDTO;
import com.meatgo.dto.AdminTodosPedidosResponseDTO.PedidoResponseDTO;
import com.meatgo.model.Pedido;
import com.meatgo.model.Sesion;
import com.meatgo.model.Usuario;
import com.meatgo.repository.PedidoRepository;
import com.meatgo.repository.SesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminTodosPedidosService {

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public AdminTodosPedidosResponseDTO verTodosPedidos(AdminTodosPedidosRequestDTO request) {
        Optional<Sesion> sesionOpt = sesionRepository.findByToken(request.getToken());
        if (sesionOpt.isEmpty()) {
            throw new RuntimeException("Token inválido");
        }

        Usuario usuario = sesionOpt.get().getUsuario();
        if (usuario == null || usuario.getRol() == null || usuario.getRol().getId() != 1) {
            throw new RuntimeException("No tienes permisos para acceder como admin");
        }

        List<Pedido> pedidos = pedidoRepository.findAll();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<PedidoResponseDTO> pedidosDTO = new ArrayList<>();
        for (Pedido p : pedidos) {
            PedidoResponseDTO dto = new PedidoResponseDTO(
                p.getId(),
                p.getUsuario().getId(),
                p.getFecha().format(formatter),
                p.getEstado()
            );
            pedidosDTO.add(dto);
        }

        return new AdminTodosPedidosResponseDTO(pedidosDTO);
    }
}
