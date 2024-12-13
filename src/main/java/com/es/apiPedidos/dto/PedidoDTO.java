package com.es.apiPedidos.dto;

import com.es.apiPedidos.model.Producto;
import com.es.apiPedidos.model.Usuario;

import java.util.Date;
import java.util.List;

public class PedidoDTO {
    private String destino;
    private Date fechaPedido;
    private Date fechaLlegada;
    private double importe;
    private Usuario usuario;
    private List<String> productos;

    public PedidoDTO() {}

    public PedidoDTO(String destino, Date fechaPedido, Date fechaLlegada, double importe, Usuario usuario, List<String> productos) {
        this.destino = destino;
        this.fechaPedido = fechaPedido;
        this.fechaLlegada = fechaLlegada;
        this.importe = importe;
        this.usuario = usuario;
        this.productos = productos;
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

    public List<String> getProductos() {
        return productos;
    }

    public void setProductos(List<String> productos) {
        this.productos = productos;
    }
}
