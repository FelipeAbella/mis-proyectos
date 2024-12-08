/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figurasHerencia;

/**
 *
 * @author abell
 */
public class PiramideTriangular extends Triangulo{
    private float alturaInclinada;    

    public PiramideTriangular() {
        super();
    }
    
    public PiramideTriangular(int x, int y, int lado, int altura) {
        super(x,y,lado,altura);
    }
    
    
    //propiedades
    public float getAlturaInclinada()
    {
        alturaInclinada = (float)(Math.sqrt((Math.pow((this.getLado()/2), 2))+(Math.pow(this.getAltura(), 2))));
        return alturaInclinada;
    }
    
    public float getArea()
    {
        return (float)(3*super.getLado()*this.alturaInclinada)+((super.getLado()*super.getAltura())/2);
    }
    
    public float getVolumen()
    {
        return (float)((((super.getLado()*super.getAltura())/2)*super.getAltura())/3);
    }

    @Override
    public String toString() {
        return super.toString()+","+this.alturaInclinada;
    }
    
}
