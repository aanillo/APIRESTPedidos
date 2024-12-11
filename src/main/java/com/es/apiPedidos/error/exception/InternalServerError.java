package com.es.apiPedidos.error.exception;

public class InternalServerError extends RuntimeException {
    private static final String DESCRIPCION = "Internal Server (500)";

    public InternalServerError(String message) {
        super(DESCRIPCION +". "+ message);
    }
}
