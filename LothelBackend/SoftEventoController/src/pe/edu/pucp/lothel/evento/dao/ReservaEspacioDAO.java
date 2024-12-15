/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.lothel.evento.dao;

import java.util.ArrayList;
import pe.edu.pucp.lothel.evento.model.ReservaEspacio;
import java.util.Date;

/**
 *
 * @author efeproceres
 */
public interface ReservaEspacioDAO {
    int insertar(ReservaEspacio reservaEspacio);
    int modificar(ReservaEspacio reserva);
    int eliminar(int idReserva);
    ArrayList<ReservaEspacio> listarReservas();
    ArrayList<ReservaEspacio> listarReservasXIdEvento(int idEvento);
    ArrayList<Integer> listarHorasDisponibles(int idEspacio, Date fechaReserva);
}
