/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author lolo
 */
public class Balance {

    private LocalDate fecha;
    private double ValorInicio;
    private double ValorFinal;

    public Balance(double ValorInicio) {
        this.ValorInicio = ValorInicio;
        this.fecha = LocalDate.now();
    }

    
    public void IncrementarBalance(double valor){
        ValorFinal+=valor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getValorInicio() {
        return ValorInicio;
    }

    public double getValorFinal() {
        return ValorFinal;
    }


    
    
    
}
