package com.ignaciojgp.www.calculadorfinanciero.dao;

import android.app.Activity;

import com.ignaciojgp.www.calculadorfinanciero.dto.Categoria;
import com.ignaciojgp.www.calculadorfinanciero.dto.Cuenta;
import com.ignaciojgp.www.calculadorfinanciero.dto.Movimiento;
import com.ignaciojgp.www.calculadorfinanciero.dto.Periodo;

import java.util.List;

/**
 * Created by ignacio on 25/01/2015.
 */
public interface Gastos {

    public List<Movimiento> getMovimientos();
    public List<Cuenta> getCuentas();
    public List<Categoria> getCategorias();
    public List<Periodo> getPeriodos();

    public Movimiento save(Movimiento movimiento);
    public Periodo save(Periodo periodo);
    public Cuenta save(Cuenta cuenta);
    public Categoria save(Categoria categoria);

    public boolean delete(Movimiento movimiento);
    public boolean delete(Periodo periodo);
    public boolean delete(Cuenta cuenta);
    public boolean delete(Categoria categoria);



}
