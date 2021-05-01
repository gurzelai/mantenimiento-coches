package com.gurzelai.mantenimientocoches.adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.gurzelai.mantenimientocoches.Cambio;
import com.gurzelai.mantenimientocoches.R;

import java.util.List;

public class AdaptadorCambio extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Cambio> cambios;

    public AdaptadorCambio(Context context, int layout, List<Cambio> cambios) {
        this.context = context;
        this.layout = layout;
        this.cambios = cambios;
    }

    @Override
    public int getCount() {
        return this.cambios.size();
    }

    @Override
    public Object getItem(int position) {
        return this.cambios.get(position);
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

        Cambio cambioActual = cambios.get(position);

        // Referenciamos el elemento a modificar y lo rellenamos
        TextView descricionCambio = (TextView) v.findViewById(R.id.descricionCambio);
        descricionCambio.setText(cambioActual.getDescripcion());
        ImageView imagen = v.findViewById(R.id.imagenCambio);
        if(cambioActual.getTipoCambio() == Cambio.TipoCambio.MANTENIMIENTO){
            imagen.setImageResource(R.drawable.mantenimiento);
        }
        else{
            imagen.setImageResource(R.drawable.reparacion);
        }
        //Devolvemos la vista inflada
        return v;
    }
}