package pe.edu.pucp.lothel.rrhh.model;

import java.util.Date;

public class PersonalDeMasajes extends PersonalDeServicio{
    private String especializacion;

    public PersonalDeMasajes(String especializacion, TipoTurno turno, boolean estadoPS, Date fechaContratacion, boolean activo, String dni, String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaRegistro, String correo, String celular, boolean estado, Cuenta cuenta,Administrador administrador) {
        super(turno, estadoPS, fechaContratacion, activo, dni, nombre, apellidoPaterno, apellidoMaterno, fechaRegistro, correo, celular, estado, cuenta,administrador);
        this.especializacion = especializacion;
    }

    public PersonalDeMasajes() {
        ;
    }

    public String getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }
    
    //Hola
}
