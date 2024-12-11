package com.es.apiPedidos.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "roles")
    private String roles;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    public Usuario() {}

    public Usuario(String username, String password, String email, String direccion, String roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.direccion = direccion;
        this.roles = roles;
    }

    public Usuario(Long id, String username, String password, String email, String direccion, String roles, List<Pedido> pedidos) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.direccion = direccion;
        this.roles = roles;
        this.pedidos = pedidos;
    }

    public Usuario(String username, String password, String email, String direccion, String roles, List<Pedido> pedidos) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.direccion = direccion;
        this.roles = roles;
        this.pedidos = pedidos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}