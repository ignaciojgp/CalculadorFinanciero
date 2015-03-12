package com.ignaciojgp.www.calculadorfinanciero;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.ignaciojgp.www.calculadorfinanciero.DataBases.GastosContract;
import com.ignaciojgp.www.calculadorfinanciero.dao.GastosDB;

import java.util.Date;


public class Categoria extends ActionBarActivity implements SeekBar.OnSeekBarChangeListener{

    private SeekBar rojoBar, verdeBar, azulBar;
    private ImageView colorIV;
    private EditText title_tv;

    private int red,green,blue;
    Bundle editBundle = null;

    com.ignaciojgp.www.calculadorfinanciero.dto.Categoria categoria = new com.ignaciojgp.www.calculadorfinanciero.dto.Categoria();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        colorIV = (ImageView) findViewById(R.id.imageView2);

        rojoBar = (SeekBar) findViewById(R.id.seekBar);
        verdeBar = (SeekBar) findViewById(R.id.seekBar2);
        azulBar = (SeekBar) findViewById(R.id.seekBar3);

        rojoBar.setOnSeekBarChangeListener(this);
        verdeBar.setOnSeekBarChangeListener(this);
        azulBar.setOnSeekBarChangeListener(this);

        title_tv = (EditText) findViewById(R.id.titulo_et);



        if(savedInstanceState!=null){
            editBundle = savedInstanceState;
        }else if(getIntent().getExtras()!= null){
            editBundle = getIntent().getExtras();
        }


        categoria.setId(-1);

        if(editBundle != null){

            categoria.setId(editBundle.getLong(GastosContract.CategoriaEntry._ID));
            categoria.setNombre(editBundle.getString(GastosContract.CategoriaEntry.COLUMN_NOMBRE));
            categoria.setColor(editBundle.getInt(GastosContract.CategoriaEntry.COLUMN_COLOR));



            red = (categoria.getColor() >> 16) & 0xFF;
            green = (categoria.getColor() >> 8) & 0xFF;
            blue = (categoria.getColor() >> 0) & 0xFF;


        }

    }

    @Override
    protected void onResume() {
        super.onResume();


        int c = Color.argb(255, red, green, blue);

        colorIV.setBackgroundColor(c);


        float r = red*100/256;
        float g = green*100/256;
        float b = blue*100/256;


        rojoBar.setProgress(Math.round(r));
        verdeBar.setProgress(Math.round(g));
        azulBar.setProgress(Math.round(b));

        title_tv.setText(categoria.getNombre());





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movimiento, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_guardar) {


            categoria.setNombre(title_tv.getText().toString());

            categoria.setColor(Color.argb(255,red,green,blue));

            GastosDB gastosDB = new GastosDB(this);

            gastosDB.save(categoria);

            finish();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        outState.putLong(GastosContract.CategoriaEntry._ID, categoria.getId());
        outState.putString(GastosContract.CategoriaEntry.COLUMN_NOMBRE, categoria.getNombre());
        outState.putInt(GastosContract.CategoriaEntry.COLUMN_COLOR, categoria.getColor());


    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {




        if(seekBar == rojoBar){

            red= (int) Math.round(256/100* progress);

        }
        if(seekBar == verdeBar){

            green= (int) Math.round(256/100* progress);

        }
        if(seekBar == azulBar){

            blue= (int) Math.round(256/100* progress);

        }


        int color = Color.argb(255, red, green, blue);

        colorIV.setBackgroundColor(color);


    }



    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
