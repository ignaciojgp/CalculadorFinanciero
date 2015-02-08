package com.ignaciojgp.www.calculadorfinanciero.dao;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ignaciojgp.www.calculadorfinanciero.DataBases.GastosContract;
import com.ignaciojgp.www.calculadorfinanciero.DataBases.GastosDbHelper;
import com.ignaciojgp.www.calculadorfinanciero.dto.Categoria;
import com.ignaciojgp.www.calculadorfinanciero.dto.Cuenta;
import com.ignaciojgp.www.calculadorfinanciero.dto.Movimiento;
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
    public Cursor getMovimientos() {

        GastosDbHelper gastosDbHelper = new GastosDbHelper(context);

        SQLiteDatabase db = gastosDbHelper.getReadableDatabase();

        String[] columnas = {
                GastosContract.MovimientoEntry._ID,
                GastosContract.MovimientoEntry.COLUMN_CUENTA,
                GastosContract.MovimientoEntry.COLUMN_FECHA,
                GastosContract.MovimientoEntry.COLUMN_CANTIDAD,
                GastosContract.MovimientoEntry.COLUMN_CATEGORIA,
                GastosContract.MovimientoEntry.COLUMN_DESCRIPCION,
                GastosContract.MovimientoEntry.COLUMN_TITULO,
                GastosContract.MovimientoEntry.COLUMN_TIPO
        };

        String sortOrder = GastosContract.MovimientoEntry.COLUMN_FECHA+" DESC";

        Cursor cursor = db.query(
                GastosContract.MovimientoEntry.TABLE_NAME,
                columnas,
                null,
                null,
                null,
                null,
                sortOrder
                );


        return cursor;
    }

    @Override
    public Cursor getMovimientos(Periodo periodo) {
        GastosDbHelper gastosDbHelper = new GastosDbHelper(context);

        SQLiteDatabase db = gastosDbHelper.getReadableDatabase();

        String[] columnas = {
                GastosContract.MovimientoEntry._ID,
                GastosContract.MovimientoEntry.COLUMN_CUENTA,
                GastosContract.MovimientoEntry.COLUMN_FECHA,
                GastosContract.MovimientoEntry.COLUMN_CANTIDAD,
                GastosContract.MovimientoEntry.COLUMN_CATEGORIA,
                GastosContract.MovimientoEntry.COLUMN_DESCRIPCION,
                GastosContract.MovimientoEntry.COLUMN_TITULO,
                GastosContract.MovimientoEntry.COLUMN_TIPO
        };



        String sortOrder = GastosContract.MovimientoEntry.COLUMN_FECHA+" DESC";

        String[] args={
                Long.toString(periodo.getInicio().getTime()),
                Long.toString(periodo.getFin().getTime())

        };

        Cursor cursor = db.query(
                GastosContract.MovimientoEntry.TABLE_NAME,
                columnas,
                GastosContract.MovimientoEntry.COLUMN_FECHA+" >= ?s AND "+ GastosContract.MovimientoEntry.COLUMN_FECHA+" <= ?s",
                args,
                null,
                null,
                sortOrder
        );


        return cursor;
    }

    @Override
    public Cursor getCuentas() {
        GastosDbHelper gastosDbHelper = new GastosDbHelper(context);

        SQLiteDatabase db = gastosDbHelper.getReadableDatabase();

        String[] columnas = {
                GastosContract.CuentaEntry._ID,
                GastosContract.CuentaEntry.COLUMN_NOMBRE,
                GastosContract.CuentaEntry.COLUMN_SALDO,
                GastosContract.CuentaEntry.COLUMN_COLOR
        };



        Cursor cursor = db.query(
                GastosContract.CuentaEntry.TABLE_NAME,
                columnas,
                null,
                null,
                null,
                null,
                null
        );


        return cursor;
    }

    @Override
    public Cursor getCategorias() {
        GastosDbHelper gastosDbHelper = new GastosDbHelper(context);

        SQLiteDatabase db = gastosDbHelper.getReadableDatabase();

        String[] columnas = {
                GastosContract.CategoriaEntry._ID,
                GastosContract.CategoriaEntry.COLUMN_NOMBRE,
                GastosContract.CategoriaEntry.COLUMN_COLOR
        };



        Cursor cursor = db.query(
                GastosContract.CategoriaEntry.TABLE_NAME,
                columnas,
                null,
                null,
                null,
                null,
                null
        );


        return cursor;
    }

    @Override
    public Cursor getPeriodos() {
        GastosDbHelper gastosDbHelper = new GastosDbHelper(context);

        SQLiteDatabase db = gastosDbHelper.getReadableDatabase();

        String[] columnas = {
                GastosContract.PeriodoEntry._ID,
                GastosContract.PeriodoEntry.COLUMN_INICIO,
                GastosContract.PeriodoEntry.COLUMN_FINAL
        };



        Cursor cursor = db.query(
                GastosContract.CategoriaEntry.TABLE_NAME,
                columnas,
                null,
                null,
                null,
                null,
                null
        );


        return cursor;
    }

    @Override
    public Movimiento save(Movimiento movimiento) {
        return null;
    }

    @Override
    public Periodo save(Periodo periodo) {
        return null;
    }

    @Override
    public Cuenta save(Cuenta cuenta) {
        return null;
    }

    @Override
    public Categoria save(Categoria categoria) {
        return null;
    }

    @Override
    public boolean delete(Movimiento movimiento) {
        return false;
    }

    @Override
    public boolean delete(Periodo periodo) {
        return false;
    }

    @Override
    public boolean delete(Cuenta cuenta) {
        return false;
    }

    @Override
    public boolean delete(Categoria categoria) {
        return false;
    }
}
