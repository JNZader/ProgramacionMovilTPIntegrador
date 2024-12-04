package com.example.tpi.model;

/**
 * Clase que representa un usuario en el sistema.
 * Esta clase contiene toda la información personal y preferencias de un usuario,
 * incluyendo sus credenciales de acceso e intereses.
 */
public class User {
    /** Identificador único del usuario */
    private String id;

    /** Nombre de usuario para el sistema */
    private String username;

    /** Correo electrónico del usuario */
    private String email;

    /** Contraseña del usuario (se recomienda almacenar de forma encriptada) */
    private String password;

    /** URL o ruta de la foto de perfil del usuario */
    private String fotoperfil;

    /** Array de intereses o preferencias del usuario */
    private String[] intereses;

    /**
     * Constructor por defecto.
     * Crea una nueva instancia de User sin inicializar sus campos.
     */
    public User() {
    }

    /**
     * Constructor que inicializa todos los campos de un usuario.
     *
     * @param id Identificador único del usuario
     * @param username Nombre de usuario
     * @param email Correo electrónico
     * @param password Contraseña
     * @param fotoperfil URL o ruta de la foto de perfil
     * @param intereses Array con los intereses del usuario
     */
    public User(String id, String username, String email, String password, String fotoperfil, String[] intereses) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.fotoperfil = fotoperfil;
        this.intereses = intereses;
    }
    /**
     * Constructor que inicializa de un usuario con solo 3 parametros.
     *
     * @param username Nombre de usuario
     * @param email Correo electrónico
     * @param password Contraseña
     */
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * Obtiene el ID del usuario.
     * @return El identificador único del usuario
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el ID del usuario.
     * @param id El nuevo identificador único para el usuario
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de usuario.
     * @return El nombre de usuario actual
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     * @param username El nuevo nombre de usuario
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     * @return El correo electrónico actual
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     * @param email El nuevo correo electrónico
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del usuario.
     * @return La contraseña actual
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * @param password La nueva contraseña
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene la URL o ruta de la foto de perfil.
     * @return La ruta actual de la foto de perfil
     */
    public String getFotoperfil() {
        return fotoperfil;
    }

    /**
     * Establece la URL o ruta de la foto de perfil.
     * @param fotoperfil La nueva ruta de la foto de perfil
     */
    public void setFotoperfil(String fotoperfil) {
        this.fotoperfil = fotoperfil;
    }

    /**
     * Obtiene el array de intereses del usuario.
     * @return Array con los intereses actuales del usuario
     */
    public String[] getIntereses() {
        return intereses;
    }

    /**
     * Establece los intereses del usuario.
     * @param intereses Nuevo array con los intereses del usuario
     */
    public void setIntereses(String[] intereses) {
        this.intereses = intereses;
    }
}