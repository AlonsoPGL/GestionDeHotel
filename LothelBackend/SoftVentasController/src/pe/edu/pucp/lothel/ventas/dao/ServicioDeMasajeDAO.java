/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.lothel.ventas.dao;

import java.util.ArrayList;
import pe.edu.pucp.lothel.ventas.model.ServicioDeMasaje;


/**
 *
 * @author efeproceres
 */
public interface ServicioDeMasajeDAO {
    public int insertar(ServicioDeMasaje servicio);
    public int modificar(ServicioDeMasaje servicio);
    public int eliminar(int idDocumentoPago);
    public ArrayList<ServicioDeMasaje> listarServicios();
}
