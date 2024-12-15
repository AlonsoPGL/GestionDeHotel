/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.lothel.ventas.dao;

import java.util.ArrayList;
import pe.edu.pucp.lothel.ventas.model.Alimento;
/**
 *
 * @author Melanie
 */
public interface AlimentoDAO {
    public ArrayList<Alimento> listarAlimentosPorNombre(String nombre_buscado);
   public int insertar(Alimento alimento);
   public int modificar(Alimento alimento);
   public int eliminar(int idAlimento);
   public ArrayList<Alimento> listarAlimentos();
}
