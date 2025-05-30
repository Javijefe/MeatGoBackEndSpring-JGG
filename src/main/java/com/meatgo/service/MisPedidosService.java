package com.meatgo.service;

import com.meatgo.dto.CestaResponseDTO;
import com.meatgo.dto.MisPedidosRequestDTO;
import com.meatgo.dto.MisPedidosResponseDTO;
import com.meatgo.dto.CestaResponseDTO.ProductoCestaDTO;
import com.meatgo.model.DetallePedido;
import com.meatgo.model.Pedido;
import com.meatgo.model.Sesion;
import com.meatgo.repository.DetallePedidoRepository;
import com.meatgo.repository.PedidoRepository;
import com.meatgo.repository.SesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
public class MisPedidosService {

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    public MisPedidosResponseDTO verSolicitud(MisPedidosRequestDTO request) {

        Optional<Sesion> sesionOpt = sesionRepository.findByToken(request.getToken());
        if (sesionOpt.isEmpty()) {
            throw new ResponseStatusException(UNAUTHORIZED, "Token inválido");
        }

        Integer usuarioId = sesionOpt.get().getUsuario().getId();

        Optional<Pedido> pedidoOpt = pedidoRepository.findByUsuarioIdAndEstado(usuarioId, "pendiente");
        if (pedidoOpt.isEmpty()) {
            return new MisPedidosResponseDTO(null, null, new ArrayList<>(), 0f);
        }

        Pedido pedido = pedidoOpt.get();
        String estadoPedido = pedido.getEstado();

        List<ProductoCestaDTO> productos = new ArrayList<>();

        if (!estadoPedido.equals("solicitud") && !estadoPedido.equals("completado")) {
            List<DetallePedido> detalles = detallePedidoRepository.findByPedidoId(pedido.getId());
            for (DetallePedido detalle : detalles) {
                ProductoCestaDTO dto = new ProductoCestaDTO(
                        detalle.getId(),
                        detalle.getProducto().getNombre(),
                        detalle.getCantidad().doubleValue(),
                        detalle.getSubtotal().doubleValue()
                );
                productos.add(dto);
            }
        }

        return new MisPedidosResponseDTO(
                pedido.getId(),
                estadoPedido,
                productos,
                pedido.getTotal().floatValue()
        );
    }
}
