/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.lothel.gestreserva.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.lothel.gestreserva.dao.FamiliarDAO;
import pe.edu.pucp.lothel.gestreserva.model.Familiar;
import pe.edu.pucp.lothel.gestreserva.model.Matrimonial;
import pe.edu.pucp.lothel.gestreserva.model.ReservaHabitacion;
import pe.edu.pucp.lothel.manager.DBManager;

/**
 *
 * @author Adrian Fujiki
 */
public class FamiliarMYSQL implements FamiliarDAO{
    
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;

    @Override
    public int insertar(Familiar familiar) {
         int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_FAMILIAR(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.registerOutParameter("_id_habitacion",java.sql.Types.INTEGER);
            cs.setInt("_piso", familiar.getPiso());
            cs.setInt("_numeroCama",  familiar.getNumeroDeCamas());
            cs.setDouble("_precio",familiar.getPiso());
            cs.setBoolean("_reservado",familiar.getReservado());
            cs.setBoolean("_cocheraPropia",familiar.getCocheraPropia());
            cs.setBytes("_imagen", familiar.getImagen());
            cs.setString("_titulo", familiar.getTitulo());
            cs.setString("_descripcion", familiar.getDescripcion());
            cs.setInt("_cantHuespedes", familiar.getCantHuespedes());
            cs.setInt("_stock", familiar.getStock());
            resultado=cs.executeUpdate();
            familiar.setIdHabitacion(cs.getInt("_id_habitacion"));
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
    public int modificar(Familiar familiar) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call MODIFICAR_FAMILIAR(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("_id_habitacion", familiar.getIdHabitacion());
            cs.setInt("_piso", familiar.getPiso());
            cs.setInt("_numeroCama",  familiar.getNumeroDeCamas());
            cs.setDouble("_precio",familiar.getPiso());
            cs.setBoolean("_reservado",familiar.getReservado());
            cs.setBoolean("_tieneJacuzzi",familiar.getCocheraPropia());
            cs.setBytes("_imagen", familiar.getImagen());
            cs.setString("_titulo", familiar.getTitulo());
            cs.setString("_descripcion", familiar.getDescripcion());
            cs.setInt("_cantHuespedes", familiar.getCantHuespedes());
            cs.setInt("_stock", familiar.getStock());
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
    public int eliminar(int idFamiliar) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ELIMINAR_FAMILIAR(?)}");
            cs.setInt("_id_habitacion", idFamiliar);
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
    public ArrayList<Familiar> listarHabitacionesFamiliares() {
     ArrayList<Familiar> familiares = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_FAMILIAR()}");
            rs = cs.executeQuery();
            while(rs.next()){
                Familiar familiar = new Familiar();
                familiar.setIdHabitacion(rs.getInt("idHabitacion"));
                familiar.setPiso(rs.getInt("piso"));
                familiar.setNumeroDeCamas(rs.getInt("numeroCamas"));
                familiar.setPrecio(rs.getDouble("precio"));
                familiar.setReservado(rs.getBoolean("reservado"));
                familiar.setCocheraPropia(rs.getBoolean("cocheraPropia"));
                familiar.setImagen(rs.getBytes("imagen"));
                familiar.setTitulo(rs.getString("titulo"));
                familiar.setDescripcion(rs.getString("descripcion"));
                familiar.setCantHuespedes(rs.getInt("cantHuespedes"));
                familiar.setStock(rs.getInt("stock"));
                familiares.add(familiar);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return familiares;
    }
    
    
    @Override
    public ArrayList<Familiar> listarHabitacionesFamiliarXPeriodo(Date fechaINI,Date fechaFin) {
        ArrayList<Familiar> familiares = new ArrayList<>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_FAMILIAR_PERIODO(?,?)}");
            cs.setDate("_fechaInicio", new java.sql.Date(fechaINI.getTime()));
            cs.setDate("_fechaFin",  new java.sql.Date(fechaFin.getTime()));
            rs = cs.executeQuery();
            while(rs.next()){
                Familiar familiar = new Familiar();
                familiar.setIdHabitacion(rs.getInt("idHabitacion"));
                familiar.setPiso(rs.getInt("piso"));
                familiar.setNumeroDeCamas(rs.getInt("numeroCamas"));
                familiar.setPrecio(rs.getDouble("precio"));
                familiar.setReservado(rs.getBoolean("reservado"));
                familiar.setCocheraPropia(rs.getBoolean("cocheraPropia"));
                //familiar.setImagen(rs.getBytes("imagen"));
                familiar.setTitulo(rs.getString("titulo"));
                familiar.setDescripcion(rs.getString("descripcion"));
                familiar.setCantHuespedes(rs.getInt("cantHuespedes"));
                familiar.setStock(rs.getInt("stock"));
                familiares.add(familiar);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(SQLException ex){System.out.println(ex.getMessage());}
        }
        return familiares;
    }
    
}
