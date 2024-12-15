/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.lothel.ventas.model;

import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author efeproceres
 */
public class Item {
    private int idIteam;
    private String descripcion;
    private String nombre;
    private double precio;
    private double calificacion;
    private Image imagen;
    private ArrayList<Pedido> pedido;
    private String urlImagen;

    public Item() {
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Item(String descripcion, String nombre, double precio, double calificacion, Image imagen) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.precio = precio;
        this.calificacion = calificacion;
        this.imagen = imagen;
    }
    public Item(String descripcion, String nombre, double precio, double calificacion) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.precio = precio;
        this.calificacion = calificacion;
        //this.imagen = imagen;
    }

    public int getIdIteam() {
        return idIteam;
    }

    public void setIdIteam(int idIteam) {
        this.idIteam = idIteam;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public ArrayList<Pedido> getPedido() {
        return pedido;
    }

    public void setPedido(ArrayList<Pedido> pedido) {
        this.pedido = pedido;
    }

    
    
}
