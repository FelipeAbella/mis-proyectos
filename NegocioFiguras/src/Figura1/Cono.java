/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Figura1;

/**
 *
 * @author abell
 */
public class Cono extends Cilindro{
    private int alturaInclinada;
    
    public Cono() {
        this.alturaInclinada=0;
    }
    
    public Cono(int x, int y, int radio,int altura, int alturaInclinada) {
        super(x,y,radio,altura);
        this.alturaInclinada=alturaInclinada;
    }
    
    public void setAlturaInclinada(int alturaInclinada)
    {
        this.alturaInclinada=alturaInclinada;
    }
    
    public int getAlturaInclinada()
    {
        return alturaInclinada;
    }
    
    //propiedades  
    public float getArea()
    {
        return (float)(Math.PI*super.getRadio())*(super.getRadio()+this.alturaInclinada);
    }
    
    public float getVolumen()
    {
        return (float)(Math.PI*super.getAltura()*Math.pow(super.getRadio(), 2))/3;
    }

    @Override
    public String toString() {
        return super.toString()+","+this.alturaInclinada;
    }
    
    
}
