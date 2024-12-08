/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helados;

/**
 *
 * @author abell
 */
public class Cono extends FiguraH implements PropiedadesFigura{
    
    public Cono() {
        super();
    }
    
    public Cono(int x, int y, int radio,int altura) {
        super(x,y,radio,altura);
    }
    
    //propiedades  
    
    @Override
    public float getPerimetro() {return 0;}
    
    @Override
    public float getVolumen()
    {
        return (float)(Math.PI*super.getDim2()*Math.pow(super.getDim1(), 2))/3;
    }
    
    @Override
    public String toString() {
        return super.getX()+","+super.getY()+","+super.getDim1()+","+super.getDim2();
    }

    @Override
    public float getArea() {return 0;}
}