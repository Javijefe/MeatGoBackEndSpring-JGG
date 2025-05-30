package com.meatgo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "detalle_pedido",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"pedido_id", "producto_id"})
       }
)
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_pedido")
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime fecha = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false, foreignKey = @ForeignKey(name = "fk_detalle_pedido_pedido"))
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false, foreignKey = @ForeignKey(name = "fk_detalle_pedido_producto"))
    private Producto producto;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cantidad;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
