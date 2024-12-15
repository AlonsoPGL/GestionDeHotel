/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.lothel.gestreserva.dao;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.lothel.gestreserva.model.Simple;


/**
 *
 * @author marcelo
 */
public interface SimpleDAO {
    int insertar(Simple simple);
    int modificar(Simple simple);
    int eliminar(int idHabitacion);
     ArrayList<Simple> listarHabitacionesSimples();
     ArrayList<Simple> listarHabitacionesSimplesXPeriodo(Date fechaINI,Date fechaFin);
}
