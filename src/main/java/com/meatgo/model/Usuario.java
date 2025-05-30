package com.meatgo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuarios")
    private Integer id;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contraseña;

    @Column(name = "creado_en", nullable = false, updatable = false)
    private Timestamp creadoEn;

    @ManyToOne
    @JoinColumn(name = "rol_id", referencedColumnName = "id_roles", foreignKey = @ForeignKey(name = "fk_usuario_rol"))
    private Rol rol;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Sesion sesion;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Timestamp getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(Timestamp creadoEn) {
        this.creadoEn = creadoEn;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }
}
