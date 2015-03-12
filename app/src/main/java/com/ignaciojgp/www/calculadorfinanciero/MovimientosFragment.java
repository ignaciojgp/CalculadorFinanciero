package com.ignaciojgp.www.calculadorfinanciero;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.ignaciojgp.www.calculadorfinanciero.DataBases.GastosContract;
import com.ignaciojgp.www.calculadorfinanciero.adapters.MovimientosCursorAdapter;
import com.ignaciojgp.www.calculadorfinanciero.dao.Gastos;
import com.ignaciojgp.www.calculadorfinanciero.dao.GastosDB;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MovimientosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MovimientosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovimientosFragment extends Fragment implements AdapterView.OnItemClickListener{


    private OnFragmentInteractionListener mListener;

    ListView lv;

    public static MovimientosFragment newInstance() {
        MovimientosFragment fragment = new MovimientosFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    public MovimientosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        new MovimientosLoader().execute(getActivity());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_movimientos, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();

        new MovimientosLoader().execute(getActivity());

        lv = (ListView) getView().findViewById(R.id.listView1);

        lv.setOnItemClickListener(this);



    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        new MovimientosLoader().execute(getActivity());

        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent i = new Intent();
        i.setClass(getActivity(), Movimiento.class);

        MovimientosCursorAdapter cadapter = (MovimientosCursorAdapter) lv.getAdapter();

        Cursor cursor= (Cursor) cadapter.getItem(position);

        i.putExtra(GastosContract.MovimientoEntry._ID, cursor.getLong(cursor.getColumnIndexOrThrow(GastosContract.MovimientoEntry._ID)));

        i.putExtra(GastosContract.MovimientoEntry.COLUMN_TITULO,cursor.getString(cursor.getColumnIndexOrThrow(GastosContract.MovimientoEntry.COLUMN_TITULO)));
        i.putExtra(GastosContract.MovimientoEntry.COLUMN_DESCRIPCION,cursor.getString(cursor.getColumnIndexOrThrow(GastosContract.MovimientoEntry.COLUMN_DESCRIPCION)));

        i.putExtra(GastosContract.MovimientoEntry.COLUMN_CATEGORIA,cursor.getLong(cursor.getColumnIndexOrThrow(GastosContract.MovimientoEntry.COLUMN_CATEGORIA)));
        i.putExtra(GastosContract.MovimientoEntry.COLUMN_CUENTA,cursor.getLong(cursor.getColumnIndexOrThrow(GastosContract.MovimientoEntry.COLUMN_CUENTA)));
        i.putExtra(GastosContract.MovimientoEntry.COLUMN_TIPO,cursor.getLong(cursor.getColumnIndexOrThrow(GastosContract.MovimientoEntry.COLUMN_TIPO)));
        i.putExtra(GastosContract.MovimientoEntry.COLUMN_FECHA,cursor.getLong(cursor.getColumnIndexOrThrow(GastosContract.MovimientoEntry.COLUMN_FECHA)));
        i.putExtra(GastosContract.MovimientoEntry.COLUMN_CANTIDAD,cursor.getDouble(cursor.getColumnIndexOrThrow(GastosContract.MovimientoEntry.COLUMN_CANTIDAD)));


        startActivity(i);

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }


    private class MovimientosLoader extends AsyncTask<Activity ,Void,Cursor > {




        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);

            MovimientosCursorAdapter adapter = new MovimientosCursorAdapter(getActivity(),cursor,false);
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

            return gastosDB.getMovimientos();

        }
    }

}
