package com.example.tpi.util;


/**
 * Clase que proporciona métodos para validar diferentes tipos de datos.
 */
public class Validaciones {

    /**
     * Método que valida si un usuario es válido.
     * Un usuario es válido si no es null, no está vacío y tiene más de 3 caracteres.
     * @param usuario El usuario a validar.
     * @return true si el usuario es válido, false en caso contrario.
     */
    public static boolean validarUsuario(String usuario) {
        // Un usuario es válido si no es null, no está vacío y tiene más de 3 caracteres.
        return usuario != null && !usuario.isEmpty() && usuario.length() > 3;
    }

    /**
     * Método que valida si un correo electrónico es válido.
     * Un correo electrónico es válido si cumple con el patrón de correo electrónico.
     * @param email El correo electrónico a validar.
     * @return true si el correo electrónico es válido, false en caso contrario.
     */
    public static boolean validarEmail(String email) {
        // Patrón de correo electrónico.
        String emailRegex = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";

        // Un correo electrónico es válido si no es null, tiene más de 3 caracteres y cumple con el patrón.
        return email != null && email.length() > 3 && email.matches(emailRegex);
    }

    /**
     * Método que valida si dos contraseñas son iguales y cumplen con los requisitos de contraseña.
     * @param pass La primera contraseña.
     * @param pass2 La segunda contraseña.
     * @return null si las contraseñas son iguales y cumplen con los requisitos, un mensaje de error en caso contrario.
     */
    public static String validarPass(String pass, String pass2) {
        // Si una de las contraseñas es null o vacía, devuelve un mensaje de error.
        if (pass == null || pass.isEmpty() || pass2 == null || pass2.isEmpty()) {
            return "La contraseña no puede estar vacia";
        }

        // Si las contraseñas no son iguales, devuelve un mensaje de error.
        if (!pass.equals(pass2)) {
            return "Las contraseñas no coinciden";
        }

        // Si la contraseña tiene menos de 6 caracteres, devuelve un mensaje de error.
        if (pass.length() < 6) {
            return "La contraseña debe tener al menos 6 caracteres";
        }

        // Si las contraseñas son iguales y cumplen con los requisitos, devuelve null.
        return null;
    }

    /**
     * Método que valida si una contraseña es válida.
     * Una contraseña es válida si no es null, no está vacía y tiene al menos 6 caracteres.
     * @param pass La contraseña a validar.
     * @return true si la contraseña es válida, false en caso contrario.
     */
    public static boolean controlarPassword(String pass) {
        // Una contraseña es válida si no es null, no está vacía y tiene al menos 6 caracteres.
        return (pass != null && !pass.isEmpty() && pass.length() >= 6);
    }
}