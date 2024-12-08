/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FigurasHi;

/**
 *
 * @author abell
 */
public class Cilindro extends Figura implements PropiedadesFigura{

    public Cilindro() {
        super();
    }
    
    public Cilindro(int x, int y, int radio, int altura) {
        super(x,y,radio,altura,0);
    }
    
    //propiedades 
    @Override
    public float getPerimetro() {return 0;}
    
    @Override
    public float getArea()
    {
        return (float)(2*Math.PI*super.getDim1())*(super.getDim1()+super.getDim2());
    }
    
    @Override
    public float getVolumen()
    {
        return (float)(super.getDim2()*Math.PI*(Math.pow(super.getDim1(), 2)));
    }

    @Override
    public String toString() {
        return super.getX()+","+super.getY()+","+super.getDim1()+","+super.getDim2();
    } 
    
}
