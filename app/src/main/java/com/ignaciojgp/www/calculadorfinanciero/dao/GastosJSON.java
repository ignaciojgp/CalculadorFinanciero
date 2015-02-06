package com.ignaciojgp.www.calculadorfinanciero.dao;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.ignaciojgp.www.calculadorfinanciero.dto.Cuenta;
import com.ignaciojgp.www.calculadorfinanciero.dto.Movimiento;
import com.ignaciojgp.www.calculadorfinanciero.dto.Periodo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ignacio on 25/01/2015.
 */
public class GastosJSON implements Gastos {

    public static final String TAG="GastosJSON";

    private List<Cuenta> cuentas;
    private List<Periodo> periodos;


    private Activity context;

    public GastosJSON(Activity context) {
        this.context = context;

        periodos = new ArrayList<Periodo>();
        cuentas = new ArrayList<Cuenta>();

        loadPeriodos();

    }

    @Override
    public List<Periodo> getPeriodos() {
        return periodos;
    }

    @Override
    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    @Override
    public void Save() {
        savePeriodos();
    }

    private void loadPeriodos(){

        try{
            BufferedReader periodosreader = new BufferedReader(new InputStreamReader(context.openFileInput("periodos.json")));
            JSONArray jsonperiodos = new JSONArray(periodosreader.readLine());


            for(int i =0;i<jsonperiodos.length();i++)
            {
                JSONObject jo= jsonperiodos.getJSONObject(i);

                Periodo p = new Periodo(jo.getString("nombre"));

                JSONArray listaMovs = jo.getJSONArray("movimientos");

                ArrayList<Movimiento> movs = new ArrayList<Movimiento>();


                for(int j=0;j<listaMovs.length();j++){

                    JSONObject jmov = listaMovs.getJSONObject(j);

                    Date d = new Date(jmov.getLong("fecha"));

                    Movimiento mov = new Movimiento(
                            jmov.getString("titulo"),
                            jmov.getString("descripcion"),
                            jmov.getInt("tipo"),
                            jmov.getDouble("cantidad"),
                            d,
                            jmov.getInt("cuenta")
                    );

                    movs.add(mov);

                }

                p.setMovimientos(movs);

                periodos.add(p);

            }


        }catch (FileNotFoundException fe){
            Log.d(TAG,fe.getMessage());
        }catch (JSONException je){
            Log.d(TAG,je.getMessage());
        }catch (IOException ie){
            Log.d(TAG,ie.getMessage());
        }catch (NullPointerException ne){
            Log.d(TAG,"algo salio mal al inicio");
        }




    }

    private void savePeriodos(){
        try {
            BufferedWriter bwriter = new BufferedWriter(new OutputStreamWriter(context.openFileOutput("periodos.json", Context.MODE_PRIVATE)));

            JSONArray jsonperiodos = new JSONArray();


            for(int i =0;i<periodos.size();i++){

                Periodo p = periodos.get(i);

                JSONObject jperiodo = new JSONObject();


                jperiodo.put("nombre",p.getNombre());


                JSONArray jmovimientos = new JSONArray();

                for(int j=0; j<p.getMovimientos().size();j++){
                    Movimiento m = p.getMovimientos().get(j);

                    JSONObject jmov = new JSONObject();

                    jmov.put("titulo",m.getTitulo());
                    jmov.put("descripcion",m.getDescripcion());
                    jmov.put("tipo",m.getTipo());
                    jmov.put("cantidad",m.getCantidad());
                    jmov.put("fecha",m.getFecha());
                    jmov.put("cuenta",m.getCuenta_fuente());

                }


                jperiodo.put("movimientos",jmovimientos);

                jsonperiodos.put(jperiodo);
            }


            bwriter.write(jsonperiodos.toString());
            bwriter.flush();
            bwriter.close();

        }catch (FileNotFoundException fe){
            Log.d(TAG,fe.getMessage());
        }catch (JSONException je){
            Log.d(TAG,je.getMessage());
        }catch (IOException ie){
            Log.d(TAG,ie.getMessage());
        }catch (NullPointerException ne){
            Log.d(TAG,"algo salio mal al cierre");
        }

    }
}
