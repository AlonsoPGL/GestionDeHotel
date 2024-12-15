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
public class Huesped extends Persona {
    private boolean esVIP;
    
    public Huesped() {
    }
    public Huesped(boolean esVIP, String dni, String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaRegistro, String correo, String celular, boolean estado, Cuenta cuenta) {
        super(dni, nombre, apellidoPaterno, apellidoMaterno, fechaRegistro, correo, celular, estado, cuenta);
        this.esVIP = esVIP;
    }

  
    public boolean getEsVIP() {
        return esVIP;
    }

    public void setEsVIP(boolean esVIP) {
        this.esVIP = esVIP;
    }

}
