//alimentoMYSQL

package pe.edu.pucp.lothel.ventas.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.lothel.manager.DBManager;
import pe.edu.pucp.lothel.ventas.dao.AlimentoDAO;
import pe.edu.pucp.lothel.ventas.model.Alimento;
import pe.edu.pucp.lothel.ventas.model.CategoriaAlimento;
import pe.edu.pucp.lothel.ventas.model.EmpresaProveedora;
//
/**
 *
 * @author efeproceres
 */
public class AlimentoMYSQL implements AlimentoDAO{
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    
    
    @Override
    public ArrayList<Alimento> listarAlimentosPorNombre(String nombre_buscado) {
        ArrayList<Alimento> alimentos = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_ALIMENTOS_POR_NOMBRE(?)}");
            cs.setString("_nombre_busqueda",nombre_buscado);
            rs = cs.executeQuery();
            while(rs.next()){
                Alimento alimento = new Alimento();
                alimento.setIdIteam(rs.getInt("Producto_Item_idItem"));
                alimento.setCantPedido(rs.getInt("cantPedida"));
               
                alimento.setDisponibilidad(rs.getBoolean("disponibilidad"));//??????????''
                alimento.setStock(rs.getInt("stock"));
                alimento.setDescripcion(rs.getString("descripcion"));    
                alimento.setNombre(rs.getString("nombre"));    
                alimento.setPrecio(rs.getDouble("precio"));    
                alimento.setCalificacion(rs.getDouble("calificacion"));    
                alimento.setEmpresa(new EmpresaProveedora());
                alimento.getEmpresa().setIdEmpresa(rs.getInt("EmpresaProveedora_idEmpresaProveedora"));
                alimento.setUrlImagen(rs.getString("urlImagen"));
                //alimento.getEmpresa().set(rs.getString("nombre_area"));
                alimento.setCategoria(CategoriaAlimento.valueOf(rs.getString("categoria")));
                
                //alimento.setString("_categoria",alimento.getCategoria().toString());
                 
                 
                alimentos.add(alimento);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return alimentos;
    }
    
    @Override
    public int insertar(Alimento alimento) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            //call insertar item
            cs = con.prepareCall("{call INSERTAR_ALIMENTO(?,?,?,?,?,?,?,?,?,?,?)}");//igual al de procedure
            cs.registerOutParameter("_idAlimento",java.sql.Types.INTEGER);
            //otr
            //parte del producto
            cs.setInt("_cantPedida", alimento.getCantPedido());
            cs.setBoolean("_disponibilidad", true);//insertar disponible
            cs.setInt("_stock",alimento.getStock());
            cs.setInt("_EmpresaProveedora_idEmpresaProveedora", alimento.getEmpresa().getIdEmpresa());
            
            //parte del item
            cs.setString("_descripcion", alimento.getDescripcion());
            cs.setString("_nombre", alimento.getNombre());
            cs.setDouble("_precio",alimento.getPrecio());
            cs.setDouble("_calificacion",alimento.getCalificacion());
            cs.setString("_urlImagen", alimento.getUrlImagen());
            
            //parte de alimento
            cs.setString("_categoria",alimento.getCategoria().toString());
            
            
            //imagen
            //arrayList
          
            //ejecutamos
            cs.executeUpdate();
            //si queremos el id
            alimento.setIdIteam(cs.getInt("_idAlimento"));
            resultado = alimento.getIdIteam();
            
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
    public int modificar(Alimento alimento) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call MODIFICAR_ALIMENTO(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("_idAlimento", alimento.getIdIteam());
            //parte del producto
            cs.setInt("_cantPedida", alimento.getCantPedido());
            cs.setBoolean("_disponibilidad", alimento.isDisponibilidad()); //aqui esta bien??????
            cs.setInt("_stock",alimento.getStock());
            cs.setInt("_EmpresaProveedora_idEmpresaProveedora", alimento.getEmpresa().getIdEmpresa());

            //parte del item
            cs.setString("_descripcion", alimento.getDescripcion());
            cs.setString("_nombre", alimento.getNombre());
            cs.setDouble("_precio",alimento.getPrecio());
            cs.setDouble("_calificacion",alimento.getCalificacion());
            cs.setString("_urlImagen", alimento.getUrlImagen());
            
            //parte del alimento
            cs.setString("_categoria",alimento.getCategoria().toString());

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
    public int eliminar(int idAlimento) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ELIMINAR_ALIMENTO (?)}");
            cs.setInt("_idAlimento", idAlimento);
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
    public ArrayList<Alimento> listarAlimentos() {
        ArrayList<Alimento> alimentos = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_ALIMENTOS_TODOS()}");
            rs = cs.executeQuery();
            while(rs.next()){
                Alimento alimento = new Alimento();
                alimento.setIdIteam(rs.getInt("Producto_Item_idItem"));
                alimento.setCantPedido(rs.getInt("cantPedida"));
               
                alimento.setDisponibilidad(rs.getBoolean("disponibilidad"));//??????????''
                alimento.setStock(rs.getInt("stock"));
                alimento.setDescripcion(rs.getString("descripcion"));    
                alimento.setNombre(rs.getString("nombre"));    
                alimento.setPrecio(rs.getDouble("precio"));    
                alimento.setCalificacion(rs.getDouble("calificacion"));    
                alimento.setEmpresa(new EmpresaProveedora());
                alimento.getEmpresa().setIdEmpresa(rs.getInt("EmpresaProveedora_idEmpresaProveedora"));
                //alimento.getEmpresa().set(rs.getString("nombre_area"));
                alimento.setCategoria(CategoriaAlimento.valueOf(rs.getString("categoria")));
                alimento.setUrlImagen(rs.getString("urlImagen"));
                
                //alimento.setString("_categoria",alimento.getCategoria().toString());
                 
                 
                alimentos.add(alimento);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return alimentos;
    }

    
}