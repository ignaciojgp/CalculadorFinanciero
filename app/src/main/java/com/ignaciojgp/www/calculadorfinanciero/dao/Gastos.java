package com.ignaciojgp.www.calculadorfinanciero.dao;

import com.ignaciojgp.www.calculadorfinanciero.dto.Cuenta;
import com.ignaciojgp.www.calculadorfinanciero.dto.Periodo;

import java.util.List;

/**
 * Created by ignacio on 25/01/2015.
 */
public interface Gastos {

    public List<Periodo> getPeriodos();

    public List<Cuenta> getCuentas();

    public void Save();


}
