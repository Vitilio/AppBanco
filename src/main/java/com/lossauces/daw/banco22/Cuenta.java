/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lossauces.daw.banco22;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author daw1
 */
public class Cuenta {

    private String codigo;
    private String titular;
    private float saldo;
    List<Movimiento> movimientos;

    public Cuenta(String codigo, String titular, float saldo) {
        this.codigo = codigo;
        this.titular = titular;
        if (saldo >= 0) {
            this.saldo = saldo;
        }
        movimientos = new ArrayList();
        movimientos.add(new Movimiento(LocalDate.now(), 'R', saldo, saldo));
    }

    public String getCodigo() {
        return codigo;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public List<Movimiento> getMovimientos(LocalDate desde, LocalDate hasta) {
        List<Movimiento> salida = new ArrayList<>();
        Iterator<Movimiento> iterador = movimientos.iterator();//
        Movimiento m;
        while (iterador.hasNext()) {
            Movimiento next = iterador.next();

        }
        return null;

    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        if (saldo >= 0) {
            this.saldo = saldo;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cuenta other = (Cuenta) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigo + "," + titular + "," + saldo;
    }
    /**
     * Metodo que permite ingresar dinero en la cuenta
     * @param cantidad cantidad a ingresar en la cuenta
     */
    public void ingresar(float cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            movimientos.add(new Movimiento(LocalDate.now(), 'I', cantidad, saldo));
        }
    }

    public void reintegrar(float cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            movimientos.add(new Movimiento(LocalDate.now(), 'R', -cantidad, saldo));
        }
    }

    public void realizarTransferencia(Cuenta destino, float cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            destino.saldo += cantidad;
            movimientos.add(new Movimiento(LocalDate.now(), 'T', -cantidad, saldo));
            destino.movimientos.add(new Movimiento(LocalDate.now(), 'T', cantidad, destino.saldo));
        }
    }

    public String listarMovimientos() {
        StringBuilder sb = new StringBuilder();

        for (Movimiento m : movimientos) {
            sb.append(m.toString()).append("\n");
        }
        return sb.toString();
    }
}