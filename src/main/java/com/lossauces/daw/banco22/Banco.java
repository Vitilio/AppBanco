/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lossauces.daw.banco22;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author daw1
 */
public class Banco {

    private String nombre;
    private Map<String, Cuenta> cuentas;

    public Banco(String nombre) {
        this.nombre = nombre;
        cuentas = new HashMap<>();
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @return
     */
    public List<Cuenta> getCuentas() {
        return new ArrayList<>(cuentas.values());
    }

    /**
     * @param nombre
     *
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param codigo
     * @param saldo
     * @param titular
     *
     * @return
     */
    public boolean abrirCuenta(String codigo, String titular, float saldo) {
        boolean salida=false;
        if (!cuentas.containsKey(codigo)) {
            cuentas.put(codigo, new Cuenta(codigo, titular, saldo));
            salida = true;
        }
        return salida;
    }

    /**
     * @param codigo
     *
     * @return
     */
    public boolean cancelarCuenta(String codigo) {
        boolean salida = false;
       
        return salida;
    }

    /**
     *
     * @return
     */
    public float getTotalDeposito() {
        return 0;

    }

    /**
     * @param codigo
     *
     * @return
     */
    public Cuenta getCuenta(String codigo) {
        return cuentas.get(codigo);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return nombre + "," + cuentas;
    }

}
