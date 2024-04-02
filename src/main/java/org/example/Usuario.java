package org.example;

import org.example.enums.ROL;

public class Usuario {
    private String nombre;
    private int dni;
    private String contraseña;

    private ROL rol;


    public Usuario(String nombre, int dni, String contraseña,ROL rol) {
        this.nombre = nombre;
        this.dni = dni;
        this.contraseña = contraseña;
        this.rol = rol;
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

    public ROL getRol() {
        return rol;
    }
}
