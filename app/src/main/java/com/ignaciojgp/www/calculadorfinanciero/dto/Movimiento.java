package com.ignaciojgp.www.calculadorfinanciero.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ignacio on 25/01/2015.
 */
public class Movimiento implements Serializable {



    public static enum Tipo{
        INGRESO,
        EGRESO
    }

    private String titulo, descripcion;
    private int tipo;
    private double cantidad;
    private Date fecha;
    private int cuenta_fuente;



    public Movimiento(String titulo, String descripcion, int tipo, double cantidad, Date fecha, int cuenta_fuente) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.cuenta_fuente = cuenta_fuente;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCuenta_fuente() {
        return cuenta_fuente;
    }

    public void setCuenta_fuente(int cuenta_fuente) {
        this.cuenta_fuente = cuenta_fuente;
    }

}
