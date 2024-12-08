/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package todero;

/**
 *
 * @author abell
 */
public class Moto {
    //atributos
    private String placa;
    private String marca;
    private float precioDia;
    private String cilindraje;
    private int modelo;
    private boolean alquilada;
    
    //métodos
    //constructores
    public Moto(String campo, String campo1, float parseFloat, String campo2, int parseInt, String campo5) {
        this.placa = "";
        this.marca = "";
        this.precioDia = 0;
        this.cilindraje = "";
        this.modelo = 0;
        this.alquilada=false;
    }

    public Moto(String placa, String marca, float precioDia, String cilindraje, int modelo, boolean alquilada) {
        this.placa = placa;
        this.marca = marca;
        this.precioDia = precioDia;
        this.cilindraje = cilindraje;
        this.modelo = modelo;
        this.alquilada=alquilada;
    }
    
    //analizadores
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setPrecioDia(float precioDia) {
        this.precioDia = precioDia;
    }

    public void setCilindraje(String cilindraje) {
        this.cilindraje = cilindraje;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }
    
    public void setAlquilada(boolean alquilada) {
        this.alquilada = alquilada;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public float getPrecioDia() {
        return precioDia;
    }

    public String getCilindraje() {
        return cilindraje;
    }

    public int getModelo() {
        return modelo;
    }
    
    public boolean getAlquilada() {
        return alquilada;
    }

    @Override
    public String toString() {
        String estado;
        if(alquilada)
        {
            estado = "ALQUILADA";
        }
        else
        {
            estado="NO ALQUILADA";
        }
        return this.placa + "," + this.marca + "," + this.precioDia + "," + this.cilindraje + "," + this.modelo + "," + estado;
        }
}
