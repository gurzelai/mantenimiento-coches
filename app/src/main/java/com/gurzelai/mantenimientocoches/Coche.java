package com.gurzelai.mantenimientocoches;

import android.media.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Coche implements Serializable {

    Image imagenCoche;
    Informacion informacion;
    List<Cambio> cambios;

    public Coche(String nombre, String fabricante, String modelo, String matricula, int anio, int kilometros) {

        //this.imagenCoche = imagenCoche;
        informacion = new Informacion(fabricante, modelo, nombre, matricula, anio, kilometros);
        //cambios = new ArrayList<>();
    }

    public String getNombre() {
        if(informacion.nombre != ""){
            return informacion.getNombre();
        }
        else{
            return informacion.getFabricante() + " - "+informacion.getModelo();
        }
    }

    public class Informacion implements Serializable{
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

    }
}
