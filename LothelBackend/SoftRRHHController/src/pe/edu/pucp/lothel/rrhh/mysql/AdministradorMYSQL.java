/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.lothel.rrhh.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import pe.edu.pucp.lothel.manager.DBManager;
import pe.edu.pucp.lothel.rrhh.dao.AdministradorDAO;
import pe.edu.pucp.lothel.rrhh.model.Administrador;
import pe.edu.pucp.lothel.rrhh.model.TipoCuenta;

/**
 *
 * @author DELL
 */
public class AdministradorMYSQL implements AdministradorDAO{
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    private Statement st;
    @Override
    public int insertar(Administrador administrador) {
        int resultado = 0;
        try{
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://db-lothel.cjgycpwdcgx4.us-east-1.rds.amazonaws.com:3306/lothel","admin","lothel321");
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_ADMINISTRADOR(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.registerOutParameter("_id_administrador",java.sql.Types.INTEGER);
            cs.setString("_rol", administrador.getRol());
            cs.setDate("_fecha_contratacion",  new java.sql.Date(administrador.getFechaContratacion().getTime()));
            cs.setBoolean("_activo",administrador.getEstado());
            cs.setString("_dni",administrador.getDni());
            cs.setString("_nombre",administrador.getNombre());
            cs.setString("_apellido_Paterno",administrador.getApellidoPaterno());
            cs.setString("_apellido_Materno",administrador.getApellidoMaterno());
            cs.setString("_correo",administrador.getCorreo());
            cs.setDate("_fecha_registro",  new java.sql.Date(administrador.getFechaRegistro().getTime()));
            cs.setString("_celular",administrador.getCelular());
            cs.setString("_usuario",administrador.getCuenta().getUser());
            cs.setString("__contrasenia",administrador.getCuenta().getPassword());
            cs.setString("_tipoCuenta",TipoCuenta.ADMINISTRADOR.toString());
            
            resultado=cs.executeUpdate();
            administrador.setIdPersona(cs.getInt("_id_administrador"));
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
    public int modificar(Administrador administrador) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            
            cs = con.prepareCall("{call MODIFICAR_ADMINISTRADOR(?,?)}");
            //cambiar la fecha del evento
            cs.setInt("_IdEvento", administrador.getIdAdministrador());
            cs.setString("_rol",administrador.getRol());

            resultado = cs.executeUpdate();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        
        return resultado;
    }

    @Override
    public int eliminar(int idAdministrador) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            
            cs = con.prepareCall("{call ELIMINAR_ADMINISTRADOR(?)}");
            cs.setInt("_id_administrador", idAdministrador);
            resultado = cs.executeUpdate();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Administrador> listarAdministradores() {
        ArrayList<Administrador> administradores =  new ArrayList<Administrador>();
        
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_ADMINISTRADORES()}");
            //String sql = "SELECT * FROM Evento";
            rs = cs.executeQuery();
           
            while(rs.next()){
                Administrador admin = new Administrador();
                admin.setIdPersona(rs.getInt("idAdministrador"));
                admin.setDni(rs.getString("dni"));
                admin.setNombre(rs.getString("nombre"));
                admin.setApellidoPaterno(rs.getString("apellidoPaterno"));
                admin.setApellidoMaterno(rs.getString("apellidoMaterno"));
                admin.setCorreo(rs.getString("correo"));
                admin.setFechaRegistro(rs.getDate("fechaRegistro"));
                admin.setCelular(rs.getString("celular"));
                admin.setFechaContratacion(rs.getDate("fechaContratacion"));
                admin.setActivo(rs.getBoolean("activo"));
                admin.setRol(rs.getString("rol"));
                administradores.add(admin);
            }   
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return administradores;
    }
    
}
