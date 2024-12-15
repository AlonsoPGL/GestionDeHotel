/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.lothel.rrhh.dao;

import java.util.ArrayList;
import pe.edu.pucp.lothel.rrhh.model.PersonalDeMasajes;

/**
 *
 * @author Adrian Fujiki
 */
public interface PersonalDeMasajesDAO {
    int insertar(PersonalDeMasajes masajista);
    int modificar(PersonalDeMasajes masajista);
    int eliminar(int idMasajista);
    ArrayList<PersonalDeMasajes> listarMasajistas();
}
