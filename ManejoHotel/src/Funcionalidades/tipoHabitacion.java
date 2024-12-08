/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funcionalidades;

import Entidades.Cliente;
import Entidades.Habitacion;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abell
 */
public class tipoHabitacion {
    private String tipo;
    private double precio;
    private List<Habitacion> habitaciones;

    public tipoHabitacion(String tipo, double precio, List<Habitacion> habitaciones) {
        this.tipo = tipo;
        this.precio = precio;
        this.habitaciones = habitaciones;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecio() {
        return precio;
    }
    
    public void escogerTipoHabitacion(Cliente cliente) {
        String seleccion = JOptionPane.showInputDialog(null,
            "Ingrese el tipo de habitación (Grande, Mediana, Pequeña)",
            "Tipo de Habitación",
            JOptionPane.QUESTION_MESSAGE);

        if (seleccion != null && !seleccion.isEmpty()) {
            seleccion = seleccion.trim(); // Eliminamos espacios en blanco al principio y al final
            List<Habitacion> habitacionesDisponibles = buscarHabitacionesPorTipo(seleccion);
            if (habitacionesDisponibles.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay habitaciones disponibles de tipo: " + seleccion);
            } else {
                // Mostrar una lista de habitaciones disponibles para que el usuario elija una
                Habitacion habitacionSeleccionada = seleccionarHabitacion(habitacionesDisponibles, seleccion);
                if (habitacionSeleccionada != null) {
                    double costo = obtenerCosto(seleccion);
                    guardarSeleccionEnArchivo(cliente.getDocumentoIdentidad(), habitacionSeleccionada, costo);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No ha ingresado ningún tipo de habitación");
        }
    }

    private List<Habitacion> buscarHabitacionesPorTipo(String tipo) {
        List<Habitacion> habitacionesDisponibles = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getTipo().equalsIgnoreCase(tipo) && !habitacion.isEstaOcupada()) {
                habitacionesDisponibles.add(habitacion);
            }
        }
        return habitacionesDisponibles;
    }

    private Habitacion seleccionarHabitacion(List<Habitacion> habitacionesDisponibles, String tipoSeleccionado) {
        double costo = obtenerCosto(tipoSeleccionado);
        String[] opciones = new String[habitacionesDisponibles.size()];
        for (int i = 0; i < habitacionesDisponibles.size(); i++) {
            Habitacion habitacion = habitacionesDisponibles.get(i);
            opciones[i] = "Habitación #" + habitacion.getNumero() + " - " + habitacion.getTipo() + " - $" + costo;
        }

        String seleccion = (String) JOptionPane.showInputDialog(null,
            "Seleccione una habitación:",
            "Habitaciones Disponibles",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]);

        if (seleccion != null) {
            for (Habitacion habitacion : habitacionesDisponibles) {
                if (("Habitación #" + habitacion.getNumero() + " - " + habitacion.getTipo() + " - $" + costo).equals(seleccion)) {
                    return habitacion;
                }
            }
        }
        return null;
    }

    private void guardarSeleccionEnArchivo(String documentoCliente, Habitacion habitacion, double costo) {
        String rutaArchivo = "C:\\Users\\abell\\Desktop\\NetBeans\\Hotel\\src\\BaseDeDatosTiposDeHabitaciones.txt";

        String contenido = documentoCliente + ":"
                         + habitacion.getNumero() + ":"
                         + habitacion.getTipo() + ":"
                         + costo + "\n";

        habitacion.setEstaOcupada(true);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            writer.write(contenido);
            JOptionPane.showMessageDialog(null, "Selección guardada");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la selección en archivo: " + e.getMessage());
        }
    }

    private double obtenerCosto(String tipo) {
        switch (tipo.toLowerCase()) {
            case "grande":
                return 150.0;
            case "mediana":
                return 100.0;
            case "pequeña":
                return 75.0;
            default:
                return 0.0; // O algún valor por defecto
        }
    }
}