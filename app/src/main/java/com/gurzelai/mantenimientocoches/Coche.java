package com.gurzelai.mantenimientocoches;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Coche implements Serializable {

    public Informacion informacion;
    List<Cambio> listaCambios;
    List<Repostaje> listaRepostajes;

    public Coche(String nombre, String fabricante, String modelo, String matricula, int anio, int kilometros) {

        informacion = new Informacion(fabricante, modelo, nombre, matricula, anio, kilometros);
        listaCambios = new ArrayList<>();
        listaRepostajes = new ArrayList<>();
    }


    public void addCambio(Cambio cambio) {
        listaCambios.add(0, cambio);
    }

    public String getNombre() {
        return informacion.getNombre();
    }

    public List<Cambio> getListaCambios() {
        return listaCambios;
    }

    public String getInfoCoche() {
        return informacion.getFabricante() +((!informacion.getModelo().equals(""))?" - "+informacion.getModelo() : "");
    }

    public void addRepostaje(Repostaje nuevo_repostaje) {
        listaRepostajes.add(0,nuevo_repostaje);
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

        public String getMatricula() {
            return matricula;
        }
    }
}
