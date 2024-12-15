/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.lothel.evento.mysql;

import java.util.ArrayList;
import pe.edu.pucp.lothel.evento.model.Espacio;
import pe.edu.pucp.lothel.manager.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import pe.edu.pucp.lothel.evento.dao.EspacioDAO;

/**
 *
 * @author efeproceres
 */
public class EspacioMYSQL implements EspacioDAO{
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;
    public int insertar(Espacio espacio) {
        int resultado = 0;
        try{
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://db-lothel.cjgycpwdcgx4.us-east-1.rds.amazonaws.com:3306/lothel","admin","lothel321");
            con = DBManager.getInstance().getConnection();
            //cs.registerOutParameter("_id_empleado",java.sql.Types.INTEGER);
            cs = con.prepareCall("{call INSERTAR_ESPACIO(?,?,?,?,?)}");
            cs.registerOutParameter("_id_Espacio",java.sql.Types.INTEGER);
            cs.setInt("_numeroPiso", espacio.getNumeroPiso());
            cs.setString("_seccion", espacio.getSeccion());
            cs.setInt("_aforo",espacio.getAforo());
            cs.setBoolean("_disponibilidad", espacio.getDisponibilidad());
            //cs.setDate("_fecha_nacimiento", new java.sql.Date(empleado.getFechaNacimiento().getTime()));
            
            resultado=cs.executeUpdate();
            espacio.setIdEspacio(cs.getInt("_id_Espacio"));
            //empleado.setIdPersona(cs.getInt("_id_empleado"));
            
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
    public int modificar(Espacio espacio) {
        int resultado = 0;
        try{
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://db-lothel.cjgycpwdcgx4.us-east-1.rds.amazonaws.com:3306/lothel","admin","lothel321");
            
            con = DBManager.getInstance().getConnection();
            
            cs = con.prepareCall("{call MODIFICAR_ESPACIO(?,?,?,?,?)}");
            cs.setInt("_idEspacio", espacio.getIdEspacio());
            cs.setInt("_numeroPiso",espacio.getNumeroPiso());
            cs.setString("_seccion",espacio.getSeccion());
            cs.setInt("_aforo", espacio.getAforo());
            cs.setBoolean("_disponibilidad", espacio.getDisponibilidad());
            resultado = cs.executeUpdate();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int idEspacio) {
       int resultado = 0;
        try{
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://db-lothel.cjgycpwdcgx4.us-east-1.rds.amazonaws.com:3306/lothel","admin","lothel321");
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ELIMINAR_ESPACIO(?)}");
            cs.setInt("_idEspacio", idEspacio);
            resultado = cs.executeUpdate();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    
    public ArrayList<Espacio> listarEspacios() {
        ArrayList<Espacio> espacios =  new ArrayList<>();
        
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_ESPACIO()}");
            rs = cs.executeQuery();
            
            while(rs.next()){
                Espacio espacio = new Espacio();
                espacio.setIdEspacio(rs.getInt("idEspacio"));
                espacio.setAforo(rs.getInt("aforo"));
                espacio.setDisponibilidad(rs.getBoolean("disponibilidad"));
                espacio.setNumeroPiso(rs.getInt("numeroPiso"));
                espacio.setSeccion(rs.getString("seccion"));
                espacios.add(espacio);
            }   
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return espacios;
    }
    
    public ArrayList<Espacio> listarEspaciosPorNombre(String nombre) {
        ArrayList<Espacio> espacios =  new ArrayList<Espacio>();
        
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_ESPACIOS_POR_NOMBRE(?)}");
            cs.setString("_nombre", nombre);
            //cs.setString(1, nombre);
            rs = cs.executeQuery();
            
            while(rs.next()){
                Espacio espacio = new Espacio();
                espacio.setIdEspacio(rs.getInt("idEspacio"));
                espacio.setAforo(rs.getInt("aforo"));
                //.setDisponibilidad(rs.getBoolean("disponibilidad"));
                //espacio.setNumeroPiso(rs.getInt("numeroPiso"));
                espacio.setSeccion(rs.getString("seccion"));
                espacios.add(espacio);
            }   
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return espacios;
    }
}