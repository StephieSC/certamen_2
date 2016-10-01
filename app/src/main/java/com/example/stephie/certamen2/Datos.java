package com.example.stephie.certamen2;

/**
 * Created by Stephie on 30-09-2016.
 */
public class Datos {
    // Atributos
    private String titulo;
    private String descripcion;
    private String actualizacion;
    private String url;


    public Datos() {
    }

    public Datos(String titulo, String descripcion, String actualizacion, String url) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.actualizacion = actualizacion;
        this.url=url;

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setActualizacion(String actualizacion) {
        this.actualizacion = actualizacion;
    }
    public String getActualizacion() {
        return actualizacion;
    }
    public void setURL(String url) {
        this.url = url;
    }
    public String getURL() {
        return url;
    }




}