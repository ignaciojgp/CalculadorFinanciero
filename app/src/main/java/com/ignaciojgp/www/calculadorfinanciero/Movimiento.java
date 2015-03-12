package com.ignaciojgp.www.calculadorfinanciero;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.ignaciojgp.www.calculadorfinanciero.DataBases.GastosContract;
import com.ignaciojgp.www.calculadorfinanciero.adapters.CategoriasCursorAdapter;
import com.ignaciojgp.www.calculadorfinanciero.adapters.CuentasCursorAdapter;
import com.ignaciojgp.www.calculadorfinanciero.adapters.MovimientosCursorAdapter;
import com.ignaciojgp.www.calculadorfinanciero.dao.GastosDB;

import java.util.Calendar;
import java.util.Date;


public class Movimiento extends ActionBarActivity {

    EditText ed_titulo;
    EditText ed_cantidad;
    DatePicker dp_fecha;
    Spinner sp_cuenta;
    Spinner sp_categoria;

    Date fecha;
    Bundle editBundle = null;

    com.ignaciojgp.www.calculadorfinanciero.dto.Movimiento movimiento = new com.ignaciojgp.www.calculadorfinanciero.dto.Movimiento();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento);

        ed_titulo = (EditText) findViewById(R.id.titulo_et);
        ed_cantidad = (EditText)findViewById(R.id.cantidad_et);
        dp_fecha = (DatePicker) findViewById(R.id.datePicker);
        sp_cuenta = (Spinner) findViewById(R.id.spinner);
        sp_categoria = (Spinner) findViewById(R.id.spinner2);

        movimiento.setId(-1);

        if(savedInstanceState!=null){
            editBundle = savedInstanceState;
        }else if(getIntent().getExtras()!= null){
            editBundle = getIntent().getExtras();
        }


        if(editBundle != null){

            movimiento.setId(editBundle.getLong(GastosContract.MovimientoEntry._ID));
            movimiento.setTipo(editBundle.getLong(GastosContract.MovimientoEntry.COLUMN_TIPO));
            movimiento.setCategoria(editBundle.getLong(GastosContract.MovimientoEntry.COLUMN_CATEGORIA));
            movimiento.setCuenta(editBundle.getLong(GastosContract.MovimientoEntry.COLUMN_CUENTA));

            movimiento.setFecha(new Date(editBundle.getLong(GastosContract.MovimientoEntry.COLUMN_FECHA)));

            movimiento.setTitulo(editBundle.getString(GastosContract.MovimientoEntry.COLUMN_TITULO));
            movimiento.setDescripcion(editBundle.getString(GastosContract.MovimientoEntry.COLUMN_DESCRIPCION));



            movimiento.setCantidad(editBundle.getDouble(GastosContract.MovimientoEntry.COLUMN_CANTIDAD));

        }





        new LoadCuentasAsyncTask().execute(this);
        new LoadCategoriasAsyncTask().execute(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        ed_titulo.setText(movimiento.getTitulo());

        ed_cantidad.setText(String.valueOf( movimiento.getCantidad()));

        Calendar cal = Calendar.getInstance();
        cal.setTime(movimiento.getFecha());
        dp_fecha.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));






    }

    private int getItemPositionByObjectId(final long id, CursorAdapter adapter)
    {



        for (int i = 0; i < adapter.getCount(); i++)
        {
            if ((adapter.getItemId(i)) == id)
                return i;
        }
        return -1;
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

            movimiento.setTitulo(ed_titulo.getText().toString());

            double d = Double.valueOf(ed_cantidad.getText().toString());

            movimiento.setCantidad(d);

            movimiento.setCategoria(sp_categoria.getAdapter().getItemId(sp_categoria.getSelectedItemPosition()));

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        outState.putLong(GastosContract.MovimientoEntry._ID, movimiento.getId());
        outState.putLong(GastosContract.MovimientoEntry.COLUMN_TIPO, movimiento.getTipo());
        outState.putLong(GastosContract.MovimientoEntry.COLUMN_CATEGORIA, movimiento.getCategoria());
        outState.putLong(GastosContract.MovimientoEntry.COLUMN_CUENTA, movimiento.getCuenta());
        outState.putLong(GastosContract.MovimientoEntry.COLUMN_FECHA, movimiento.getFecha().getTime());

        outState.putString(GastosContract.MovimientoEntry.COLUMN_TITULO, movimiento.getTitulo());
        outState.putString(GastosContract.MovimientoEntry.COLUMN_DESCRIPCION, movimiento.getDescripcion());

        outState.putDouble(GastosContract.MovimientoEntry.COLUMN_CANTIDAD, movimiento.getCantidad());

    }

    private class LoadCuentasAsyncTask extends AsyncTask<Activity,Void,Cursor>{

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);

            CuentasCursorAdapter adapter = new CuentasCursorAdapter(getApplicationContext(),cursor,false);

            sp_cuenta.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            sp_cuenta.refreshDrawableState();

            int posCuenta = getItemPositionByObjectId(movimiento.getCuenta(),adapter);
            sp_cuenta.setSelection(posCuenta);





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


            int posCategoria = getItemPositionByObjectId(movimiento.getCategoria(),adapter);
            sp_categoria.setSelection(posCategoria);

        }

        @Override
        protected Cursor doInBackground(Activity... params) {
            GastosDB gastosDB = new GastosDB(params[0]);

            return gastosDB.getCategorias();
        }
    }
}
