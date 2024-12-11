package com.es.apiPedidos.error;

public class ErrorMessage {
    private String mensaje;
    private String uri;
    public ErrorMessage(String mensaje, String uri) {
        this.mensaje = mensaje;
        this.uri = uri;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
