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
import pe.edu.pucp.lothel.rrhh.dao.RecepcionistaDAO;
import pe.edu.pucp.lothel.rrhh.model.Administrador;
import pe.edu.pucp.lothel.rrhh.model.Recepcionista;
import pe.edu.pucp.lothel.rrhh.model.TipoCuenta;
import pe.edu.pucp.lothel.rrhh.model.TipoTurno;

/**
 *
 * @author DELL
 */
public class RecepcionistaMYSQL implements RecepcionistaDAO{
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    private Statement st;
    @Override
    public int insertar(Recepcionista recepcionista) {
        int resultado = 0;
        try{
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://db-lothel.cjgycpwdcgx4.us-east-1.rds.amazonaws.com:3306/lothel","admin","lothel321");
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_RECEPCIONISTA(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.registerOutParameter("_id_Recepcionista",java.sql.Types.INTEGER);
            cs.setDouble("_deuda", recepcionista.getDeuda());
            cs.setString("_turno",recepcionista.getTurno().toString());
            cs.setBoolean("_estado",recepcionista.getEstado());
            cs.setInt("_idAdministrador",recepcionista.getAdministrador().getIdAdministrador());
            cs.setDate("_fecha_contratacion",new java.sql.Date(recepcionista.getFechaContratacion().getTime()));
            cs.setBoolean("_activo",recepcionista.getActivo());
            cs.setString("_dni",recepcionista.getDni());
            cs.setString("_nombre",recepcionista.getNombre());
            cs.setString("_apellido_Paterno",recepcionista.getApellidoPaterno());
            cs.setString("_apellido_Materno",recepcionista.getApellidoMaterno());
            cs.setString("_correo",recepcionista.getCorreo());
            cs.setDate("_fecha_registro",new java.sql.Date(recepcionista.getFechaRegistro().getTime()));
            cs.setString("_celular",recepcionista.getCelular());
            cs.setString("_usuario",recepcionista.getCuenta().getUser());
            cs.setString("__contrasenia",recepcionista.getCuenta().getPassword());
            cs.setString("_tipoCuenta",TipoCuenta.RECEPCIONISTA.toString());
            cs.setDouble("_sueldo", recepcionista.getSueldo());
            resultado=cs.executeUpdate();
            recepcionista.setIdPersona(cs.getInt("_id_Recepcionista"));
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        return resultado;    }

    @Override
    public int modificar(Recepcionista recepcionista) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            
            cs = con.prepareCall("{call MODIFICAR_RECEPCIONISTA(?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("_id_Recepcionista", recepcionista.getIdPersona());
            cs.setDouble("_deuda",recepcionista.getDeuda());
            cs.setString("_turno", recepcionista.getTurno().toString());
            cs.setDouble("_sueldo", recepcionista.getSueldo());
            cs.setString("_correo", recepcionista.getCorreo());
            cs.setString("_celular", recepcionista.getCelular());
            cs.setString("_dni", recepcionista.getDni());
            cs.setString("_nombre", recepcionista.getNombre());
            cs.setString("_apellidoPaterno", recepcionista.getApellidoPaterno());
            cs.setString("_apellidoMaterno", recepcionista.getApellidoMaterno());

            resultado = cs.executeUpdate();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        
        return resultado;
    }

    @Override
    public int eliminar(int idRecepcionista) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            
            cs = con.prepareCall("{call ELIMINAR_RECEPCIONISTA(?)}");
            cs.setInt("_id_Recepcionista", idRecepcionista);
            resultado = cs.executeUpdate();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<Recepcionista> listarRecepcionistas() {
        ArrayList<Recepcionista> recepcionistas =  new ArrayList<Recepcionista>();
        
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_RECEPCIONISTA()}");
            //String sql = "SELECT * FROM Evento";
            rs = cs.executeQuery();
           
            while(rs.next()){
                Recepcionista recepcionista = new Recepcionista();
                recepcionista.setIdPersona(rs.getInt("idRecepcionista"));
                recepcionista.setDni(rs.getString("dni"));
                recepcionista.setNombre(rs.getString("nombre"));
                recepcionista.setApellidoPaterno(rs.getString("apellidoPaterno"));
                recepcionista.setApellidoMaterno(rs.getString("apellidoMaterno"));
                recepcionista.setCorreo(rs.getString("correo"));
                recepcionista.setFechaRegistro(rs.getDate("fechaRegistro"));
                recepcionista.setCelular(rs.getString("celular"));
                recepcionista.setFechaContratacion(rs.getDate("fechaContratacion"));
                recepcionista.setActivo(rs.getBoolean("activo"));
                recepcionista.setSueldo(rs.getDouble("sueldo"));
                recepcionista.setTurno(TipoTurno.valueOf(rs.getString("turno")));
                recepcionista.setActivo(rs.getBoolean("estado"));
                recepcionista.setDeuda(rs.getDouble("deuda"));
                recepcionistas.add(recepcionista);
            }   
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return recepcionistas;
    }

   
}
