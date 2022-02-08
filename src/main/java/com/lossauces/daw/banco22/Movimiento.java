/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lossauces.daw.banco22;

import java.time.LocalDate;

/**
 *
 * @author daw1
 */
public class Movimiento {
    private LocalDate fecha;
    private TipoMovimiento tipo;
    private float cantidad;
    private float saldo;
/**
 * Metodo que inicializa los objetos
 * @param fecha
 * @param tipo
 * @param cantidad
 * @param saldo 
 */
    public Movimiento(LocalDate fecha, TipoMovimiento tipo, float cantidad, float saldo) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.saldo = saldo;
    }
/**
 * Devuelve la fecha en la que se realizo dicho movimiento
 * @return 
 */
    public LocalDate getFecha() {
        return fecha;
    }
/**
 * Devuelve el tipo de movimiento
 * @return 
 */
    public TipoMovimiento getTipo() {
        return tipo;
    }
/**
 * Devuelve la cantidad de dinero 
 * @return 
 */
    public float getCantidad() {
        return cantidad;
    }
/**
 * Devuelve el saldo de la cuenta que realiza el movimiento
 * @return 
 */
    public float getSaldo() {
        return saldo;
    }
/**
 * Muestra los datos en forma de cadena de caracteres
 * @return 
 */
    @Override
    public String toString() {
        return fecha+","+tipo+","+cantidad+","+saldo;
    }
    
    
    
}
