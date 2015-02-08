package com.ignaciojgp.www.calculadorfinanciero.dto;

/**
 * Created by ignacio on 07/02/2015.
 */
public class Categoria {

    private int id;
    private String titulo,color;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
