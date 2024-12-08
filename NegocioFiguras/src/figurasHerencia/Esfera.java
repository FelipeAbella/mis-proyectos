/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figurasHerencia;

/**
 *
 * @author abell
 */
public class Esfera extends Circulo{

    public Esfera() {
        super();
    }
    
    public Esfera(int x, int y, int radio) {
        super(x,y,radio);
    }
    
    //propiedades  
    public float getArea()
    {
        return (float)(4*Math.PI*(Math.pow(super.getRadio(), 2)));
    }
    
    public float getVolumen()
    {
        return (float)(4*Math.PI*(Math.pow(super.getRadio(), 3)))/3;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
}
