package org.example;

import org.example.enums.ROL;
import org.example.enums.TAMAÑO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner entrada = new Scanner(System.in);
  //  private static List<Combo> combos = new ArrayList<>();
    private static List<Producto> productos = new ArrayList<>();
    public static void main(String[] args) {

        Usuario primerUsuario = new Usuario("Juan",12345, "12", ROL.CAJERO);
        Caja caja = new Caja(new Balance(0),primerUsuario);
        int seguirIngresando = 1;
      /*  productos.add(new Producto("BIGMAC",400, TAMAÑO.CHICO));
        productos.add(new Producto("BIGMAC",500, TAMAÑO.MEDIANO));
        productos.add(new Producto("BIGMAC",600, TAMAÑO.GRANDE));*/
        do {
            menuCajero();
            System.out.println("DESEA VOLVER AL MENU? 1-SI ,2-NO");
            seguirIngresando =  Integer.parseInt(entrada.nextLine());
        }while (seguirIngresando == 1);



    }



    private static void menuCajero(){
        System.out.println("Digite la opcion");
        System.out.println("OPCIONES:");
        System.out.println("1- GENERAR VENTA");
        System.out.println("2- CARGAR PRODUCTO");
        int opcion = Integer.parseInt(entrada.nextLine());

        switch (opcion){
            case 1: menuVenta();
               break;
            case 2: menuCargarProductos();
              break;
        }
    }

    private static void menuVenta(){
        Pedido pedido = new Pedido();

        int eleccionComida;
        int seguirIngresando = 1;
        do {
            System.out.println("Seleccione si desea ver los 1-COMBOS o  2-PRODUCTOS");
            int seleccionTipoPedido = Integer.parseInt(entrada.nextLine());
            if(seleccionTipoPedido == 1){
                System.out.println("COMBOS EXISTENTES");
                //mostrarListas(combos);
                System.out.println("INGRESE EL ID DEL COMBO QUE DESEA AGREGAR");
                eleccionComida = Integer.parseInt(entrada.nextLine()) -1;
                //pedido.añadirCombo(combos.get(eleccionComida));
            }else{
                mostrarListas(productos);
                System.out.println("INGRESE EL ID DEL PRODUCTO QUE DESEA AGREGAR");
                eleccionComida = Integer.parseInt(entrada.nextLine()) -1;
                pedido.añadirProducto(productos.get(eleccionComida));
            }
            System.out.println("DESEA SEGUIR INGRESANDO? 1-SI ,2-NO");
            seguirIngresando = Integer.parseInt(entrada.nextLine());;
        }while (seguirIngresando == 1);

        Venta venta = new Venta(pedido);
        venta.imprimirTicket();
    }


    private static void menuCargarProductos(){
        System.out.println("digite el nombre del producto");
        String nombre = entrada.nextLine();
        System.out.println("digite el precio");
        double precio = Double.parseDouble(entrada.nextLine());
        System.out.println("Ingrese el Tamaño");
        mostrarListas(Arrays.stream(TAMAÑO.values()).toList());
        int indexTamaño =Integer.parseInt(entrada.nextLine())-1;
        TAMAÑO tamaño = TAMAÑO.values()[indexTamaño];
        productos.add(new Producto(nombre, precio, tamaño));

    }




    private static <E> void mostrarListas (List<E> list){
        for (int i = 0; i<list.size();i++){
            System.out.println("ID:" + (i+1) + "  " + list.get(i));
        }

    }




}