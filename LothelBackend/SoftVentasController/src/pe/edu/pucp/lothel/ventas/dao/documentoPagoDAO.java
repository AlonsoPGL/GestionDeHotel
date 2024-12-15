/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.lothel.ventas.dao;

import java.util.ArrayList;
import pe.edu.pucp.lothel.ventas.model.DocumentoDePago;

/**
 *
 * @author efeproceres
 */
public interface documentoPagoDAO {
   public int insertar(DocumentoDePago documentoPago);
   public int modificar(DocumentoDePago documentoPago);
   public int eliminar(int idDocumentoPago);
   public ArrayList<DocumentoDePago> listarDocumentos();
}
