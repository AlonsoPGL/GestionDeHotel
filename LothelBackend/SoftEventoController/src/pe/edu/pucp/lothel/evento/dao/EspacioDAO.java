/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.lothel.evento.dao;

import java.util.ArrayList;
import pe.edu.pucp.lothel.evento.model.Espacio;

/**
 *
 * @author efeproceres
 */
public interface EspacioDAO {
    int insertar(Espacio espacio);
    int modificar(Espacio espacio);
    int eliminar(int idEspacio);
    ArrayList<Espacio> listarEspacios();
    ArrayList<Espacio> listarEspaciosPorNombre(String nombre);
}

