/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.lothel.rrhh.dao;

import java.util.ArrayList;
import pe.edu.pucp.lothel.rrhh.model.Administrador;

/**
 *
 * @author DELL
 */
public interface AdministradorDAO {
    int insertar(Administrador administrador);
    int modificar(Administrador administrador);
    int eliminar(int idAdministrador);
    ArrayList<Administrador> listarAdministradores();
}
