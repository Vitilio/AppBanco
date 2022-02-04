/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lossauces.daw.banco22;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;

/**
 *
 * @author daw1
 */
public class Banco {

    private String nombre;
    private Set<Cuenta> cuentas;

    public Banco(String nombre) {
        this.nombre = nombre;
        cuentas = new HashSet<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Cuenta> getCuentas() {
         return new ArrayList<>(cuentas);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean abrirCuenta(String codigo, String titular, float saldo) {
        return cuentas.add(new Cuenta(codigo, titular, saldo));
    }

    public boolean cancelarCuenta(String codigo) {
        boolean salida;

        return false;
    }

    public float getTotalDeposito() {
        return 0;

    }

    public Cuenta getCuenta(String codigo) {
        Cuenta c = null;
        
        for (Cuenta c1 : cuentas) {
            if (c1.getCodigo().equals(codigo)) {
                c=c1;
            }
        }
        return c;
    }

    @Override
    public String toString() {
        return nombre + "," + cuentas;
    }

}
