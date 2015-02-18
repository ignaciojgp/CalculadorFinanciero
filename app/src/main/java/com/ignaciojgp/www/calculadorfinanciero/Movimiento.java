package com.ignaciojgp.www.calculadorfinanciero;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.ignaciojgp.www.calculadorfinanciero.adapters.CategoriasCursorAdapter;
import com.ignaciojgp.www.calculadorfinanciero.adapters.CuentasCursorAdapter;
import com.ignaciojgp.www.calculadorfinanciero.adapters.MovimientosCursorAdapter;
import com.ignaciojgp.www.calculadorfinanciero.dao.GastosDB;

import java.util.Date;


public class Movimiento extends ActionBarActivity {

    EditText ed_titulo;
    EditText ed_cantidad;
    DatePicker dp_fecha;
    Spinner sp_cuenta;
    Spinner sp_categoria;

    Date fecha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento);

        ed_titulo = (EditText) findViewById(R.id.titulo_et);
        ed_cantidad = (EditText)findViewById(R.id.cantidad_et);
        dp_fecha = (DatePicker) findViewById(R.id.datePicker);
        sp_cuenta = (Spinner) findViewById(R.id.spinner);
        sp_categoria = (Spinner) findViewById(R.id.spinner2);

        new LoadCuentasAsyncTask().execute(this);
        new LoadCategoriasAsyncTask().execute(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_movimiento, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_guardar) {


            com.ignaciojgp.www.calculadorfinanciero.dto.Movimiento movimiento = new com.ignaciojgp.www.calculadorfinanciero.dto.Movimiento();

            movimiento.setId(-1);
            movimiento.setTitulo(ed_titulo.getText().toString());
            double d = Double.valueOf(ed_cantidad.getText().toString());

            movimiento.setCantidad(d);

            movimiento.setCategoria(sp_categoria.getAdapter().getItemId(sp_cuenta.getSelectedItemPosition()));

            Date now = new Date(   dp_fecha.getYear()-1900 , dp_fecha.getMonth(),dp_fecha.getDayOfMonth());

            movimiento.setFecha(now);


            movimiento.setCuenta( sp_cuenta.getAdapter().getItemId(sp_cuenta.getSelectedItemPosition()));
            movimiento.setDescripcion("");
            movimiento.setTipo(1);

            GastosDB gastosDB = new GastosDB(this);

            gastosDB.save(movimiento);

            finish();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class LoadCuentasAsyncTask extends AsyncTask<Activity,Void,Cursor>{

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);

            CuentasCursorAdapter adapter = new CuentasCursorAdapter(getApplicationContext(),cursor,false);

            sp_cuenta.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            sp_cuenta.refreshDrawableState();



        }

        @Override
        protected Cursor doInBackground(Activity... params) {
            GastosDB gastosDB = new GastosDB(params[0]);

            return gastosDB.getCuentas();
        }
    }

    private class LoadCategoriasAsyncTask extends AsyncTask<Activity,Void,Cursor>{

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);

            CategoriasCursorAdapter adapter = new CategoriasCursorAdapter(getApplicationContext(),cursor,false);

            sp_categoria.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            sp_categoria.refreshDrawableState();



        }

        @Override
        protected Cursor doInBackground(Activity... params) {
            GastosDB gastosDB = new GastosDB(params[0]);

            return gastosDB.getCategorias();
        }
    }
}
