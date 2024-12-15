
package pe.edu.pucp.lothel.gestreserva.model;

import java.util.Date;
import pe.edu.pucp.lothel.rrhh.model.Huesped;

public class ReservaHabitacion {
    private int idReserva;
    private Date fechaDeReserva;
    private Date fechaInicio;
    private Date fechaFin;
    private EstadoReserva estado;
    private Huesped huesped;
    private Habitacion habitacion;
    
    
    public ReservaHabitacion() {
        this.huesped=new Huesped();
        this.habitacion=new Habitacion();
    }
    

    public ReservaHabitacion(Date fechaDeReserva, Date fechaInicio, Date fechaFin, EstadoReserva estado, Huesped huesped, Habitacion habitacion) {
        this.fechaDeReserva = fechaDeReserva;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.huesped = huesped;
        this.habitacion = habitacion;
    }
    
    public Huesped getHuesped() {
        return this.huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }
    
    
    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Date getFechaDeReserva() {
        return fechaDeReserva;
    }

    public void setFechaDeReserva(Date fechaDeReserva) {
        this.fechaDeReserva = fechaDeReserva;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }
}
