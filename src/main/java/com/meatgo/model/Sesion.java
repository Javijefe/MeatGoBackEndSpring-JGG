package com.meatgo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sesiones")
public class Sesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sesiones")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false, foreignKey = @ForeignKey(name = "fk_sesion_usuario"))
    private Usuario usuario;

    @Column(length = 30, nullable = false)
    private String token;

    @Column(name = "ultima_actividad", nullable = false)
    private LocalDateTime ultimaActividad = LocalDateTime.now();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getUltimaActividad() {
        return ultimaActividad;
    }

    public void setUltimaActividad(LocalDateTime ultimaActividad) {
        this.ultimaActividad = ultimaActividad;
    }
}
