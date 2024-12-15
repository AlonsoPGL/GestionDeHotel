/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.lothel.rrhh.dao;

import java.util.ArrayList;
import pe.edu.pucp.lothel.rrhh.model.PersonalDeLavanderia;

/**
 *
 * @author Adrian Fujiki
 */
public interface PersonalDeLavanderiaDAO {
    int insertar(PersonalDeLavanderia lavandero);
    int modificar(PersonalDeLavanderia lavandero);
    int eliminar(int idLavandero);
    ArrayList<PersonalDeLavanderia> listarLavanderos();
}
