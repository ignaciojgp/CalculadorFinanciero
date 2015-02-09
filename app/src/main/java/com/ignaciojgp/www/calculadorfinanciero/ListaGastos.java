package com.ignaciojgp.www.calculadorfinanciero;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.ignaciojgp.www.calculadorfinanciero.DataBases.GastosDbHelper;
import com.ignaciojgp.www.calculadorfinanciero.adapters.MovimientosCursorAdapter;
import com.ignaciojgp.www.calculadorfinanciero.dao.Gastos;
import com.ignaciojgp.www.calculadorfinanciero.dao.GastosDB;


public class ListaGastos extends ActionBarActivity implements AdapterView.OnItemClickListener {

    Gastos gastos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_gastos);



    }


    @Override
    protected void onPostResume() {
        super.onPostResume();

        new MovimientosLoader().execute(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_gastos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {

            Intent i = new Intent();
            i.setClass(this,Movimiento.class);

            startActivity(i);


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private class MovimientosLoader extends AsyncTask<Activity ,Void,Cursor >{




        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);

            MovimientosCursorAdapter adapter = new MovimientosCursorAdapter(getApplicationContext(),cursor,false);

            ListView lv = (ListView) findViewById(R.id.listView1);

            lv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            lv.refreshDrawableState();



        }

        @Override
        protected Cursor doInBackground(Activity... params) {


            GastosDB gastosDB = new GastosDB(params[0]);

            return gastosDB.getMovimientos();

        }
    }

}
