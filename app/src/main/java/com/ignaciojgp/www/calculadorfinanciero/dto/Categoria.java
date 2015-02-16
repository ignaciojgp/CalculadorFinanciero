package com.ignaciojgp.www.calculadorfinanciero.dto;

/**
 * Created by ignacio on 07/02/2015.
 */
public class Categoria {

    private long id;
    private String nombre;
    private int color;

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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
