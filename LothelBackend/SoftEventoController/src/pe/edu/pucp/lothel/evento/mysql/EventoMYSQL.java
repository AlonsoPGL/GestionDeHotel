/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.lothel.evento.mysql;

import java.util.ArrayList;
import pe.edu.pucp.lothel.evento.dao.EventoDAO;
import pe.edu.pucp.lothel.evento.model.Evento;
import pe.edu.pucp.lothel.manager.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;
import pe.edu.pucp.lothel.evento.model.EstadoEvento;
import pe.edu.pucp.lothel.rrhh.model.Administrador;
import pe.edu.pucp.lothel.evento.model.ReservaEspacio;

import java.time.LocalTime;
import java.sql.Time;
/**
 *
 * @author efeproceres
 */
public class EventoMYSQL implements EventoDAO{
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    private Statement st;
    
    @Override
    public int insertar(Evento evento) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_EVENTO(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.registerOutParameter("_idEvento",java.sql.Types.INTEGER);
            cs.setString("_nombre", evento.getNombre());
            cs.setString("_descripcion", evento.getDescripcion());
            cs.setInt("_cantidadAsistentes",evento.getCantidadAsistentes());
            //cs.setTimestamp("_fechaInicio", new java.sql.Timestamp(evento.getFechaRealizacion().getTime()));
            cs.setDate("_fechaInicio", new java.sql.Date(evento.getFechaInicio().getTime()));
            cs.setDate("_fechaFin", new java.sql.Date(evento.getFechaFin().getTime()));
            cs.setString("_estado",evento.getEstado().toString());
            cs.setInt("_idAdministrador",evento.getAdministrador().getIdAdministrador());
            cs.setInt("_idOperario",evento.getAdministrador().getIdAdministrador());
            cs.setInt("_idPersona",evento.getAdministrador().getIdAdministrador());
            cs.setBoolean("_activo", evento.isActivo());
            resultado=cs.executeUpdate();
            evento.setIdEvento(cs.getInt("_idEvento"));
            
            for (ReservaEspacio resEsp : evento.getReservaEspacios())
                {
                    con = DBManager.getInstance().getConnection();
                    cs = con.prepareCall("{call INSERTAR_RESERVA_ESPACIO(?,?,?,?,?,?,?)}");

                    cs.registerOutParameter("_idReservaEspacio",java.sql.Types.INTEGER);
                    //cs.setTime("_horaInicio", Time.valueOf(resEsp.getHoraInicio()));                    
                    cs.setTime("_horaInicio", java.sql.Time.valueOf(resEsp.getHoraInicio()));                  
                    cs.setTime("_horaFin", java.sql.Time.valueOf(resEsp.getHoraFin()));                  
                    //cs.setTime("_horaFin", Time.valueOf(resEsp.getHoraFin()));
                    cs.setBoolean("_estado", true);
                    cs.setInt("_idEvento", evento.getIdEvento());//foreign key evento
                    cs.setInt("_idEspacio", resEsp.getEspacio().getIdEspacio());//foreign key espacio
                    cs.setDate("_fechaDeReserva", new java.sql.Date(resEsp.getFechaDeReserva().getTime()));

                    resultado=cs.executeUpdate();
                    resEsp.setIdReservaEspacio(cs.getInt("_idReservaEspacio"));

                }
                resultado = evento.getIdEvento();
            
            
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
    public int modificar(Evento evento) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            
            cs = con.prepareCall("{call MODIFICAR_EVENTO(?,?,?,?,?,?,?,?,?)}");
            cs.setInt("_idEvento",evento.getIdEvento());
            cs.setString("_nombre", evento.getNombre());
            cs.setString("_descripcion", evento.getDescripcion());
            cs.setInt("_cantidadAsistentes",evento.getCantidadAsistentes());
            cs.setDate("_fechaInicio", new java.sql.Date(evento.getFechaInicio().getTime()));
            cs.setDate("_fechaFin", new java.sql.Date(evento.getFechaFin().getTime()));
            cs.setString("_estado",evento.getEstado().toString());
            //cs.setInt("_idAdministrador",evento.getAdministrador().getIdAdministrador());
            cs.setInt("_idAdministrador",0);
            cs.setBoolean("_activo", evento.isActivo());
            resultado=cs.executeUpdate();
            //evento.setIdEvento(cs.getInt("_idEvento"));
            
            for (ReservaEspacio resEsp : evento.getReservaEspacios())
                {
                    con = DBManager.getInstance().getConnection();
                    cs = con.prepareCall("{call INSERTAR_RESERVA_ESPACIO(?,?,?,?,?,?,?)}");

                    cs.registerOutParameter("_idReservaEspacio",java.sql.Types.INTEGER);
                    //cs.setTime("_horaInicio", Time.valueOf(resEsp.getHoraInicio()));                    
                    cs.setTime("_horaInicio", java.sql.Time.valueOf(resEsp.getHoraInicio()));                  
                    cs.setTime("_horaFin", java.sql.Time.valueOf(resEsp.getHoraFin()));                  
                    //cs.setTime("_horaFin", Time.valueOf(resEsp.getHoraFin()));
                    cs.setBoolean("_estado", true);
                    cs.setInt("_idEvento", evento.getIdEvento());//foreign key evento
                    cs.setInt("_idEspacio", resEsp.getEspacio().getIdEspacio());//foreign key espacio
                    cs.setDate("_fechaDeReserva", new java.sql.Date(resEsp.getFechaDeReserva().getTime()));

                    resultado=cs.executeUpdate();
                    resEsp.setIdReservaEspacio(cs.getInt("_idReservaEspacio"));

                }
                resultado = evento.getIdEvento();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        
        return resultado;
    }

    @Override
    public int eliminar(int idEvento) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            
            cs = con.prepareCall("{call ELIMINAR_EVENTO(?)}");
            cs.setInt("_idEvento", idEvento);
            resultado = cs.executeUpdate();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Evento> listarEventos() {
        ArrayList<Evento> eventos =  new ArrayList<Evento>();
        
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_EVENTOS()}");
            rs = cs.executeQuery();
           
            while(rs.next()){
                Evento evento = new Evento();
                evento.setIdEvento(rs.getInt("idEvento"));
                evento.setNombre(rs.getString("nombre"));
                evento.setDescripcion(rs.getString("descripcion"));
                evento.setCantidadAsistentes(rs.getInt("cantidadAsistentes"));
                evento.setFechaInicio(rs.getDate("fechaInicio"));
                evento.setActivo(rs.getBoolean("activo"));
                evento.setEstado(EstadoEvento.valueOf(rs.getString("estado")));
                evento.setFechaFin(rs.getDate("fechaFin"));
                //alimento.setEmpresa(new EmpresaProveedora());
                //alimento.getEmpresa().setIdEmpresa(rs.getInt("EmpresaProveedora_idEmpresaProveedora"));
                //evento.setAdministrador(new Administrador());
                //evento.getAdministrador().setIdPersona(rs.getInt("_idAdministrador"));
                eventos.add(evento);
            }   
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return eventos;
    }
    
    

    @Override
    public ArrayList<Evento> listarEventosPorNombre(String nombre) {
        ArrayList<Evento> eventos =  new ArrayList<Evento>();
        
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_EVENTOS_POR_NOMBRE(?)}");
            cs.setString("_nombre", nombre);
            rs = cs.executeQuery();
            
            while(rs.next()){
                Evento evento = new Evento();
                evento.setIdEvento(rs.getInt("idEvento"));
                evento.setNombre(rs.getString("nombre"));
                evento.setDescripcion(rs.getString("descripcion"));
                evento.setEstado(EstadoEvento.valueOf(rs.getString("estado")));
                evento.setCantidadAsistentes(rs.getInt("cantidadAsistentes"));
                evento.setFechaInicio(rs.getDate("fechaInicio"));
                evento.setFechaFin(rs.getDate("fechaFin"));
                eventos.add(evento);
            }   
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return eventos;
    }

}  
