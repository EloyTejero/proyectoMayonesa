package org.example;

import org.example.enums.ROL;
import org.example.enums.TAMAÑO;

import java.util.*;


public class Main {

    private static Scanner entrada = new Scanner(System.in);
    
    private static Usuario primerUsuario = new Usuario("juan", 12345, "12", ROL.CAJERO);
    private static Usuario segundoUsuario = new Usuario("jua", 12346, "12", ROL.GERENTE);

    private static Usuario usuarioActual = null;
    private static UserService userService = UserService.userServiceGetInstance();

    
    private static Caja caja = new Caja(new Balance(0), primerUsuario);
    private static List<Combo> combos = new ArrayList<>();
    private static List<Producto> productos = new ArrayList<>();

    public static void main(String[] args) {

        userService.addUser(primerUsuario);
        userService.addUser(segundoUsuario);
        
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
        
        
        int seguirIngresando = 1;
        do {
            
            login();
            if(userService.isAdmin(usuarioActual)){
                menuAdmin();
                System.out.println("1. INGRESAR CON OTRA CUENTA");
                System.out.println("2. SALIR");
                seguirIngresando = Integer.parseInt(entrada.nextLine());
                while (seguirIngresando < 1 || seguirIngresando > 2) {
                    System.out.println("1. INGRESAR CON OTRA CUENTA");
                    System.out.println("2. SALIR");
                    seguirIngresando = Integer.parseInt(entrada.nextLine());
                }
            }
            if(!userService.isAdmin(usuarioActual)){
                menuCajero();
                System.out.println("1. INGRESAR CON OTRA CUENTA");
                System.out.println("2. SALIR");
                seguirIngresando = Integer.parseInt(entrada.nextLine());
                while (seguirIngresando < 1 || seguirIngresando > 2) {
                    System.out.println("1. INGRESAR CON OTRA CUENTA");
                    System.out.println("2. SALIR");
                    seguirIngresando = Integer.parseInt(entrada.nextLine());
                }
            }
            
        } while (seguirIngresando == 1);

    }

    private static void menuAdmin() {
        int opcion = 0;
        do{
            System.out.println("Digite la opcion");
            System.out.println("OPCIONES:");
            System.out.println("1- GENERAR VENTA");
            System.out.println("2- CREAR PRODUCTO");
            System.out.println("3- CREAR COMBO");
            System.out.println("4- BALANCE");
            System.out.println("5- AÑADIR USUARIOS");
            System.out.println("6- VER USUARIOS");
            System.out.println("7- ELIMINAR USUARIOS");
            System.out.println("8- SALIR");
            opcion = Integer.parseInt(entrada.nextLine());
            while (opcion < 1 || opcion > 8) {
                System.out.println("ERROR, INGRESE NUEVAMENTE");
                System.out.println("Digite la opcion");
                System.out.println("OPCIONES:");
                System.out.println("1- GENERAR VENTA");
                System.out.println("2- CREAR PRODUCTO");
                System.out.println("3- CREAR COMBO");
                System.out.println("4- BALANCE");
                System.out.println("5- AÑADIR USUARIOS");
                System.out.println("6- VER USUARIOS");
                System.out.println("7- ELIMINAR USUARIOS");
                System.out.println("8- SALIR");

                opcion = Integer.parseInt(entrada.nextLine());
            }

            switch (opcion) {
                case 1 ->
                    menuVenta();
                case 2 ->
                    menuCrearProducto();
                case 3 ->
                    menuCrearCombo();
                case 4 ->System.out.println(caja.getValorFinalBalance()); 
                case 5 -> anadirUsuarios();
                case 6 -> verUsuarios();
                case 7 -> eliminarUsuarios();
            }
            
        }while(opcion!=8);
    }
    
    private static void menuCajero(){
        int opcion;
        
        do{
            System.out.println("Digite la opcion");
            System.out.println("OPCIONES:");
            System.out.println("1- GENERAR VENTA");
            System.out.println("2- SALIR");
            opcion = Integer.parseInt(entrada.nextLine());
            while (opcion < 1 || opcion > 5) {
                System.out.println("ERROR, INGRESE NUEVAMENTE");
                System.out.println("Digite la opcion");
                System.out.println("OPCIONES:");
                System.out.println("1- GENERAR VENTA");
                System.out.println("2- SALIR");

                opcion = Integer.parseInt(entrada.nextLine());
            }

            switch (opcion) {
                case 1 ->
                    menuVenta();
            }
            
        }while(opcion!=2);
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
         while (precio <= 0) {
            System.out.println("ERROR, INGRESE NUEVAMENTE");
            System.out.println("digite el precio");

            precio = Double.parseDouble(entrada.nextLine());

        }
        System.out.println("Ingrese el Tamaño");
        mostrarListas(Arrays.stream(TAMAÑO.values()).toList());
        int indexTamaño = Integer.parseInt(entrada.nextLine()) - 1;
        while (indexTamaño < 0 || indexTamaño > Arrays.stream(TAMAÑO.values()).toList().size() - 1) {
            System.out.println("ERROR, INGRESE NUEVAMENTE");
            System.out.println("Ingrese el Tamaño");
            indexTamaño = Integer.parseInt(entrada.nextLine()) - 1;

        }

        TAMAÑO tamaño = TAMAÑO.values()[indexTamaño];
        productos.add(new Producto(nombre, precio, tamaño));

    }

    private static void menuCrearCombo() {
        List<Producto> productosCombo = new ArrayList<>();
        System.out.println("digite el nombre del combo");
        String nombre = entrada.nextLine();
        System.out.println("digite el precio");
        double precio = Double.parseDouble(entrada.nextLine());
          while (precio <= 0) {
            System.out.println("ERROR, INGRESE NUEVAMENTE");
            System.out.println("digite el precio");
            precio = Double.parseDouble(entrada.nextLine());
          }
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

        do {
            System.out.print("Ingrese su dni: ");
            int dni = Integer.parseInt(entrada.nextLine());
            System.out.print("Ingrese contraseña: ");
            String password = entrada.nextLine();

            usuarioActual = userService.login(dni, password);
            if (usuarioActual == null) {
                System.out.println("Error en la contraseña o usuario");
                System.out.println();
            }
        } while (usuarioActual == null);

        System.out.println("Ingreso Exitoso");
    }

    private static void anadirUsuarios() {
        System.out.print("INGRESE EL NOMBRE: ");
        String nombre = entrada.nextLine();
        System.out.print("INGRESE DNI: ");
        int dni = Integer.parseInt(entrada.nextLine());
        System.out.print("INGRESE CONTRASEÑA: ");
        String contraseña = entrada.nextLine();
        System.out.println("SELECCCIONES un ROL ");
        mostrarListas(Arrays.stream(ROL.values()).toList());
        int opcion = Integer.parseInt(entrada.nextLine())-1;
        while(opcion<0 || opcion>(ROL.values().length-1)){
            System.out.println("ERROR");
            System.out.println("SELECCCIONES un ROL ");
            mostrarListas(Arrays.stream(ROL.values()).toList());
            opcion = Integer.parseInt(entrada.nextLine())-1;
        }
        ROL rol = ROL.values()[opcion];
        
        Usuario user = new Usuario(nombre, dni, contraseña, rol);
        
        if(userService.getUserByDni(dni)!=null){
            System.out.println("EL USUARIO YA EXISTE");
        }
        else{
            userService.addUser(user);
        }
    }
    
    private static void verUsuarios(){
        for(Usuario u:userService.getUsers()){
            System.out.print(u.getNombre()+", ");
            System.out.println(u.getDni());
        }
    }

    private static void eliminarUsuarios() {
        System.out.print("INGRESE EL DNI DE LA CUENTA A ELIMINAR: ");
        int dni = Integer.parseInt(entrada.nextLine());
        Usuario usuarioABorrar = userService.getUserByDni(dni);
        if(usuarioABorrar==null){
            System.out.println("Usuario no existente");
        }else{
            userService.deleteUser(usuarioABorrar);
            System.out.println("USUARIO ELIMINADO");
        }
        
        System.out.println("USUARIOS RESTANTES");
        verUsuarios();
    }

}
