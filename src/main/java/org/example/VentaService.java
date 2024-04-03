package org.example;

import java.util.ArrayList;
import org.example.enums.TAMAÑO;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;

public class VentaService {

    public void generarVenta(List<Producto> productos, List<Combo> combos, Scanner entrada, Caja caja) {
        Pedido pedido = new Pedido();
        int eleccionComida;
        int seguirIngresando = 1;
        do {
            System.out.println("Seleccione si desea ver los 1-COMBOS o  2-PRODUCTOS");
            int seleccionTipoPedido = Integer.parseInt(entrada.nextLine());
            while (seleccionTipoPedido < 1 || seleccionTipoPedido > 2) {
                System.out.println("ERROR, INGRESE NUEVAMENTE");
                System.out.println("Seleccione si desea ver los 1-COMBOS o  2-PRODUCTOS");
                seleccionTipoPedido = Integer.parseInt(entrada.nextLine());
            }
            if (seleccionTipoPedido == 1) {
                System.out.println("COMBOS EXISTENTES");
                mostrarListas(combos);
                System.out.println("INGRESE EL ID DEL COMBO QUE DESEA AGREGAR AL PEDIDO");
                eleccionComida = Integer.parseInt(entrada.nextLine()) - 1;
                while (eleccionComida < 0 || eleccionComida > combos.size() - 1) {
                    System.out.println("ID NO VALIDO, INGRESE NUEVAMENTE");
                    System.out.println("INGRESE EL ID DEL COMBO QUE DESEA AGREGAR");
                    eleccionComida = Integer.parseInt(entrada.nextLine()) - 1;
                }
                Combo comboElegido = combos.get(eleccionComida);

                System.out.println("DESEA AGRANDAR ALGUN PRODUCTO 1-SI , 2- NO?");
                int agrandar = Integer.parseInt(entrada.nextLine());
                while (agrandar < 1 || agrandar > 2) {
                    System.out.println("ERROR, INGRESE NUEVAMENTE");
                    System.out.println("DESEA AGRANDAR ALGUN PRODUCTO 1-SI , 2- NO?");
                    agrandar = Integer.parseInt(entrada.nextLine());
                }
                if (agrandar == 1) {
                    agrandarCombo(comboElegido, productos, entrada);
                }
                pedido.añadirCombo(comboElegido);

            } else {
                mostrarListas(productos);
                System.out.println("INGRESE EL ID DEL PRODUCTO QUE DESEA AGREGAR AL PEDIDO");
                eleccionComida = Integer.parseInt(entrada.nextLine()) - 1;
                while (eleccionComida < 0 || eleccionComida > productos.size() - 1) {
                    System.out.println("ID NO VALIDO, INGRESE NUEVAMENTE");
                    System.out.println("INGRESE EL ID DEL PRODUCTO QUE DESEA AGREGAR");
                    eleccionComida = Integer.parseInt(entrada.nextLine()) - 1;
                }
                pedido.añadirProducto(productos.get(eleccionComida));
            }
            System.out.println("DESEA SEGUIR INGRESANDO? 1-SI ,2-NO");

            seguirIngresando = Integer.parseInt(entrada.nextLine());
            while (seguirIngresando < 1 || seguirIngresando > 2) {
                System.out.println("ERROR, INGRESE NUEVAMENTE");
                System.out.println("DESEA SEGUIR INGRESANDO? 1-SI ,2-NO");

                seguirIngresando = Integer.parseInt(entrada.nextLine());
            }
        } while (seguirIngresando == 1);

        Venta venta = new Venta(pedido);
        caja.AñadirVenta(venta);
        venta.imprimirTicket();
    }

    private void agrandarCombo(Combo combo, List<Producto> productos, Scanner entrada) {
        mostrarListas(combo.getProductos());
        System.out.println("Ingrese el ID del producto a agrandar");
        int id = Integer.parseInt(entrada.nextLine()) - 1;
        while (id < 0 || id > combo.getProductos().size() - 1) { 
            System.out.println("ID NO INVALIDO, INGRESE NUEVAMENTE");
            System.out.println("Ingrese el ID del producto a agrandar");
            id = Integer.parseInt(entrada.nextLine()) - 1;
        }
        Producto productoAAgrandar = combo.getProductos().get(id);
        System.out.println("INGRESE EL ID DEL TAMAÑO A AGRANDAR");

        
        List<TAMAÑO> listaTamaños= new ArrayList<>(EnumSet.allOf(TAMAÑO.class));
        listaTamaños.remove(productoAAgrandar.getTamaño());
        mostrarListas(listaTamaños);

        int idAgrandar = Integer.parseInt(entrada.nextLine()) - 1;
        while (idAgrandar < 0 || idAgrandar > listaTamaños.size() - 1) {
            System.out.println("ID NO INVALIDO, INGRESE NUEVAMENTE");
            System.out.println("Ingrese el ID del producto a agrandar");
            idAgrandar = Integer.parseInt(entrada.nextLine()) - 1;
        }
        TAMAÑO tamañoNuevo = TAMAÑO.values()[idAgrandar];
        Producto productoAgrandado = null;
        for (Producto p : productos) {
            if (p.getNombre().equals(productoAAgrandar.getNombre()) && p.getTamaño().equals(tamañoNuevo)) {
                productoAgrandado = p;
                break;
            }
        }
        combo.agrandarProducto(productoAAgrandar, productoAgrandado);
        System.out.println("*---------------COMBO AGRANDADO---------------*");
    }

    private <E> void mostrarListas(List<E> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("ID:" + (i + 1) + "  " + list.get(i));
        }
    }
 
}
