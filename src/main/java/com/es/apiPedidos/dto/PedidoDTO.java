package com.es.apiPedidos.dto;

import com.es.apiPedidos.model.Producto;
import com.es.apiPedidos.model.Usuario;

import java.util.Date;
import java.util.List;

public class PedidoDTO {
    private String destino; // Dirección o lugar de entrega del pedido
    private Date fechaPedido; // Fecha en la que se realizó el pedido
    private Date fechaLlegada; // Fecha estimada de llegada del pedido
    private double importe; // Importe total del pedido
    private Long idUsuario; // ID del usuario que realiza el pedido
    private List<Long> productos; // Lista de IDs de los productos del pedido

    // Constructor sin parámetros para frameworks como Jackson
    public PedidoDTO() {}

    // Constructor con parámetros
    public PedidoDTO(String destino, Date fechaPedido, Date fechaLlegada, double importe, Long idUsuario, List<Long> productos) {
        this.destino = destino;
        this.fechaPedido = fechaPedido;
        this.fechaLlegada = fechaLlegada;
        this.importe = importe;
        this.idUsuario = idUsuario;
        this.productos = productos;
    }

    // Getters y Setters
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

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Long> getProductos() {
        return productos;
    }

    public void setProductos(List<Long> productos) {
        this.productos = productos;
    }
}