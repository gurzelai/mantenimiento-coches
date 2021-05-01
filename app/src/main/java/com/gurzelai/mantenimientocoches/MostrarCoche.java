package com.gurzelai.mantenimientocoches;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MostrarCoche extends AppCompatActivity {

    TextView tvNombre, tvFabricante, tvModelo, tvAnio, tvKilometros;
    Coche coche;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_coche);
        setTitle("Informacion coche");
        reconocer();
        coche = (Coche) getIntent().getSerializableExtra("coche");
        asignar();
    }

    private void asignar() {
        tvNombre.setText(coche.informacion.nombre);
        tvFabricante.setText(coche.informacion.fabricante);
        tvModelo.setText(coche.informacion.modelo);
        tvAnio.setText(String.valueOf(coche.informacion.getAnio()));
        tvKilometros.setText(String.valueOf(coche.informacion.getKilometros())+ " km");
    }

    private void reconocer() {
        tvNombre = findViewById(R.id.tvNombre);
        tvFabricante = findViewById(R.id.tvFabricante);
        tvModelo = findViewById(R.id.tvModelo);
        tvAnio = findViewById(R.id.tvAnio);
        tvKilometros = findViewById(R.id.tvKilometros);
    }
}