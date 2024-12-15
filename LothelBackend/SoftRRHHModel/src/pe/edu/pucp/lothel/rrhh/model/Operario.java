/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.lothel.rrhh.model;

import java.util.Date;

/**
 *
 * @author marcelo
 */
public class Operario extends Persona {
    private Date fechaContratacion;
    private boolean activo;

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
    private double sueldo;

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Operario(Date fechaContratacion, boolean activo, String dni, String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaRegistro, String correo, String celular, boolean estado, Cuenta cuenta) {
        super(dni, nombre, apellidoPaterno, apellidoMaterno, fechaRegistro, correo, celular, estado, cuenta);
        this.fechaContratacion = fechaContratacion;
        this.activo = activo;
    } 
    public Operario(){};
    
}
