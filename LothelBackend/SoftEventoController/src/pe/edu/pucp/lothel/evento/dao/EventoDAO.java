/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.lothel.evento.dao;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.lothel.evento.model.Evento;

/**
 *
 * @author efeproceres
 */
public interface EventoDAO {
    int insertar(Evento evento);
    int modificar(Evento evento);
    int eliminar(int idEvento);
    ArrayList<Evento> listarEventos();
    ArrayList<Evento> listarEventosPorNombre(String nombre);
}
