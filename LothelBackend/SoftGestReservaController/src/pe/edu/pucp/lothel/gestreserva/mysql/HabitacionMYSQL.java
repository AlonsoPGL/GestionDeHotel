/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.lothel.gestreserva.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.lothel.gestreserva.dao.HabitacionDAO;
import pe.edu.pucp.lothel.gestreserva.model.Familiar;
import pe.edu.pucp.lothel.gestreserva.model.Habitacion;
import pe.edu.pucp.lothel.manager.DBManager;

/**
 *
 * @author DELL
 */
public class HabitacionMYSQL implements HabitacionDAO{
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    public ArrayList<Habitacion> ListarTodasHabitaciones(){
        ArrayList<Habitacion> habitaciones = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_HABITACIONES_TODAS()}");
            rs = cs.executeQuery();
            while(rs.next()){
                Habitacion habitacion = new Habitacion();
                habitacion.setIdHabitacion(rs.getInt("idHabitacion"));
                habitacion.setPiso(rs.getInt("piso"));
                habitacion.setNumeroDeCamas(rs.getInt("numeroCamas"));
                habitacion.setPrecio(rs.getDouble("precio"));
                //habitacion.setReservado(rs.getBoolean("reservado"));
                //habitacion.setCocheraPropia(rs.getBoolean("cocheraPropia"));
                //habitacion.setImagen(rs.getBytes("imagen"));
                //habitacion.setTitulo(rs.getString("titulo"));
                habitacion.setDescripcion(rs.getString("descripcion"));
                //habitacion.setCantHuespedes(rs.getInt("cantHuespedes"));
                //habitacion.setStock(rs.getInt("stock"));
                habitaciones.add(habitacion);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return habitaciones;
        
    }
}
