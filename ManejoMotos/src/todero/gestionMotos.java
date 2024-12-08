/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package todero;
//import java.util.ArrayList;
//import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author abell
 */
public class gestionMotos {
    //variables de clase
    private String ruta;
    
    //métodos
    public gestionMotos()
    {
        this.ruta="./Archivos/misMotos.txt";
        this.verificArchivo();
    }
    public void verificArchivo()
    {
        try
        {
            File filex = new File(this.ruta);
            if(!filex.exists())
                filex.createNewFile();  
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "Errores encontrando ruta");
        }
    }

   public void agregarMoto()
   {
       //crear la moto
       String placa, marca, cilin;
       float precio;
       int model;
       boolean existe;
       
       do
       {
           placa= JOptionPane.showInputDialog("Digite la placa: ").toUpperCase();
           existe=verificarPlaca(placa);
           if (existe)
               JOptionPane.showMessageDialog(null,"Esa placa ya existe, digítela nuevamente.");
       }
       while (existe);
       marca=JOptionPane.showInputDialog("Digite la marca: ").toUpperCase();
       precio=Float.parseFloat(JOptionPane.showInputDialog("Digite el precio x día de alquiler"));
       cilin=JOptionPane.showInputDialog("Digite el cilindraje: ");
       model=Integer.parseInt(JOptionPane.showInputDialog("Digite el modelo:"));
       boolean alquilada = false;
       
       //construir objeto tipo moto
       Moto moto=new Moto(placa, marca, precio, cilin,  model, alquilada);
       
       //agregamos la moto al contenedor
       this.guardarMoto(moto);
       JOptionPane.showMessageDialog(null, "Operación exitosa");
   }
   public void guardarMoto(Moto moto)
   {
       File file=new File(ruta);
       try
       {
           FileWriter fr = new FileWriter(file,true);
           PrintWriter ps = new PrintWriter(fr);
           ps.println(moto);
           ps.close();
       }
       catch (IOException ex) 
       {
           JOptionPane.showMessageDialog(null, "Fallo creando la moto");
       }
   }
   public void verInventario()
   {
       ArrayList<Moto> motos=this.getInventario();
       for (Moto moto: motos)
       {
           System.out.println(moto);
       }
       System.out.println("=======================================");
   }
   public ArrayList<Moto> getInventario()
   {
       ArrayList<Moto> motos=new ArrayList();
       FileReader file;
       String registro;
       boolean alquilada;
       alquilada=hacerAlquiler();
       
       try
       {    
           file=new FileReader(ruta);
           Scanner lector=new Scanner(file);
           while(lector.hasNextLine())
           {
               if (alquilada==true)
               {
                   registro=lector.nextLine();
                   String[] campos = registro.split(",");
                   Moto moto=new Moto(campos[0],campos[1],Float.parseFloat(campos[2]),campos[3],Integer.parseInt(campos[4]),alquilada);
                   motos.add(moto);
               }
               else
               {
                   registro=lector.nextLine();
                   String[] campos = registro.split(",");
                   Moto moto=new Moto(campos[0],campos[1],Float.parseFloat(campos[2]),campos[3],Integer.parseInt(campos[4]),alquilada);
                   motos.add(moto); 
               }
           }
       }
       catch (FileNotFoundException ex)
       {
           JOptionPane.showMessageDialog(null, "Fallo cargando inventario");
       }
       return motos;
   }
   public void verMoto()
   {
       String placa;
       Moto moto;
       
       placa= JOptionPane.showInputDialog("Digite la placa que desea buscar: ").toUpperCase();
       moto=this.buscarMoto(placa);
       if(moto!=null)
       {
           System.out.println(moto);
           System.out.println("=======================================");
       }
       else
           JOptionPane.showMessageDialog(null, "No existe una moto con esa placa");
   }
   public Moto buscarMoto(String placa)
   {
       Moto moto=null;
       FileReader file;
       String registro;
       boolean alquilada;
       alquilada=hacerAlquiler();
       
       try
       {
           file=new FileReader(ruta);
           Scanner lector = new Scanner(file);
           while(lector.hasNextLine())
           {
               registro=lector.nextLine();
               String [] campos=registro.split(",");
               if(campos[0].equals(placa))
               {
                   moto=new Moto(campos[0],campos[1],Float.parseFloat(campos[2]),campos[3],Integer.parseInt(campos[4]),alquilada);
                   break;
               }
           }
       }
       catch (FileNotFoundException ex)
       {
           JOptionPane.showMessageDialog(null, "Fallo leyendo el archivo");
       }
       return moto;
   }
   private boolean verificarPlaca(String placa)
   {
       boolean existe=false;
       FileReader file;
       String registro;
       
       try
       {
           file=new FileReader(ruta);
           Scanner lector = new Scanner(file);
           while(lector.hasNextLine())
           {
               registro=lector.nextLine();
               String [] campos=registro.split(",");
               if(campos[0].equals(placa))
               {
                   existe=true;
                   break;
               }
           }
       }
       catch (FileNotFoundException ex)
       {
           JOptionPane.showMessageDialog(null, "Fallo leyendo el archivo");
       }
       return existe;
   }
   public void modificarPlaca()
   {
       String placa, nuevoDato;
       boolean encontrada=false,existe;
       
       ArrayList<Moto> misMotos=this.getInventario();
       placa= JOptionPane.showInputDialog("Digite la placa a modificar: ").toUpperCase();
       for(Moto moto: misMotos)
       {
           if (moto.getPlaca().equals(placa))
           {
               do
               {
                   nuevoDato=JOptionPane.showInputDialog("Digite la nueva placa: ").toUpperCase();
                   existe=this.verificarPlaca(nuevoDato);
                   if (existe)
                       JOptionPane.showMessageDialog(null, "Ya existe una moto con esa placa");
               }
               while (existe);
               moto.setPlaca(nuevoDato);
               this.recargarArchivo(misMotos);
               JOptionPane.showMessageDialog(null,"Operación exitosa");
               encontrada=true;
               break;
           }
       }
       if(!encontrada)
           JOptionPane.showMessageDialog(null,"Esa placa no existe");
   }
   public void recargarArchivo(ArrayList<Moto> motos)
   {
       try
       {
           File file=new File(ruta);
           FileWriter fr = new FileWriter(file,false);
           PrintWriter ps = new PrintWriter(fr);
           for (Moto moto:motos)
               ps.println(moto);
           ps.close();
       }
       catch (IOException ioe)
       {
           System.exit(1);
       }
   }
   public void modificarMarca()
   {
       String placa, nuevoDato;
       boolean encontrada=false,existe;
       
       ArrayList<Moto> misMotos=this.getInventario();
       placa= JOptionPane.showInputDialog("Digite la placa a modificar: ").toUpperCase();
       for(Moto moto: misMotos)
       {
           if (moto.getPlaca().equals(placa))
           {
               nuevoDato=JOptionPane.showInputDialog("Digite la nueva marca: ").toUpperCase();
               moto.setMarca(nuevoDato);
               this.recargarArchivo(misMotos);
               JOptionPane.showMessageDialog(null,"Operación exitosa");
               encontrada=true;
               break;
           }
       }
       if(!encontrada)
           JOptionPane.showMessageDialog(null,"Esa placa no existe");
   }
   public void modificarPrecio()
   {
       String placa;
       float nuevoDato;
       boolean encontrada=false,existe;
       
       ArrayList<Moto> misMotos=this.getInventario();
       placa= JOptionPane.showInputDialog("Digite la placa a modificar: ").toUpperCase();
       for(Moto moto: misMotos)
       {
           if (moto.getPlaca().equals(placa))
           {
               nuevoDato=Float.parseFloat(JOptionPane.showInputDialog("Digite el nuevo precio por día: "));
               moto.setPrecioDia(nuevoDato);
               this.recargarArchivo(misMotos);
               JOptionPane.showMessageDialog(null,"Operación exitosa");
               encontrada=true;
               break;
           }
       }
       if(!encontrada)
           JOptionPane.showMessageDialog(null,"Esa placa no existe");
   }
   public void modificarCilindraje()
   {
       String placa, nuevoDato;
       boolean encontrada=false,existe;
       
        ArrayList<Moto> misMotos=this.getInventario();
       placa= JOptionPane.showInputDialog("Digite la placa a modificar: ").toUpperCase();
       for(Moto moto: misMotos)
       {
           if (moto.getPlaca().equals(placa))
           {
               nuevoDato=JOptionPane.showInputDialog("Digite el nuevo cilindraje: ").toUpperCase();
               moto.setCilindraje(nuevoDato);
               this.recargarArchivo(misMotos);
               JOptionPane.showMessageDialog(null,"Operación exitosa");
               encontrada=true;
               break;
           }
       }
       if(!encontrada)
           JOptionPane.showMessageDialog(null,"Esa placa no existe");
   }
   public void modificarModelo()
   {
       String placa;
       int nuevoDato;
       boolean encontrada=false,existe;
       
       ArrayList<Moto> misMotos=this.getInventario();
       placa= JOptionPane.showInputDialog("Digite la placa a modificar: ").toUpperCase();
       for(Moto moto: misMotos)
       {
           if (moto.getPlaca().equals(placa))
           {
               nuevoDato=Integer.parseInt(JOptionPane.showInputDialog("Digite el nuevo modelo: "));
               moto.setModelo(nuevoDato);
               this.recargarArchivo(misMotos);
               JOptionPane.showMessageDialog(null,"Operación exitosa");
               encontrada=true;
               break;
           }
       }
       if(!encontrada)
           JOptionPane.showMessageDialog(null,"Esa placa no existe");
   }
   public void eliminarMoto()
   {
       String placa;
       int respuesta;
       boolean encontrada=false;
       ArrayList<Moto> misMotos=this.getInventario();
       
       placa= JOptionPane.showInputDialog("Digite la placa a eliminar: ").toUpperCase();
       for (Moto moto: misMotos)
       {
           if ((moto.getPlaca().toUpperCase()).equals(placa))
           {
               respuesta=JOptionPane.showConfirmDialog(null, "Está seguro?");
               if(respuesta==0)
               {
                   misMotos.remove(moto);
                   this.recargarArchivo(misMotos);
                   JOptionPane.showMessageDialog(null, "Operación exitosa");
                   encontrada=true;
                   break;
               }
           }
       }
       if(!encontrada)
           JOptionPane.showMessageDialog(null, "Esa placa no existe en el sistema");
   }
   public boolean hayMotos()
   {
       boolean hay=false;
       FileReader file;
       
       try
       {
           file=new FileReader(ruta);
           Scanner lector = new Scanner(file);
           while(lector.hasNextLine())
           {
               hay=true;
               break;
           }
       }
       catch (FileNotFoundException ex)
       {
           JOptionPane.showMessageDialog(null, "Fallo leyendo el archivo");
       }
       return hay;
   }
   public boolean hacerAlquiler()
   {
       String marca, placa;
       float total, precio=0;
       boolean hay=false;
       int dias;
       boolean alquilada=false;
       
       ArrayList<Moto> misMotos=this.getInventario();
       
       marca=JOptionPane.showInputDialog("Digite la marca que desea: ").toUpperCase();
       for (Moto moto: misMotos)
       {
           if(moto.getMarca().equals(marca))
           {
               System.out.println(moto);
               hay=true;
           }
       }
       System.out.println("=======================================");
       if(!hay)
           JOptionPane.showMessageDialog(null,"No tenemos esa marca en el momento");
       else
       {
           boolean existe;
           do{
               placa= JOptionPane.showInputDialog("Digite la placa a buscar para su alquiler: ").toUpperCase();
               existe=verificarPlaca(placa);
               if(!existe)
                   JOptionPane.showMessageDialog(null, "Esa placa no existe");
           }
           while(!existe);
           for (Moto moto:misMotos)
           {
               if(moto.getPlaca().equals(placa))
               {
                   {
                        moto.setAlquilada(true);
                        precio=moto.getPrecioDia();
                        break;
                   }
               }
           }
           dias=Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos días desea alquilar la moto?"));
           total=precio*dias;
           
           JOptionPane.showMessageDialog(null, "Usted se lleva una moto "+marca+" por "+dias+ " días por un total de $" +total);
       }
       return alquilada;
   }
   public void entregaMoto()
   {
       String placa;
       boolean encontrada=false,estadoAlquiler=true;
       
       ArrayList<Moto> misMotos=this.getInventario();
       placa= JOptionPane.showInputDialog("Digite la placa de la moto a entregar: ").toUpperCase();
       for(Moto moto: misMotos)
       {
           if (moto.getPlaca().equals(placa))
           {
               moto.setAlquilada(false);
               this.recargarArchivo(misMotos);
               JOptionPane.showMessageDialog(null,"Operación exitosa");
               encontrada=true;
               break;
           }
       }
       if(!encontrada)
           JOptionPane.showMessageDialog(null,"Esa placa no existe");
   }
}