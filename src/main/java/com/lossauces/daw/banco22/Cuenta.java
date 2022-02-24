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
public class Cuenta implements Comparable<Cuenta>{

    private String codigo;
    private String titular;
    private float saldo;
    private List<Movimiento> movimientos;

    /**
     *
     * @param codigo serie de caracteres con los que identificar la cuenta
     * @param saldo cantidad de capital almacenado en nuestra cuenta
     * @param titular nombre del dueño de la cuenta
     * @throws com.lossauces.daw.banco22.SaldoException
     *
     */
    public Cuenta(String codigo, String titular, float saldo) throws SaldoException {
        this.codigo = codigo;
        this.titular = titular;
        if (saldo < 0) {
            throw new SaldoException("Error en el saldo");
        }
        this.saldo = saldo;
        movimientos = new ArrayList();
        movimientos.add(new Movimiento(LocalDate.now(), TipoMovimiento.INGRESO, saldo, saldo));
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
     * @throws com.lossauces.daw.banco22.SaldoException
     *
     */
    public void setSaldo(float saldo) throws SaldoException {
        if (saldo < 0) {
            throw new SaldoException("El saldo a introducir debe ser positivo");
        }
        if (saldo >= 0) {
            this.saldo = saldo;
        }
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
            m = iterador.next();
            if (m.getFecha().isAfter(desde) && m.getFecha().isBefore(hasta)) {
                salida.add(m);
            }
        }
        return salida;

    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
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
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad a ingresar debe ser positiva");
        }
        saldo += cantidad;
        movimientos.add(new Movimiento(LocalDate.now(), TipoMovimiento.INGRESO, cantidad, saldo));

    }

    /**
     * Metodo que permite sacar dinero de la cuenta
     *
     * @param cantidad cantidad a retirar de la cuenta
     * @throws com.lossauces.daw.banco22.SaldoException
     */
    public void reintegrar(float cantidad) throws SaldoException {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad a retirar debe ser positiva");
        }
        if (cantidad > saldo) {
            throw new SaldoException("Saldo insuficiente");
        }
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
public void realizarTransferencia(Cuenta destino, float cantidad) throws SaldoException {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad a retirar debe ser positiva");
        }

        if (cantidad > saldo) {
            throw new SaldoException("Error en el saldo");
        }

        if(destino==null){
            throw new NullPointerException("La cuenta elegida no existe");
        }
            saldo -= cantidad;
            destino.saldo += cantidad;
            movimientos.add(new Movimiento(LocalDate.now(),TipoMovimiento.TRANSFERENCIA, -cantidad, saldo));
            destino.movimientos.add(new Movimiento(LocalDate.now(),TipoMovimiento.INGRESO, cantidad, destino.saldo));
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
