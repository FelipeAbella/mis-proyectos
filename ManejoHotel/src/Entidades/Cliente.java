package Entidades;

public class Cliente {
    private static String nombre;
    private String documentoIdentidad;
    private String contrasena;

    public Cliente(String nombre, String documentoIdentidad, String contraseña) {
        Cliente.nombre = nombre;
        this.documentoIdentidad = documentoIdentidad;
        this.contrasena = contraseña;
    }

    public static String getNombre() {
        return nombre;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public String getContraseña() {
        return contrasena;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", documentoIdentidad='" + documentoIdentidad + '\'' +
                '}';
    }
}

