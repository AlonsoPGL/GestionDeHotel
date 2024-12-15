/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.lothel.ventas.dao;

import java.util.ArrayList;
import pe.edu.pucp.lothel.ventas.model.Pedido;

/**
 *
 * @author efeproceres
 */
public interface PedidoDAO {
   public int insertar(Pedido pedido);
   public int modificar(Pedido pedido);
   public int eliminar(int pedido);
   public ArrayList<Pedido> listarPedidos();
   public ArrayList<Pedido> listarPedidosMasajista();
   public ArrayList<Pedido> listarPedidosLavanderia();
   public ArrayList<Pedido> listarPedidosPorCliente(int idHuesped);
   public int insertarServicio(Pedido pedido);
}
