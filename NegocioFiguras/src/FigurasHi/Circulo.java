/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FigurasHi;

/**
 *
 * @author abell
 */
public class Circulo extends Figura implements PropiedadesFigura{

    public Circulo() {
        super();
    }
    
    public Circulo(int x, int y, int radio) {
        super(x,y,radio,0,0);
    }

    //propiedades
    @Override
    public String toString() {
        return super.getX()+","+super.getY()+","+super.getDim1();
    }

    @Override
    public float getPerimetro() {
        return (float)(2*Math.PI*super.getDim1());
    }

    @Override
    public float getArea() {
        return (float)(Math.PI*(Math.pow(super.getDim1(), 2)));
    }

    @Override
    public float getVolumen() {return 0;}
    
}
