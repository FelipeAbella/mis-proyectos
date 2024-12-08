/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FigurasHi;

/**
 *
 * @author abell
 */
public class Figura {
    private int x;
    private int y;
    private int dim1;
    private int dim2;
    private int dim3;

    public Figura() {
        this.x = 0;
        this.y = 0;
        this.dim1 = 0;
        this.dim2 = 0;
        this.dim3 = 0;
    }

    public Figura(int x, int y, int dim1, int dim2, int dim3) {
        this.x = x;
        this.y = y;
        this.dim1 = dim1;
        this.dim2 = dim2;
        this.dim3= dim3;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDim1(int dim1) {
        this.dim1 = dim1;
    }

    public void setDim2(int dim2) {
        this.dim2 = dim2;
    }
    
    public void setDim3(int dim3) {
        this.dim3 = dim3;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDim1() {
        return dim1;
    }

    public int getDim2() {
        return dim2;
    }
    
    public int getDim3() {
        return dim3;
    }

    /*No se puede implementar un ToString ni métodos de propiedades porque dependen de cada hijo
    Hay dos soluciones:
    1. Usar una interface
    2. Usar clases abstractas
    */
}
