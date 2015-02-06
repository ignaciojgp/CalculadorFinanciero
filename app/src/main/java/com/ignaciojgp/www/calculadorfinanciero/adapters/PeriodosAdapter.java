package com.ignaciojgp.www.calculadorfinanciero.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ignaciojgp.www.calculadorfinanciero.R;
import com.ignaciojgp.www.calculadorfinanciero.dto.Periodo;

import java.util.List;

/**
 * Created by ignacio on 25/01/2015.
 */
public class PeriodosAdapter extends BaseAdapter {

    private Activity context;
    private List<Periodo> periodos;

    public PeriodosAdapter(List<Periodo> periodos, Activity c) {
        this.periodos = periodos;
        this.context = c;
    }

    @Override
    public int getCount() {
        return periodos.size();
    }

    @Override
    public Object getItem(int position) {
        return periodos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null)
        {
            v= context.getLayoutInflater().inflate(R.layout.periodos_cell,null);
        }

        TextView tv = (TextView) v.findViewById(R.id.textView1);

        Periodo p = (Periodo) getItem(position);

        tv.setText(p.getNombre());

        return v;
    }
}
