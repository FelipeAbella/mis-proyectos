/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parametrizacion;

/**
 *
 * @author abell
 */
public class Calculadora {
    public double operacion(double a, double b, Operacion operacion) {
        return operacion.operar(a, b);
    }
    
    public int operacion(int a, int b, Operacion operacion) {
        return (int) operacion.operar(a, b);
    }
}
