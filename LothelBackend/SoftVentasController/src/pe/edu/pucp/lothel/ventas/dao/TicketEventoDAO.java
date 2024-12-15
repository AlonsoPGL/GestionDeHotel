/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.lothel.ventas.dao;

import java.util.ArrayList;
import pe.edu.pucp.lothel.ventas.model.TicketEvento;

/**
 *
 * @author gumar
 */
public interface TicketEventoDAO {
    public int insertar(TicketEvento ticket);
    public int modificar(TicketEvento ticket);
    public int eliminar(int numeroTicket);
    public ArrayList<TicketEvento> listarTickets();
}
