//CuidadoPersonalJAVA

package pe.edu.pucp.lothel.ventas.model;

import java.awt.Image;

/**
 *
 * @author efeproceres
 */
public class CuidadoPersonal extends Producto{
    private CategoriaCuidadoPersonal categoria;

    public CuidadoPersonal(CategoriaCuidadoPersonal categoria, int cantPedido, int stock, String descripcion, String nombre, double precio, double calificacion) {
        super(cantPedido, stock, descripcion, nombre, precio, calificacion);
        this.categoria = categoria;
    }

    public CuidadoPersonal() {
    }
    //con disponibilidad
    public CuidadoPersonal(int cantPedido, int stock, String descripcion, String nombre, double precio, double calificacion, boolean disponibilidad, CategoriaCuidadoPersonal categoria) {
        super(cantPedido, stock, descripcion, nombre, precio, calificacion, disponibilidad);
        this.categoria= categoria;
    }
    //con empresa
    public CuidadoPersonal(int cantPedido, int stock, String descripcion, String nombre, double precio, double calificacion, boolean disponibilidad, CategoriaCuidadoPersonal categoria, EmpresaProveedora empresa) {
        super(cantPedido, stock, descripcion, nombre, precio, calificacion, disponibilidad, empresa);
        this.categoria= categoria;
    }
    
    
    public CategoriaCuidadoPersonal getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaCuidadoPersonal categoria) {
        this.categoria = categoria;
    }
    
    
}
