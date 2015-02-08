package com.ignaciojgp.www.calculadorfinanciero.DataBases;

import android.provider.BaseColumns;

/**
 * Created by ignacio on 06/02/2015.
 */
public final class GastosContract {

    public GastosContract(){}

    public static final String _TYPE_INT = " INTEGER ";
    public static final String _TYPE_TEXT = " TEXT ";
    public static final String _TYPE_REAL = " REAL ";
    public static final String _SP = " ";
    public static final String _COMMA = " , ";
    public static final String _PRIMARY_KEY = " PRIMARY_KEY ";
    public static final String _AUTOINCREMENT = " AUTOINCREMENT ";
    public static final String _CREATE = "CREATE TABLE ";
    public static final String _DROP = "DROP TABLE IF EXIST ";



    public static abstract class MovimientoEntry implements BaseColumns{

        public static final String TABLE_NAME="movimiento";

        public static final String COLUMN_TITULO = "titulo";
        public static final String COLUMN_TITULO_TYPE = _TYPE_TEXT;

        public static final String COLUMN_DESCRIPCION = "descripcion";
        public static final String COLUMN_DESCRIPCION_TYPE = _TYPE_TEXT;

        public static final String COLUMN_CATEGORIA = "categoria";
        public static final String COLUMN_CATEGORIA_TYPE = _TYPE_INT;

        public static final String COLUMN_CANTIDAD = "cantidad";
        public static final String COLUMN_CANTIDAD_TYPE = _TYPE_REAL;

        public static final String COLUMN_FECHA = "fecha";
        public static final String COLUMN_FECHA_TYPE = _TYPE_INT;

        public static final String COLUMN_CUENTA = "cuenta";
        public static final String COLUMN_CUENTA_TYPE = _TYPE_INT;

        public static final String COLUMN_TIPO = "tipo";
        public static final String COLUMN_TIPO_TYPE = _TYPE_INT;

        public static final String CREATE = _CREATE+TABLE_NAME+" ("+
                _ID+_TYPE_INT+_AUTOINCREMENT +_COMMA +
                COLUMN_TITULO + COLUMN_TITULO_TYPE+_COMMA+
                COLUMN_DESCRIPCION +COLUMN_DESCRIPCION_TYPE+_COMMA+
                COLUMN_CATEGORIA + COLUMN_CATEGORIA_TYPE +_COMMA+
                COLUMN_CANTIDAD+COLUMN_CANTIDAD_TYPE+_COMMA+
                COLUMN_FECHA+COLUMN_FECHA_TYPE+_COMMA+
                COLUMN_CUENTA+COLUMN_CUENTA_TYPE+_COMMA+
                COLUMN_TIPO+COLUMN_TIPO_TYPE+

                " )";

        public static final String DELETE = _DROP+TABLE_NAME;

    }

    public static abstract class CuentaEntry implements BaseColumns{

        public static final String TABLE_NAME="cuenta";

        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_NOMBRE_TYPE = _TYPE_TEXT;

        public static final String COLUMN_COLOR = "color";
        public static final String COLUMN_COLOR_TYPE = _TYPE_TEXT;

        public static final String COLUMN_SALDO = "saldo";
        public static final String COLUMN_SALDO_TYPE = _TYPE_REAL;



        public static final String CREATE = _CREATE+TABLE_NAME+" ("+
                _ID+_TYPE_INT+_AUTOINCREMENT +_COMMA +
                COLUMN_NOMBRE+COLUMN_NOMBRE_TYPE+_COMMA+
                COLUMN_COLOR+COLUMN_COLOR_TYPE+_COMMA+
                COLUMN_SALDO+COLUMN_SALDO_TYPE+

                " )";

        public static final String DELETE = _DROP+TABLE_NAME;
    }

    public static abstract class CategoriaEntry implements BaseColumns{

        public static final String TABLE_NAME="categoria";

        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_NOMBRE_TYPE = _TYPE_TEXT;

        public static final String COLUMN_COLOR = "color";
        public static final String COLUMN_COLOR_TYPE = _TYPE_TEXT;

        public static final String CREATE = _CREATE+TABLE_NAME+" ("+
                _ID+_TYPE_INT+_AUTOINCREMENT +_COMMA +
                COLUMN_NOMBRE+COLUMN_NOMBRE_TYPE+_COMMA+
                COLUMN_COLOR+COLUMN_COLOR_TYPE+
                " )";

        public static final String DELETE = _DROP+TABLE_NAME;

    }

    public static abstract class PeriodoEntry implements BaseColumns{

        public static final String TABLE_NAME="periodo";

        public static final String COLUMN_INICIO = "inicio";
        public static final String COLUMN_INICIO_TYPE = _TYPE_INT;

        public static final String COLUMN_FINAL = "fin";
        public static final String COLUMN_FINAL_TYPE = _TYPE_INT;

        public static final String CREATE = _CREATE+TABLE_NAME+" ("+
                _ID+_TYPE_INT+_AUTOINCREMENT +_COMMA +
                COLUMN_INICIO+COLUMN_INICIO_TYPE+_COMMA+
                COLUMN_FINAL+COLUMN_FINAL_TYPE+
                " )";

        public static final String DELETE = _DROP+TABLE_NAME;
    }

}
