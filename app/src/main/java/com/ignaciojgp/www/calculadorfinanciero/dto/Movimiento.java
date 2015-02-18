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
    private long tipo,cuenta,categoria,id;
    private double cantidad;
    private Date fecha;



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

    public long getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public long getCuenta() {
        return cuenta;
    }

    public void setCuenta(long cuenta) {
        this.cuenta = cuenta;
    }

    public long getCategoria() {
        return categoria;
    }

    public void setCategoria(long categoria) {
        this.categoria = categoria;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
