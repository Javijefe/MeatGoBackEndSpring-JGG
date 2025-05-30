package com.meatgo.repository;

import com.meatgo.model.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
    List<DetallePedido> findByPedidoId(Integer pedidoId);
    List<DetallePedido> findByIdAndPedidoUsuarioIdAndPedidoEstado(Integer id, Integer usuarioId, String estado);
}
