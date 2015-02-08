package com.ignaciojgp.www.calculadorfinanciero.DataBases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ignacio on 07/02/2015.
 */
public class GastosDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Gastos.db";

    public GastosDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(GastosContract.MovimientoEntry.CREATE);
        db.execSQL(GastosContract.CuentaEntry.CREATE);
        db.execSQL(GastosContract.PeriodoEntry.CREATE);
        db.execSQL(GastosContract.CategoriaEntry.CREATE);

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
