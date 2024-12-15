/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.lothel.ventas.model;

import java.awt.Image;
import java.util.Date;

/**
 *
 * @author efeproceres
 */
public class TicketEvento extends Producto{
    private static int idCorrelativo=1;
    private int idTicket;
    private Date fechaCompra;

    public TicketEvento(int cantPedido, int stock, String descripcion, String nombre, double precio, double calificacion) {
        super(cantPedido, stock, descripcion, nombre, precio, calificacion);
        this.idTicket = idCorrelativo;
        this.fechaCompra = new Date();
        idCorrelativo++;
    }

    public TicketEvento() {
    }

    public static int getIdCorrelativo() {
        return idCorrelativo;
    }

    public static void setIdCorrelativo(int idCorrelativo) {
        TicketEvento.idCorrelativo = idCorrelativo;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    
    
}
