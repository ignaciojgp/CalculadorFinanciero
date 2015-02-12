package com.ignaciojgp.www.calculadorfinanciero;


import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ignaciojgp.www.calculadorfinanciero.adapters.CategoriasCursorAdapter;
import com.ignaciojgp.www.calculadorfinanciero.dao.GastosDB;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoriasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriasFragment extends Fragment {

    public static CategoriasFragment newInstance() {
        CategoriasFragment fragment = new CategoriasFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public CategoriasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();

        new CategoriasLoader().execute(getActivity());

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_movimientos, container, false);

    }


    private class CategoriasLoader extends AsyncTask<Activity ,Void,Cursor > {




        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);

            CategoriasCursorAdapter adapter = new CategoriasCursorAdapter(getActivity(),cursor,false);
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

            return gastosDB.getCategorias();

        }
    }



}
