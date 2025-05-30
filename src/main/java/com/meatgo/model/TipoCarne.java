package com.meatgo.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;

@Entity
@Table(name = "tipos_carne")
public class TipoCarne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipos_carne")
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "tipo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Producto> productos;

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

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
