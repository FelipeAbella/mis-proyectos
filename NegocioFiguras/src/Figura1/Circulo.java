/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Figura1;

/**
 *
 * @author abell
 */
public class Circulo {
    private int x;
    private int y;
    private int radio;

    public Circulo() {
        this.x = 0;
        this.y = 0;
        this.radio = 0;
    }
    
    public Circulo(int x, int y, int radio) {
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
        return this.x+","+this.y+","+this.radio;
    }
    
}
