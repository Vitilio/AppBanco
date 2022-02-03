/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lossauces.daw.banco22;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author daw1
 */
public class AppBanco {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Banco banco = new Banco("Sauces");
        int opcion, opcion2;
        Scanner teclado = new Scanner(System.in);
        String codigo, titular;
        float saldo, cantidad;
        List<Cuenta> listado;
        Cuenta cuenta1;

        do {
            System.out.println("1.- Abrir cuenta");
            System.out.println("2.- Operar con cuenta");
            System.out.println("3.- Cancelar cuenta");
            System.out.println("4.- Listar cuentas");
            System.out.println("5.- Consultar total depósitos");
            System.out.println("0.- Salir");
            System.out.println("Introduzca una opcion: ");
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1:
                    System.out.println("ABRIR CUENTA");
                    System.out.println("Introduzca el codigo de la cuenta");
                    codigo = teclado.nextLine();
                    System.out.println("Introduzca el titular de la cuenta");
                    titular = teclado.nextLine();
                    System.out.println("Introduzca el saldo de la cuenta");
                    saldo = teclado.nextFloat();
                    teclado.nextLine();
                    if (banco.abrirCuenta(codigo, titular, saldo)) {
                        System.out.println("Cuenta abierta");
                    } else {
                        System.out.println("No se puede abrir la cuenta");
                    }
                    break;
                case 2:
                    System.out.println("OPERAR CON CUENTA");
                    System.out.println("Introduzca el codgo de la cuenta con la que operar");
                    codigo=teclado.nextLine();
                    cuenta1=banco.getCuenta(codigo);
                    do {
                        System.out.println("1.- Ingresar dinero");
                        System.out.println("2.- Retirar dinero");
                        System.out.println("3.- Consultar saldo");
                        System.out.println("4.- Realizar transferencia");
                        System.out.println("5.- Consultar movimientos");
                        System.out.println("0.- Salir");
                        System.out.println("Introduzca una opcion: ");
                        opcion2 = teclado.nextInt();
                        switch (opcion2) {
                            case 1:
                                System.out.println("INGRESAR DINERO");
                                System.out.println("Introduzca la cantidad que quiere introducir");
                                cantidad = teclado.nextFloat();
                                cuenta1.ingresar(cantidad);
                                System.out.println(cuenta1.getSaldo());
                                break;
                            case 2:
                                System.out.println("RETIRAR DINERO");
                                System.out.println("Introduzca la cantidad a retirar");
                                cantidad = teclado.nextFloat();
                                cuenta1.reintegrar(cantidad);
                                System.out.println(cuenta1.getSaldo());
                                break;
                            case 3:
                                System.out.println("CONSULTAR SALDO");
                                System.out.println(cuenta1.getSaldo());
                                break;
                            case 4:
                                System.out.println("REALIZAR TRANSFERENCIA");
                                System.out.println("Introduzca la cantidad que quiere enviar");
                                while (!teclado.hasNextFloat()) {                                    
                                    teclado.nextLine();
                                }
                                cantidad = teclado.nextFloat();
                                teclado.nextLine();
                                cuenta1.realizarTransferencia(cuenta2, cantidad);
                                break;
                            case 5:
                                System.out.println("CONSULTAR MOVIMIENTOS");

                                break;
                            case 0:
                                System.out.println("Adios");
                                break;
                            default:
                                System.out.println("Opcion incorrecta");
                        }
                        break;
                    } while (true);

                case 3:
                    System.out.println("CANCELAR CUENTA");
                    System.out.println("Introduzca el numero de la cuenta");
                    codigo = teclado.nextLine();
                    cuenta1 = banco.getCuenta(codigo);
                    if (banco.cancelarCuenta(codigo)) {

                    } else {

                    }

                    break;
                case 4:
                    System.out.println("LISTAR CUENTAS");
                    listado = banco.getCuentas();
                    for (Cuenta c : listado) {
                        System.out.println(c);
                    }
                    break;
                case 5:
                    System.out.println("CONSULTAR TOTAL DEPÓSITOS");
                    System.out.printf("Total depositos; %f\n", banco.getTotalDepositos);

                    break;

                case 0:
                    break;
                default:
                    System.out.println("Opcion Incorrecta");
            }
        } while (opcion != 0);

    }
}