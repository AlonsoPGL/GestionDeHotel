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
public class ServicioDeLavanderia extends Servicio{
    private String anotaciones;

    public ServicioDeLavanderia(String anotaciones, EstadoServicio estado, String incidencia, String descripcion, String nombre, double precio, double calificacion) {
        super(estado, incidencia, descripcion, nombre, precio, calificacion);
        this.anotaciones = anotaciones;
    }

    public ServicioDeLavanderia() {
    }

    public String getAnotaciones() {
        return anotaciones;
    }

    public void setAnotaciones(String anotaciones) {
        this.anotaciones = anotaciones;
    }
    
}
