/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.lothel.evento.model;

import java.util.ArrayList;

/**
 *
 * @author efeproceres
 */
public class Espacio {
    private int idEspacio;
    private int numeroPiso;
    private String seccion;
    private int aforo;
    private boolean disponibilidad;
    private ArrayList<ReservaEspacio> reservasEspacio;

    public Espacio(int numeroPiso, String seccion, int aforo, boolean disponibilidad) {
        this.numeroPiso = numeroPiso;
        this.seccion = seccion;
        this.aforo = aforo;
        this.disponibilidad = disponibilidad;
    }

    public int getIdEspacio() {
        return idEspacio;
    }

    public void setIdEspacio(int idEspacio) {
        this.idEspacio = idEspacio;
    }
    
    public Espacio() {
    }

    public int getNumeroPiso() {
        return numeroPiso;
    }

    public void setNumeroPiso(int numeroPiso) {
        this.numeroPiso = numeroPiso;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public ArrayList<ReservaEspacio> getReservasEspacio() {
        return reservasEspacio;
    }

    public void setReservasEspacio(ArrayList<ReservaEspacio> reservasEspacio) {
        this.reservasEspacio = reservasEspacio;
    }
    
    
}
