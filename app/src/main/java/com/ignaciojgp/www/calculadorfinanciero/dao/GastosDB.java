package com.ignaciojgp.www.calculadorfinanciero.dao;

import android.app.Activity;

import com.ignaciojgp.www.calculadorfinanciero.dto.Cuenta;
import com.ignaciojgp.www.calculadorfinanciero.dto.Periodo;

import java.util.List;

/**
 * Created by ignacio on 06/02/2015.
 */
public class GastosDB implements Gastos{

    private Activity context;

    public GastosDB(Activity context) {
        this.context = context;
    }

    @Override
    public List<Periodo> getPeriodos() {
        return null;
    }

    @Override
    public List<Cuenta> getCuentas() {
        return null;
    }

    @Override
    public void Save() {

    }
}
