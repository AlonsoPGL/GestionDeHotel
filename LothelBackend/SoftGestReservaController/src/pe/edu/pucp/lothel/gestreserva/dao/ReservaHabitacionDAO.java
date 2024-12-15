/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.lothel.gestreserva.dao;

import java.util.ArrayList;
import pe.edu.pucp.lothel.gestreserva.model.Habitacion;
import pe.edu.pucp.lothel.gestreserva.model.ReservaHabitacion;

/**
 *
 * @author efeproceres
 */
public interface ReservaHabitacionDAO {
    int insertar(ReservaHabitacion reserva);
    int modificar(ReservaHabitacion reserva);
    int eliminar(int idReserva);
    ArrayList<ReservaHabitacion>listarReserva();
    ArrayList<ReservaHabitacion>listarXIDHuesped(int idHuesped);
    Habitacion listarHabitacionxHuesped(int idReserva,int idHabitacion,int idHuesped);
    ArrayList<ReservaHabitacion> listarReservasEnCurso();
}
