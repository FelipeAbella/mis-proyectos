/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figurasHerencia;

/**
 *
 * @author abell
 */
public class Cilindro extends Circulo{
    private int altura;

    public Cilindro() {
        this.altura=0;
    }
    
    public Cilindro(int x, int y, int radio, int altura) {
        super(x,y,radio);
        this.altura=altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getAltura() {
        return altura;
    }
    
    //propiedades  
    public float getArea()
    {
        return (float)(2*Math.PI*super.getRadio())*(super.getRadio()+this.altura);
    }
    
    public float getVolumen()
    {
        return (float)(this.altura*Math.PI*(Math.pow(this.getRadio(), 2)));
    }

    @Override
    public String toString() {
        return super.toString()+","+this.altura;
    }
    
    
}
