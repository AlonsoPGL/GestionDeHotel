/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.lothel.gestreserva.mysql;

import java.util.ArrayList;
import pe.edu.pucp.lothel.gestreserva.dao.SimpleDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import pe.edu.pucp.lothel.gestreserva.model.EstadoReserva;
import pe.edu.pucp.lothel.gestreserva.model.Habitacion;
import pe.edu.pucp.lothel.gestreserva.model.ReservaHabitacion;
import pe.edu.pucp.lothel.gestreserva.model.Simple;
import pe.edu.pucp.lothel.rrhh.model.Huesped;
import pe.edu.pucp.lothel.manager.DBManager;

/**
 *
 * @author marcelo
 */
public class SimpleMYSQL implements SimpleDAO{
    
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    
    @Override
    public int insertar(Simple simple) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_SIMPLE(?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.registerOutParameter("_id_habitacion",java.sql.Types.INTEGER);
            cs.setInt("_piso", simple.getPiso());
            cs.setInt("_numeroCama",  simple.getNumeroDeCamas());
            cs.setDouble("_precio",simple.getPiso());
            cs.setBoolean("_reservado",simple.getReservado());
            cs.setBoolean("_tieneVista",simple.getTieneVistaInterior());
            cs.setBoolean("_servicioStreaming",simple.getServicioStreaming());
            cs.setBytes("_imagen", simple.getImagen());
            cs.setString("_titulo", simple.getTitulo());
            cs.setString("_descripcion", simple.getDescripcion());
            cs.setInt("_cantHuespedes", simple.getCantHuespedes());
            cs.setInt("_stock", simple.getStock());
            resultado=cs.executeUpdate();
            simple.setIdHabitacion(cs.getInt("_id_habitacion"));
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
    public int modificar(Simple simple) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call MODIFICAR_SIMPLE(?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("_id_habitacion", simple.getIdHabitacion());
            cs.setInt("_piso", simple.getPiso());
            cs.setInt("_numeroCama",  simple.getNumeroDeCamas());
            cs.setDouble("_precio",simple.getPiso());
            cs.setBoolean("_reservado",simple.getReservado());
            cs.setBoolean("_tieneVista",simple.getTieneVistaInterior());
            cs.setBoolean("_servicioStreaming",simple.getServicioStreaming());
            cs.setBytes("_imagen", simple.getImagen());
            cs.setString("_titulo", simple.getTitulo());
            cs.setString("_descripcion", simple.getDescripcion());
            cs.setInt("_cantHuespedes", simple.getCantHuespedes());
            cs.setInt("_stock", simple.getStock());
            resultado=cs.executeUpdate();
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
    public int eliminar(int idHabitacion) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ELIMINAR_SIMPLE(?)}");
            cs.setInt("_id_habitacion", idHabitacion);
            
            resultado=cs.executeUpdate();
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
    public ArrayList<Simple> listarHabitacionesSimples() {
        ArrayList<Simple> simples = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_SIMPLE()}");
            rs = cs.executeQuery();
            while(rs.next()){
                Simple simple = new Simple();
                simple.setIdHabitacion(rs.getInt("idHabitacion"));
                simple.setPiso(rs.getInt("piso"));
                simple.setNumeroDeCamas(rs.getInt("numeroCamas"));
                simple.setPrecio(rs.getDouble("precio"));
                simple.setReservado(rs.getBoolean("reservado"));
                simple.setTieneVistaInterior(rs.getBoolean("tieneVistaExterior"));
                simple.setTieneVistaInterior(rs.getBoolean("tieneServicioStreaming"));
                //simple.setImagen(rs.getBytes("imagen"));
                simple.setTitulo(rs.getString("titulo"));
                simple.setDescripcion(rs.getString("descripcion"));
                simple.setCantHuespedes(rs.getInt("cantHuespedes"));
                simple.setStock(rs.getInt("stock"));
                simples.add(simple);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return simples;
    }
    
    @Override
    public ArrayList<Simple> listarHabitacionesSimplesXPeriodo(Date fechaINI,Date fechaFin) {
        ArrayList<Simple> simples = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_SIMPLE_PERIODO(?,?)}");
            cs.setDate("_fechaInicio", new java.sql.Date(fechaINI.getTime()));
            cs.setDate("_fechaFin",  new java.sql.Date(fechaFin.getTime()));
            rs = cs.executeQuery();
            while(rs.next()){
                Simple simple = new Simple();
                simple.setIdHabitacion(rs.getInt("idHabitacion"));
                simple.setPiso(rs.getInt("piso"));
                simple.setNumeroDeCamas(rs.getInt("numeroCamas"));
                simple.setPrecio(rs.getDouble("precio"));
                simple.setReservado(rs.getBoolean("reservado"));
                simple.setTieneVistaInterior(rs.getBoolean("tieneVistaExterior"));
                simple.setServicioStreaming(rs.getBoolean("servicioStreaming"));
                simple.setTitulo(rs.getString("titulo"));
                simple.setDescripcion(rs.getString("descripcion"));
                simple.setCantHuespedes(rs.getInt("cantHuespedes"));
                simple.setStock(rs.getInt("stock"));
                simples.add(simple);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return simples;
    }
    
    

}
