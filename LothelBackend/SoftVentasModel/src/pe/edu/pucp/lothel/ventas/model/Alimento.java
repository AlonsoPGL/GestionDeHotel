//alimento.java
package pe.edu.pucp.lothel.ventas.model;
//package lothel.soft.ventas.model;

import java.awt.Image;

/**
 *
 * @author efeproceres
 */
public class Alimento extends Producto{
    private CategoriaAlimento categoria;

    public Alimento() {
    }

    
    
    public Alimento(CategoriaAlimento categoria, int cantPedido, boolean disponibilidad, int stock, String descripcion, String nombre, double precio, double calificacion, Image imagen) {
        super(cantPedido, stock, descripcion, nombre, precio, calificacion, disponibilidad);
        //super(cantPedido, disponibilidad, stock, descripcion, nombre, precio, calificacion, imagen);
        this.categoria = categoria;
    }
    
     //con disponibilidad
    public Alimento(int cantPedido, int stock, String descripcion, String nombre, double precio, double calificacion, boolean disponibilidad, CategoriaAlimento categoria) {
        super(cantPedido, stock, descripcion, nombre, precio, calificacion, disponibilidad);
        this.categoria= categoria;
    }
     //con empresa
    public Alimento(int cantPedido, int stock, String descripcion, String nombre, double precio, double calificacion, boolean disponibilidad, CategoriaAlimento categoria, EmpresaProveedora empresa) {
        super(cantPedido, stock, descripcion, nombre, precio, calificacion, disponibilidad, empresa);
        this.categoria= categoria;
    }

    public CategoriaAlimento getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaAlimento categoria) {
        this.categoria = categoria;
    }
    
}
