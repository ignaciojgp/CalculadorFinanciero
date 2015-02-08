package com.ignaciojgp.www.calculadorfinanciero.DataBases;

import android.provider.BaseColumns;

/**
 * Created by ignacio on 06/02/2015.
 */
public final class GastosContract {

    public GastosContract(){}

    public static abstract class GastosEntry implements BaseColumns{

        public static final String TABLE_NAME="movimiento";

        private String titulo, descripcion;
        private static final String COLUMN_TIPO ="tipo";
        private static final String COLUMN_CANTIDAD = "cantidad";
        private static final String COLUMN_FECHA = "fecha";
        private static final String COLUMN_CUENTA = "cuenta_fuentes";


    }

}
