package com.gurzelai.mantenimientocoches;

public class Repostaje {

    String fecha;
    double precio, cantidad, costeRepostaje;
    String gasolinera, forma_de_pago;

    public Repostaje(String fecha, double precio, double cantidad, double costeRepostaje, String gasolinera, String forma_de_pago) {
        this.fecha = fecha;
        this.precio = precio;
        this.cantidad = cantidad;
        this.costeRepostaje = costeRepostaje;
        this.gasolinera = gasolinera;
        this.forma_de_pago = forma_de_pago;
    }
}
