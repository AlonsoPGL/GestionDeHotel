/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.lothel.ventas.mysql;

import java.util.ArrayList;
import pe.edu.pucp.lothel.ventas.dao.PedidoDAO;
import pe.edu.pucp.lothel.ventas.model.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.Statement;
import java.util.Date;
import java.util.HashSet;

import pe.edu.pucp.lothel.gestreserva.model.ReservaHabitacion;
import pe.edu.pucp.lothel.manager.DBManager;
import pe.edu.pucp.lothel.ventas.dao.itemDAO;
import pe.edu.pucp.lothel.ventas.model.EstadoPedido;
import pe.edu.pucp.lothel.ventas.model.Item;
import pe.edu.pucp.lothel.ventas.model.ServicioDeLavanderia;
import pe.edu.pucp.lothel.ventas.model.ServicioDeMasaje;
import pe.edu.pucp.lothel.rrhh.model.Huesped;
import pe.edu.pucp.lothel.ventas.model.EstadoServicio;
import pe.edu.pucp.lothel.ventas.model.Producto;
/**
 *
 * @author efeproceres
 */
public class PedidoMYSQL implements PedidoDAO{
    private Connection con;
    private Connection con2;
    private PreparedStatement pst;
    private CallableStatement cs;
    private ResultSet rs;
    private CallableStatement cs2;
    private ResultSet rs2;
    private Statement st;
    private itemDAO daoItem;
    
    @Override
    public int insertar(Pedido pedido) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_PEDIDO_V2(?,?,?,?,?,?)}");
            cs.registerOutParameter("_idPedido", java.sql.Types.INTEGER);
            cs.setDate("_fechaSolicitud",new java.sql.Date(pedido.getFechaSolicitud().getTime()));
            cs.setString("_estado",pedido.getEstado().toString());
            cs.setDouble("_montoAcumulado",pedido.getMontoAcumulado());
            cs.setInt("_DocumentoDePago_idDocumentoDePago", 1);
            cs.setInt("_ReservaHabitacion_idReservaHabitacion", pedido.getReserva().getIdReserva());
            cs.executeUpdate();
            pedido.setIdPedido(cs.getInt("_idPedido"));
            for(Item it: pedido.getItems()) {
                con2 = DBManager.getInstance().getConnection();
                cs2 = con2.prepareCall("{call INSERTAR_ITEM_EN_PEDIDO(?,?,?,?)}");
                cs2.registerOutParameter("_idPedidoXItem", java.sql.Types.INTEGER);
                if(it instanceof Producto) {
                    cs2.setInt("_cantidadSolicitada", ((Producto)it).getCantPedido());
                } else
                    cs2.setInt("_cantidadSolicitada", 1);
                cs2.setInt("_Pedido_idPedido", pedido.getIdPedido());
                cs2.setInt("_Item_idItem", it.getIdIteam());
                cs2.executeUpdate();
                
                //descontando items de la bd
                cs2=con2.prepareCall("{call DESCONTAR_STOCK_ITEM(?,?)}");
                cs2.setInt("_idProducto",it.getIdIteam());
                cs2.setInt("_cantidadSolicitada",((Producto)it).getCantPedido());
                cs2.executeUpdate();
            }
            resultado= pedido.getIdPedido();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){
                System.out.println(ex.getMessage());
            }
            try{con2.close();}catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        return resultado;
    }
    
    @Override
    public int insertarServicio(Pedido pedido) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_PEDIDO_V2(?,?,?,?,?,?)}");
            cs.registerOutParameter("_idPedido", java.sql.Types.INTEGER);
            cs.setDate("_fechaSolicitud",new java.sql.Date(pedido.getFechaSolicitud().getTime()));
            cs.setString("_estado",pedido.getEstado().toString());
            cs.setDouble("_montoAcumulado",pedido.getMontoAcumulado());
            cs.setInt("_DocumentoDePago_idDocumentoDePago", 1);
            cs.setInt("_ReservaHabitacion_idReservaHabitacion", pedido.getReserva().getIdReserva());
            cs.executeUpdate();
            pedido.setIdPedido(cs.getInt("_idPedido"));
            for(Item it: pedido.getItems()) {
                con2 = DBManager.getInstance().getConnection();
                cs2 = con2.prepareCall("{call INSERTAR_ITEM_EN_PEDIDO(?,?,?,?)}");
                cs2.registerOutParameter("_idPedidoXItem", java.sql.Types.INTEGER);
                if(it instanceof Producto) {
                    cs2.setInt("_cantidadSolicitada", ((Producto)it).getCantPedido());
                } else
                    cs2.setInt("_cantidadSolicitada", 1);
                cs2.setInt("_Pedido_idPedido", pedido.getIdPedido());
                cs2.setInt("_Item_idItem", it.getIdIteam());
                cs2.executeUpdate();
                
                /*//descontando items de la bd
                cs2=con2.prepareCall("{call DESCONTAR_STOCK_ITEM(?,?)}");
                cs2.setInt("_idProducto",it.getIdIteam());
                cs2.setInt("_cantidadSolicitada",((Producto)it).getCantPedido());
                cs2.executeUpdate();*/
            }
            resultado= pedido.getIdPedido();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){
                System.out.println(ex.getMessage());
            }
            try{con2.close();}catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        return resultado;
    }


    @Override
    public int modificar(Pedido pedido) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call MODIFICAR_PEDIDO(?,?,?,?)}");
            cs.setInt("_idPedido", pedido.getIdPedido());
            cs.setDate("_fechaSolicitud",new java.sql.Date(pedido.getFechaSolicitud().getTime()));
            cs.setString("_estado",pedido.getEstado().toString());
            cs.setDouble("_montoAcumulado",pedido.getMontoAcumulado());
            resultado = cs.executeUpdate();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        
        return resultado;
    }

    @Override
    public int eliminar(int pedido) {
    int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            
            cs = con.prepareCall("{call EliminarEvento(?)}");
            cs.setInt("_idPedido", pedido);
            resultado = cs.executeUpdate();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    
    
    @Override
    public ArrayList<Pedido> listarPedidosLavanderia() {
        ArrayList<Pedido> pedidos =  new ArrayList<Pedido>();
        
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_PEDIDOS_SERVICIOS_LAVANDERIA()}");
            rs = cs.executeQuery();
            while(rs.next()){
                Pedido pedido = new Pedido();
                pedido.setItems(new ArrayList<Item>());
                ServicioDeLavanderia ser=new ServicioDeLavanderia();
                ReservaHabitacion reserva= new ReservaHabitacion();
                
                
                //reserva
                
                pedido.setIdPedido(rs.getInt("idPedido"));
                pedido.setFechaSolicitud(rs.getDate("fechaSolicitud"));
               
              
                reserva.setIdReserva(rs.getInt("idReservaHabitacion"));
                reserva.getHabitacion().setIdHabitacion(rs.getInt("idHabitacion"));
                reserva.getHabitacion().setPiso(rs.getInt("piso"));
                pedido.setFechaSolicitud(rs.getDate("fechaSolicitud"));
                
                
                reserva.getHuesped().setNombre(rs.getString("nombre"));
                reserva.getHuesped().setApellidoPaterno(rs.getString("apellidoPaterno"));
                String nc="";
                nc+=reserva.getHuesped().getNombre()+"  ";
                nc+=reserva.getHuesped().getApellidoPaterno();
                pedido.setNombreHuesped(nc);
                
                
                pedido.setNumHabitacion(reserva.getHabitacion().getIdHabitacion());
                pedido.setPisoHabitacion(reserva.getHabitacion().getPiso());
                pedido.setReserva(reserva);
                
                ser.setIdIteam(rs.getInt("idItem"));
                ser.setIncidencia(rs.getString("incidencia"));
                
                ser.setEstado(EstadoServicio.valueOf(rs.getString("estado")));
                
                ser.setAnotaciones(rs.getString("anotaciones"));
                
                
                pedido.setIncidenciaDeHabitacion(ser.getIncidencia());
                pedido.setAnotacionesServicio(ser.getAnotaciones());
                
                
                pedido.getItems().add(ser);

                //falta datos de la reservahabitacion falta los get y sets
                pedidos.add(pedido);
            }   
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return pedidos;
    }
    
    @Override
    public ArrayList<Pedido> listarPedidosMasajista() {
        ArrayList<Pedido> pedidos =  new ArrayList<Pedido>();
        
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_PEDIDOS_SERVICIOS_MASAJE()}");
            rs = cs.executeQuery();
            while(rs.next()){
                Pedido pedido = new Pedido();
                pedido.setItems(new ArrayList<Item>());
                ServicioDeMasaje ser=new ServicioDeMasaje();
                ReservaHabitacion reserva= new ReservaHabitacion();
                
                
                //reserva
                
                pedido.setIdPedido(rs.getInt("idPedido"));
                pedido.setFechaSolicitud(rs.getDate("fechaSolicitud"));

                reserva.setIdReserva(rs.getInt("idReservaHabitacion"));
                reserva.getHabitacion().setIdHabitacion(rs.getInt("idHabitacion"));
                reserva.getHabitacion().setPiso(rs.getInt("piso"));
                pedido.setFechaSolicitud(rs.getDate("fechaSolicitud"));
                
                
                reserva.getHuesped().setNombre(rs.getString("nombre"));
                reserva.getHuesped().setApellidoPaterno(rs.getString("apellidoPaterno"));
                String nc="";
                nc+=reserva.getHuesped().getNombre()+"  ";
                nc+=reserva.getHuesped().getApellidoPaterno();
                pedido.setNombreHuesped(nc);
                
                
                pedido.setNumHabitacion(reserva.getHabitacion().getIdHabitacion());
                pedido.setPisoHabitacion(reserva.getHabitacion().getPiso());
                pedido.setReserva(reserva);
                
                ser.setIdIteam(rs.getInt("idItem"));
                ser.setIncidencia(rs.getString("incidencia"));
                ser.setEstado(EstadoServicio.valueOf(rs.getString("estado")));
                
                ser.setHoraInicio(rs.getDate("horaInicio"));
                ser.setHoraFin(rs.getDate("horaFin"));
                
                pedido.setIncidenciaDeHabitacion(ser.getIncidencia());
                pedido.setHoraFinServicio(ser.getHoraFin());
                
                pedido.getItems().add(ser);

                //falta datos de la reservahabitacion falta los get y sets
                pedidos.add(pedido);
            }   
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return pedidos;
    }
    
    
    @Override
    public ArrayList<Pedido> listarPedidos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public ArrayList<Pedido> listarPedidosPorCliente(int idHuesped) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_PEDIDOS_DE_HUESPED(?)}");
            cs.setInt("_idHuesped", idHuesped);
            rs = cs.executeQuery();
            while(rs.next()) {
                Pedido ped = new Pedido();
                ReservaHabitacion res = new ReservaHabitacion();
                ArrayList<Item> itemsPedido = new ArrayList<>();
                
                
                ped.setIdPedido(rs.getInt("idPedido"));
                ped.setFechaSolicitud(rs.getDate("fechaSolicitud"));
                ped.setEstado(EstadoPedido.valueOf(rs.getString("estado")));
                ped.setMontoAcumulado(rs.getDouble("montoAcumulado"));
                
                res.setIdReserva(rs.getInt("idReservaHabitacion"));
                res.getHabitacion().setIdHabitacion(rs.getInt("idHabitacion"));
                res.getHabitacion().setPiso(rs.getInt("Piso"));
                ped.setReserva(res);
                
                pedidos.add(ped);
            }
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try{rs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}

        }
        return pedidos;
    }


    

}
