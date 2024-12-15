/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.lothel.gestreserva.dao;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.lothel.gestreserva.model.Matrimonial;

/**
 *
 * @author Adrian Fujiki
 */
public interface MatrimonialDAO {
    int insertar(Matrimonial matrimonial);
    int modificar(Matrimonial matrimonial);
    int eliminar(int idHabitacion);
     ArrayList<Matrimonial> listarHabitacionesMatrimoniales();
     ArrayList<Matrimonial> listarHabitacionesMatrimonialXPeriodo(Date fechaINI,Date fechaFin);
}
