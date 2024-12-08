/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parametrizacion;

/**
 *
 * @author abell
 */
public class Main {
    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();
        
        double sumaDecimales = calculadora.operacion(5.5, 3.5, (a, b) -> a + b);
        System.out.println("La suma de decimales es: " + sumaDecimales);
        
        int restaEnteros = calculadora.operacion(5, 3, (a, b) -> a - b);
        System.out.println("La resta de enteros es: " + restaEnteros);
    }
}
