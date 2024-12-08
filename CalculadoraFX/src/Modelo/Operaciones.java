/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author abell
 */
public class Operaciones {
    
    private float x;
    private float y;
    
    public Operaciones(float x, float y)
    {
        this.x=x;
        this.y=y;
    }
    
    public float getSuma()
    {
        float z;
        z=this.x+this.y;
        return z;
    }
    
    public float getResta()
    {
        float z;
        z=this.x-this.y;
        return z;
    }
    
    public float getMult()
    {
        float z;
        z=this.x*this.y;
        return z;
    }
    
    public float getDiv()
    {
        float z;
        z=this.x/this.y;
        return z;
    }
}
