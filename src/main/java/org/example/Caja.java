/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

import java.util.ArrayList;

/**
 *
 * @author lolo
 */
public class Caja {
   private  Balance balance;
    private Usuario usuario;
    private ArrayList<Venta>ventas = new ArrayList<>();

    public Caja(Balance balance, Usuario usuario) {
        this.balance = balance;
        this.usuario = usuario;
    }
    public void AñadirVenta(Venta venta){
        ventas.add(venta);
        balance.IncrementarBalance(venta.getValorTotal());
    }

    public double getValorFinalBalance(){
        return balance.getValorFinal();
    }

    
}
