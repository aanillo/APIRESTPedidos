package com.es.apiPedidos.utils.validate;

import java.util.Date;

public class PedidoValidate {

    public static String isValidDestiny(String destino) {
        if(destino == null || destino.isEmpty()) {
            return "El destino no puede ser nulo";
        }
        return "";
    }

    public static String isValidDate(Date fechaPedido) {
        if(fechaPedido == null) {
            return "La fecha no puede ser nula";
        }
        return "";
    }

    public static String isValidArriveDate(Date fechaLlegada) {
        if(fechaLlegada == null) {
            return "La fecha no puede ser nula";
        }
        return "";
    }

    public static String isValidAmount(double importe) {
        if(importe <= 0) {
            return "El importe no puede ser menor o igual que 0";
        }
        return "";
    }
}
