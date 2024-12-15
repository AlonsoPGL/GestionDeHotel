/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.lothel.ventas.model;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.lothel.gestreserva.model.ReservaHabitacion;

/**
 *
 * @author efeproceres
 */
public class Pedido {
    private int idPedido;
    private Date fechaSolicitud;
    private EstadoPedido estado;
    private double montoAcumulado;
    private ArrayList<Item>items;
    private DocumentoDePago documentoPago;
    private ReservaHabitacion reserva;
    private String NombreHuesped;
    private int numHabitacion;
    private int pisoHabitacion;

    
    private Date horaFinServicio;
    
    private String anotacionesServicio;
   
    
    private String incidenciaDeHabitacion;
    
    
    
    
    public Date getHoraFinServicio() {
        return horaFinServicio;
    }

    public void setHoraFinServicio(Date horaFinServicio) {
        this.horaFinServicio = horaFinServicio;
    }
    
    public Pedido() {
    }

    public Pedido(Date fechaSolicitud, EstadoPedido estado, double montoAcumulado, ReservaHabitacion reserva,TipoDocumento tipo) {
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
        this.montoAcumulado = montoAcumulado;
        this.reserva = reserva;
        documentoPago=new DocumentoDePago(tipo);
    }
    
    public String getAnotacionesServicio() {
        return anotacionesServicio;
    }

    public void setAnotacionesServicio(String anotacionesServicio) {
        this.anotacionesServicio = anotacionesServicio;
    }
    
    public Pedido(Date fechaSolicitud, EstadoPedido estado, double montoAcumulado, DocumentoDePago documentoPago) {
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
        this.montoAcumulado = montoAcumulado;
        this.documentoPago = documentoPago;
    }
    
    public String getIncidenciaDeHabitacion() {
        return incidenciaDeHabitacion;
    }

    public void setIncidenciaDeHabitacion(String incidenciaDeHabitacion) {
        this.incidenciaDeHabitacion = incidenciaDeHabitacion;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public double getMontoAcumulado() {
        return montoAcumulado;
    }

    public void setMontoAcumulado(double montoAcumulado) {
        this.montoAcumulado = montoAcumulado;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public DocumentoDePago getDocumentoPago() {
        return documentoPago;
    }

    public void setDocumentoPago(DocumentoDePago documentoPago) {
        this.documentoPago = documentoPago;
    }

    public ReservaHabitacion getReserva() {
        return reserva;
    }

    public void setReserva(ReservaHabitacion reserva) {
        this.reserva = reserva;
        
    }
    
    
     public void setNumHabitacion(int numHabitacion){
        this.numHabitacion=numHabitacion;
    }
    
    public int getNumHabitacion(){
        return this.numHabitacion;
    }
    
    
    
    public void setPisoHabitacion(int pisoHabitacion){
        this.pisoHabitacion=pisoHabitacion;
    }
    
    public int getPisoHabitacion(){
        return this.pisoHabitacion;
    }
    
    
    
    
    
    public void setNombreHuesped(String nombre){
        this.NombreHuesped=nombre;
    }
    
    public String getNombreHuesped(){
        return this.NombreHuesped;
    }
    
    
    
}

