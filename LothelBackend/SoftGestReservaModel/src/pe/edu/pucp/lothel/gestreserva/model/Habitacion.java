/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.lothel.gestreserva.model;

import java.util.ArrayList;
import pe.edu.pucp.lothel.gestreserva.model.ReservaHabitacion;

/**
 *
 * @author marcelo
 */
public class Habitacion {
    
    private int idHabitacion;
    private int piso;
    private int numeroDeCamas;
    private double precio;
    private boolean reservado;
    private boolean estado; 
    private byte[] imagen;
    private String titulo;
    private String descripcion;
    private int cantHuespedes;
    private int stock;
    private ArrayList<ReservaHabitacion> reservas;
    private CategoriaHabitacion categoria;

    public Habitacion(int piso, int numeroDeCamas, double precio, boolean reservado,byte[] imagen,String titulo,
            String descripcion,int cantHuespedes,int stock) {
        this.piso = piso;
        this.numeroDeCamas = numeroDeCamas;
        this.precio = precio;
        this.reservado = reservado;
        this.estado=true;
        this.imagen=imagen;
        this.titulo=titulo;
        this.descripcion=descripcion;
        this.cantHuespedes=cantHuespedes;
        this.stock=stock;
    }

    public Habitacion() {
    }
    
    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getNumeroDeCamas() {
        return numeroDeCamas;
    }

    public void setNumeroDeCamas(int numeroDeCamas) {
        this.numeroDeCamas = numeroDeCamas;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean getReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }

    public ArrayList<ReservaHabitacion> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<ReservaHabitacion> reservas) {
        this.reservas = reservas;
    }
    
    public byte[] getImagen() {
        return imagen;
    }
    
    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantHuespedes() {
        return cantHuespedes;
    }

    public void setCantHuespedes(int cantHuespedes) {
        this.cantHuespedes = cantHuespedes;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
