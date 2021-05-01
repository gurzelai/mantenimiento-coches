package com.gurzelai.mantenimientocoches.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gurzelai.mantenimientocoches.Cambio;
import com.gurzelai.mantenimientocoches.Coche;
import com.gurzelai.mantenimientocoches.R;

public class MostrarCoche extends AppCompatActivity {

    final int CODE_RESULT_CAMBIO=1;
    TextView tvNombre, tvFabricante, tvModelo, tvAnio, tvKilometros;
    FloatingActionButton btnMantenimiento, btnReparacion;
    Coche coche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_coche);
        setTitle("Informacion coche");
        reconocer();
        setOnClick();
        coche = (Coche) getIntent().getSerializableExtra("coche");
        asignar();
    }

    private void setOnClick() {
        btnReparacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NuevoCambio.class);
                intent.putExtra("Tipo cambio", Cambio.TipoCambio.REPARACION);
                startActivityForResult(intent, CODE_RESULT_CAMBIO);
            }
        });
        btnMantenimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NuevoCambio.class);
                intent.putExtra("Tipo cambio", Cambio.TipoCambio.MANTENIMIENTO);
                startActivityForResult(intent, CODE_RESULT_CAMBIO);
            }
        });
    }

    private void asignar() {
        tvNombre.setText(coche.informacion.getNombre());
        tvFabricante.setText(coche.informacion.getFabricante());
        tvModelo.setText(coche.informacion.getModelo());
        tvAnio.setText(String.valueOf(coche.informacion.getAnio()));
        tvKilometros.setText(String.valueOf(coche.informacion.getKilometros())+ " km");
    }

    private void reconocer() {
        tvNombre = findViewById(R.id.tvNombre);
        tvFabricante = findViewById(R.id.tvFabricante);
        tvModelo = findViewById(R.id.tvModelo);
        tvAnio = findViewById(R.id.tvAnio);
        tvKilometros = findViewById(R.id.tvKilometros);
        btnMantenimiento = findViewById(R.id.btnMantenimiento);
        btnReparacion = findViewById(R.id.btnReparacion);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_RESULT_CAMBIO) {
            if(resultCode == Activity.RESULT_OK){
                coche.addCambio((Cambio) data.getSerializableExtra("nuevo cambio"));
                //aqui actualizar el adaptador
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}