package com.ignaciojgp.www.calculadorfinanciero;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ignaciojgp.www.calculadorfinanciero.DataBases.GastosContract;
import com.ignaciojgp.www.calculadorfinanciero.adapters.CategoriasCursorAdapter;
import com.ignaciojgp.www.calculadorfinanciero.adapters.CuentasCursorAdapter;
import com.ignaciojgp.www.calculadorfinanciero.dao.GastosDB;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CuentasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CuentasFragment extends Fragment  implements AdapterView.OnItemClickListener{
    ListView lv;
    public static CuentasFragment newInstance() {
        CuentasFragment fragment = new CuentasFragment();

        return fragment;
    }

    public CuentasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onResume() {
        super.onResume();

        lv = (ListView) getView().findViewById(R.id.listView1);
        lv.setOnItemClickListener(this);

        new CuentasLoader().execute(getActivity());

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movimientos, container, false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent i = new Intent();
        i.setClass(getActivity(), Cuenta.class);

        CuentasCursorAdapter cadapter = (CuentasCursorAdapter) lv.getAdapter();

        Cursor cursor= (Cursor) cadapter.getItem(position);

        i.putExtra(GastosContract.CuentaEntry._ID, cursor.getLong(cursor.getColumnIndexOrThrow(GastosContract.CuentaEntry._ID)));

        i.putExtra(GastosContract.CuentaEntry.COLUMN_NOMBRE,cursor.getString(cursor.getColumnIndexOrThrow(GastosContract.CuentaEntry.COLUMN_NOMBRE)));
        i.putExtra(GastosContract.CuentaEntry.COLUMN_COLOR,cursor.getInt(cursor.getColumnIndexOrThrow(GastosContract.CuentaEntry.COLUMN_COLOR)));
        i.putExtra(GastosContract.CuentaEntry.COLUMN_SALDO,cursor.getDouble(cursor.getColumnIndexOrThrow(GastosContract.CuentaEntry.COLUMN_SALDO)));


        startActivity(i);

    }



    private class CuentasLoader extends AsyncTask<Activity ,Void,Cursor > {




        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);

            CuentasCursorAdapter adapter = new CuentasCursorAdapter(getActivity(),cursor,false);
            if(getView() != null) {
                ListView lv = (ListView) getView().findViewById(R.id.listView1);



                lv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                lv.refreshDrawableState();

            }


        }

        @Override
        protected Cursor doInBackground(Activity... params) {


            GastosDB gastosDB = new GastosDB(params[0]);

            return gastosDB.getCuentas();

        }
    }

}
