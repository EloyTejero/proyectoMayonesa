package org.example;


import org.example.enums.TAMAÑO;

public class Producto {
    private String nombre;
    private double precio;
    private TAMAÑO tamaño;

    public Producto(String nombre, double precio, TAMAÑO tamaño) {
        this.nombre = nombre;
        this.precio = precio;
        this.tamaño = tamaño;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public TAMAÑO getTamaño() {
        return tamaño;
    }

    @Override
    public String toString() {
        return "Producto :" +
                "  nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", tamaño=" + tamaño;
    }
}
