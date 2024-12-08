package Entidades;

public class Promocion {
    private String codigo;
    private double descuento;

    public Promocion(String codigo, double descuento) {
        this.codigo = codigo;
        this.descuento = descuento;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getDescuento() {
        return descuento;
    }

    public boolean validarCodigo(String codigo) {
        return this.codigo.equals(codigo);
    }

    @Override
    public String toString() {
        return "Promocion{" +
                "codigo='" + codigo + '\'' +
                ", descuento=" + descuento +
                '}';
    }
}
