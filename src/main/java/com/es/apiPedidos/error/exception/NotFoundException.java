package com.es.apiPedidos.error.exception;

public class NotFoundException extends RuntimeException {
    private static final String DESCRIPCION = "Not Found (404)";
    public NotFoundException(String message) {
        super(DESCRIPCION +". "+ message);
    }
}
