package com.es.apiPedidos.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "destino", nullable = false)
    private String destino;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_pedido", nullable = false)
    private Date fechaPedido;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_llegada", nullable = false)
    private Date fechaLlegada;

    @Column(name = "importe", nullable = false)
    private double importe;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "pedido_producto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos;

    public Pedido() {}

    public Pedido(String destino, Date fechaPedido, Date fechaLlegada, double importe) {
        this.destino = destino;
        this.fechaPedido = fechaPedido;
        this.fechaLlegada = fechaLlegada;
        this.importe = importe;
    }

    public Pedido(Long id, String destino, Date fechaPedido, Date fechaLlegada, double importe) {
        this.id = id;
        this.destino = destino;
        this.fechaPedido = fechaPedido;
        this.fechaLlegada = fechaLlegada;
        this.importe = importe;
    }

    public Pedido(String destino, Date fechaPedido, Date fechaLlegada, double importe, Usuario usuario, List<Producto> productos) {
        this.destino = destino;
        this.fechaPedido = fechaPedido;
        this.fechaLlegada = fechaLlegada;
        this.importe = importe;
        this.usuario = usuario;
        this.productos = productos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
