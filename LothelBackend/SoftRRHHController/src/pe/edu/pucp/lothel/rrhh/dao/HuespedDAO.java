/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.lothel.rrhh.dao;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.lothel.rrhh.model.Huesped;


public interface HuespedDAO {
    int insertar(Huesped huesped);
    int modificar(Huesped huesped);
    int eliminar(int idHabitacion);
    ArrayList<Huesped> listarHuespedes();
    ArrayList<Huesped> listarHuespedXPeriodo(Date fechaInicio,Date fechaFin);
    
}

