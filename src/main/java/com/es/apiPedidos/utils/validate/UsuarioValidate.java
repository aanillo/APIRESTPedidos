package com.es.apiPedidos.utils.validate;

public class UsuarioValidate {

    public static String isValidUsername(String username) {
        if(username == null || username.isEmpty()) {
            return "Username no puede estar vacío";
        } else if(username.length() < 3) {
            return "Username debe ser mayor a 3 caracteres";
        } else if(username.length() > 15) {
            return "Username debe ser menor de 15 caracteres";
        }
        return "";
    }

    public static String isValidDireccion(String direccion) {
        if(direccion == null || direccion.isEmpty()) {
            return "Dirección no puede ser nula";
        }
        return "";
    }

    public static String isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return "Email no puede ser nulo";
        }

        int atIndex = email.indexOf('@');
        if (atIndex < 3) {
            return "Tu cuenta de email debe ser mayor a 3 caracteres";
        }


        String domain = email.substring(email.lastIndexOf('.'));
        if (!(domain.equals(".com") || domain.equals(".es"))) {
            return "El dominio debe acabar en .com o .es";
        }


        String localPart = email.substring(0, atIndex);
        String domainPart = email.substring(atIndex + 1, email.lastIndexOf('.'));
        if (domainPart.isEmpty()) {
            return "El dominio debe acabar en .com o .es";
        }

        return "";
    }

    public static String isValidPassword(String password) {
        if (password == null) {
            return "La contraseña no puede estar vacía";
        } else if (password.length() <= 6) {
            return "La contraseña debe ser mayor a 6 caracteres";
        }

        boolean hasNumber = false;
        boolean hasSpecialChar = false;
        String specialChars = "!@#$%^&*()-+=<>?/{}~";

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (specialChars.indexOf(c) >= 0) {
                hasSpecialChar = true;
            }

            if (hasNumber && hasSpecialChar) {
                return "";
            }
        }

        return "La contraseña debe tener al menos un número y un carácter especial";
    }
}
