package Interfaces;

import Entidades.Cliente;
import Entidades.Habitacion;
import Entidades.Reserva;
import Entidades.Pago;
import Entidades.Promocion;

import java.util.Date;

public interface ServicioHotel {
    void realizarReserva(Cliente cliente, Habitacion habitacion, Date fechaEntrada, Date fechaSalida);
    void cancelarReserva(Reserva reserva);
    void checkIn(Cliente cliente, Habitacion habitacion);
    void checkOut(Cliente cliente, Habitacion habitacion);
    void procesarPago(Pago pago);
    void aplicarPromocion(Reserva reserva, Promocion promocion);
}