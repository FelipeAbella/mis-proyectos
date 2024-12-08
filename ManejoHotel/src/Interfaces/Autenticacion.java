package Interfaces;

import Entidades.Cliente;

public interface Autenticacion {
    boolean iniciarSesion(String Nombre, String documentoIdentidad, String contraseña);
    void registrarCliente(Cliente cliente);
}
