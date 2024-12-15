/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.lothel.gestreserva.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.lothel.gestreserva.dao.MatrimonialDAO;
import pe.edu.pucp.lothel.gestreserva.model.Matrimonial;
import pe.edu.pucp.lothel.gestreserva.model.Simple;
import pe.edu.pucp.lothel.manager.DBManager;

/**
 *
 * @author Adrian Fujiki
 */
public class MatrimonialMYSQL implements MatrimonialDAO{

    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;

    @Override
    public int insertar(Matrimonial matrimonial) {
         int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_MATRIMONIAL(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.registerOutParameter("_id_habitacion",java.sql.Types.INTEGER);
            cs.setInt("_piso", matrimonial.getPiso());
            cs.setInt("_numeroCama",  matrimonial.getNumeroDeCamas());
            cs.setDouble("_precio",matrimonial.getPiso());
            cs.setBoolean("_reservado",matrimonial.getReservado());
            cs.setBoolean("_tieneJacuzzi",matrimonial.getTieneJacuzzi());
            cs.setBytes("_imagen", matrimonial.getImagen());
            cs.setString("_titulo", matrimonial.getTitulo());
            cs.setString("_descripcion", matrimonial.getDescripcion());
            cs.setInt("_cantHuespedes", matrimonial.getCantHuespedes());
            cs.setInt("_stock", matrimonial.getStock());
            resultado=cs.executeUpdate();
            matrimonial.setIdHabitacion(cs.getInt("_id_habitacion"));
            
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
    public int modificar(Matrimonial matrimonial) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call MODIFICAR_MATRIMONIAL(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("_id_habitacion", matrimonial.getIdHabitacion());
            cs.setInt("_piso", matrimonial.getPiso());
            cs.setInt("_numeroCama",  matrimonial.getNumeroDeCamas());
            cs.setDouble("_precio",matrimonial.getPiso());
            cs.setBoolean("_reservado",matrimonial.getReservado());
            cs.setBoolean("_tieneJacuzzi",matrimonial.getTieneJacuzzi());
            cs.setBytes("_imagen", matrimonial.getImagen());
            cs.setString("_titulo", matrimonial.getTitulo());
            cs.setString("_descripcion", matrimonial.getDescripcion());
            cs.setInt("_cantHuespedes", matrimonial.getCantHuespedes());
            cs.setInt("_stock", matrimonial.getStock());
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
            cs = con.prepareCall("{call ELIMINAR_MATRIMONIAL(?)}");
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
    public ArrayList<Matrimonial> listarHabitacionesMatrimoniales() {
        ArrayList<Matrimonial> matrimoniales = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_MATRIMONIAL()}");
            rs = cs.executeQuery();
            while(rs.next()){
                Matrimonial matrimonial = new Matrimonial();
                matrimonial.setIdHabitacion(rs.getInt("idHabitacion"));
                matrimonial.setPiso(rs.getInt("piso"));
                matrimonial.setNumeroDeCamas(rs.getInt("numeroCamas"));
                matrimonial.setPrecio(rs.getDouble("precio"));
                matrimonial.setReservado(rs.getBoolean("reservado"));
                matrimonial.setTieneJacuzzi(rs.getBoolean("tieneJacuzi"));
                //matrimonial.setImagen(rs.getBytes("imagen"));
                matrimonial.setTitulo(rs.getString("titulo"));
                matrimonial.setDescripcion(rs.getString("descripcion"));
                matrimonial.setCantHuespedes(rs.getInt("cantHuespedes"));
                matrimonial.setStock(rs.getInt("stock"));
                matrimoniales.add(matrimonial);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return matrimoniales;
    }
    
    
    @Override
    public ArrayList<Matrimonial> listarHabitacionesMatrimonialXPeriodo(Date fechaINI,Date fechaFin) {
        ArrayList<Matrimonial> matrimoniales = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_MATRIMONIAL_PERIODO(?,?)}");
            cs.setDate("_fechaInicio", new java.sql.Date(fechaINI.getTime()));
            cs.setDate("_fechaFin",  new java.sql.Date(fechaFin.getTime()));
            rs = cs.executeQuery();
            while(rs.next()){
                Matrimonial matrimonial = new Matrimonial();
                matrimonial.setIdHabitacion(rs.getInt("idHabitacion"));
                matrimonial.setPiso(rs.getInt("piso"));
                matrimonial.setNumeroDeCamas(rs.getInt("numeroCamas"));
                matrimonial.setPrecio(rs.getDouble("precio"));
                matrimonial.setReservado(rs.getBoolean("reservado"));
                matrimonial.setTieneJacuzzi(rs.getBoolean("tieneJacuzi"));
                //matrimonial.setImagen(rs.getBytes("imagen"));
                matrimonial.setTitulo(rs.getString("titulo"));
                matrimonial.setDescripcion(rs.getString("descripcion"));
                matrimonial.setCantHuespedes(rs.getInt("cantHuespedes"));
                matrimonial.setStock(rs.getInt("stock"));
                matrimoniales.add(matrimonial);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return matrimoniales;
    }
    
    
    
}
