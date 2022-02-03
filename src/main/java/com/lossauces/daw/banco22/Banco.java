/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lossauces.daw.banco22;

import java.util.List;

/**
 *
 * @author daw1
 */
public class Banco {
    private String nombre;
    private List<Cuenta> cuentas;

    public Banco(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public boolean abrirCuenta(String codigo, String titular, float saldo){
        boolean salida=false;
            
        return salida;
    }
    
    public boolean cancelarCuenta(String codigo){
        boolean salida;
        
        return false;
    }
    
    public float getTotalDeposito(){
        return 0;
        
    }
    
    public Cuenta getCuenta(String codigo){
        return null;
    
    }
    
    @Override
    public String toString() {
        return  nombre+","+cuentas;
    }
 
    private int buscarCuenta(String codigo){
        int posicion=-1;
        for (int i = 0; i <cuentas.size(); i++) {
            if (cuentas.get(i).getCodigo().equals(nombre)) {
                posicion=i;
            }
        }
        
        return posicion;
    }
    
    
    
}
