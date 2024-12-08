/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FigurasHi;

/**
 *
 * @author abell
 */
public class Punto extends Figura{

    public Punto() {
        super();
    }

    public Punto(int x, int y) {
        super(x,y,0,0,0);
    }

    @Override
    public String toString() {
        return super.getX()+","+super.getY();
    }
}
