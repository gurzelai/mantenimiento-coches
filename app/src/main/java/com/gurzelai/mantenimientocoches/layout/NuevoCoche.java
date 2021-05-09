package com.gurzelai.mantenimientocoches.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gurzelai.mantenimientocoches.Coche;
import com.gurzelai.mantenimientocoches.R;

public class NuevoCoche extends AppCompatActivity {

    EditText etNombre, etFabricante, etModelo, etAnio, etMatricula, etKilometros, etConsumo;
    TextView tvKilometros;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_coche);
        setTitle("Nuevo coche");
        reconocer();
    }

    private void reconocer() {
        etNombre = findViewById(R.id.etNombre);
        etFabricante = findViewById(R.id.etFabricante);
        etModelo = findViewById(R.id.etModelo);
        etMatricula = findViewById(R.id.etMatricula);
        etAnio = findViewById(R.id.etAnio);
        etKilometros = findViewById(R.id.etKilometros);
        tvKilometros = findViewById(R.id.tvKilometros);
        etKilometros.setHint(etKilometros.getHint().toString());
        setOnClick();
    }

    private void setOnClick() {
        Button confirmar = findViewById(R.id.btnConfirmar);
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Coche coche = crearNuevoCoche();
                Intent resultIntent = new Intent(String.valueOf(getApplicationContext()));
                resultIntent.putExtra("nuevo coche", coche);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }


    private Coche crearNuevoCoche() {

        String nombre = etNombre.getText().toString();
        String fabricante = etFabricante.getText().toString();
        String modelo = etModelo.getText().toString();
        String matricula = etMatricula.getText().toString();
        int anio = -1, kilometros = -1;

        if (!etAnio.getText().toString().equals("")) {
            anio = Integer.parseInt(etAnio.getText().toString());
        }
        if (!etKilometros.getText().toString().equals("")) {
            kilometros = Integer.parseInt(etKilometros.getText().toString());
        }

        return new Coche(nombre, fabricante, modelo, matricula, anio, kilometros);
    }
}