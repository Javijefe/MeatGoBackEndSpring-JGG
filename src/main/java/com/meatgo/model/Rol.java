package com.meatgo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_roles")
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Usuario> usuarios;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
