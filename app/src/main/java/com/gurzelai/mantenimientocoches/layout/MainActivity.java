package com.gurzelai.mantenimientocoches.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gurzelai.mantenimientocoches.Coche;
import com.gurzelai.mantenimientocoches.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Coche> listaCoches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaCoches = new ArrayList<>();
    }
}