//producto
package pe.edu.pucp.lothel.ventas.model;
//package lothel.soft.ventas.model;

import java.awt.Image;

/**
 *
 * @author efeproceres
 */
public class Producto extends Item{
    private int idProducto;
    private int cantPedido;
    private boolean disponibilidad;
    private int stock;
    private EmpresaProveedora empresa; //falta

    public Producto() {
    }
    
    public Producto(int cantPedido, int stock, String descripcion, String nombre, double precio, double calificacion) {
        super(descripcion, nombre, precio, calificacion);
        this.cantPedido = cantPedido;
        this.stock = stock;
    }
    //con disponibilidad
    public Producto(int cantPedido, int stock, String descripcion, String nombre, double precio, double calificacion, boolean disponibilidad) {
        super(descripcion, nombre, precio, calificacion);
        this.cantPedido = cantPedido;
        this.stock = stock;
        this.disponibilidad = disponibilidad;
    }
    //con empresa
    public Producto(int cantPedido, int stock, String descripcion, String nombre, double precio, double calificacion, boolean disponibilidad, EmpresaProveedora empresa) {
        super(descripcion, nombre, precio, calificacion);
        this.cantPedido = cantPedido;
        this.stock = stock;
        this.disponibilidad = disponibilidad;
        this.empresa= empresa;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
    public EmpresaProveedora getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaProveedora empresa) {
        this.empresa = empresa;
    }
    
    public int getCantPedido() {
        return cantPedido;
    }

    public void setCantPedido(int cantPedido) {
        this.cantPedido = cantPedido;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
}