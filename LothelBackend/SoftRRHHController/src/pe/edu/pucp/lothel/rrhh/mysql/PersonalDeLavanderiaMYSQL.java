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
import pe.edu.pucp.lothel.rrhh.dao.PersonalDeLavanderiaDAO;
import pe.edu.pucp.lothel.rrhh.model.PersonalDeLavanderia;
import pe.edu.pucp.lothel.rrhh.model.TipoTurno;

/**
 *
 * @author Adrian Fujiki
 */
public class PersonalDeLavanderiaMYSQL implements PersonalDeLavanderiaDAO{
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    private Statement st;
    
    @Override
    public int insertar(PersonalDeLavanderia lavandero) {
        int resultado = 0;
        try{
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://db-lothel.cjgycpwdcgx4.us-east-1.rds.amazonaws.com:3306/lothel","admin","lothel321");
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_LAVANDERO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.registerOutParameter("_id_Lavandero",java.sql.Types.INTEGER);
            cs.setBoolean("_riesgo",lavandero.getAutorizacionDeRiesgoBiologico());
            cs.setString("_turno",lavandero.getTurno().toString());
            cs.setBoolean("_estado",lavandero.getEstado());
            cs.setInt("_idAdministrador",lavandero.getAdministrador().getIdAdministrador());
            cs.setDate("_fecha_contratacion",new java.sql.Date(lavandero.getFechaContratacion().getTime()));
            cs.setBoolean("_activo",lavandero.getActivo());
            cs.setString("_dni",lavandero.getDni());
            cs.setString("_nombre",lavandero.getNombre());
            cs.setString("_apellido_Paterno",lavandero.getApellidoPaterno());
            cs.setString("_apellido_Materno",lavandero.getApellidoMaterno());
            cs.setString("_correo",lavandero.getCorreo());
            cs.setDate("_fecha_registro",new java.sql.Date(lavandero.getFechaRegistro().getTime()));
            cs.setString("_celular",lavandero.getCelular());
            cs.setDouble("_sueldo",lavandero.getSueldo());
            resultado=cs.executeUpdate();
            lavandero.setIdPersona(cs.getInt("_id_Lavandero"));
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
    public int modificar(PersonalDeLavanderia lavandero) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            
            cs = con.prepareCall("{call MODIFICAR_LAVANDERO(?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("_id_PersonalDeLavanderia", lavandero.getIdPersona());
            cs.setBoolean("_riesgo",lavandero.getAutorizacionDeRiesgoBiologico());
            cs.setString("_turno", lavandero.getTurno().toString());
            cs.setDouble("_sueldo", lavandero.getSueldo());
            cs.setString("_correo", lavandero.getCorreo());
            cs.setString("_celular", lavandero.getCelular());
            cs.setString("_dni", lavandero.getDni());
            cs.setString("_nombre", lavandero.getNombre());
            cs.setString("_apellidoPaterno", lavandero.getApellidoPaterno());
            cs.setString("_apellidoMaterno", lavandero.getApellidoMaterno());
            resultado = cs.executeUpdate();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int idLavandero) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            
            cs = con.prepareCall("{call ELIMINAR_LAVANDERO(?)}");
            cs.setInt("_id_PersonalDeLavanderia", idLavandero);
            resultado = cs.executeUpdate();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<PersonalDeLavanderia> listarLavanderos() {
        ArrayList<PersonalDeLavanderia> perLavs =  new ArrayList<PersonalDeLavanderia>();
        
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_LAVANDEROS()}");
            rs = cs.executeQuery();
           
            while(rs.next()){
                PersonalDeLavanderia perLav = new PersonalDeLavanderia();
                perLav.setIdPersona(rs.getInt("idPersonalDeLavanderia"));
                perLav.setDni(rs.getString("dni"));
                perLav.setNombre(rs.getString("nombre"));
                perLav.setApellidoPaterno(rs.getString("apellidoPaterno"));
                perLav.setApellidoMaterno(rs.getString("apellidoMaterno"));
                perLav.setCorreo(rs.getString("correo"));
                perLav.setFechaRegistro(rs.getDate("fechaRegistro"));
                perLav.setCelular(rs.getString("celular"));
                perLav.setFechaContratacion(rs.getDate("fechaContratacion"));
                perLav.setActivo(rs.getBoolean("activo"));
                perLav.setTurno(TipoTurno.valueOf(rs.getString("turno")));
                perLav.setActivo(rs.getBoolean("estado"));
                perLav.setAutorizacionDeRiesgoBiologico(rs.getBoolean("autorizacionDeRiesgoBiologico"));
                perLav.setSueldo(rs.getDouble("sueldo"));
                
                perLavs.add(perLav);
            }   
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return perLavs;
    }
    
}
