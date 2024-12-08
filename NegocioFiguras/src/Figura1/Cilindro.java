/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Figura1;

/**
 *
 * @author abell
 */
public class Cilindro {
    private int x;
    private int y;
    private int radio;
    private int altura;

    public Cilindro() {
        this.x = 0;
        this.y = 0;
        this.radio = 0;
        this.altura=0;
    }
    
    public Cilindro(int x, int y, int radio,int altura) {
        this.x = x;
        this.y = y;
        this.radio = radio;
        this.altura=altura;
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
    
    public void setAltura(int altura) {
        this.altura = altura;
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
    
    public int getAltura() {
        return altura;
    }
    
    //propiedades  
    public float getArea()
    {
        return (float)(2*Math.PI*this.radio)*(this.radio+this.altura);
    }
    
    public float getVolumen()
    {
        return (float)(this.altura*Math.PI*(Math.pow(this.getRadio(), 2)));
    }

    @Override
    public String toString() {
        return this.x+","+this.y+","+this.radio+","+this.altura;
    }
    
    
}
