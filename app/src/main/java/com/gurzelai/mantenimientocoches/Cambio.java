package com.gurzelai.mantenimientocoches;

import android.media.Image;

public class Cambio {

    public enum TipoCambio{
        REPARACION, MANTENIMIENTO;
    }

    String descripcion, taller;
    TipoCambio tipoCambio;
    int coste;
    int fecha;
    Image factura;
    Image imagenCambio;

    public Cambio(String descripcion, int coste, int fecha, TipoCambio tipoCambio) {
        this.descripcion = descripcion;
        this.coste = coste;
        this.fecha = fecha;
        this.tipoCambio = tipoCambio;
    }

    public TipoCambio getTipoCambio() {
        return tipoCambio;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
