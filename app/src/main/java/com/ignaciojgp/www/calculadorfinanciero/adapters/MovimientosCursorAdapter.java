package com.ignaciojgp.www.calculadorfinanciero.adapters;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.ignaciojgp.www.calculadorfinanciero.DataBases.GastosContract;
import com.ignaciojgp.www.calculadorfinanciero.R;
import com.ignaciojgp.www.calculadorfinanciero.dto.Periodo;

import java.util.Date;
import java.util.List;

/**
 * Created by ignacio on 25/01/2015.
 */
public class MovimientosCursorAdapter extends CursorAdapter {


    public MovimientosCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.movimiento_cell,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView titulo_tv = (TextView)view.findViewById(R.id.textView);
        TextView cantidad_tv = (TextView)view.findViewById(R.id.textView2);
        TextView fecha_tv = (TextView)view.findViewById(R.id.textView3);

        String titulo = cursor.getString( cursor.getColumnIndexOrThrow(GastosContract.MovimientoEntry.COLUMN_TITULO));
        double cantidad =  cursor.getDouble( cursor.getColumnIndexOrThrow(GastosContract.MovimientoEntry.COLUMN_CANTIDAD));
        long fechaL = cursor.getLong(cursor.getColumnIndexOrThrow(GastosContract.MovimientoEntry.COLUMN_FECHA));

        Date d = new Date(fechaL);


        titulo_tv.setText(titulo);
        cantidad_tv.setText(String.valueOf(cantidad));
        fecha_tv.setText(d.toString());


    }


}
