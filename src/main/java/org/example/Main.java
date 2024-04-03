package org.example;

import org.example.enums.ROL;
import org.example.enums.TAMAÑO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner entrada = new Scanner(System.in);
    private static Usuario primerUsuario = new Usuario("Juan", 12345, "12", ROL.CAJERO);
    private static Usuario usuarioActual = null;
    private static Caja caja = new Caja(new Balance(0), primerUsuario);
    private static List<Combo> combos = new ArrayList<>();
    private static List<Producto> productos = new ArrayList<>();

    public static void main(String[] args) {

        int seguirIngresando = 1;
        productos.add(new Producto("BIGMAC", 450, TAMAÑO.CHICO));
        productos.add(new Producto("BIGMAC", 550, TAMAÑO.MEDIANO));
        productos.add(new Producto("BIGMAC", 800, TAMAÑO.GRANDE));

        productos.add(new Producto("PAPAS", 200, TAMAÑO.CHICO));
        productos.add(new Producto("PAPAS", 400, TAMAÑO.MEDIANO));
        productos.add(new Producto("PAPAS", 600, TAMAÑO.GRANDE));

        ArrayList<Producto> productosParaCombo = new ArrayList<>();
        productosParaCombo.add(productos.get(0));
        productosParaCombo.add(productos.get(3));
        combos.add(new Combo("MEGADIBU", 2000, productosParaCombo));

        do {
            menuCajero();
            System.out.println("DESEA VOLVER AL MENU? 1-SI ,2-NO");
            seguirIngresando = Integer.parseInt(entrada.nextLine());
            while (seguirIngresando < 1 || seguirIngresando > 2) {
                System.out.println("ERROR, INGRESE NUEVAMENTE");
                System.out.println("DESEA VOLVER AL MENU? 1-SI ,2-NO");
                seguirIngresando = Integer.parseInt(entrada.nextLine());

            }
        } while (seguirIngresando == 1);

    }

    private static void menuCajero() {
        System.out.println("Digite la opcion");
        System.out.println("OPCIONES:");
        System.out.println("1- GENERAR VENTA");
        System.out.println("2- CREAR PRODUCTO");
        System.out.println("3- CREAR COMBO");
        int opcion = Integer.parseInt(entrada.nextLine());
        while (opcion < 1 || opcion > 3) {
            System.out.println("ERROR, INGRESE NUEVAMENTE");
            System.out.println("Digite la opcion");
            System.out.println("OPCIONES:");
            System.out.println("1- GENERAR VENTA");
            System.out.println("2- CREAR PRODUCTO");
            System.out.println("3- CREAR COMBO");
            opcion = Integer.parseInt(entrada.nextLine());
        }

        switch (opcion) {
            case 1 ->
                menuVenta();
            case 2 ->
                menuCrearProducto();
            case 3 ->
                menuCrearCombo();
        }
    }

    private static void menuVenta() {
        VentaService service = new VentaService();
        service.generarVenta(productos, combos, entrada, caja);
    }

    private static void menuCrearProducto() {
        System.out.println("digite el nombre del producto");
        String nombre = entrada.nextLine();
        System.out.println("digite el precio");
        double precio = Double.parseDouble(entrada.nextLine());
        System.out.println("Ingrese el Tamaño");
        mostrarListas(Arrays.stream(TAMAÑO.values()).toList());
        int indexTamaño = Integer.parseInt(entrada.nextLine()) - 1;
        TAMAÑO tamaño = TAMAÑO.values()[indexTamaño];
        productos.add(new Producto(nombre, precio, tamaño));

    }

    private static void menuCrearCombo() {
        List<Producto> productosCombo = new ArrayList<>();
        System.out.println("digite el nombre del combo");
        String nombre = entrada.nextLine();
        System.out.println("digite el precio");
        double precio = Double.parseDouble(entrada.nextLine());
        int ingresar = 1;
        do {
            System.out.println("ELIJA LOS PRODUCTOS PARA SU COMBO");
            mostrarListas(productos);
            System.out.println("INGRESE EL ID del Producto");
            int id = Integer.parseInt(entrada.nextLine()) - 1;
            while (id < 0 || id > productos.size() - 1) {
                System.out.println("ERROR, INGRESE NUEVAMENTE");
                System.out.println("INGRESE EL ID del Producto");
                id = Integer.parseInt(entrada.nextLine()) - 1;
            }

            productosCombo.add(productos.get(id));
            System.out.println("DESEA SEGUIR CARGANDO PRODUCTOS AL COMBO? 1-SI, 2-NO");
            ingresar = Integer.parseInt(entrada.nextLine());
            while (ingresar < 1 || ingresar > 2) {
                System.out.println("ERROR, INGRESE NUEVAMENE");
                System.out.println("DESEA SEGUIR CARGANDO PRODUCTOS AL COMBO? 1-SI, 2-NO");
                ingresar = Integer.parseInt(entrada.nextLine());
            }
        } while (ingresar == 1);
        combos.add(new Combo(nombre, precio, productosCombo));

    }

    private static <E> void mostrarListas(List<E> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("ID:" + (i + 1) + "  " + list.get(i));
        }

    }

    private static void login() {
        UserService userService = UserService.userServiceGetInstance();

        do {
            Scanner in = new Scanner(System.in);
            System.out.print("Ingrese su usuario: ");
            String user = in.nextLine();
            System.out.print("Ingrese contraseña: ");
            String password = in.nextLine();

            usuarioActual = userService.login(user, password);
            if (usuarioActual == null) {
                System.out.println("Error en la contraseña o usuario");
                System.out.println();
            }
        } while (usuarioActual == null);

        System.out.println("Ingreso Exitoso");
    }

}
