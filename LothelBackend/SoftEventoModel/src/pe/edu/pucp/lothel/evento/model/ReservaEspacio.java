/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.lothel.evento.model;

import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author efeproceres
 */
public class ReservaEspacio {
    private int idReservaEspacio;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private boolean estado;
    private Evento evento;
    private Espacio espacio;
    private Date fechaDeReserva;
    

    public int getIdReservaEspacio() {
        return idReservaEspacio;
    }

    public void setIdReservaEspacio(int idReservaEspacio) {
        this.idReservaEspacio = idReservaEspacio;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Espacio getEspacio() {
        return espacio;
    }

    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    public Date getFechaDeReserva() {
        return fechaDeReserva;
    }

    public void setFechaDeReserva(Date fechaDeReserva) {
        this.fechaDeReserva = fechaDeReserva;
    }
    
    
    
}
