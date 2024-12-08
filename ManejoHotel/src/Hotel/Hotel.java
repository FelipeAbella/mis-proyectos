package Hotel;

import Entidades.Cliente;
import Entidades.Habitacion;
import Funcionalidades.IniciarSesion;
import Funcionalidades.RegistrarCliente;
import Funcionalidades.tipoHabitacion;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private int option;
    private List<Habitacion> habitaciones;
    private List<tipoHabitacion> tiposHabitacion;

    public static void main(String[] args) {
        new Hotel().menu();
    }

    public Hotel() {
        // Inicializar algunas habitaciones
        habitaciones = new ArrayList<>();
        habitaciones.add(new Habitacion(101, "Grande"));
        habitaciones.add(new Habitacion(102, "Mediana"));
        habitaciones.add(new Habitacion(103, "Pequeña"));
        habitaciones.add(new Habitacion(104, "Grande"));
        habitaciones.add(new Habitacion(105, "Mediana"));
        habitaciones.add(new Habitacion(106, "Pequeña"));
        
        // Inicializar los tipos de habitación y sus precios
        tiposHabitacion = new ArrayList<>();
        tiposHabitacion.add(new tipoHabitacion("Grande", 150.0, habitaciones));
        tiposHabitacion.add(new tipoHabitacion("Pequeña", 100.0, habitaciones));
        tiposHabitacion.add(new tipoHabitacion("Mediana", 50.0, habitaciones));
    }

    private void menu() {
        IniciarSesion iniciarSesion = new IniciarSesion();
        do {
            String input = JOptionPane.showInputDialog(null,
                    "1. Iniciar sesión\n" +
                            "2. Registrarse\n" +
                            "3. Salir\n" +
                            "Seleccione una opción:");

            try {
                option = Integer.parseInt(input);
                switch (option) {
                    case 1:
                        if (iniciarSesion.iniciarSesion(null, null, null)) {
                            menuCliente(iniciarSesion.obtenerClienteLogeado());
                        }
                        break;
                    case 2:
                        RegistrarCliente registrarCliente = new RegistrarCliente();
                        registrarCliente.solicitarDatosCliente();
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese una opción válida");
            }
        } while (true);
    }
    
    private void menuCliente (Cliente cliente) {
        do {
            String input = JOptionPane.showInputDialog(null,
                    "1. Hacer Reservas\n" +
                            "2. Escoger tipo de habitación\n" +
                            "3. Salir\n" +
                            "Seleccione una opción:");

            try {
                option = Integer.parseInt(input);
                switch (option) {
                    case 1:
                        //hacerReserva();
                        break;
                    case 2:
                        tipoHabitacion tipoHab = new tipoHabitacion("", 0.0, habitaciones);
                        tipoHab.escogerTipoHabitacion(cliente);
                        break;
                    case 3:
                        return;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido");
            }
        } while (true);
    }
}