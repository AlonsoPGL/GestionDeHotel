/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.lothel.rrhh.dao;

import java.util.ArrayList;
import pe.edu.pucp.lothel.rrhh.model.Recepcionista;

/**
 *
 * @author DELL
 */
public interface RecepcionistaDAO {
    int insertar(Recepcionista recepcionista);
    int modificar(Recepcionista recepcionista);
    int eliminar(int idRecepcionista);
    ArrayList<Recepcionista> listarRecepcionistas();
}
