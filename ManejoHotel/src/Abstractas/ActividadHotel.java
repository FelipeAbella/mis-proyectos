package Abstractas;

import Entidades.Cliente;
import Entidades.Habitacion;

public abstract class ActividadHotel {
    private Cliente cliente;
    private Habitacion habitacion;

    public ActividadHotel(Cliente cliente, Habitacion habitacion) {
        this.cliente = cliente;
        this.habitacion = habitacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public abstract void ejecutar();
}