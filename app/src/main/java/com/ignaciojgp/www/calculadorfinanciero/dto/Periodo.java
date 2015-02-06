package com.ignaciojgp.www.calculadorfinanciero.dto;

import java.util.List;

/**
 * Created by ignacio on 25/01/2015.
 */
public class Periodo {
    private String nombre;
    private List<Movimiento> movimientos;

    public Periodo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }
}
