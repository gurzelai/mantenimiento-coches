package com.gurzelai.mantenimientocoches.layout;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.gurzelai.mantenimientocoches.DatePickerFragment;
import com.gurzelai.mantenimientocoches.R;
import com.gurzelai.mantenimientocoches.Repostaje;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NuevoRepostaje extends AppCompatActivity {

    EditText etFecha, etCoste, etPrecio, etCantidad, etGasolinera, etFormaDePago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_repostaje);
        setTitle("Nuevo repostaje");
        reconocer();
    }

    private void reconocer() {
        etCantidad = findViewById(R.id.etCantidad);
        etCoste = findViewById(R.id.etCoste);
        etGasolinera = findViewById(R.id.etGasolinera);
        etFecha = findViewById(R.id.etFecha);
        etFecha.setOnClickListener(v -> showDatePickerDialog());
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
        Button hoy = findViewById(R.id.hoy);
        hoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd / MM / yyyy");
                Date date = new Date();
                etFecha.setText(formatter.format(date));
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
                        double a = Double.valueOf(etCoste.getText().toString());
                        double b = Double.valueOf(etPrecio.getText().toString());
                        etCantidad.setText("" + ((int) (a / b)));
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
                    if (!etCoste.getText().toString().equals("") && !etCantidad.getText().toString().equals("") && etPrecio.getText().toString().equals("")) {
                        double a = Double.valueOf(etCantidad.getText().toString());
                        double b = Double.valueOf(etCoste.getText().toString());
                        etPrecio.setText("" + ((int) (b / a)));
                    }
                }
            }
        });
    }


    private Repostaje crearNuevoRepostaje() {
        return new Repostaje(etFecha.getText().toString(), (etPrecio.getText().toString().equals("")) ? -1 : Double.parseDouble(etPrecio.getText().toString()), (etCantidad.getText().toString().equals("")) ? -1 : Double.parseDouble(etCantidad.getText().toString()), (etCoste.getText().toString().equals("")) ? -1 : Double.parseDouble(etCoste.getText().toString()), etGasolinera.getText().toString(), etFormaDePago.getText().toString());
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month + 1) + " / " + year;
                etFecha.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}