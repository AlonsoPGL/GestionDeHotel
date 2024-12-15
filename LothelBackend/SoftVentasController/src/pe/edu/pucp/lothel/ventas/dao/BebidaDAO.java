/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.lothel.ventas.dao;


import java.util.ArrayList;
import pe.edu.pucp.lothel.ventas.model.Bebida;
/**
 *
 * @author Melanie
 */
public interface BebidaDAO {
    public ArrayList<Bebida> listarBebidasPorNombre(String nombre_buscado);
   public int insertar(Bebida bebida);
   public int modificar(Bebida bebida);
   public int eliminar(int idBebida);
   public ArrayList<Bebida> listarBebidas();
}
