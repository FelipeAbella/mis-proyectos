/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package todero;
import javax.swing.JOptionPane;
/**
 *
 * @author abell
 */
public class Entrada {
    private gestionMotos gestor;
    
    public static void main(String[] args)
    {
        new Entrada();
    }
    public Entrada()
    {
        this.gestor=new gestionMotos();
        this.menu();
    }
    private void menu()
    {
        int opcion;
        do{
            opcion=Integer.parseInt(JOptionPane.showInputDialog("""
                                                                ==========OPCIONES==========
                                                                1. Agregar moto 
                                                                2. Ver inventario 
                                                                3. Buscar moto 
                                                                4. Modificar moto 
                                                                5. Eliminar moto
                                                                6. Hacer alquiler
                                                                7. Entregar moto
                                                                0. Salir"""));
            switch(opcion)
            {
                case 1:
                    this.gestor.agregarMoto();
                    break;
                case 2:
                    if (this.gestor.hayMotos())
                        this.gestor.verInventario();
                    else
                        JOptionPane.showMessageDialog(null, "No hay motos en el inventario");
                    break;
                case 3: 
                    if (this.gestor.hayMotos())
                        this.gestor.verMoto();
                    else
                        JOptionPane.showMessageDialog(null, "No hay motos en el inventario");
                    break;
                case 4:
                    if (this.gestor.hayMotos())
                        this.modificaciones();
                    else
                        JOptionPane.showMessageDialog(null, "No hay motos en el inventario");
                    break;
                case 5:
                    if (this.gestor.hayMotos())
                        this.gestor.eliminarMoto();
                    else
                        JOptionPane.showMessageDialog(null, "No hay motos en el inventario");
                    break;
                case 6:
                    if (this.gestor.hayMotos())
                        this.gestor.hacerAlquiler();
                    else
                        JOptionPane.showMessageDialog(null, "No hay motos en el inventario");
                    break;
                case 7:
                    if (this.gestor.hayMotos())
                        this.gestor.entregaMoto();
                    else
                        JOptionPane.showMessageDialog(null, "No hay motos en el inventario");
                    break;
                case 0:
                        if(this.gestor.hayMotos())
                            JOptionPane.showMessageDialog(null, "No hay motos en el inventario");
                        else{
                        JOptionPane.showMessageDialog(null, "Gracias por usar nuestro servicio");
                        }
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Ese caso no existe, digite una opción válida.");
                    break;
            }
        }
        while(opcion!=0);        
    }
    private void modificaciones(){
        int opcion;
            do{
                opcion=Integer.parseInt(JOptionPane.showInputDialog("""
                                                                    ==========OPCIONES==========
                                                                    1. Modficar placa
                                                                    2. Modificar marca 
                                                                    3. Modificar precio
                                                                    4. Modificar cilindraje 
                                                                    5. Modificar modelo
                                                                    0. Regresar"""));
                switch(opcion){
                    case 1:
                        this.gestor.modificarPlaca();
                        break;
                    case 2:
                        this.gestor.modificarMarca();
                        break;
                    case 3: 
                        this.gestor.modificarPrecio();
                        break;
                    case 4:
                        this.gestor.modificarCilindraje();
                        break;
                    case 5:
                        this.gestor.modificarModelo();
                        break;
                    case 0:
                        break;
                    default:
                        JOptionPane.showMessageDialog(null,"Ese caso no existe, digite una opción válida.");
                        break;
                }
            }
            while(opcion!=0);
    }
}