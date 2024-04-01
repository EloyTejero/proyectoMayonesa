package org.example;

public class Usuario {
    private String nombre;
    private int dni;
    private String contraseña;


    public Usuario(String nombre, int dni, String contraseña) {
        this.nombre = nombre;
        this.dni = dni;
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDni() {
        return dni;
    }

    public String getContraseña() {
        return contraseña;
    }
}
