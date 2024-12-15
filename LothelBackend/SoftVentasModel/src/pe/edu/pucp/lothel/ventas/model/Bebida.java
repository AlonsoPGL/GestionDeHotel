//bebidaJAVA
package pe.edu.pucp.lothel.ventas.model;
//package pe.edu.pucp.lothel.ventas.dao;

import java.awt.Image;
import pe.edu.pucp.lothel.ventas.model.CategoriaBebida;
import pe.edu.pucp.lothel.ventas.model.Producto;

/**
 *
 * @author efeproceres
 */
public class Bebida extends Producto{
    private CategoriaBebida categoria;
    private boolean estaHelada;

    public Bebida() {
    }
    
    public Bebida(int cantPedido, int stock, String descripcion, String nombre, double precio, double calificacion, boolean estaHelada) {
        super(cantPedido, stock, descripcion, nombre, precio, calificacion);
    }
    //con disponibilidad
    public Bebida(int cantPedido, int stock, String descripcion, String nombre, double precio, double calificacion, boolean estaHelada, boolean disponibilidad, CategoriaBebida categoria) {
        super(cantPedido, stock, descripcion, nombre, precio, calificacion, disponibilidad);
        this.estaHelada = estaHelada;
        this.categoria= categoria;
    }
    //con empresa
    public Bebida(int cantPedido, int stock, String descripcion, String nombre, double precio, double calificacion, boolean estaHelada, boolean disponibilidad, CategoriaBebida categoria, EmpresaProveedora empresa) {
        super(cantPedido, stock, descripcion, nombre, precio, calificacion, disponibilidad, empresa);
        this.estaHelada = estaHelada;
        this.categoria= categoria;
    }

    public CategoriaBebida getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaBebida categoria) {
        this.categoria = categoria;
    }

    public boolean isEstaHelada() {
        return estaHelada;
    }

    public void setEstaHelada(boolean estaHelada) {
        this.estaHelada = estaHelada;
    }
    
}
