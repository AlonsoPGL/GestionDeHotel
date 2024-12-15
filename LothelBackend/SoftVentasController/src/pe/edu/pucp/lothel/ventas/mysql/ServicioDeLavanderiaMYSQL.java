/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.lothel.ventas.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.lothel.manager.DBManager;
import pe.edu.pucp.lothel.ventas.dao.ServicioDeLavanderiaDAO;
import pe.edu.pucp.lothel.ventas.model.CategoriaCuidadoPersonal;
import pe.edu.pucp.lothel.ventas.model.CuidadoPersonal;
import pe.edu.pucp.lothel.ventas.model.EstadoServicio;
import pe.edu.pucp.lothel.ventas.model.ServicioDeLavanderia;

/**
 *
 * @author efeproceres
 */
public class ServicioDeLavanderiaMYSQL implements ServicioDeLavanderiaDAO{
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    
    @Override
    public int insertar(ServicioDeLavanderia servicio) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            //call insertar item
             cs = con.prepareCall("{call INSERTAR_SERVICIODELAVANDERIA(?,?,?,?,?,?,?,?)}");//igual al de procedure
            cs.registerOutParameter("_idItem",java.sql.Types.INTEGER);
            //parte del producto
            cs.setString("_descripcion", servicio.getDescripcion());
            cs.setString("_nombre", servicio.getNombre());
            cs.setDouble("_precio",servicio.getPrecio());
            cs.setDouble("_calificacion",servicio.getCalificacion());
            //parte del item

            cs.setString("_estado", servicio.getEstado().toString());
            cs.setString("_incidencia", servicio.getIncidencia());
            cs.setString("_anotaciones",servicio.getAnotaciones());
            //ejecutamos
            cs.executeUpdate();
            //si queremos el id
            servicio.setIdIteam(cs.getInt("_idItem"));
            resultado = servicio.getIdIteam();
            
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
    public int modificar(ServicioDeLavanderia servicio) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            //call insertar item
             cs = con.prepareCall("{call MODIFICAR_SERVICIODELAVANDERIA(?,?,?,?,?,?,?,?)}");//igual al de procedure
            cs.setInt("_idItem",servicio.getIdIteam());
            //parte del producto
            cs.setString("_descripcion", servicio.getDescripcion());
            cs.setString("_nombre", servicio.getNombre());
            cs.setDouble("_precio",servicio.getPrecio());
            cs.setDouble("_calificacion",servicio.getCalificacion());
            //parte del item

            cs.setString("_estado", servicio.getEstado().toString());
            cs.setString("_incidencia", servicio.getIncidencia());
            cs.setString("_anotaciones",servicio.getAnotaciones());
          
            //ejecutamos
            resultado=cs.executeUpdate();
            //si queremos el id
            
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
    public int eliminar(int idServicioLavanderia) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ELIMINAR_BEBIDA (?)}");
            cs.setInt("_idBebida", idServicioLavanderia);
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
    public ArrayList<ServicioDeLavanderia> listarServicios() {
        ArrayList<ServicioDeLavanderia> servicios = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_SERVICIOSDELAVANDERIA()}");
            rs = cs.executeQuery();
            while(rs.next()){
                ServicioDeLavanderia servicio = new ServicioDeLavanderia();
                servicio.setIdIteam(rs.getInt("idItem"));
                servicio.setDescripcion(rs.getString("descripcion"));
                servicio.setNombre(rs.getString("nombre"));
                servicio.setPrecio(rs.getDouble("precio"));
                servicio.setCalificacion(rs.getDouble("calificacion"));
                //bebida.set //
                String cad=rs.getString("estado");
                if(cad.equals("POR_CONFIRMAR")){
                    servicio.setEstado(EstadoServicio.POR_CONFIRMAR);
                }else if(cad.equals("EN_PROCESO")){
                    servicio.setEstado(EstadoServicio.EN_PROCESO);
                }else{
                    servicio.setEstado(EstadoServicio.COMPLETADO);
                }
                
                servicio.setIncidencia(rs.getString("incidencia")); 
                //
                servicio.setAnotaciones(rs.getString("anotaciones"));
                 
                servicios.add(servicio);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return servicios;
    }
    
}
