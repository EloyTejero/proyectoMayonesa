package org.example;

import java.util.ArrayList;

public class Pedido {
    private ArrayList<Combo> combos;
    private ArrayList<Producto> productos;

    public Pedido() {
        combos = new ArrayList<>();
        productos = new ArrayList<>();
    }

    public void añadirProducto(Producto producto){
        productos.add(producto);
    }

    public void añadirCombo(Combo combo){
        combos.add(combo);
    }

    public void eliminarProducto(Producto producto){
        productos.remove(producto);
    }

    public void eliminarCombo(Combo combo){
        combos.remove(combo);
    }

    public ArrayList<Combo> getCombos() {
        return combos;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }
}
