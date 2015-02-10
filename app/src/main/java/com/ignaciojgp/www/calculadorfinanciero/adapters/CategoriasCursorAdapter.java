package com.ignaciojgp.www.calculadorfinanciero.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.ignaciojgp.www.calculadorfinanciero.DataBases.GastosContract;

/**
 * Created by ignacio on 09/02/2015.
 */
public class CategoriasCursorAdapter extends CursorAdapter {

    public CategoriasCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return new TextView(context);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv = (TextView) view;
        tv.setTextColor(view.getResources().getColor(android.R.color.black));
        tv.setText( cursor.getString(cursor.getColumnIndexOrThrow(GastosContract.CategoriaEntry.COLUMN_NOMBRE)));

    }
}
