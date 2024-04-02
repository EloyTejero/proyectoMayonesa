package org.example;

import java.util.ArrayList;

public class Venta {
    private ArrayList<Combo> combos;
    private ArrayList<Producto> productos;
    private double valorTotal;

    public Venta(ArrayList<Combo> combos, ArrayList<Producto> productos) {
        this.combos = combos;
        this.productos = productos;
        valorTotal = calcularValorTotalVenta();
    }
    
    private double calcularValorTotalVenta(){
        double valor=0;
        for(Producto p:productos){
            valor += p.getPrecio();
        }
        /* //cuando este hecho combo
        for(Combo c:combos){
            valor += c.getPrecio();
        }
        */
        return valor;
    }
    
    public void imprimirTicket(){
        System.out.println("Listado de Combos: ");
        for(Combo c:combos){
            System.out.println(c.toString());
        }
        for(Producto p:productos){
            System.out.println(p.toString());
        }
        System.out.println("PRECIO TOTAL: "+valorTotal);
    }
}
