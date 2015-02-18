package com.ignaciojgp.www.calculadorfinanciero;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;


public class Cuenta extends ActionBarActivity implements SeekBar.OnSeekBarChangeListener {

    private SeekBar rojoBar, verdeBar, azulBar;
    private ImageView colorIV;
    private TextView tv;

    private int red,green,blue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);

        colorIV = (ImageView) findViewById(R.id.imageView2);

        rojoBar = (SeekBar) findViewById(R.id.seekBar);
        verdeBar = (SeekBar) findViewById(R.id.seekBar2);
        azulBar = (SeekBar) findViewById(R.id.seekBar3);

        rojoBar.setOnSeekBarChangeListener(this);
        verdeBar.setOnSeekBarChangeListener(this);
        azulBar.setOnSeekBarChangeListener(this);

        tv = (TextView) findViewById(R.id.titulotv);

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
        if (id == R.id.action_settings) {

            com.ignaciojgp.www.calculadorfinanciero.dto.Cuenta cuenta = new com.ignaciojgp.www.calculadorfinanciero.dto.Cuenta();

            cuenta.setId(-1);
            cuenta.setNombre(tv.getText().toString());
            cuenta.setColor(Color.argb(255,red,green,blue));


            return true;
        }

        return super.onOptionsItemSelected(item);
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


        int color = Color.argb(255,red,green,blue);

        colorIV.setBackgroundColor(color);


    }



    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


}
