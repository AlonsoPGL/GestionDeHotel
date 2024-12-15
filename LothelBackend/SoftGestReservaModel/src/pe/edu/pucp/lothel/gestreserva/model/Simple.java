/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.lothel.gestreserva.model;

/**
 *
 * @author marcelo
 */
public class Simple extends Habitacion{
    private boolean tieneVistaInterior;
    private boolean servicioStreaming;

    public Simple() {
    }

    public Simple(boolean tieneVistaInterior, boolean servicioStreaming, int piso, int numeroDeCamas, double precio,
            boolean reservado,byte[] imagen,String titulo,String descripcion,int cantHuespedes,int stock) {
        super(piso, numeroDeCamas, precio, reservado,imagen,titulo,descripcion,cantHuespedes,stock);
        this.tieneVistaInterior = tieneVistaInterior;
        this.servicioStreaming = servicioStreaming;
    }

    public boolean getTieneVistaInterior() {
        return tieneVistaInterior;
    }

    public void setTieneVistaInterior(boolean tieneVistaInterior) {
        this.tieneVistaInterior = tieneVistaInterior;
    }

    public boolean getServicioStreaming() {
        return servicioStreaming;
    }

    public void setServicioStreaming(boolean servicioStreaming) {
        this.servicioStreaming = servicioStreaming;
    }
}
