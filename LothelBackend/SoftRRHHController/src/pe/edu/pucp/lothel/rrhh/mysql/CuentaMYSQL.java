/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.lothel.rrhh.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import pe.edu.pucp.lothel.manager.DBManager;
import pe.edu.pucp.lothel.rrhh.dao.CuentaDAO;
import pe.edu.pucp.lothel.rrhh.model.Administrador;
import pe.edu.pucp.lothel.rrhh.model.Cuenta;
import pe.edu.pucp.lothel.rrhh.model.Huesped;
import pe.edu.pucp.lothel.rrhh.model.Persona;
import pe.edu.pucp.lothel.rrhh.model.PersonalDeLavanderia;
import pe.edu.pucp.lothel.rrhh.model.PersonalDeMasajes;
import pe.edu.pucp.lothel.rrhh.model.PersonalDeServicio;
import pe.edu.pucp.lothel.rrhh.model.Recepcionista;
import pe.edu.pucp.lothel.rrhh.model.TipoCuenta;

/**
 *
 * @author Adrian Fujiki
 */
public class CuentaMYSQL implements CuentaDAO{
    private Connection con;
    private PreparedStatement pst;
    private CallableStatement cs;
    private ResultSet rs;
    private Statement st;
    
    //Se encarga de obtener todos los datos del usuario segun su tipo de cuenta
    @Override
    public Persona obtenerCuentaUserPass(String user, String password) {
        //Huesped o Adminis o recepcionista o personal de lavanderia o personal de masajes
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call OBTENER_CUENTA_USUARIO_CONTRASENA(?,?,?)}");
            cs.setString("pUsuario", user);
            cs.setString("pContrasena", password);
            cs.registerOutParameter("pTipoCuenta", java.sql.Types.VARCHAR);
            rs = cs.executeQuery(); 

            rs.next();
            String tipo=cs.getString("pTipoCuenta");
            
            Cuenta cuenta=new Cuenta();
            cuenta.setUser(user);
            cuenta.setPassword(password);
            if(tipo.equals("ADMINISTRADOR")){
                Administrador administrador=new Administrador();
                administrador.setIdPersona(rs.getInt("idAdministrador"));
                administrador.setDni(rs.getString("dni"));
                administrador.setNombre(rs.getString("nombre"));
                administrador.setApellidoPaterno(rs.getString("apellidoPaterno"));
                administrador.setApellidoMaterno(rs.getString("apellidoMaterno"));
                administrador.setCorreo(rs.getString("correo"));
                administrador.setFechaRegistro(rs.getDate("fechaRegistro"));
                administrador.setCelular(rs.getString("celular"));
                administrador.setFechaContratacion(rs.getDate("fechaContratacion"));
                administrador.setRol(rs.getString("rol"));
                cuenta.setTipocuenta(TipoCuenta.ADMINISTRADOR);
                administrador.setCuenta(cuenta);
                return administrador;
            }else{
                if(tipo.equals("HUESPED")){
                    Huesped huesped=new Huesped();
                    huesped.setIdPersona(rs.getInt("idHuesped"));
                    huesped.setDni(rs.getString("dni"));
                    huesped.setNombre(rs.getString("nombre"));
                    huesped.setApellidoPaterno(rs.getString("apellidoPaterno"));
                    huesped.setApellidoMaterno(rs.getString("apellidoMaterno"));
                    huesped.setCorreo(rs.getString("correo"));
                    huesped.setFechaRegistro(rs.getDate("fechaRegistro"));
                    huesped.setCelular(rs.getString("celular"));
                    huesped.setEsVIP(rs.getBoolean("esVIP"));
                    cuenta.setTipocuenta(TipoCuenta.HUESPED);
                    huesped.setCuenta(cuenta);
                    return huesped;
                }else{
                    if(tipo.equals("PERSONAL_DE_LAVANDERIA")){
                        PersonalDeLavanderia perLav=new PersonalDeLavanderia();
                        perLav.setIdPersona(rs.getInt("idPersonalDeLavanderia"));
                        perLav.setDni(rs.getString("dni"));
                        perLav.setNombre(rs.getString("nombre"));
                        perLav.setApellidoPaterno(rs.getString("apellidoPaterno"));
                        perLav.setApellidoMaterno(rs.getString("apellidoMaterno"));
                        perLav.setCorreo(rs.getString("correo"));
                        perLav.setFechaRegistro(rs.getDate("fechaRegistro"));
                        perLav.setCelular(rs.getString("celular"));
                        perLav.setAutorizacionDeRiesgoBiologico(rs.getBoolean("autorizacionDeRiesgoBiologico"));
                        cuenta.setTipocuenta(TipoCuenta.PERSONAL_DE_LAVANDERIA);
                        perLav.setCuenta(cuenta);
                        return perLav;
                    }else{
                        if(tipo.equals("PERSONAL_DE_MASAJES")){
                            PersonalDeMasajes perMas=new PersonalDeMasajes();
                            perMas.setIdPersona(rs.getInt("idPersonalDeMasajes"));
                            perMas.setDni(rs.getString("dni"));
                            perMas.setNombre(rs.getString("nombre"));
                            perMas.setApellidoPaterno(rs.getString("apellidoPaterno"));
                            perMas.setApellidoMaterno(rs.getString("apellidoMaterno"));
                            perMas.setCorreo(rs.getString("correo"));
                            perMas.setFechaRegistro(rs.getDate("fechaRegistro"));
                            perMas.setCelular(rs.getString("celular"));
                            perMas.setEspecializacion(rs.getString("especializacion"));
                            cuenta.setTipocuenta(TipoCuenta.PERSONAL_DE_MASAJES);
                            perMas.setCuenta(cuenta);
                            return perMas;
                        }else{
                            if(tipo.equals("RECEPCIONISTA")){
                                Recepcionista recep=new Recepcionista();
                                recep.setIdPersona(rs.getInt("idRecepcionista"));
                                recep.setDni(rs.getString("dni"));
                                recep.setNombre(rs.getString("nombre"));
                                recep.setApellidoPaterno(rs.getString("apellidoPaterno"));
                                recep.setApellidoMaterno(rs.getString("apellidoMaterno"));
                                recep.setCorreo(rs.getString("correo"));
                                recep.setFechaRegistro(rs.getDate("fechaRegistro"));
                                recep.setCelular(rs.getString("celular"));
                                recep.setDeuda(rs.getDouble("deuda"));
                                cuenta.setTipocuenta(TipoCuenta.RECEPCIONISTA);
                                recep.setCuenta(cuenta);
                                return recep;
                            }
                        }
                    }
                }
            }
        
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try{rs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return null;
    }
    
}
