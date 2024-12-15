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
import pe.edu.pucp.lothel.rrhh.dao.PersonalDeMasajesDAO;
import pe.edu.pucp.lothel.rrhh.model.PersonalDeMasajes;
import pe.edu.pucp.lothel.rrhh.model.TipoTurno;

/**
 *
 * @author Adrian Fujiki
 */
public class PersonalDeMasajesMYSQL implements PersonalDeMasajesDAO{
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    private Statement st;
    
    @Override
    public int insertar(PersonalDeMasajes masajista) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_MASAJISTA(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.registerOutParameter("_id_PersonalDeMasajes",java.sql.Types.INTEGER);
            cs.setString("_especializacion",masajista.getEspecializacion());
            cs.setString("_turno",masajista.getTurno().toString());
            cs.setBoolean("_estado",masajista.getEstado());
            cs.setInt("_idAdministrador",masajista.getAdministrador().getIdAdministrador());
            cs.setDate("_fecha_contratacion",new java.sql.Date(masajista.getFechaContratacion().getTime()));
            cs.setBoolean("_activo",masajista.getActivo());
            cs.setString("_dni",masajista.getDni());
            cs.setString("_nombre",masajista.getNombre());
            cs.setString("_apellido_Paterno",masajista.getApellidoPaterno());
            cs.setString("_apellido_Materno",masajista.getApellidoMaterno());
            cs.setString("_correo",masajista.getCorreo());
            cs.setDate("_fecha_registro",new java.sql.Date(masajista.getFechaRegistro().getTime()));
            cs.setString("_celular",masajista.getCelular());
            cs.setDouble("_sueldo", masajista.getSueldo());
            resultado=cs.executeUpdate();
            masajista.setIdPersona(cs.getInt("_id_PersonalDeMasajes"));
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
    public int modificar(PersonalDeMasajes masajista) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            
            cs = con.prepareCall("{call MODIFICAR_MASAJISTA(?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("_id_PersonalDeMasajes", masajista.getIdPersona());
            cs.setString("_especializacion",masajista.getEspecializacion());
            cs.setString("_turno", masajista.getTurno().toString());
            cs.setDouble("_sueldo", masajista.getSueldo());
            cs.setString("_correo", masajista.getCorreo());
            cs.setString("_celular", masajista.getCelular());
            cs.setString("_dni", masajista.getDni());
            cs.setString("_nombre", masajista.getNombre());
            cs.setString("_apellidoPaterno", masajista.getApellidoPaterno());
            cs.setString("_apellidoMaterno", masajista.getApellidoMaterno());
            resultado = cs.executeUpdate();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int idMasajista) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            
            cs = con.prepareCall("{call ELIMINAR_MASAJISTA(?)}");
            cs.setInt("_id_PersonalDeMasajes", idMasajista);
            resultado = cs.executeUpdate();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public ArrayList<PersonalDeMasajes> listarMasajistas() {
        ArrayList<PersonalDeMasajes> listMas =  new ArrayList<PersonalDeMasajes>();
        
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_MASAJISTAS()}");
            rs = cs.executeQuery();
           
            while(rs.next()){
                PersonalDeMasajes perMas = new PersonalDeMasajes();
                perMas.setIdPersona(rs.getInt("idPersonalDeMasajes"));
                perMas.setDni(rs.getString("dni"));
                perMas.setNombre(rs.getString("nombre"));
                perMas.setApellidoPaterno(rs.getString("apellidoPaterno"));
                perMas.setApellidoMaterno(rs.getString("apellidoMaterno"));
                perMas.setCorreo(rs.getString("correo"));
                perMas.setFechaRegistro(rs.getDate("fechaRegistro"));
                perMas.setCelular(rs.getString("celular"));
                perMas.setFechaContratacion(rs.getDate("fechaContratacion"));
                perMas.setActivo(rs.getBoolean("activo"));
                perMas.setTurno(TipoTurno.valueOf(rs.getString("turno")));
                perMas.setActivo(rs.getBoolean("estado"));
                perMas.setEspecializacion(rs.getString("especializacion"));
                perMas.setSueldo(rs.getDouble("sueldo"));
                listMas.add(perMas);
            }   
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return listMas;
    }
    
}
