package com.es.apiPedidos.error.exception;

public class NoContentException extends RuntimeException {
  private static final String DESCRIPCION = "No content Exception (204)";

  public NoContentException(String mensaje) {
    super(DESCRIPCION + ". " + mensaje);
  }
}
