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
import pe.edu.pucp.lothel.gestreserva.dao.ReservaHabitacionDAO;
import pe.edu.pucp.lothel.gestreserva.model.EstadoReserva;
import pe.edu.pucp.lothel.gestreserva.model.Habitacion;
import pe.edu.pucp.lothel.gestreserva.model.ReservaHabitacion;
import pe.edu.pucp.lothel.rrhh.model.Huesped;
import pe.edu.pucp.lothel.manager.DBManager;

/**
 *
 * @author efeproceres
 */
public class ReservaHabitacionMYSQL implements ReservaHabitacionDAO{
    
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    
    @Override
    public int insertar(ReservaHabitacion reserva) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_RESERVA_HABITACION(?,?,?,?,?,?,?)}");
            cs.registerOutParameter("_id_reserva_habitacion", java.sql.Types.INTEGER);
            cs.setDate("_fecha_reserva", new java.sql.Date(reserva.getFechaDeReserva().getTime()));
            cs.setDate("_fecha_inicio", new java.sql.Date(reserva.getFechaInicio().getTime()));
            cs.setDate("_fecha_fin", new java.sql.Date(reserva.getFechaFin().getTime()));
            cs.setString("_estado", reserva.getEstado().toString());
            cs.setInt("_id_habitacion", reserva.getHabitacion().getIdHabitacion());
            cs.setInt("_id_huesped", reserva.getHuesped().getIdPersona());
                    
            cs.executeUpdate();
            reserva.setIdReserva(cs.getInt("_id_reserva_habitacion"));
            resultado = reserva.getIdReserva();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int modificar(ReservaHabitacion reserva) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call MODIFICAR_RESERVA_HABITACION(?,?,?,?,?,?,?)}");
            cs.setInt("_idReserva", reserva.getIdReserva());
            cs.setDate("_fecha_reserva", new java.sql.Date(reserva.getFechaDeReserva().getTime()));
            cs.setDate("_fecha_inicio", new java.sql.Date(reserva.getFechaInicio().getTime()));
            cs.setDate("_fecha_fin", new java.sql.Date(reserva.getFechaFin().getTime()));
            cs.setString("_estado", reserva.getEstado().toString());
            cs.setInt("_id_habitacion", reserva.getHabitacion().getIdHabitacion());
            cs.setInt("_id_habitacion", reserva.getHuesped().getIdPersona());
                    
            resultado =cs.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int idReserva) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ELIMINAR_RESERVA_HABITACION(?)}");
            cs.setInt("_idReserva", idReserva);  
            resultado =cs.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<ReservaHabitacion> listarReserva() {
        ArrayList<ReservaHabitacion> reservas = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_RESERVA_HABITACION()}");
            rs = cs.executeQuery();
            while(rs.next()){
                ReservaHabitacion reserva = new ReservaHabitacion();
                Habitacion habitacion = new Habitacion();
                Huesped huesped=new Huesped();
                
                reserva.setIdReserva(rs.getInt("idReservaHabitacion"));
                reserva.setFechaDeReserva(rs.getDate("fechaDeReserva"));
                reserva.setFechaInicio(rs.getDate("fechaInicio"));
                reserva.setFechaFin(rs.getDate("fechaFin"));
                reserva.setEstado(EstadoReserva.valueOf(rs.getString("estadoReserva")));
                habitacion.setIdHabitacion(rs.getInt("habitacion"));
                huesped.setIdPersona(rs.getInt("huesped"));
                reserva.setHabitacion(habitacion);
                reserva.setHuesped(huesped);
                reservas.add(reserva);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return reservas;
    }

    @Override
    public ArrayList<ReservaHabitacion> listarXIDHuesped(int idHuesped) {
     ArrayList<ReservaHabitacion> reservas = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ListarHabitacion_X_idHueped(?)}");
            cs.setInt("_id_huesped", idHuesped);
            rs = cs.executeQuery();
            while(rs.next()){
                ReservaHabitacion reserva = new ReservaHabitacion();
                Habitacion habitacion = new Habitacion();
                Huesped huesped=new Huesped();
                
                reserva.setIdReserva(rs.getInt("idReservaHabitacion"));
                reserva.setFechaDeReserva(rs.getDate("fechaDeReserva"));
                reserva.setFechaInicio(rs.getDate("fechaInicio"));
                reserva.setFechaFin(rs.getDate("fechaFin"));
                reserva.setEstado(EstadoReserva.valueOf(rs.getString("estado")));
                habitacion.setIdHabitacion(rs.getInt("Habitacion_idHabitacion"));
                huesped.setIdPersona(rs.getInt("Huesped_idHuesped"));
                reserva.setHabitacion(habitacion);
                reserva.setHuesped(huesped);
                reservas.add(reserva);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return reservas;
    }

    @Override
    public Habitacion listarHabitacionxHuesped(int idReserva, int idHabitacion, int idHuesped) {
        Habitacion habitacion =new Habitacion();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ListarHabitaciones_X_Huesped(?,?,?)}");
            cs.setInt("_id_ReservaHabitacion", idHuesped);
            cs.setInt("_id_habitacion", idHabitacion);
            cs.setInt("_id_Huesped", idHuesped);
            rs = cs.executeQuery();
            
            rs.next();
            habitacion.setIdHabitacion(rs.getInt("idHabitacion"));
            habitacion.setPiso(rs.getInt("piso"));
            habitacion.setNumeroDeCamas(rs.getInt("numeroCamas"));
            habitacion.setPrecio(rs.getDouble("precio"));
            habitacion.setCantHuespedes(rs.getInt("cantHuespedes"));
            /*if(rs.next()){
                
                habitacion.setIdHabitacion(rs.getInt("idHabitacion"));
                habitacion.setPiso(rs.getInt("piso"));
                habitacion.setNumeroDeCamas(rs.getInt("numeroCamas"));
                habitacion.setPrecio(rs.getDouble("precio"));
                habitacion.setCantHuespedes(rs.getInt("cantHuespedes"));
            }*/
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return habitacion;
    }
    
    
    // PARTE MARCELO
    @Override
    public ArrayList<ReservaHabitacion> listarReservasEnCurso() {
        ArrayList<ReservaHabitacion> reservas = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_RESERVAS_EN_CURSO()}");
            rs = cs.executeQuery();
            while(rs.next()){
                ReservaHabitacion reserva = new ReservaHabitacion();
                Habitacion habitacion = new Habitacion();
                Huesped huesped=new Huesped();
                
                reserva.setIdReserva(rs.getInt("idReservaHabitacion"));
                reserva.setFechaDeReserva(rs.getDate("fechaDeReserva"));
                reserva.setFechaInicio(rs.getDate("fechaInicio"));
                reserva.setFechaFin(rs.getDate("fechaFin"));
                reserva.setEstado(EstadoReserva.valueOf(rs.getString("estadoReserva")));
                habitacion.setIdHabitacion(rs.getInt("habitacion"));
                huesped.setIdPersona(rs.getInt("huesped"));
                reserva.setHabitacion(habitacion);
                reserva.setHuesped(huesped);
                reservas.add(reserva);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return reservas;
    }
    
}

    
    

