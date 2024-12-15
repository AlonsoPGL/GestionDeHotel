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
public class PersonalDeServicio extends Operario{
    private TipoTurno turno;
    private boolean estadoPS;
    private Administrador administrador;
    public PersonalDeServicio(TipoTurno turno, boolean estadoPS, Date fechaContratacion, boolean activo, String dni, String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaRegistro, String correo, String celular, boolean estado, Cuenta cuenta,Administrador administrador) {
        super(fechaContratacion, activo, dni, nombre, apellidoPaterno, apellidoMaterno, fechaRegistro, correo, celular, estado, cuenta);
        this.turno = turno;
        this.estadoPS = estadoPS;
        this.administrador=administrador;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public PersonalDeServicio(){
    }    
    
    public boolean getEstadoPS() {
        return estadoPS;
    }

    public void setEstadoPS(boolean estadoPS) {
        this.estadoPS = estadoPS;
    }

    public TipoTurno getTurno() {
        return turno;
    }

    public void setTurno(TipoTurno turno) {
        this.turno = turno;
    }

    
    
}
