/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.lothel.ventas.dao;

import java.util.ArrayList;
import pe.edu.pucp.lothel.ventas.model.ServicioDeLavanderia;


/**
 *
 * @author efeproceres
 */
public interface ServicioDeLavanderiaDAO {
    public int insertar(ServicioDeLavanderia servicio);
    public int modificar(ServicioDeLavanderia servicio);
    public int eliminar(int idServicioLavanderia);
    public ArrayList<ServicioDeLavanderia> listarServicios();
}
