package Funcionalidades;

import Entidades.Cliente;
import Interfaces.Autenticacion;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IniciarSesion implements Autenticacion {

    private static final String RUTA_BASE_DE_DATOS_CLIENTES = "C:\\Users\\abell\\Desktop\\NetBeans\\Hotel\\src\\BaseDeDatosClientes.txt";
    private Cliente clienteLogeado;

    @Override
    public void registrarCliente(Cliente cliente) {
        // Método de la interfaz, no implementado en esta clase
    }

    @Override
    public boolean iniciarSesion(String Nombre, String documentoIdentidad, String contrasena) {
    documentoIdentidad = JOptionPane.showInputDialog("Ingrese su documento de identidad:");
    contrasena = JOptionPane.showInputDialog("Ingrese su contraseña:");
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_BASE_DE_DATOS_CLIENTES))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length == 3) {
                    String documentoIdentidadArchivo = partes[1];
                    String contrasenaArchivo = partes[2];
                    if (documentoIdentidadArchivo.equals(documentoIdentidad) && contrasenaArchivo.equals(contrasena)) {
                        JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso. Bienvenido " + partes[0]);
                        clienteLogeado = new Cliente(partes[0], documentoIdentidadArchivo, contrasenaArchivo);
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Documento de identidad o contraseña incorrectos. Intente nuevamente.");
        return false;
    }

    public Cliente obtenerClienteLogeado() {
        return clienteLogeado;
    }
}