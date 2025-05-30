package com.meatgo.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_productos")
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "tipo_id", referencedColumnName = "id_tipos_carne", foreignKey = @ForeignKey(name = "fk_producto_tipo"))
    @JsonBackReference
    private TipoCarne tipo;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal stock;

    @Column(name = "reservado_cestas", precision = 10, scale = 2)
    private BigDecimal reservadoCestas;

    @Column(name = "en_preparacion", precision = 10, scale = 2)
    private BigDecimal enPreparacion;

    @Column(columnDefinition = "TEXT")
    private String foto;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detallesPedido;

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

    public TipoCarne getTipo() {
        return tipo;
    }

    public void setTipo(TipoCarne tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public BigDecimal getReservadoCestas() {
        return reservadoCestas;
    }

    public void setReservadoCestas(BigDecimal reservadoCestas) {
        this.reservadoCestas = reservadoCestas;
    }

    public BigDecimal getEnPreparacion() {
        return enPreparacion;
    }

    public void setEnPreparacion(BigDecimal enPreparacion) {
        this.enPreparacion = enPreparacion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<DetallePedido> getDetallesPedido() {
        return detallesPedido;
    }

    public void setDetallesPedido(List<DetallePedido> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }
}
