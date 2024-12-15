/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//CUIDADO PERSONAL MYSQL
package pe.edu.pucp.lothel.ventas.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.lothel.manager.DBManager;
import pe.edu.pucp.lothel.ventas.dao.CuidadoPersonalDAO;

import pe.edu.pucp.lothel.ventas.model.CategoriaCuidadoPersonal;
import pe.edu.pucp.lothel.ventas.model.CuidadoPersonal;
import pe.edu.pucp.lothel.ventas.model.EmpresaProveedora;


/**
 *
 * @author efeproceres
 */
public class CuidadoPersonalMYSQL implements CuidadoPersonalDAO{
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    
    @Override
    public ArrayList<CuidadoPersonal> listarCuidadosPersonalesPorNombre(String nombre_buscado) {
        ArrayList<CuidadoPersonal> cuidados = new ArrayList<>();
        try{
            con =  DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_CUIDADOPERSONAL_POR_NOMBRE(?)}");
            cs.setString("_nombre_busqueda", nombre_buscado);
            rs = cs.executeQuery();
            while(rs.next()){
                CuidadoPersonal cuidado = new CuidadoPersonal();
                cuidado.setIdIteam(rs.getInt("Producto_Item_idItem"));
                cuidado.setCantPedido(rs.getInt("cantPedida"));
                
                cuidado.setDisponibilidad(rs.getBoolean("disponibilidad"));//??????????''
                cuidado.setStock(rs.getInt("stock"));
                cuidado.setDescripcion(rs.getString("descripcion"));    
                cuidado.setNombre(rs.getString("nombre"));    
                cuidado.setPrecio(rs.getDouble("precio"));    
                cuidado.setCalificacion(rs.getDouble("calificacion"));    
                cuidado.setUrlImagen(rs.getString("urlImagen"));
                //agregando empresa
                cuidado.setEmpresa(new EmpresaProveedora());
                cuidado.getEmpresa().setIdEmpresa(rs.getInt("EmpresaProveedora_idEmpresaProveedora"));
                //
                cuidado.setCategoria(CategoriaCuidadoPersonal.valueOf(rs.getString("categoria")));
                
                cuidados.add(cuidado);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return cuidados;
    }
    
    @Override
    public int insertar(CuidadoPersonal cuidadoPersonal) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            //call insertar item
             cs = con.prepareCall("{call INSERTAR_CUIDADOPERSONAL(?,?,?,?,?,?,?,?,?,?,?)}");//igual al de procedure
            cs.registerOutParameter("_idCuidadoPersonal",java.sql.Types.INTEGER);
            //parte del producto
            cs.setInt("_cantPedida", cuidadoPersonal.getCantPedido());
            cs.setBoolean("_disponibilidad", true);
            cs.setInt("_stock",cuidadoPersonal.getStock());
            cs.setInt("_EmpresaProveedora_idEmpresaProveedora", cuidadoPersonal.getEmpresa().getIdEmpresa());
            //parte del item

            cs.setString("_descripcion", cuidadoPersonal.getDescripcion());
            cs.setString("_nombre", cuidadoPersonal.getNombre());
            cs.setDouble("_precio",cuidadoPersonal.getPrecio());
            cs.setDouble("_calificacion",cuidadoPersonal.getCalificacion());
            cs.setString("_urlImagen", cuidadoPersonal.getUrlImagen());
            //
            cs.setString("_categoria",cuidadoPersonal.getCategoria().toString());
            //imagen
            //arrayList
          
            //ejecutamos
            cs.executeUpdate();
            //si queremos el id
            cuidadoPersonal.setIdIteam(cs.getInt("_idCuidadoPersonal"));
            resultado = cuidadoPersonal.getIdIteam();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            
            try{con.close();}catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public int modificar(CuidadoPersonal cuidadoPersonal) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call MODIFICAR_CUIDADOPERSONAL(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("_idCuidadoPersonal", cuidadoPersonal.getIdIteam());
            //parte del producto
            cs.setInt("_cantPedida", cuidadoPersonal.getCantPedido());
            cs.setBoolean("_disponibilidad", cuidadoPersonal.isDisponibilidad()); //aqui esta bien??????
            cs.setInt("_stock",cuidadoPersonal.getStock());
            
            //Para empresa
            cs.setInt("_EmpresaProveedora_idEmpresaProveedora", cuidadoPersonal.getEmpresa().getIdEmpresa());

            //parte del item
            cs.setString("_descripcion", cuidadoPersonal.getDescripcion());
            cs.setString("_nombre", cuidadoPersonal.getNombre());
            cs.setDouble("_precio",cuidadoPersonal.getPrecio());
            cs.setDouble("_calificacion",cuidadoPersonal.getCalificacion());
            cs.setString("_urlImagen", cuidadoPersonal.getUrlImagen());
            
            //parte de cuidado personal
            cs.setString("_categoria",cuidadoPersonal.getCategoria().toString());

            resultado = cs.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public int eliminar(int idCuidadoPersonal) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ELIMINAR_CUIDADO_PERSONAL (?)}");
            cs.setInt("_idCuidadoPersonal", idCuidadoPersonal);
            resultado = cs.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public ArrayList<CuidadoPersonal> listarCuidadosPersonales() {
        ArrayList<CuidadoPersonal> cuidados = new ArrayList<>();
        try{
            con =  DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_CUIDADOPERSONAL()}");
            rs = cs.executeQuery();
            while(rs.next()){
                CuidadoPersonal cuidado = new CuidadoPersonal();
                cuidado.setIdIteam(rs.getInt("Producto_Item_idItem"));
                cuidado.setCantPedido(rs.getInt("cantPedida"));
                
                cuidado.setDisponibilidad(rs.getBoolean("disponibilidad"));//??????????''
                cuidado.setStock(rs.getInt("stock"));
                cuidado.setDescripcion(rs.getString("descripcion"));    
                cuidado.setNombre(rs.getString("nombre"));    
                cuidado.setPrecio(rs.getDouble("precio"));    
                cuidado.setCalificacion(rs.getDouble("calificacion"));    
                cuidado.setUrlImagen(rs.getString("urlImagen"));
                //
                cuidado.setCategoria(CategoriaCuidadoPersonal.valueOf(rs.getString("categoria")));
                
                cuidados.add(cuidado);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return cuidados;
    }

    

}
