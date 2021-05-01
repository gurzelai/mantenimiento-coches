package com.gurzelai.mantenimientocoches.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.gurzelai.mantenimientocoches.Coche;
import com.gurzelai.mantenimientocoches.R;
import com.gurzelai.mantenimientocoches.adaptadores.AdaptadorCoche;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvCoches;

    List<Coche> listaCoches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializar();
        reconocer();
    }

    private void reconocer() {
        AdaptadorCoche adaptadorCoche = new AdaptadorCoche(getApplicationContext(), R.layout.list_item_coche, listaCoches);
        lvCoches = findViewById(R.id.lvCoches);
        lvCoches.setAdapter(adaptadorCoche);
    }

    private void inicializar() {
        listaCoches = new ArrayList<>();
    }
}