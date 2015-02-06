package com.ignaciojgp.www.calculadorfinanciero;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ignaciojgp.www.calculadorfinanciero.adapters.PeriodosAdapter;
import com.ignaciojgp.www.calculadorfinanciero.dao.GastosJSON;


public class ListaGastos extends ActionBarActivity implements AdapterView.OnItemClickListener {

    GastosJSON gastos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_gastos);


        gastos = new GastosJSON(this);


        PeriodosAdapter pa = new PeriodosAdapter(gastos.getPeriodos(),this);

        ListView lv = (ListView) findViewById(R.id.listView1);

        lv.setAdapter(pa);


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

        gastos.Save();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
