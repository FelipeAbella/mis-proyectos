package Menu;

import ManejoDatos.DBPerson;
import javax.swing.JOptionPane;

public class PersonManageController {
    private DBPerson dbPerson;
    
    public PersonManageController(){
    this.dbPerson = new DBPerson();
}

    public void menu(){
        int opcion;
        do{
            opcion=Integer.parseInt(JOptionPane.showInputDialog("""
                                                                ==========OPCIONES==========
                                                                1. Agregar persona 
                                                                2. Ver lista de personas
                                                                3. Actualizar datos de una persona
                                                                4. Eliminar persona
                                                                0. Salir"""));
 
            switch(opcion)
            {
                case 1:
                    this.dbPerson.agregarPersona();
                    break;
                case 2:
                    this.dbPerson.listaPersonas();
                    break;
                case 3:
                    this.dbPerson.actualizarPersona();
                    break;
                case 4:
                    this.dbPerson.eliminarPersona();
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "¡Gracias por usar nuestros servicios!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "¡Opción inválida, inténtelo nuevamente!");
                    break;
            }
    }
        while (opcion != 0);
    }
}