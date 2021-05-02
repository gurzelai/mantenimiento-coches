package com.gurzelai.mantenimientocoches.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.gurzelai.mantenimientocoches.Cambio;
import com.gurzelai.mantenimientocoches.R;

public class NuevoCambio extends AppCompatActivity {

    Button btnConfirmar;
    EditText etDescripcion, etTaller, etCoste, etFecha, etExplicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_cambio);
        reconocer();
        setOnClick();
    }

    private void reconocer() {
        btnConfirmar = findViewById(R.id.btnConfirmar);
        etDescripcion = findViewById(R.id.etDescripcion);
        etTaller = findViewById(R.id.etTaller);
        etCoste = findViewById(R.id.etCoste);
        etFecha = findViewById(R.id.etFecha);
    }

    public Cambio crearNuevoCambio() {
        Cambio.TipoCambio tipo_cambio = (Cambio.TipoCambio) getIntent().getSerializableExtra("Tipo cambio");
        String descripcion = etDescripcion.getText().toString();
        String explicacion = etExplicacion.getText().toString();
        String taller = etTaller.getText().toString();
        String fecha = etFecha.getText().toString();
        int coste = 0;
        if (!etCoste.getText().toString().equals("")) {
            coste = Integer.valueOf(etCoste.getText().toString());
        }
        return new Cambio(descripcion, explicacion, taller, coste, fecha, tipo_cambio);
    }

    private void setOnClick() {
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cambio cambio = crearNuevoCambio();
                Intent resultIntent = new Intent(String.valueOf(getApplicationContext()));
                resultIntent.putExtra("nuevo cambio", cambio);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}