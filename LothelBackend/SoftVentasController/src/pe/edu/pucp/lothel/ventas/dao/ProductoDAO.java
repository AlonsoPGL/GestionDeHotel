/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
=======
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.lothel.ventas.dao;

import java.util.ArrayList;
import pe.edu.pucp.lothel.ventas.model.Producto;

/**
 *
 * @author gumar
 */
public interface ProductoDAO {
    ArrayList<Producto> ListarPorNombre(String nombreBusqueda);
}
