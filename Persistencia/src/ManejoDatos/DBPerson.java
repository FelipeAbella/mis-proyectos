package ManejoDatos;

import Personas.Person;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class DBPerson {
    private String ruta;

    public DBPerson() {
        this.ruta = "./Archivos/Personas.txt";
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
    
    public void agregarPersona(){
        String name = JOptionPane.showInputDialog("Ingrese el nombre de la persona:");
        String lastName = JOptionPane.showInputDialog("Ingrese el primer apellido de la persona:");
        int age = Integer.parseInt(JOptionPane.showInputDialog("Digite la edad de la persona:"));
        int id = Integer.parseInt(JOptionPane.showInputDialog("Digite el ID de la persona:"));
        
        Person persona=new Person(id, name, lastName, age);
        this.guardarPersona(persona);
        JOptionPane.showMessageDialog(null, "¡Datos agregados exitosamente!");
    }
    
    public void guardarPersona(Person persona)
   {
       File file=new File(ruta);
       try
       {
           FileWriter fr = new FileWriter(file,true);
           PrintWriter ps = new PrintWriter(fr);
           ps.println(persona);
           ps.close();
       }
       catch (IOException ex) 
       {
           JOptionPane.showMessageDialog(null, "Fallo guardando los datos de la persona");
       }
   }
    
    public void listaPersonas(){
       ArrayList<Person> personas=this.getLista();
       for (Person persona: personas)
       {
           System.out.println(persona);
       }
       System.out.println("=======================================");
    }
    
    public ArrayList<Person> getLista()
    {
       ArrayList<Person> personas=new ArrayList();
       FileReader file;
       String registro;
       
       try
       {    
           file=new FileReader(ruta);
           Scanner lector=new Scanner(file);
           while(lector.hasNextLine())
           {
               registro=lector.nextLine();
               String[] campos = registro.split(",");
               Person persona=new Person(Integer.parseInt(campos[0]),campos[1],campos[2],Integer.parseInt(campos[3]));
               personas.add(persona);
           }
       }
       catch (FileNotFoundException ex)
       {
           JOptionPane.showMessageDialog(null, "Fallo cargando lista");
       }
       return personas;
   }

    public void actualizarPersona(){
        int id, nuevaEdad;
        boolean existe=false;
        String nuevoNombre, nuevoApellido;
        ArrayList<Person> misPersonas=this.getLista();
        id = Integer.parseInt(JOptionPane.showInputDialog("Digite el ID de la persona que desea actualizar:"));
        
        for (Person persona : misPersonas) {
            if (persona.getId()==id) {
                existe=true;
                nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre:");
                nuevoApellido = JOptionPane.showInputDialog("Ingrese el nuevo apellido:");
                nuevaEdad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva edad:"));
                
                persona.setName(nuevoNombre);
                persona.setLastName(nuevoApellido);
                persona.setAge(nuevaEdad);
                this.recargarArchivo(misPersonas);

                JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
                break;
            }
        }
        if(!existe){
            JOptionPane.showMessageDialog(null, "No se encontró ninguna persona con este ID");
        }
    }
    
    public void recargarArchivo(ArrayList<Person> personas)
   {
       try
       {
           File file=new File(ruta);
           FileWriter fr = new FileWriter(file,false);
           PrintWriter ps = new PrintWriter(fr);
           for (Person persona:personas)
               ps.println(persona);
           ps.close();
       }
       catch (IOException ioe)
       {
           System.exit(1);
       }
   }
    
    public void eliminarPersona(){
       int id;
       int respuesta;
       boolean existe=false;
       ArrayList<Person> misPersonas=this.getLista();
       id = Integer.parseInt(JOptionPane.showInputDialog("Digite el ID de la persona que desea actualizar:"));
       
       for (Person persona : misPersonas) {
            if (persona.getId()==id) {
                existe=true;
                respuesta=JOptionPane.showConfirmDialog(null, "¿Está seguro?");
                if(respuesta==0){
                   misPersonas.remove(persona);
                   this.recargarArchivo(misPersonas);
                   JOptionPane.showMessageDialog(null, "Operación exitosa");
                   break;
                }
            }
        }
       if(!existe){
           JOptionPane.showMessageDialog(null, "No se encontró ninguna persona con este ID");
       }
    }
}
