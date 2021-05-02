package com.gurzelai.mantenimientocoches;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;

public class Ajustes extends AppCompatActivity {

    AppCompatRadioButton radioKM, radioMilles;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    boolean unidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        sharedPreferences = getSharedPreferences("Ajustes", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        reconocer();
    }

    private void reconocer() {
        radioKM = findViewById(R.id.radioKM);
        radioMilles = findViewById(R.id.radioMILLES);

        radioMilles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("KM", false);
                editor.apply();
            }
        });
        radioKM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("KM", true);
                editor.apply();
            }
        });

        unidad = sharedPreferences.getBoolean("KM", true);
        if(unidad){
            radioKM.setChecked(true);
        }
        else
        {
            radioMilles.setChecked(true);
        }
    }
}