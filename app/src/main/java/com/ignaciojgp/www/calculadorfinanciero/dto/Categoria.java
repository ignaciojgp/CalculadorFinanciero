package com.ignaciojgp.www.calculadorfinanciero.dto;

/**
 * Created by ignacio on 07/02/2015.
 */
public class Categoria {

    private long id;
    private String nombre,color;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
