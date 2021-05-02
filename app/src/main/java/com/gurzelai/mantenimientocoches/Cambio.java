package com.gurzelai.mantenimientocoches;

import android.media.Image;

import java.io.Serializable;

public class Cambio implements Serializable{

    public enum TipoCambio implements Serializable {
        REPARACION, MANTENIMIENTO;
    }

    String descripcion, taller, explicacion;
    TipoCambio tipoCambio;
    int coste;
    String fecha;
    Image factura;
    Image imagenCambio;

    public Cambio(String descripcion, String explicacion, String taller, int coste, String fecha, TipoCambio tipoCambio) {
        this.descripcion = descripcion;
        this.taller = taller;
        this.coste = coste;
        this.fecha = fecha;
        this.tipoCambio = tipoCambio;
        this.explicacion = explicacion;
    }

    public TipoCambio getTipoCambio() {
        return tipoCambio;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
