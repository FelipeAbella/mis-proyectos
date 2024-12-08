/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FigurasHi;

/**
 *
 * @author abell
 */
public class Cono extends Figura implements PropiedadesFigura{
    private float alturaInclinada;
    
    public Cono() {
        super();
    }
    
    public Cono(int x, int y, int radio,int altura) {
        super(x,y,radio,altura,0);
    }
    
    //propiedades  
    public float getAlturaInclinada()
    {
        alturaInclinada = (float)(Math.sqrt((Math.pow(super.getDim1(), 2))+(Math.pow(super.getDim2(), 2))));
        return alturaInclinada;
    }
    
    @Override
    public float getPerimetro() {return 0;}
    
    @Override
    public float getArea()
    {
        return (float)(Math.PI*super.getDim1())*(super.getDim1()+this.alturaInclinada);
    }
    
    @Override
    public float getVolumen()
    {
        return (float)(Math.PI*super.getDim2()*Math.pow(super.getDim1(), 2))/3;
    }
    
    @Override
    public String toString() {
        return super.getX()+","+super.getY()+","+super.getDim1()+","+super.getDim2()+","+this.alturaInclinada;
    }
}