package org.example;

import static java.lang.Math.abs;
import org.example.enums.TAMAÑO;

import java.util.List;

public class Combo {

    private String nombre;
    private List<Producto> productos;

    private double precio;

    public Combo(String nombre,double precio,List<Producto> productos) {
        this.nombre = nombre;
        this.productos = productos;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public double getPrecio() {
        return precio;
    }

    public void agrandarProducto(Producto aAgrandar, Producto productoAgrandado){
        for (Producto p : productos){
            if ( aAgrandar.getNombre().equals(p.getNombre()) && p.getNombre().equals(productoAgrandado.getNombre())){
                double diferenciaPrecio = abs(productoAgrandado.getPrecio() - p.getPrecio());
                precio = precio + diferenciaPrecio;
                productos.remove(p);
                productos.add(productoAgrandado);
                break;
            }
        }
    }

    public void achicarProducto(Producto aAchicar, Producto productoAchicado){
        for (Producto p : productos){
            if ( aAchicar.getNombre().equals(p.getNombre()) && p.getNombre().equals(productoAchicado.getNombre())){
                double diferenciaPrecio = abs(productoAchicado.getPrecio() - p.getPrecio());
                precio = precio - diferenciaPrecio;
                productos.remove(p);
                productos.add(productoAchicado);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Combo :" +
                "nombre='" + nombre + '\'' +
                ", productos=" + productos +
                ", precio=" + precio;
    }
}
