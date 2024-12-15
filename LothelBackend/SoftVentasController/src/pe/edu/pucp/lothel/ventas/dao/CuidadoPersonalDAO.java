/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.lothel.ventas.dao;

import java.util.ArrayList;
import pe.edu.pucp.lothel.ventas.model.CuidadoPersonal;

/**
 *
 */
public interface CuidadoPersonalDAO {
    public ArrayList<CuidadoPersonal> listarCuidadosPersonalesPorNombre(String nombre_buscado);
   public int insertar(CuidadoPersonal cuidadoPersonal);
   public int modificar(CuidadoPersonal cuidadoPersonal);
   public int eliminar(int idCuidadoPersonal);
   public ArrayList<CuidadoPersonal> listarCuidadosPersonales();
}
