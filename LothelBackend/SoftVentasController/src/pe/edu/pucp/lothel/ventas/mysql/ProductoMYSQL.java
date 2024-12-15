/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.lothel.ventas.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import pe.edu.pucp.lothel.manager.DBManager;
import pe.edu.pucp.lothel.ventas.dao.ProductoDAO;
import pe.edu.pucp.lothel.ventas.model.EmpresaProveedora;
import pe.edu.pucp.lothel.ventas.model.Producto;

/**
 *
 * @author gumar
 */
public class ProductoMYSQL implements ProductoDAO{

    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    
    @Override
    public ArrayList<Producto> ListarPorNombre(String nombreBusqueda) {
        ArrayList<Producto> productos = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_PRODUCTO_POR_NOMBRE(?)}");
            cs.setString("_nombre_busqueda", nombreBusqueda);
            rs = cs.executeQuery();

            while(rs.next()){
                Producto producto = new Producto();
                
                producto.setIdIteam(rs.getInt("idItem"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setCalificacion(rs.getDouble("calificacion"));
                producto.setDisponibilidad(rs.getBoolean("disponibilidad"));
                producto.setUrlImagen(rs.getString("urlImagen"));
                producto.setStock(rs.getInt("stock"));
                producto.setCantPedido(rs.getInt("cantPedida"));
                producto.setEmpresa(new EmpresaProveedora());
                producto.getEmpresa().setIdEmpresa(rs.getInt("EmpresaProveedora_idEmpresaProveedora"));
                productos.add(producto);
            }   
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return productos;
    }
    
}
