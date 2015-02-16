package com.ignaciojgp.www.calculadorfinanciero.DataBases;

import android.app.Activity;
import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;

import com.ignaciojgp.www.calculadorfinanciero.dao.Gastos;
import com.ignaciojgp.www.calculadorfinanciero.dao.GastosDB;
import com.ignaciojgp.www.calculadorfinanciero.dto.Cuenta;

/**
 * Created by ignacio on 07/02/2015.
 */
public class GastosDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 6;
    public static final String DATABASE_NAME = "Gastos.db";

    Activity parent;

    public GastosDbHelper(Activity context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        parent = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(GastosContract.MovimientoEntry.CREATE);
        db.execSQL(GastosContract.CuentaEntry.CREATE);
        db.execSQL(GastosContract.PeriodoEntry.CREATE);
        db.execSQL(GastosContract.CategoriaEntry.CREATE);




        ContentValues cuentaInicial = new ContentValues();

        cuentaInicial.put(GastosContract.CuentaEntry.COLUMN_COLOR, Color.argb(255,255,255,0));
        cuentaInicial.put(GastosContract.CuentaEntry.COLUMN_NOMBRE, "Cuenta Base");
        cuentaInicial.put(GastosContract.CuentaEntry.COLUMN_SALDO, 10);

        db.insert(
                GastosContract.CuentaEntry.TABLE_NAME,
                null,
                cuentaInicial
        );

        ContentValues categoriaInicial = new ContentValues();

        categoriaInicial.put(GastosContract.CategoriaEntry.COLUMN_COLOR, Color.argb(255,255,0,0));
        categoriaInicial.put(GastosContract.CategoriaEntry.COLUMN_NOMBRE, "Gasto Generico");


        db.insert(
                GastosContract.CategoriaEntry.TABLE_NAME,
                null,
                categoriaInicial
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(GastosContract.MovimientoEntry.DELETE);
        db.execSQL(GastosContract.CuentaEntry.DELETE);
        db.execSQL(GastosContract.PeriodoEntry.DELETE);
        db.execSQL(GastosContract.CategoriaEntry.DELETE);

        onCreate(db);
    }
}
