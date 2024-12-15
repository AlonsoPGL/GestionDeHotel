/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.lothel.ventas.model;

import java.awt.Image;

/**
 *
 * @author efeproceres
 */
public class Servicio extends Item {
    private EstadoServicio estado;
    private String incidencia;
    public Servicio(EstadoServicio estado, String incidencia, String descripcion, String nombre, double precio, double calificacion) {
        super(descripcion, nombre, precio, calificacion);
        this.estado = estado;
        this.incidencia = incidencia;
    }

    public Servicio() {
    }

    public EstadoServicio getEstado() {
        return estado;
    }

    public void setEstado(EstadoServicio estado) {
        this.estado = estado;
    }

    public String getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(String incidencia) {
        this.incidencia = incidencia;
    }
    
}
