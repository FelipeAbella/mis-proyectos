/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figurasHerencia;

/**
 *
 * @author abell
 */
public class Triangulo extends Punto{
    
    private int lado;
    private int altura;
    
    public Triangulo() {
        this.lado=0;
        this.altura=0;
    }

    public Triangulo(int x, int y, int lado, int altura) {
        this.lado = lado;
        this.altura = altura;
    }

    public int getLado() {
        return lado;
    }

    public int getAltura() {
        return altura;
    }

    public void setLado(int lado) {
        this.lado = lado;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
    
    //Propiedades
    
    public float getPerimetro()
    {
        return (float)(3*this.lado);
    }
    
    public float getArea()
    {
        return (float)((this.lado*this.altura)/2);
    }

    @Override
    public String toString() {
        return super.toString()+","+this.lado+","+this.altura;
    }
}
