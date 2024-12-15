/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.lothel.rrhh.model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author marcelo
 */
public class Administrador extends Operario{
    private String rol;
    
    private ArrayList<PersonalDeServicio> personal;

    public Administrador(String rol, Date fechaContratacion, boolean activo, 
            String dni, String nombre, String apellidoPaterno, String apellidoMaterno, 
            Date fechaRegistro, String correo, String celular, boolean estado, Cuenta cuenta) {
        super(fechaContratacion, activo, dni, nombre, apellidoPaterno, apellidoMaterno, fechaRegistro, correo, celular, estado, cuenta);
        this.rol = rol;
    }
    
    public Administrador(){
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    public int getIdAdministrador(){
        return super.getIdPersona();
    }    
}

