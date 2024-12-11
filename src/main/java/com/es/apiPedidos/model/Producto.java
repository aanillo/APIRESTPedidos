package com.es.apiPedidos.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Column(name = "stock")
    private int stock;

    @Column(name = "precio")
    private double precio;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToMany(mappedBy = "productos")
    private List<Pedido> pedidos;

    public Producto() {}

    public Producto(String nombre, String categoria, int stock, double precio, String descripcion) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.stock = stock;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public Producto(Long id, String nombre, String categoria, int stock, double precio, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.stock = stock;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public Producto(String nombre, String categoria, int stock, double precio, String descripcion, List<Pedido> pedidos) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.stock = stock;
        this.precio = precio;
        this.descripcion = descripcion;
        this.pedidos = pedidos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
