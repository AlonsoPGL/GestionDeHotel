/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.lothel.gestreserva.dao;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.lothel.gestreserva.model.Familiar;

/**
 *
 * @author Adrian Fujiki
 */
public interface FamiliarDAO {
    int insertar(Familiar familiar);
    int modificar(Familiar familiar);
    int eliminar(int idFamiliar);
     ArrayList<Familiar> listarHabitacionesFamiliares();
     ArrayList<Familiar> listarHabitacionesFamiliarXPeriodo(Date fechaINI,Date fechaFin);
}
