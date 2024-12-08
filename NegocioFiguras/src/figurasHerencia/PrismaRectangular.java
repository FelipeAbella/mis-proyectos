/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figurasHerencia;

/**
 *
 * @author abell
 */
public class PrismaRectangular extends Rectangulo{
    private int altura;

    public PrismaRectangular() {
        this.altura = 0;
    }
    
    public PrismaRectangular(int x, int y, int largo, int ancho, int altura) {
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
    public float getVolumen()
    {
        return (float)(super.getLargo()*super.getAncho()*this.altura);
    }
    
    public float getArea()
    {
        return (float)(2*(super.getLargo()*this.altura))+(2*(super.getAncho()*this.altura))+(2*(super.getLargo()*super.getAncho()));
    }

    @Override
    public String toString() {
        return super.toString()+","+this.altura;
    }
    
}
