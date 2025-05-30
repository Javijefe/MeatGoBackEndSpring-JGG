package com.meatgo.repository;

import com.meatgo.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    //alomejor cambiar a List
    Optional<Pedido> findByUsuarioIdAndEstado(Integer usuarioId, String estado);
}
