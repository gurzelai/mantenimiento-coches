package com.gurzelai.mantenimientocoches.layout;

import android.app.Activity;
import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.gurzelai.mantenimientocoches.Ajustes;
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
        lvCoches.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("¿Quieres borrar este coche?");
                builder.setMessage("Se borrarán todos los datos");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listaCoches.remove(position);
                        adaptadorCoche.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancelar", null);
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
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
        Intent intent;
        switch (item.getItemId()) {
            case R.id.nuevoCoche:
                intent = new Intent(getApplicationContext(), NuevoCoche.class);
                startActivityForResult(intent, CODE_NUEVO_COCHE);
                return true;
            case R.id.ajustes:
                intent = new Intent(getApplicationContext(), Ajustes.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void cargarDatos() {
        Coche c = new Coche("Mi coche", "Audi", "A3", "SS 0458 BK", 1999, 355000);
        c.addCambio(new Cambio("Rueda pichada" , "as", "Jaso", 10, "Hoy", Cambio.TipoCambio.REPARACION));
        c.addCambio(new Cambio("b", "as","Jaso", 10, "Hoy", Cambio.TipoCambio.MANTENIMIENTO));
        c.addCambio(new Cambio("c", "as","Jaso", 10, "Hoy", Cambio.TipoCambio.MANTENIMIENTO));
        c.addCambio(new Cambio("d","as", "Jaso", 33, "Ayer", Cambio.TipoCambio.REPARACION));
        c.addCambio(new Cambio("e", "as","Jaso", 23, "Hoy", Cambio.TipoCambio.MANTENIMIENTO));
        c.addCambio(new Cambio("f", "as","Jaso", 50, "Hoy", Cambio.TipoCambio.MANTENIMIENTO));
        c.addCambio(new Cambio("g","as", "Jaso", 10, "Hoy", Cambio.TipoCambio.REPARACION));
        listaCoches.add(c);
    }
}