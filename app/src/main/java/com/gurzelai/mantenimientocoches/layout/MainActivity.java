package com.gurzelai.mantenimientocoches.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gurzelai.mantenimientocoches.Cambio;
import com.gurzelai.mantenimientocoches.Coche;
import com.gurzelai.mantenimientocoches.R;
import com.gurzelai.mantenimientocoches.adaptadores.AdaptadorCoche;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final int CODE_NUEVO_COCHE = 1;

    ListView lvCoches;
    AdaptadorCoche adaptadorCoche;

    List<Coche> listaCoches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Tus coches");
        inicializar();
        reconocer();
    }

    private void reconocer() {
        adaptadorCoche = new AdaptadorCoche(this, R.layout.list_item_coche, listaCoches);
        lvCoches = (ListView) findViewById(R.id.lvCoches);
        lvCoches.setAdapter(adaptadorCoche);
        onClicks();
    }

    private void onClicks() {

        lvCoches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MostrarCoche.class);
                intent.putExtra("coche", listaCoches.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_NUEVO_COCHE) {
            if(resultCode == Activity.RESULT_OK){
                listaCoches.add((Coche) data.getSerializableExtra("nuevo coche"));
                adaptadorCoche.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    private void inicializar() {
        listaCoches = new ArrayList<>();
        cargarDatos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.nuevocoche_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nuevoCoche:
                Intent intent = new Intent(getApplicationContext(), NuevoCoche.class);
                startActivityForResult(intent, CODE_NUEVO_COCHE);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void cargarDatos() {
        Coche c = new Coche("Mi coche", "Audi", "A3", "SS 0458 BK", 1999, 355000);
        c.addCambio(new Cambio("Rueda pichada", "Jaso", 10, "Hoy", Cambio.TipoCambio.REPARACION));
        c.addCambio(new Cambio("b", "Jaso", 10, "Hoy", Cambio.TipoCambio.MANTENIMIENTO));
        c.addCambio(new Cambio("c", "Jaso", 10, "Hoy", Cambio.TipoCambio.MANTENIMIENTO));
        c.addCambio(new Cambio("d", "Jaso", 33, "Ayer", Cambio.TipoCambio.REPARACION));
        c.addCambio(new Cambio("e", "Jaso", 23, "Hoy", Cambio.TipoCambio.MANTENIMIENTO));
        c.addCambio(new Cambio("f", "Jaso", 50, "Hoy", Cambio.TipoCambio.MANTENIMIENTO));
        c.addCambio(new Cambio("g", "Jaso", 10, "Hoy", Cambio.TipoCambio.REPARACION));
        listaCoches.add(c);
    }
}