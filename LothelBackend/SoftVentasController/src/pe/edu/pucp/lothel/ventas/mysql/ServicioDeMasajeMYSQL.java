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
import pe.edu.pucp.lothel.ventas.dao.ServicioDeMasajeDAO;
import pe.edu.pucp.lothel.ventas.model.ServicioDeMasaje;
import java.util.Date;
/**
 *
 * @author DELL
 */
public class ServicioDeMasajeMYSQL implements ServicioDeMasajeDAO {
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    @Override
    public int insertar(ServicioDeMasaje servicio) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            //call insertar item
            cs = con.prepareCall("{call INSERTAR_SERVICIODEMASAJE(?,?,?,?,?,?,?,?,?)}");//igual al de procedure
            cs.registerOutParameter("_idItem",java.sql.Types.INTEGER);
            //parte del producto
            cs.setString("_descripcion", servicio.getDescripcion());
            cs.setString("_nombre", servicio.getNombre());
            cs.setDouble("_precio",servicio.getPrecio());
            cs.setDouble("_calificacion",servicio.getCalificacion());
            //parte del item

            cs.setString("_estado", servicio.getEstado().toString());
            cs.setString("_incidencia", servicio.getIncidencia());
            
            cs.setDate("_hora_inicio",new java.sql.Date(servicio.getHoraInicio().getTime()));
            cs.setDate("_hora_fin",new java.sql.Date(servicio.getHoraFin().getTime()));
            //cs.setString("_anotaciones",servicio.getAnotaciones());
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
    public int modificar(ServicioDeMasaje servicio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int eliminar(int idDocumentoPago) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ServicioDeMasaje> listarServicios() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
