package com.gurzelai.mantenimientocoches.layout;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gurzelai.mantenimientocoches.Cambio;
import com.gurzelai.mantenimientocoches.DatePickerFragment;
import com.gurzelai.mantenimientocoches.R;

public class NuevoCambio extends AppCompatActivity {

    Button btnConfirmar;
    EditText etDescripcion, etTaller, etCoste, etFecha, etExplicacion;
    TextView tvTipoCambio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_cambio);
        reconocer();
        setTitle("Nuevo cambio");
    }

    private void reconocer() {
        tvTipoCambio = findViewById(R.id.tvTipo);
        tvTipoCambio.setText(((Cambio.TipoCambio) getIntent().getSerializableExtra("Tipo cambio") == Cambio.TipoCambio.REPARACION) ? "Nueva reparación" : "Nuevo mantenimiento");
        btnConfirmar = findViewById(R.id.btnConfirmar);
        etDescripcion = findViewById(R.id.etDescripcion);
        etExplicacion = findViewById(R.id.etExplicacion);
        etTaller = findViewById(R.id.etTaller);
        etCoste = findViewById(R.id.etCoste);
        etFecha = findViewById(R.id.etFecha);
        etFecha.setOnClickListener(v -> showDatePickerDialog());
        setOnClick();
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
    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                etFecha.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}