/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FigurasHi;

/**
 *
 * @author abell
 */
public class Esfera extends Figura implements PropiedadesFigura{

    public Esfera() {
        super();
    }
    
    public Esfera(int x, int y, int radio) {
        super(x,y,radio,0,0);
    }
    
    //propiedades 
    @Override
    public float getPerimetro() {return 0;}
    
    @Override
    public float getArea()
    {
        return (float)(4*Math.PI*(Math.pow(super.getDim1(), 2)));
    }
    
    @Override
    public float getVolumen()
    {
        return (float)(4*Math.PI*(Math.pow(super.getDim1(), 3)))/3;
    }

    @Override
    public String toString() {
        return super.toString();
    }    
}
