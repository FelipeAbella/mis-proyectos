/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figurasHerencia;

/**
 *
 * @author abell
 */
public class Cono extends Cilindro{
    private float alturaInclinada;
    
    public Cono() {
        super();
    }
    
    public Cono(int x, int y, int radio,int altura) {
        super(x,y,radio,altura);
    }
    
    //propiedades  
    public float getAlturaInclinada()
    {
        alturaInclinada = (float)(Math.sqrt((Math.pow(this.getRadio(), 2))+(Math.pow(this.getAltura(), 2))));
        return alturaInclinada;
    }
    
    public float getArea()
    {
        return (float)(Math.PI*super.getRadio())*(super.getRadio()+this.alturaInclinada);
    }
    
    public float getVolumen()
    {
        return (float)(Math.PI*super.getAltura()*Math.pow(super.getRadio(), 2))/3;
    }
    
    @Override
    public String toString() {
        return super.toString()+","+this.alturaInclinada;
    }
}