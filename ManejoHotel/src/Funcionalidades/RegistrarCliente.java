package Funcionalidades;

import Entidades.Cliente;
import Interfaces.Autenticacion;

import javax.swing.*;
import java.io.*;

public class RegistrarCliente implements Autenticacion {

    private static final String RUTA_BASE_DE_DATOS_CLIENTES = "C:\\Users\\abell\\Desktop\\NetBeans\\Hotel\\src\\BaseDeDatosClientes.txt";

    @Override
    public boolean iniciarSesion(String Nombre, String documentoIdentidad, String contrasena) {
        // Método de la interfaz, no implementado en esta clase
        return false;
    }

    @Override
    public void registrarCliente(Cliente cliente) {
        if (verificacionExistenciaDeCliente(cliente.getDocumentoIdentidad())) {
            JOptionPane.showMessageDialog(null, "El cliente con el documento " + cliente.getDocumentoIdentidad() + " ya está registrado.");
            return; // No se registra el cliente si ya existe
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_BASE_DE_DATOS_CLIENTES, true))) {
            // Escribir en el archivo la información del cliente
            writer.write(cliente.getNombre() + ":" + cliente.getDocumentoIdentidad() + ":" + cliente.getContraseña() + "\n");
            JOptionPane.showMessageDialog(null, "Registro exitoso. Bienvenido " + cliente.getNombre());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void solicitarDatosCliente() {
        String nombre = JOptionPane.showInputDialog("Ingrese su nombre:");
        String documentoIdentidad = JOptionPane.showInputDialog("Ingrese su documento de identidad:");
        String contrasena = JOptionPane.showInputDialog("Ingrese su contraseña:");

        Cliente cliente = new Cliente(nombre, documentoIdentidad, contrasena);
        registrarCliente(cliente);
    }

    public static boolean verificacionExistenciaDeCliente(String documentoIdentidad) {
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_BASE_DE_DATOS_CLIENTES))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length >= 2) {
                    String documentoIdentidadArchivo = partes[1];
                    if (documentoIdentidadArchivo.equals(documentoIdentidad)) {
                        return true; // El cliente ya existe
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // El cliente no existe
    }

    public static void main(String[] args) {
        RegistrarCliente registrarCliente = new RegistrarCliente();
        registrarCliente.solicitarDatosCliente();
    }
}
