/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figurasHerencia;

/**
 *
 * @author abell
 */
public class PiramideRectangular extends Rectangulo{
    private int altura;
    private float alturaInclinada;    

    public PiramideRectangular() {
        this.altura=0;
    }
    
    public PiramideRectangular(int x, int y, int largo, int ancho, int altura) {
        super(x,y,largo,ancho);
        this.altura = altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getAltura() {
        return altura;
    }
    
    //propiedades
    public float getAlturaInclinada()
    {
        alturaInclinada = (float)(Math.sqrt((Math.pow((this.getLargo()/2), 2))+(Math.pow(this.getAltura(), 2))));
        return alturaInclinada;
    }
    
    public float getArea()
    {
        return (float)(super.getLargo()*super.getAncho())+((2*(super.getLargo()*this.alturaInclinada))+(2*(super.getAncho()*this.alturaInclinada)));
    }
    
    public float getVolumen()
    {
        return (float)((super.getLargo()*super.getAncho()*this.altura)/(3));
    }

    @Override
    public String toString() {
        return super.toString()+","+this.alturaInclinada;
    }
    
}
