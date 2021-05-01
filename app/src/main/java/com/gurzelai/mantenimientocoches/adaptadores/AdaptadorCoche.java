package com.gurzelai.mantenimientocoches.adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gurzelai.mantenimientocoches.Coche;
import com.gurzelai.mantenimientocoches.R;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorCoche extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Coche> coches;

    public AdaptadorCoche(Context context, int layout, List<Coche> coches) {
        this.context = context;
        this.layout = layout;
        this.coches = coches;
    }

    @Override
    public int getCount() {
        return this.coches.size();
    }

    @Override
    public Object getItem(int position) {
        return this.coches.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @SuppressLint("ViewHolder")
    @Override

    public View getView(int position, View convertView, ViewGroup viewGroup) {
        // Copiamos la vista
        View v = convertView;

        //Inflamos la vista con nuestro propio layout
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);

        v = layoutInflater.inflate(R.layout.list_item_coche, null);
        // Valor actual según la posición

        Coche cocheActual = coches.get(position);

        // Referenciamos el elemento a modificar y lo rellenamos
        TextView nombreCoche = (TextView) v.findViewById(R.id.nombreCoche);
        nombreCoche.setText(cocheActual.getNombre());
        //Devolvemos la vista inflada
        return v;
    }
}