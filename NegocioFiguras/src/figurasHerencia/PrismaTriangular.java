/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figurasHerencia;

/**
 *
 * @author abell
 */
public class PrismaTriangular extends PrismaRectangular{
    private float alturaInclinada;

    public PrismaTriangular() {
        super();
    }
    
    public PrismaTriangular(int x, int y, int largo, int ancho, int altura) {
        super(x,y,largo,ancho,altura);
    }

    //propiedades
    public float getAlturaInclinada()
    {
        alturaInclinada = (float)(Math.sqrt((Math.pow((super.getLargo()/2), 2))+(Math.pow(super.getAltura(), 2))));
        return alturaInclinada;
    }
    
    public float getVolumen()
    {
        return (float)(((super.getAltura()*super.getAncho())/2)*super.getLargo());
    }
    
    public float getArea()
    {
        return (float)(2*((super.getAltura()*super.getAncho())/2)+2*((super.getLargo()*this.alturaInclinada)/2)+(super.getLargo()*super.getAncho()));
    }

    @Override
    public String toString() {
        return super.toString()+","+this.alturaInclinada;
    }
    
}
