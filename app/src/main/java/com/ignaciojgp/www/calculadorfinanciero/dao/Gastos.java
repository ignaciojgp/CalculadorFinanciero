package com.ignaciojgp.www.calculadorfinanciero.dao;

import android.app.Activity;
import android.database.Cursor;

import com.ignaciojgp.www.calculadorfinanciero.dto.Categoria;
import com.ignaciojgp.www.calculadorfinanciero.dto.Cuenta;
import com.ignaciojgp.www.calculadorfinanciero.dto.Movimiento;
import com.ignaciojgp.www.calculadorfinanciero.dto.Periodo;

import java.util.List;

/**
 * Created by ignacio on 25/01/2015.
 */
public interface Gastos {

    public Cursor getMovimientos();
    public Cursor getMovimientos(Periodo periodo);

    public Cursor getCuentas();
    public Cursor getCategorias();
    public Cursor getPeriodos();

    public Movimiento save(Movimiento movimiento);
    public Periodo save(Periodo periodo);
    public Cuenta save(Cuenta cuenta);
    public Categoria save(Categoria categoria);

    public boolean delete(Movimiento movimiento);
    public boolean delete(Periodo periodo);
    public boolean delete(Cuenta cuenta);
    public boolean delete(Categoria categoria);



}
