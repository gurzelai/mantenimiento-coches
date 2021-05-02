package com.gurzelai.mantenimientocoches.layout;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gurzelai.mantenimientocoches.Coche;
import com.gurzelai.mantenimientocoches.R;

import java.io.IOException;

public class NuevoCoche extends AppCompatActivity {

    final int PERMISO_CAMARA = 1;
    final int CODIGO_FOTO = 100, CODIGO_GALERIA = 200;

    EditText etNombre, etFabricante, etModelo, etAnio, etMatricula, etKilometros;
    TextView tvKilometros;
    Uri pathImagen;
    ImageView imagenCoche;
    Button btnConfirmar;
    FloatingActionButton btnCamara;
    Bitmap b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_coche);
        reconocer();
    }

    private void reconocer() {
        etNombre = findViewById(R.id.etNombre);
        etFabricante = findViewById(R.id.etFabricante);
        etModelo = findViewById(R.id.etModelo);
        etMatricula = findViewById(R.id.etMatricula);
        etAnio = findViewById(R.id.etAnio);
        etKilometros = findViewById(R.id.etKilometros);
        btnConfirmar = findViewById(R.id.btnConfirmar);
        btnCamara = findViewById(R.id.btnCamara);
        imagenCoche = findViewById(R.id.imagenCoche);
        tvKilometros = findViewById(R.id.tvKilometros);
        if (getUnidad().equals(" km")) {
            tvKilometros.setText("Kilometros");
        } else {
            tvKilometros.setText("Millas");
        }
        etKilometros.setHint(etKilometros.getHint().toString()+getUnidad());
        setOnClick();
    }

    public String getUnidad() {
        SharedPreferences sharedPreferences = getSharedPreferences("Ajustes", Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("KM", true)) {
            return " km";
        } else {
            return " mi";
        }
    }

    private void setOnClick() {
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Coche coche = crearNuevoCoche();
                Intent resultIntent = new Intent(String.valueOf(getApplicationContext()));
                resultIntent.putExtra("nuevo coche", coche);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence[] opciones = {"Tomar foto", "Elegir de galería", "Cancelar"};
                AlertDialog.Builder builder = new AlertDialog.Builder(NuevoCoche.this);
                builder.setTitle("Elige una opción:");
                builder.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int opcion) {
                        if(opciones[opcion] == "Tomar foto"){
                            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {// Marshmallow+
                                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(NuevoCoche.this, new String[]{Manifest.permission.CAMERA}, PERMISO_CAMARA);
                                } else {
                                    abrirCamara();
                                }
                            } else {
                                abrirCamara();
                            }
                        }else if(opciones[opcion] == "Elegir de galería"){
                            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/*");
                            startActivityForResult(Intent.createChooser(intent, "Selecciona app de imagen"), CODIGO_GALERIA);
                        }else {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });
    }

    public void abrirCamara (){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,CODIGO_FOTO);
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

        //Bitmap bitmap = ((BitmapDrawable)imagenCoche.getDrawable()).getBitmap();

        return new Coche(nombre, fabricante, modelo, matricula, anio, kilometros);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case CODIGO_FOTO:
                    break;
                case CODIGO_GALERIA:
                    pathImagen = data.getData();
                    //imagenCoche.setImageURI(pathImagen);
                    try {
                         b = MediaStore.Images.Media.getBitmap(this.getContentResolver(), pathImagen);
                        imagenCoche.setImageBitmap(b);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

            }
        }
    }
}