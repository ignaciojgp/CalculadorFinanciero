package com.ignaciojgp.www.calculadorfinanciero.dto;

/**
 * Created by ignacio on 25/01/2015.
 */

// comentario aparte
public class Cuenta {
    private double saldo;
    private String nombre, color;
    private int id;



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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
