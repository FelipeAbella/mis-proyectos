package Entidades;

public class Pago {
    private Reserva reserva;
    private String metodoPago; // Tarjeta, Efectivo, etc.
    private boolean exito;

    public Pago(Reserva reserva, String metodoPago) {
        this.reserva = reserva;
        this.metodoPago = metodoPago;
        this.exito = false;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "reserva=" + reserva +
                ", metodoPago='" + metodoPago + '\'' +
                ", exito=" + exito +
                '}';
    }
}