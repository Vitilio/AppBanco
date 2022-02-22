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

    /**
     *
     * @param codigo serie de caracteres con los que identificar la cuenta
     * @param saldo cantidad de capital almacenado en nuestra cuenta
     * @param titular nombre del dueño de la cuenta
     * @throws com.lossauces.daw.banco22.SaldoException
     *  
     */
    public Cuenta(String codigo, String titular, float saldo) throws SaldoException {
        if (saldo < 0) {
            throw new SaldoException("Error en el saldo");
        }
        this.codigo = codigo;
        this.titular = titular;
        this.saldo = saldo;
        movimientos = new ArrayList();
        movimientos.add(new Movimiento(LocalDate.now(), TipoMovimiento.REINTEGRO, saldo, saldo));
    }

    /**
     *
     * Devuelve el codigo de la cuenta
     *
     * @return
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     *
     * Devuelve la lista de movimientos de la cuenta
     *
     * @return
     */
    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    /**
     * Muestra una lista de los movimientos efectuados durante un periodo de
     * tiempo
     *
     * @param desde Fecha inicial
     * @param hasta Fecha final
     *
     * @return
     */
    public List<Movimiento> getMovimientos(LocalDate desde, LocalDate hasta) {
        List<Movimiento> salida = new ArrayList<>();
        Iterator<Movimiento> iterador = movimientos.iterator();//
        Movimiento m;
        while (iterador.hasNext()) {
            Movimiento next = iterador.next();

        }
        return null;

    }

    /**
     * Permite modificar el codigo de una cuenta
     *
     * @param codigo serie de caracteres con los que identificar la cuenta
     *
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Devuelve el nombre del titular de la cuenta
     *
     * @return
     */
    public String getTitular() {
        return titular;
    }

    /**
     * @param titular nombre del dueño de la cuenta
     *
     */
    public void setTitular(String titular) {
        this.titular = titular;
    }

    /**
     *
     * Devuelve el saldo de nuestra cuenta
     *
     * @return
     */
    public float getSaldo() {
        return saldo;
    }

    /**
     * Modifica el saldo de la cuenta
     *
     * @param saldo cantidad de capital almacenado en nuestra cuenta
     *
     */
    public void setSaldo(float saldo) throws SaldoException {
        if (saldo < 0) {
            throw new SaldoException("Error en el saldo");
        }
        if (saldo >= 0) {
            this.saldo = saldo;
        }
    }

    /**
     *
     *
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    /**
     *
     *
     */
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

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return codigo + "," + titular + "," + saldo;
    }

    /**
     * Metodo que permite ingresar dinero en la cuenta
     *
     * @param cantidad cantidad a ingresar en la cuenta
     */
    public void ingresar(float cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            movimientos.add(new Movimiento(LocalDate.now(), TipoMovimiento.INGRESO, cantidad, saldo));
        }
    }

    /**
     * Metodo que permite sacar dinero de la cuenta
     *
     * @param cantidad cantidad a retirar de la cuenta
     */
    public void reintegrar(float cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            movimientos.add(new Movimiento(LocalDate.now(), TipoMovimiento.REINTEGRO, -cantidad, saldo));
        }
    }

    /**
     *
     * @param destino cuenta receptora de la transferencia
     * @param cantidad cantidad a enviar
     *
     */
    public void realizarTransferencia(Cuenta destino, float cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            destino.saldo += cantidad;
            movimientos.add(new Movimiento(LocalDate.now(), TipoMovimiento.TRANSFERENCIA, -cantidad, saldo));
            destino.movimientos.add(new Movimiento(LocalDate.now(), TipoMovimiento.TRANSFERENCIA, cantidad, destino.saldo));
        }
    }

    /**
     *
     * @return
     */
    public String listarMovimientos() {
        StringBuilder sb = new StringBuilder();

        for (Movimiento m : movimientos) {
            sb.append(m.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Compara dos cuentas entre si
     *
     * @param o cuenta a comparar
     * @return
     */
    public int compareTo(Cuenta o) {
        return this.codigo.compareTo(o.codigo);
    }
}
