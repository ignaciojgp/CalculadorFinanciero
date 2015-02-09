package com.ignaciojgp.www.calculadorfinanciero;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.ignaciojgp.www.calculadorfinanciero.dao.GastosDB;

import java.util.Date;


public class Movimiento extends ActionBarActivity {

    EditText ed_titulo;
    EditText ed_cantidad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento);

        ed_titulo = (EditText) findViewById(R.id.titulo_et);
        ed_cantidad = (EditText)findViewById(R.id.cantidad_et);



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

            movimiento.setCategoria(1);

            Date now = new Date();
            movimiento.setFecha(now);
            movimiento.setCuenta(1);
            movimiento.setDescripcion("");
            movimiento.setTipo(1);

            GastosDB gastosDB = new GastosDB(this);

            gastosDB.save(movimiento);

            finish();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
