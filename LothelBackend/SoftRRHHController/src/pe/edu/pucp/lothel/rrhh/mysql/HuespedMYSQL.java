/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.lothel.rrhh.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import pe.edu.pucp.lothel.manager.DBManager;
import pe.edu.pucp.lothel.rrhh.dao.HuespedDAO;
import pe.edu.pucp.lothel.rrhh.model.Huesped;

/**
 *
 * @author marcelo
 */
public class HuespedMYSQL implements HuespedDAO {

    private Connection con;
    private PreparedStatement pst;
    private CallableStatement cs;
    private ResultSet rs;
    private Statement st;
    
    @Override 
    public int insertar(Huesped huesped) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_HUESPED(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.registerOutParameter("_idHuesped",java.sql.Types.INTEGER);
            cs.setString("_dni", huesped.getDni());
            cs.setString("_nombre", huesped.getNombre());
            cs.setString("_apellido_Paterno", huesped.getApellidoPaterno());
            cs.setString("_apellido_Materno", huesped.getApellidoMaterno());
            cs.setString("_correo", huesped.getCorreo());
            cs.setDate("_fecha_registro", new java.sql.Date(huesped.getFechaRegistro().getTime()));
            
            cs.setString("_celular", huesped.getCelular());
            cs.setBoolean("_esVIP", huesped.getEsVIP());
            cs.setBoolean("_activo", true);
            cs.setString("_usuario",huesped.getCuenta().getUser());
            cs.setString("__contrasenia",huesped.getCuenta().getPassword());
            cs.setString("_tipoCuenta",huesped.getCuenta().getTipocuenta().toString());
            cs.executeUpdate();
            huesped.setIdPersona(cs.getInt("_idHuesped"));
            resultado = huesped.getIdPersona();
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
    public int modificar(Huesped huesped) {
        //DEBE PODER MODIFICAR SU CORREO, CELULAR, CONTRASEÑA
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call MODIFICAR_DATOS_HUESPED(?,?,?,?,?)}");
            cs.setInt("_idHuesped", huesped.getIdPersona());
            cs.setString("_nuevoCorreo", huesped.getCorreo());
            cs.setString("_nuevoCelular", huesped.getCelular());
            cs.setString("_nuevaContraseña", huesped.getCuenta().getPassword());
            cs.setString("_nuevoUsuario", huesped.getCuenta().getUser());
            resultado = cs.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}            
        }
        return resultado;
    }

    @Override
    public int eliminar(int idHuesped) {
    int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ELIMINAR_HUESPED(?)}");
            cs.setInt(("_idHuesped"), idHuesped);
            resultado = cs.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
    return resultado;
    }

    @Override
    public ArrayList<Huesped> listarHuespedes() {
        ArrayList<Huesped> huespedes = new ArrayList<Huesped>();
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_HUESPEDES()}");
            //String sql = "SELECT * FROM HUESPED";
            rs = cs.executeQuery();
            
            while(rs.next()) {
                Huesped huesped = new Huesped();
                huesped.setIdPersona(rs.getInt("idHuesped"));
                huesped.setNombre(rs.getString("dni"));
                huesped.setNombre(rs.getString("nombre"));
                huesped.setApellidoPaterno(rs.getString("apellidoPaterno"));
                huesped.setApellidoMaterno(rs.getString("apellidoMaterno"));
                huesped.setFechaRegistro(rs.getDate("fechaRegistro"));
                huesped.setCorreo(rs.getString("correo"));
                huesped.setCelular(rs.getString("celular"));
                huesped.setEsVIP(rs.getBoolean("esVIP"));
                
                huespedes.add(huesped);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try{rs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        
        }
        return huespedes;
    }

    @Override
    public ArrayList<Huesped> listarHuespedXPeriodo(Date fechaInicio,Date fechaFin) {
        ArrayList<Huesped> huespedes = new ArrayList<Huesped>();
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ListarHuespedXPeriodo(?,?)}");
            cs.setDate("_fechaInicio", new java.sql.Date(fechaInicio.getTime()));
            cs.setDate("_fechaFin",  new java.sql.Date(fechaFin.getTime()));
            rs = cs.executeQuery();
            
            while(rs.next()) {
                Huesped huesped = new Huesped();
                huesped.setIdPersona(rs.getInt("idHuesped"));
                huesped.setNombre(rs.getString("nombre"));
                huesped.setApellidoPaterno(rs.getString("apellidoPaterno"));
                huesped.setApellidoMaterno(rs.getString("apellidoMaterno"));
                huesped.setCorreo(rs.getString("correo"));
                huesped.setFechaRegistro(rs.getDate("fechaRegistro"));
                huesped.setCelular(rs.getString("celular"));
                huesped.setDni(rs.getString("dni"));
                huesped.setEsVIP(rs.getBoolean("esVIP"));
                
                huespedes.add(huesped);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try{rs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        
        }
        return huespedes;
    }
    
}

