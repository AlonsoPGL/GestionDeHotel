/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.lothel.ventas.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pe.edu.pucp.lothel.manager.DBManager;
import pe.edu.pucp.lothel.ventas.dao.TicketEventoDAO;
import pe.edu.pucp.lothel.ventas.model.TicketEvento;

/**
 *
 * @author gumar
 */
public class TicketEventoMYSQL implements TicketEventoDAO {

    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    @Override
    public int insertar(TicketEvento ticket) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_TICKET_EVENTO(?,?,?,?,?,?,?,?,?)");
            cs.registerOutParameter("_numeroTicket", java.sql.Types.INTEGER);
            //parte del item
            cs.setString("_descripcion", ticket.getDescripcion());
            cs.setString("_nombre", ticket.getNombre());
            cs.setDouble("_precio", ticket.getPrecio());
            cs.setDouble("_calificacion", ticket.getCalificacion());
            
            cs.setInt("_cantPedida", ticket.getCantPedido());
            cs.setBoolean("_disponibilidad", true);
            cs.setInt("_stock",ticket.getStock());
            
            cs.setDate("_fechaTicket", new java.sql.Date(ticket.getFechaCompra().getTime()));
            cs.executeUpdate();
            ticket.setIdIteam(cs.getInt("_numeroTicket"));
            resultado = ticket.getIdIteam();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try{con.close();}catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public int modificar(TicketEvento ticket) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_TICKET_EVENTO(?,?,?,?,?,?,?,?,?)");
            cs.setInt("_numeroTicket", ticket.getIdIteam());
            //parte del item
            cs.setString("_descripcion", ticket.getDescripcion());
            cs.setString("_nombre", ticket.getNombre());
            cs.setDouble("_precio", ticket.getPrecio());
            cs.setDouble("_calificacion", ticket.getCalificacion());
            
            cs.setInt("_cantPedida", ticket.getCantPedido());
            cs.setBoolean("_disponibilidad", true);
            cs.setInt("_stock",ticket.getStock());
            
            cs.setDate("_fechaTicket", new java.sql.Date(ticket.getFechaCompra().getTime()));
            resultado = cs.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try{con.close();}catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public int eliminar(int numeroTicket) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ELIMINAR_TICKET(?)");
            cs.setInt("_numeroTicket", numeroTicket);
            resultado = cs.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try{con.close();}catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        return resultado;
        
    }

    @Override
    public ArrayList<TicketEvento> listarTickets() {
        ArrayList<TicketEvento> tickets = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_TICKETS_EVENTO()");
            rs = cs.executeQuery();
            
            while(rs.next()) {
                TicketEvento ticket = new TicketEvento();
                ticket.setIdIteam(rs.getInt("numeroTicket"));
                ticket.setCantPedido(rs.getInt("cantPedida"));
                ticket.setDisponibilidad(rs.getBoolean("disponibilidad"));
                ticket.setStock(rs.getInt("stock"));
                ticket.setDescripcion(rs.getString("descripcion"));
                ticket.setNombre(rs.getString("nombre"));
                ticket.setPrecio(rs.getDouble("Precio"));
                ticket.setCalificacion(rs.getDouble("calificacion"));
                ticket.setFechaCompra(rs.getDate("fechaCompra"));
                tickets.add(ticket);
            }
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try{rs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return tickets;
    }
    
}
