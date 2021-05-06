package com.gurzelai.mantenimientocoches.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.gurzelai.mantenimientocoches.R;
import com.gurzelai.mantenimientocoches.Repostaje;

public class NuevoRepostaje extends AppCompatActivity {

    EditText etFecha, etCoste, etPrecio, etCantidad, etGasolinera, etFormaDePago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_repostaje);

        reconocer();
    }

    private void reconocer() {
        etCantidad = findViewById(R.id.etCantidad);
        etCoste = findViewById(R.id.etCoste);
        etGasolinera = findViewById(R.id.etGasolinera);
        etFecha = findViewById(R.id.etFecha);
        etPrecio = findViewById(R.id.etPrecio);
        etFormaDePago = findViewById(R.id.etFormaDePago);
        Button btnConfirmar = findViewById(R.id.btnConfirmar);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Repostaje repostaje = crearNuevoRepostaje();
                Intent resultIntent = new Intent(String.valueOf(getApplicationContext()));
                resultIntent.putExtra("nuevo repostaje", repostaje);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        etPrecio.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (etCoste.getText().toString().equals("") && !etCantidad.getText().toString().equals("") && !etPrecio.getText().toString().equals("")) {
                        double a = Double.valueOf(etCantidad.getText().toString());
                        double b = Double.valueOf(etPrecio.getText().toString());
                        etCoste.setText("" + ((int) (a * b)));
                    }
                    if (!etCoste.getText().toString().equals("") && etCantidad.getText().toString().equals("") && !etPrecio.getText().toString().equals("")) {

                    }
                    if (!etCoste.getText().toString().equals("") && !etCantidad.getText().toString().equals("") && etPrecio.getText().toString().equals("")) {

                    }
                }
            }
        });
        etCantidad.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (etCoste.getText().toString().equals("") && !etCantidad.getText().toString().equals("") && !etPrecio.getText().toString().equals("")) {
                        double a = Double.valueOf(etCantidad.getText().toString());
                        double b = Double.valueOf(etPrecio.getText().toString());
                        etCoste.setText("" + ((int) (a * b)));
                    }
                    if (!etCoste.getText().toString().equals("") && etCantidad.getText().toString().equals("") && !etPrecio.getText().toString().equals("")) {

                    }
                    if (!etCoste.getText().toString().equals("") && !etCantidad.getText().toString().equals("") && etPrecio.getText().toString().equals("")) {

                    }
                }
            }
        });
    }

    private void precios() {
        if (etCoste.getText().toString().equals("") && !etCantidad.getText().toString().equals("") && !etPrecio.getText().toString().equals("")) {
            etCoste.setText((int) (Double.parseDouble(etCantidad.getText().toString()) * Double.parseDouble(etPrecio.getText().toString())));
        }
        if (!etCoste.getText().toString().equals("") && etCantidad.getText().toString().equals("") && !etPrecio.getText().toString().equals("")) {

        }
        if (!etCoste.getText().toString().equals("") && !etCantidad.getText().toString().equals("") && etPrecio.getText().toString().equals("")) {

        }
    }

    private Repostaje crearNuevoRepostaje() {
        return new Repostaje(etFecha.getText().toString(), Double.parseDouble(etPrecio.getText().toString()), Double.parseDouble(etCantidad.getText().toString()), Double.parseDouble(etCoste.getText().toString()), etGasolinera.getText().toString(), etFormaDePago.getText().toString());
    }
}