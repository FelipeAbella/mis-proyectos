/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sobrecarga;

/**
 *
 * @author abell
 */
public class Main {
    public static void main(String[] args) {
        sumaEntero sumaEntero = new sumaEntero();
        sumaDecimal sumaDecimal = new sumaDecimal();
        Potencias potencias = new Potencias();

        int sumaEnteros = sumaEntero.sumar(5, 3);
        double sumaDecimales = sumaDecimal.sumar(4.5, 2.3);
        double potencia = potencias.potenciar(2.0, 3);

        System.out.println("Suma de enteros: " + sumaEnteros);
        System.out.println("Suma de decimales: " + sumaDecimales);
        System.out.println("Potencia: " + potencia);
    }
}