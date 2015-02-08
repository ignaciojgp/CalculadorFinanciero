package com.ignaciojgp.www.calculadorfinanciero.dto;

/**
 * Created by ignacio on 25/01/2015.
 */

// comentario aparte
public class Cuenta {
    private double saldo;
    private String nombre;
    private int ide;

    public Cuenta(double saldo, String nombre, int ide) {
        this.saldo = saldo;
        this.nombre = nombre;
        this.ide = ide;
    }

    public int getIde() {
        return ide;
    }

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


}
