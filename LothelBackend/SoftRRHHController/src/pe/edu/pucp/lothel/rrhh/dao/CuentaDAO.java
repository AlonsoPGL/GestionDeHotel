/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.lothel.rrhh.dao;
import pe.edu.pucp.lothel.rrhh.model.Persona;

/**
 *
 * @author Adrian Fujiki
 */
public interface CuentaDAO {
     Persona obtenerCuentaUserPass(String user,String password);
}
