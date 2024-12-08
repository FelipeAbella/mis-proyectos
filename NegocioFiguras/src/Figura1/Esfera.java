/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Figura1;

/**
 *
 * @author abell
 */
public class Esfera {
    private int x;
    private int y;
    private int radio;

    public Esfera() {
        this.x = 0;
        this.y = 0;
        this.radio = 0;
    }
    
    public Esfera(int x, int y, int radio) {
        this.x = x;
        this.y = y;
        this.radio = radio;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadio() {
        return radio;
    }
    
    //propiedades  
    public float getArea()
    {
        return (float)(4*Math.PI*(Math.pow(this.getRadio(), 2)));
    }
    
    public float getVolumen()
    {
        return (float)(4*Math.PI*(Math.pow(this.getRadio(), 3)))/3;
    }

    @Override
    public String toString() {
        return this.x+","+this.y+","+this.radio;
    }
    
}
