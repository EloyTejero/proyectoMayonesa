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



}
