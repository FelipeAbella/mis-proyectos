/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figurasHerencia;

/**
 *
 * @author abell
 */
public class Circulo extends Punto{
    private int radio;

    public Circulo() {
        this.radio = 0;
    }
    
    public Circulo(int x, int y, int radio) {
        super(x,y);
        this.radio = radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    public int getRadio() {
        return radio;
    }
    
    //propiedades
    public float getPerimetro()
    {
        return (float)(2*Math.PI*this.getRadio());
    }
    
    public float getArea()
    {
        return (float)(Math.PI*(Math.pow(this.getRadio(), 2)));
    }

    @Override
    public String toString() {
        return super.getX()+","+super.getY()+","+this.radio;
    }
    
}
