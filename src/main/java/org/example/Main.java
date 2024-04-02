package org.example;

import org.example.enums.ROL;
import org.example.enums.TAMAÑO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner entrada = new Scanner(System.in);
  //  private static List<Combo> combos = new ArrayList<>();
    private static List<Producto> productos = new ArrayList<>();
    public static void main(String[] args) {

        Usuario primerUsuario = new Usuario("Juan",12345, "12", ROL.CAJERO);
        Caja caja = new Caja(new Balance(0),primerUsuario);
        productos.add(new Producto("BIGMAC",400, TAMAÑO.CHICO));
        productos.add(new Producto("BIGMAC",500, TAMAÑO.MEDIANO));
        productos.add(new Producto("BIGMAC",600, TAMAÑO.GRANDE));
        menuCajero();



    }



    private static void menuCajero(){
        System.out.println("Digite la opcion");
        System.out.println("OPCIONES:");
        System.out.println("1- GENERAR VENTA");
        int opcion = entrada.nextInt();

        switch (opcion){
            case 1: menuVenta();
        }
    }

    private static void menuVenta(){
        Pedido pedido = new Pedido();

        int eleccionComida;
        int seguirIngresando = 1;
        do {
            System.out.println("Seleccione si desea ver los 1-COMBOS o  2-PRODUCTOS");
            int seleccionTipoPedido = entrada.nextInt();
            if(seleccionTipoPedido == 1){
                System.out.println("COMBOS EXISTENTES");
                //mostrarListas(combos);
                System.out.println("INGRESE EL ID DEL COMBO QUE DESEA AGREGAR");
                eleccionComida = entrada.nextInt() -1;
                //pedido.añadirCombo(combos.get(eleccionComida));
            }else{
                mostrarListas(productos);
                System.out.println("INGRESE EL ID DEL PRODUCTO QUE DESEA AGREGAR");
                eleccionComida = entrada.nextInt() -1;
                pedido.añadirProducto(productos.get(eleccionComida));
            }
            System.out.println("DESEA SEGUIR INGRESANDO? 1-SI ,2-NO");
            seguirIngresando = entrada.nextInt();
        }while (seguirIngresando == 1);

        Venta venta = new Venta(pedido);
        venta.imprimirTicket();




    }


    private static <E> void mostrarListas (List<E> list){
        for (int i = 0; i<list.size();i++){
            System.out.println("ID:" + (i+1) + "  " + list.get(i));
        }

    }




}