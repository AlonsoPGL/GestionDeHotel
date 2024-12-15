//bebidaMYSQL
package pe.edu.pucp.lothel.ventas.mysql;
//package lothel.soft.ventas.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.lothel.manager.DBManager;
import pe.edu.pucp.lothel.ventas.dao.BebidaDAO;
import pe.edu.pucp.lothel.ventas.model.Bebida;
import pe.edu.pucp.lothel.ventas.model.CategoriaBebida;
import pe.edu.pucp.lothel.ventas.model.EmpresaProveedora;
/**
 *
 * @author Melanie
 */
public class BebidaMYSQL implements BebidaDAO{
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;

    @Override
    public ArrayList<Bebida> listarBebidasPorNombre(String nombre_buscado) {
        ArrayList<Bebida> bebidas = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_BEBIDAS_POR_NOMBRE(?)}");
            cs.setString("_nombre_busqueda", nombre_buscado);
            rs = cs.executeQuery();
            while(rs.next()){
                Bebida bebida = new Bebida();
                bebida.setIdIteam(rs.getInt("Producto_Item_idItem")); //aqui de la workbench
                bebida.setCantPedido(rs.getInt("cantPedida"));
                //aqui //como llamo a padres
                bebida.setDisponibilidad(rs.getBoolean("disponibilidad"));//??????????''
                bebida.setStock(rs.getInt("stock"));
                
                
                bebida.setDescripcion(rs.getString("descripcion"));    
                bebida.setNombre(rs.getString("nombre"));    
                bebida.setPrecio(rs.getDouble("precio"));    
                bebida.setCalificacion(rs.getDouble("calificacion"));   
                bebida.setUrlImagen(rs.getString("urlImagen"));
                bebida.setEmpresa(new EmpresaProveedora());
                bebida.getEmpresa().setIdEmpresa(rs.getInt("EmpresaProveedora_idEmpresaProveedora"));
         
                bebida.setCategoria(CategoriaBebida.valueOf(rs.getString("categoria")));
                bebida.setEstaHelada(rs.getBoolean("estaHelada"));  //trabajado como int

                 
                bebidas.add(bebida);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return bebidas;
    }
    
    @Override
    public int insertar(Bebida bebida) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            //call insertar item
             cs = con.prepareCall("{call INSERTAR_BEBIDA(?,?,?,?,?,?,?,?,?,?,?,?)}");//igual al de procedure
            cs.registerOutParameter("_idBebida",java.sql.Types.INTEGER);
            //parte del producto
            cs.setInt("_cantPedida", bebida.getCantPedido());
            cs.setBoolean("_disponibilidad", true);
            cs.setInt("_stock",bebida.getStock());
            cs.setInt("_EmpresaProveedora_idEmpresaProveedora", bebida.getEmpresa().getIdEmpresa());

            //cs.setInt("_EmpresaProveedora_idEmpresaProveedora",bebida.getEmpresa().getIdEmpresa());
            //parte del item
            cs.setString("_descripcion", bebida.getDescripcion());
            cs.setString("_nombre", bebida.getNombre());
            cs.setDouble("_precio",bebida.getPrecio());
            cs.setDouble("_calificacion",bebida.getCalificacion());
            cs.setString("_urlImagen", bebida.getUrlImagen());
            //parte de bebida
            cs.setString("_categoria",bebida.getCategoria().toString());
            cs.setBoolean("_estaHelada",bebida.isEstaHelada());
            
            
            //imagen
            //arrayList
          
            //ejecutamos
            cs.executeUpdate();
            //si queremos el id
            bebida.setIdIteam(cs.getInt("_idBebida"));
            resultado = bebida.getIdIteam();
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
    public int modificar(Bebida bebida) {
         int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call MODIFICAR_BEBIDA(?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("_idBebida", bebida.getIdIteam());
            //parte del producto
            cs.setInt("_cantPedida", bebida.getCantPedido());
            cs.setBoolean("_disponibilidad", bebida.isDisponibilidad()); //aqui esta bien??????
            cs.setInt("_stock",bebida.getStock());
            cs.setInt("_EmpresaProveedora_idEmpresaProveedora", bebida.getEmpresa().getIdEmpresa());

            //parte del item
            cs.setString("_descripcion", bebida.getDescripcion());
            cs.setString("_nombre", bebida.getNombre());
            cs.setDouble("_precio",bebida.getPrecio());
            cs.setDouble("_calificacion",bebida.getCalificacion());
            cs.setString("_urlImagen", bebida.getUrlImagen());
            
            //parte del alimento
            cs.setString("_categoria",bebida.getCategoria().toString());
            cs.setBoolean("_estaHelada",bebida.isEstaHelada());

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
    public int eliminar(int idBebida) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ELIMINAR_BEBIDA (?)}");
            //cs.setInt("_idBebida", idBebida); //otra vez
            cs.setInt("_idBebida", idBebida);
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
    public ArrayList<Bebida> listarBebidas() {
        ArrayList<Bebida> bebidas = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_BEBIDAS_TODOS()}");
            rs = cs.executeQuery();
            while(rs.next()){
                Bebida bebida = new Bebida();
                bebida.setIdIteam(rs.getInt("Producto_Item_idItem")); //aqui de la workbench
                bebida.setCantPedido(rs.getInt("cantPedida"));
                //aqui //como llamo a padres
                bebida.setDisponibilidad(rs.getBoolean("disponibilidad"));//??????????''
                bebida.setStock(rs.getInt("stock"));
                
                
                bebida.setDescripcion(rs.getString("descripcion"));    
                bebida.setNombre(rs.getString("nombre"));    
                bebida.setPrecio(rs.getDouble("precio"));    
                bebida.setCalificacion(rs.getDouble("calificacion"));   
                bebida.setUrlImagen(rs.getString("urlImagen"));
                bebida.setEmpresa(new EmpresaProveedora());
                bebida.getEmpresa().setIdEmpresa(rs.getInt("EmpresaProveedora_idEmpresaProveedora"));
                
                bebida.setEmpresa(new EmpresaProveedora());
                bebida.getEmpresa().setIdEmpresa(rs.getInt("EmpresaProveedora_idEmpresaProveedora"));
                
                
                bebida.setCategoria(CategoriaBebida.valueOf(rs.getString("categoria")));
                bebida.setEstaHelada(rs.getBoolean("estaHelada"));  //trabajado como int

                 
                bebidas.add(bebida);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return bebidas;
    }


    
    
    
    
}
