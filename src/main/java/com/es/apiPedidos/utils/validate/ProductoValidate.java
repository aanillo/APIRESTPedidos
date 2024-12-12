package com.es.apiPedidos.utils.validate;

public class ProductoValidate {

    public static String isValidName(String nombre) {
        if(nombre == null || nombre.isEmpty()) {
            return "El nombre del producto no puede ser nulo";
        }
        return "";
    }

    public static String isValidCategory(String categoria) {
        if(categoria == null || categoria.isEmpty()) {
            return "El nombre de la categoría no puede ser nulo";
        }
        return "";
    }


    public static String isValidStock(int stock) {
        if(stock < 0) {
            return "La cantidad del stock debe ser mayor que 0";
        }
        return "";
    }

    public static String isValidPrice(double precio) {
        if(precio <= 0) {
            return "El precio del producto debe ser mayor que 0";
        }
        return "";
    }


}
