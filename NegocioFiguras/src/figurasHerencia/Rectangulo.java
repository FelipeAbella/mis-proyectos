/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figurasHerencia;

/**
 *
 * @author abell
 */
public class Rectangulo extends Punto{
    private int ancho;
    private int largo;

    public Rectangulo() {
        this.ancho = 0;
        this.largo = 0;
    }
    
    public Rectangulo(int x, int y, int largo, int ancho) {
        super(x,y);
        this.ancho = ancho;
        this.largo = largo;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAncho() {
        return ancho;
    }
    
    public void setLargo(int largo) {
        this.largo = largo;
    }

    public int getLargo() {
        return largo;
    }
    
    //propiedades
    public float getPerimetro()
    {
        return (float)((2*this.ancho)+(2*this.largo));
    }
    
    public float getArea()
    {
        return (float)(this.ancho*this.largo);
    }

    @Override
    public String toString() {
        return super.toString()+","+this.largo+","+this.ancho;
    }
    
}
