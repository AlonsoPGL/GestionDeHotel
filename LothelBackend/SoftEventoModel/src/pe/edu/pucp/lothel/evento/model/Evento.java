/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.lothel.evento.model;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.lothel.rrhh.model.Administrador;

/**
 *
 * @author efeproceres
 */
public class Evento {
    private static int correlativo=1;
    private int idEvento;
    private String nombre;
    private String descripcion;
    private int cantidadAsistentes;
    private Date fechaInicio;
    private Date fechaFin;

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    private EstadoEvento estado;
    private Administrador administrador;
    private boolean activo;
    private ArrayList<ReservaEspacio> reservaEspacios;

    
    

    public Evento(String nombre, String descripcion, int cantidadAsistentes, Date fechaInicio, Date fechaFin, EstadoEvento estado,Administrador administrador, boolean activo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidadAsistentes = cantidadAsistentes;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.administrador=administrador;
        this.activo = activo;
        this.idEvento=correlativo;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }
    
    public Evento() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidadAsistentes() {
        return cantidadAsistentes;
    }

    public void setCantidadAsistentes(int cantidadAsistentes) {
        this.cantidadAsistentes = cantidadAsistentes;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public EstadoEvento getEstado() {
        return estado;
    }

    public void setEstado(EstadoEvento estado) {
        this.estado = estado;
    }

    public ArrayList<ReservaEspacio> getReservaEspacios() {
        return reservaEspacios;
    }

    public void setReservaEspacios(ArrayList<ReservaEspacio> reservaEspacios) {
        this.reservaEspacios = reservaEspacios;
    }
    
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}

