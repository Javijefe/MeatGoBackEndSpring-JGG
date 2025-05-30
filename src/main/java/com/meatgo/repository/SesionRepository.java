package com.meatgo.repository;

import com.meatgo.model.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SesionRepository extends JpaRepository<Sesion, Integer> {
    Optional<Sesion> findByToken(String token);
    Optional<Sesion> findByUsuarioId(Integer usuarioId);

    Optional<Sesion> findTopByUsuarioIdOrderByIdDesc(Integer usuarioId);
}

