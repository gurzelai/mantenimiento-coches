package com.gurzelai.mantenimientocoches;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Coche implements Serializable {

    public Informacion informacion;
    List<Cambio> listaCambios;

    public Coche(String nombre, String fabricante, String modelo, String matricula, int anio, int kilometros) {

        informacion = new Informacion(fabricante, modelo, nombre, matricula, anio, kilometros);
        listaCambios = new ArrayList<>();

    }


    public void addCambio(Cambio cambio) {
        listaCambios.add(cambio);
    }

    public String getNombre() {
        return informacion.getNombre();
    }

    public List<Cambio> getListaCambios() {
        return listaCambios;
    }

    public static class Informacion implements Serializable {
        String fabricante, modelo, nombre, matricula;
        int anio, kilometros;

        public Informacion(String fabricante, String modelo, String nombre, String matricula, int anio, int kilometros) {
            this.fabricante = fabricante;
            this.modelo = modelo;
            this.nombre = nombre;
            this.matricula = matricula;
            this.anio = anio;
            this.kilometros = kilometros;
        }

        public String getModelo() {
            return modelo;
        }

        public String getFabricante() {
            return fabricante;
        }

        public String getNombre() {
            return nombre;
        }

        public int getAnio() {
            return anio;
        }
        public int getKilometros() {
            return kilometros;
        }
    }
}
