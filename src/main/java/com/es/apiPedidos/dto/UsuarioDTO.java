package com.es.apiPedidos.dto;

public class UsuarioDTO {
    private String username;
    private String password;
    private String email;
    private String direccion;
    private String[] roles;

    public UsuarioDTO(){}

    public UsuarioDTO(String username, String password, String email, String direccion, String[] roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.direccion = direccion;
        this.roles = roles;
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

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
