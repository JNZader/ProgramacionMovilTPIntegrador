package com.example.tpi.model;

import java.util.List;

/**
 * Representa un post en la aplicación.
 */
public class Post {

    /**
     * El título del post.
     */
    private String titulo;

    /**
     * La descripción del post.
     */
    private String descripcion;

    /**
     * La duración del post en minutos.
     */
    private int duracion;

    /**
     * La categoría del post.
     */
    private String categoria;

    /**
     * El presupuesto del post.
     */
    private double presupuesto;

    /**
     * La lista de URLs de imágenes asociadas al post.
     */
    private List<String> imagenes;

    /**
     * Constructor para crear un post con todos los campos.
     *
     * @param titulo El título del post.
     * @param descripcion La descripción del post.
     * @param duracion La duración del post en minutos.
     * @param categoria La categoría del post.
     * @param presupuesto El presupuesto del post.
     * @param imagenes La lista de URLs de imágenes asociadas al post.
     */
    public Post(String titulo, String descripcion, int duracion, String categoria, double presupuesto, List<String> imagenes) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.categoria = categoria;
        this.presupuesto = presupuesto;
        this.imagenes = imagenes;
    }

    /**
     * Constructor para crear un post con todos los campos excepto las imágenes.
     *
     * @param titulo El título del post.
     * @param descripcion La descripción del post.
     * @param duracion La duración del post en minutos.
     * @param categoria La categoría del post.
     * @param presupuesto El presupuesto del post.
     */
    public Post(String titulo, String descripcion, int duracion, String categoria, double presupuesto) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.categoria = categoria;
        this.presupuesto = presupuesto;
    }

    /**
     * Constructor vacio para crear un post.
     */
    public Post() {
    }

    /**
     * Obtiene el título del post.
     *
     * @return El título del post.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título del post.
     *
     * @param titulo El título del post.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene la descripción del post.
     *
     * @return La descripción del post.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del post.
     *
     * @param descripcion La descripción del post.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la duración del post en minutos.
     *
     * @return La duración del post en minutos.
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Establece la duración del post en minutos.
     *
     * @param duracion La duración del post en minutos.
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * Obtiene la categoría del post.
     *
     * @return La categoría del post.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Establece la categoría del post.
     *
     * @param categoria La categoría del post.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtiene el presupuesto del post.
     *
     * @return El presupuesto del post.
     */
    public double getPresupuesto() {
        return presupuesto;
    }

    /**
     * Establece el presupuesto del post.
     *
     * @param presupuesto El presupuesto del post.
     */
    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    /**
     * Obtiene la lista de URLs de imágenes asociadas al post.
     *
     * @return La lista de URLs de imágenes asociadas al post.
     */
    public List<String> getImagenes() {
        return imagenes;
    }

    /**
     * Establece la lista de URLs de imágenes asociadas al post.
     *
     * @param imagenes La lista de URLs de imágenes asociadas al post.
     */
    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }
}