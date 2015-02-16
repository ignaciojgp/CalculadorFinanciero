package com.ignaciojgp.www.calculadorfinanciero.dto;

/**
 * Created by ignacio on 25/01/2015.
 */

// comentario aparte
public class Cuenta {
    private double saldo;
    private String nombre;
    private long id;
    private int color;



    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
