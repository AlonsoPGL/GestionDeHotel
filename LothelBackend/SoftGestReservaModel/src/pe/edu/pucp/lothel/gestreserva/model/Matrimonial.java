/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.lothel.gestreserva.model;

/**
 *
 * @author marcelo
 */
public class Matrimonial extends Habitacion{
    private boolean tieneJacuzzi;
    public Matrimonial(boolean tieneJacuzzi, int piso, int numeroDeCamas, double precio, boolean reservado,byte[] imagen,
            String titulo,String descripcion,int cantHuespedes,int stock) {
        super(piso, numeroDeCamas, precio, reservado,imagen,titulo,descripcion,cantHuespedes,stock);
        this.tieneJacuzzi = tieneJacuzzi;
    }

    public Matrimonial() {
    }

    public boolean getTieneJacuzzi() {
        return tieneJacuzzi;
    }

    public void setTieneJacuzzi(boolean tieneJacuzzi) {
        this.tieneJacuzzi = tieneJacuzzi;
    }
    
}
