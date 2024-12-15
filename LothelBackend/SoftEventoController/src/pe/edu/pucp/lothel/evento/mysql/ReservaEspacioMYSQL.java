/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.lothel.evento.mysql;


import pe.edu.pucp.lothel.evento.dao.ReservaEspacioDAO;
import pe.edu.pucp.lothel.evento.model.ReservaEspacio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.lothel.evento.model.Espacio;
import pe.edu.pucp.lothel.evento.model.EstadoEvento;
import pe.edu.pucp.lothel.evento.model.Evento;
import pe.edu.pucp.lothel.manager.DBManager;

/**
 *
 * @author efeproceres
 */
public class ReservaEspacioMYSQL implements ReservaEspacioDAO{
    private Connection con;
    private PreparedStatement pst;
    private CallableStatement cs;
    private ResultSet rs;
    private Statement st;
    
    @Override
    public int insertar(ReservaEspacio reserva) {
        int resultado = 0;
        try{
            
            con = DBManager.getInstance().getConnection();  
            
            cs = con.prepareCall("{call INSERTAR_RESERVA_ESPACIO(?,?,?,?,?,?,?)}");
            cs.registerOutParameter("_idReservaEspacio",java.sql.Types.INTEGER);
            //cs.setDate("_horaInicio", new java.sql.LocalTime(reserva.getFechaInicio().getTime()));
            //s.setDate("_horaFin", new java.sql.Date(reserva.getFechaFin().getTime()));
            cs.setBoolean("_estado", false);//false ocupado
            cs.setInt("_idEvento", reserva.getEvento().getIdEvento());//foreign key evento
            cs.setInt("_idEspacio", reserva.getEspacio().getIdEspacio());//foreign key espacio
            //cs.setInt("_fechaDeReserva", reserva.get;//foreign key espacio
            
            resultado=cs.executeUpdate();
            reserva.setIdReservaEspacio(cs.getInt("_id_Reserva"));
            
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
    public int modificar(ReservaEspacio reserva) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            //cambiar de espacio para la reserva
            cs = con.prepareCall("{call MODIFICAR_RESERVAESPACIO(?,?)}");
            cs.setInt("_idEspacio", reserva.getEspacio().getIdEspacio());
            cs.setInt("_idReservaEspacio", reserva.getIdReservaEspacio());
            resultado = cs.executeUpdate();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int idReservaEspacio) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            
            cs = con.prepareCall("{call ELIMINAR_RESERVAESPACIO(?)}");
            cs.setInt("_idReservaEspacio", idReservaEspacio);
            resultado = cs.executeUpdate();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<ReservaEspacio> listarReservas() {
        ArrayList<ReservaEspacio> reservas =  new ArrayList<ReservaEspacio>();
        try{
            con = DBManager.getInstance().getConnection();
            
            cs = con.prepareCall("{call LISTAR_RESERVA_ESPACIO()}");
            rs = cs.executeQuery();
           
            
            while(rs.next()){
                ReservaEspacio reserva = new ReservaEspacio();
                Evento evento = new Evento();
                Espacio espacio = new Espacio();
                
                reserva.setIdReservaEspacio(rs.getInt("idReservaEspacio"));

                reservas.add(reserva);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return reservas;
    }
    
    @Override
    public ArrayList<ReservaEspacio> listarReservasXIdEvento(int idEvento) {
        ArrayList<ReservaEspacio> reservas =  new ArrayList<ReservaEspacio>();
        try{
            con = DBManager.getInstance().getConnection();
            
            cs = con.prepareCall("{call LISTAR_RESERVA_ESPACIO_X_ID_EVENTO(?)}");
            cs.setInt("_idEvento", idEvento);
            rs = cs.executeQuery();
           
            
            while(rs.next()){
                ReservaEspacio reserva = new ReservaEspacio();
                reserva.setIdReservaEspacio(rs.getInt("idReservaEspacio"));
                //reserva.setHoraFin(LocalTime.MIN);
                //reserva.setHoraInicio(rs.getTime("horaFin"));
                reserva.setHoraFin(rs.getTime("horaFin").toLocalTime());
                reserva.setHoraInicio(rs.getTime("horaInicio").toLocalTime());
                reserva.setFechaDeReserva(rs.getDate("fechaDeReserva"));
                reserva.setEstado(rs.getBoolean("estado"));
                //limento.setEmpresa(new EmpresaProveedora());
                //alimento.getEmpresa().setIdEmpresa(rs.getInt("EmpresaProveedora_idEmpresaProveedora"));
                reserva.setEvento(new Evento());
                reserva.getEvento().setIdEvento(rs.getInt("Evento_idEvento"));
                reserva.setEspacio(new Espacio());
                reserva.getEspacio().setIdEspacio(rs.getInt("Espacio_idEspacio"));
                /*
                cs.setTime("_horaInicio", java.sql.Time.valueOf(resEsp.getHoraInicio()));                  
                    cs.setTime("_horaFin", java.sql.Time.valueOf(resEsp.getHoraFin()));                  
                    //cs.setTime("_horaFin", Time.valueOf(resEsp.getHoraFin()));
                    cs.setBoolean("_estado", true);
                    cs.setInt("_idEvento", evento.getIdEvento());//foreign key evento
                    cs.setInt("_idEspacio", resEsp.getEspacio().getIdEspacio());//foreign key espacio
                    cs.setDate("_fechaDeReserva", new java.sql.Date(resEsp.getFechaDeReserva().getTime()));
*/
                reservas.add(reserva);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return reservas;
    }

    public ArrayList<Integer> listarHorasDisponibles(int idEspacio, Date fechaReserva) {
        ArrayList<Integer> horasDisponibles =  new ArrayList<Integer>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call HORAS_DE_RESERVA_ESPACIOS_X_ID_ESPACIO_X_FECHA(?,?)}");
            cs.setInt("_idEspacio", idEspacio);
            cs.setDate("_fechaReserva", new java.sql.Date(fechaReserva.getTime()));
            rs = cs.executeQuery();
           
            
            while (rs.next()) {
                horasDisponibles.add(rs.getInt("hora"));
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return horasDisponibles;
    }

}
