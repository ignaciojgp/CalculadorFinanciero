package com.ignaciojgp.www.calculadorfinanciero.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ignaciojgp.www.calculadorfinanciero.DataBases.GastosContract;
import com.ignaciojgp.www.calculadorfinanciero.DataBases.GastosDbHelper;
import com.ignaciojgp.www.calculadorfinanciero.dto.Categoria;
import com.ignaciojgp.www.calculadorfinanciero.dto.Cuenta;
import com.ignaciojgp.www.calculadorfinanciero.dto.Movimiento;
import com.ignaciojgp.www.calculadorfinanciero.dto.Periodo;

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
                GastosContract.MovimientoEntry.COLUMN_FECHA+" >= ? AND "+ GastosContract.MovimientoEntry.COLUMN_FECHA+" <= ?",
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
                GastosContract.PeriodoEntry.TABLE_NAME,
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

        GastosDbHelper gastosDbHelper = new GastosDbHelper(context);
        SQLiteDatabase db = gastosDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(GastosContract.MovimientoEntry.COLUMN_CANTIDAD,      movimiento.getCantidad());
        values.put(GastosContract.MovimientoEntry.COLUMN_CATEGORIA,     movimiento.getCategoria());
        values.put(GastosContract.MovimientoEntry.COLUMN_CUENTA,        movimiento.getCuenta());
        values.put(GastosContract.MovimientoEntry.COLUMN_DESCRIPCION,   movimiento.getDescripcion());
        values.put(GastosContract.MovimientoEntry.COLUMN_FECHA,         movimiento.getFecha().getTime());
        values.put(GastosContract.MovimientoEntry.COLUMN_TIPO,          movimiento.getTipo());
        values.put(GastosContract.MovimientoEntry.COLUMN_TITULO,        movimiento.getTitulo());

        if(movimiento.getId()>0){

            String selection = GastosContract.MovimientoEntry._ID+" LIKE ?";
            String[] args = {String.valueOf( movimiento.getId())};

            db.update(
                    GastosContract.MovimientoEntry.TABLE_NAME,
                    values,
                    selection,
                    args
            );


        }else {

            movimiento.setId(db.insert(
                    GastosContract.MovimientoEntry.TABLE_NAME,
                    null,
                    values
            ));
        }



        return movimiento;
    }

    @Override
    public Periodo save(Periodo periodo) {


        GastosDbHelper gastosDbHelper = new GastosDbHelper(context);
        SQLiteDatabase db = gastosDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(GastosContract.PeriodoEntry.COLUMN_FINAL,      periodo.getFin().getTime());
        values.put(GastosContract.PeriodoEntry.COLUMN_INICIO,     periodo.getInicio().getTime());

        if(periodo.getId()>0){

            String selection = GastosContract.PeriodoEntry._ID+" LIKE ?";
            String[] args = {String.valueOf( periodo.getId())};

            db.update(
                    GastosContract.PeriodoEntry.TABLE_NAME,
                    values,
                    selection,
                    args
            );


        }else {

            periodo.setId(db.insert(
                    GastosContract.PeriodoEntry.TABLE_NAME,
                    null,
                    values
            ));
        }




        return periodo;
    }

    @Override
    public Cuenta save(Cuenta cuenta) {


        GastosDbHelper gastosDbHelper = new GastosDbHelper(context);
        SQLiteDatabase db = gastosDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(GastosContract.CuentaEntry.COLUMN_COLOR,     cuenta.getColor());
        values.put(GastosContract.CuentaEntry.COLUMN_NOMBRE,    cuenta.getNombre());
        values.put(GastosContract.CuentaEntry.COLUMN_SALDO,    cuenta.getSaldo());


        if(cuenta.getId()>0){

            String selection = GastosContract.CuentaEntry._ID+" LIKE ?";
            String[] args = {String.valueOf( cuenta.getId())};

            db.update(
                    GastosContract.CuentaEntry.TABLE_NAME,
                    values,
                    selection,
                    args
            );


        }else {

            cuenta.setId(db.insert(
                    GastosContract.CuentaEntry.TABLE_NAME,
                    null,
                    values
            ));
        }



        return cuenta;
    }

    @Override
    public Categoria save(Categoria categoria) {


        GastosDbHelper gastosDbHelper = new GastosDbHelper(context);
        SQLiteDatabase db = gastosDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(GastosContract.CategoriaEntry.COLUMN_COLOR,     categoria.getColor());
        values.put(GastosContract.CategoriaEntry.COLUMN_NOMBRE,    categoria.getNombre());



        if(categoria.getId()>0){

            String selection = GastosContract.CategoriaEntry._ID+" LIKE ?";
            String[] args = {String.valueOf( categoria.getId())};

            db.update(
                    GastosContract.CategoriaEntry.TABLE_NAME,
                    values,
                    selection,
                    args
            );


        }else {

            categoria.setId(db.insert(
                    GastosContract.CategoriaEntry.TABLE_NAME,
                    null,
                    values
            ));
        }



        return categoria;
    }

    @Override
    public boolean delete(Movimiento movimiento) {

        GastosDbHelper gastosDbHelper = new GastosDbHelper(context);
        SQLiteDatabase db = gastosDbHelper.getWritableDatabase();

        if(movimiento.getId()>0){

            String selection = GastosContract.MovimientoEntry._ID+" LIKE ?";
            String[] args = {String.valueOf( movimiento.getId())};

            if(db.delete(
                    GastosContract.MovimientoEntry.TABLE_NAME,
                    selection,
                    args
            ) >0){

                return true;
            }


        }

        return false;
    }

    @Override
    public boolean delete(Periodo periodo) {
        GastosDbHelper gastosDbHelper = new GastosDbHelper(context);
        SQLiteDatabase db = gastosDbHelper.getWritableDatabase();

        if(periodo.getId()>0){

            String selection = GastosContract.PeriodoEntry._ID+" LIKE ?";
            String[] args = {String.valueOf( periodo.getId())};

            if(db.delete(
                    GastosContract.PeriodoEntry.TABLE_NAME,
                    selection,
                    args
            ) >0){

                return true;
            }


        }

        return false;
    }

    @Override
    public boolean delete(Cuenta cuenta) {
        GastosDbHelper gastosDbHelper = new GastosDbHelper(context);
        SQLiteDatabase db = gastosDbHelper.getWritableDatabase();

        if(cuenta.getId()>0){

            String selection = GastosContract.CuentaEntry._ID+" LIKE ?";
            String[] args = {String.valueOf( cuenta.getId())};

            if(db.delete(
                    GastosContract.CuentaEntry.TABLE_NAME,
                    selection,
                    args
            ) >0){

                return true;
            }


        }

        return false;
    }

    @Override
    public boolean delete(Categoria categoria) {
        GastosDbHelper gastosDbHelper = new GastosDbHelper(context);
        SQLiteDatabase db = gastosDbHelper.getWritableDatabase();

        if(categoria.getId()>0){

            String selection = GastosContract.CategoriaEntry._ID+" LIKE ?";
            String[] args = {String.valueOf( categoria.getId())};

            if(db.delete(
                    GastosContract.CategoriaEntry.TABLE_NAME,
                    selection,
                    args
            ) >0){

                return true;
            }


        }

        return false;
    }
}
