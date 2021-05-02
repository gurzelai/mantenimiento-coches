package com.gurzelai.mantenimientocoches.layout;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gurzelai.mantenimientocoches.Ajustes;
import com.gurzelai.mantenimientocoches.Cambio;
import com.gurzelai.mantenimientocoches.Coche;
import com.gurzelai.mantenimientocoches.R;
import com.gurzelai.mantenimientocoches.adaptadores.AdaptadorCambio;

public class MostrarCoche extends AppCompatActivity {

    final int CODE_RESULT_CAMBIO = 1;
    TextView tvNombre, tvFabricante, tvModelo, tvAnio, tvKilometros;
    FloatingActionButton btnMantenimiento, btnReparacion;
    Coche coche;
    AdaptadorCambio adaptadorCambio;
    ListView lvCambios;
    ImageView imagenCoche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_coche);
        setTitle("Informacion coche");
        coche = (Coche) getIntent().getSerializableExtra("coche");
        reconocer();
        setOnClick();
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
        lvCambios.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MostrarCoche.this);
                builder.setTitle("¿Quieres borrar este cambio?");
                builder.setMessage("Se borrarán todos los datos");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        coche.getListaCambios().remove(position);
                        adaptadorCambio.notifyDataSetChanged();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mostrar_coche_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.darDeBaja:


                return true;
            case R.id.eliminarCoche:
                AlertDialog.Builder builder = new AlertDialog.Builder(MostrarCoche.this);
                builder.setTitle("¿Quieres borrar este coche?");
                builder.setMessage("Se borrarán todos los datos");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent resultIntent = new Intent(String.valueOf(getApplicationContext()));
                        resultIntent.putExtra("Coche eliminado", getIntent().getExtras().getInt("posicion"));
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                    }
                });
                builder.setNegativeButton("Cancelar", null);
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void asignar() {
        tvNombre.setText(coche.informacion.getNombre());
        tvFabricante.setText(coche.informacion.getFabricante());
        tvModelo.setText(coche.informacion.getModelo());
        tvAnio.setText(String.valueOf((coche.informacion.getAnio() != -1) ? coche.informacion.getAnio() : ""));
        tvKilometros.setText(String.valueOf((coche.informacion.getKilometros() != -1) ? coche.informacion.getKilometros() + getUnidad() : ""));
    }

    public String getUnidad() {
        SharedPreferences sharedPreferences = getSharedPreferences("Ajustes", Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("KM", true)) {
            return " km";
        } else {
            return " mi";
        }
    }

    private void reconocer() {
        tvNombre = findViewById(R.id.tvNombre);
        tvFabricante = findViewById(R.id.tvFabricante);
        tvModelo = findViewById(R.id.tvModelo);
        tvAnio = findViewById(R.id.tvAnio);
        tvKilometros = findViewById(R.id.tvKilometros);
        btnMantenimiento = findViewById(R.id.btnMantenimiento);
        btnReparacion = findViewById(R.id.btnReparacion);
        lvCambios = findViewById(R.id.lvCambios);
        adaptadorCambio = new AdaptadorCambio(getApplicationContext(), R.layout.list_item_cambio, coche.getListaCambios());
        lvCambios.setAdapter(adaptadorCambio);
        imagenCoche = findViewById(R.id.imagenCoche);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_RESULT_CAMBIO) {
            if(resultCode == Activity.RESULT_OK){
                coche.addCambio((Cambio) data.getSerializableExtra("nuevo cambio"));
                adaptadorCambio.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}