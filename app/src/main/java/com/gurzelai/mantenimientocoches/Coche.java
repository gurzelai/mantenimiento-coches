package com.gurzelai.mantenimientocoches;

import android.media.Image;

import java.util.ArrayList;
import java.util.List;

public class Coche {

    int id;
    Image imagenCoche;

    Informacion informacion;

    List<Cambio> cambios;

    public Coche(String nombre, String fabricante, String modelo, String matricula, String conbustible, int anio, int kilometros, int id, Image imagenCoche) {
        this.id = id;
        this.imagenCoche = imagenCoche;
        informacion = new Informacion(fabricante, modelo, nombre, matricula, anio, kilometros);
        cambios = new ArrayList<>();
    }

    public class Informacion {
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
    }
}
