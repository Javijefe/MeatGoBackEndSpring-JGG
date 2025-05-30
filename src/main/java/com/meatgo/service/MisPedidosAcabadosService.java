package com.meatgo.service;

import com.meatgo.dto.MisPedidosAcabadosRequestDTO;
import com.meatgo.dto.MisPedidosAcabadosResponseDTO;
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
public class MisPedidosAcabadosService {

    @Autowired
    private SesionRepository sesionRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    public MisPedidosAcabadosResponseDTO verPedidoCompletado(MisPedidosAcabadosRequestDTO request) {

        Optional<Sesion> sesionOpt = sesionRepository.findByToken(request.getToken());
        if (sesionOpt.isEmpty()) {
            throw new ResponseStatusException(UNAUTHORIZED, "Token inválido");
        }

        Integer usuarioId = sesionOpt.get().getUsuario().getId();

        Optional<Pedido> pedidoOpt = pedidoRepository.findByUsuarioIdAndEstado(usuarioId, "completado");
        if (pedidoOpt.isEmpty()) {
            return new MisPedidosAcabadosResponseDTO(null, null, new ArrayList<>(), 0f);
        }

        Pedido pedido = pedidoOpt.get();

        List<ProductoCestaDTO> productos = new ArrayList<>();

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

        return new MisPedidosAcabadosResponseDTO(
                pedido.getId(),
                pedido.getEstado(),
                productos,
                pedido.getTotal().floatValue()
        );
    }
}
