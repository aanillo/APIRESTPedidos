package com.es.apiPedidos.dto;

public class UsuarioRegisterDTO {
    private String username;
    private String password1;
    private String password2;
    private String email;
    private String direccion;
    private String roles;


    public UsuarioRegisterDTO(){}

    public UsuarioRegisterDTO(String username, String password1, String password2, String email, String direccion, String roles) {
        this.username = username;
        this.password1 = password1;
        this.password2 = password2;
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

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
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
}
