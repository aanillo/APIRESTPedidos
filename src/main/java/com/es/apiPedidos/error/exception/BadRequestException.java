package com.es.apiPedidos.error.exception;

public class BadRequestException extends RuntimeException {

    private static final String DESCRIPCION = "Bad Request (400)";

    public BadRequestException(String message) {
        super(DESCRIPCION +". "+ message);
    }
}
