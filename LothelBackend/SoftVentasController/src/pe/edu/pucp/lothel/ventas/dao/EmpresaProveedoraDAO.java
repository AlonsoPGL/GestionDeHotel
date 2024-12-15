/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.lothel.ventas.dao;

import java.util.ArrayList;
import pe.edu.pucp.lothel.ventas.model.EmpresaProveedora;

/**
 *
 * @author gumar
 */
public interface EmpresaProveedoraDAO {
    public int insertar(EmpresaProveedora empresaProveedora);
    public int modificar(EmpresaProveedora empresaProveedora);
    public int eliminar(int idEmpresa);
    public ArrayList<EmpresaProveedora> listarEmpresas();
    
}
