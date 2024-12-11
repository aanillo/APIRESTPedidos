package com.es.apiPedidos.dto;

import com.es.apiPedidos.model.Producto;
import com.es.apiPedidos.model.Usuario;

import java.util.Date;
import java.util.List;

public class PedidoDTO {
    private String destino;
    private Date fechaPedido;
    private Date fechaLlgada;
    private double importe;
    private Usuario usuario;
    private List<Producto> productos;

    public PedidoDTO() {}

    public PedidoDTO(String destino, Date fechaPedido, Date fechaLlgada, double importe, Usuario usuario, List<Producto> productos) {
        this.destino = destino;
        this.fechaPedido = fechaPedido;
        this.fechaLlgada = fechaLlgada;
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

    public Date getFechaLlgada() {
        return fechaLlgada;
    }

    public void setFechaLlgada(Date fechaLlgada) {
        this.fechaLlgada = fechaLlgada;
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
