/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.lothel.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;

import pe.edu.pucp.lothel.ventas.dao.ProductoDAO;
import pe.edu.pucp.lothel.ventas.model.Producto;

import pe.edu.pucp.lothel.ventas.dao.AlimentoDAO;
import pe.edu.pucp.lothel.ventas.dao.BebidaDAO;
import pe.edu.pucp.lothel.ventas.dao.CuidadoPersonalDAO;
import pe.edu.pucp.lothel.ventas.dao.PedidoDAO;
import pe.edu.pucp.lothel.ventas.dao.ProductoDAO;
import pe.edu.pucp.lothel.ventas.dao.ServicioDeLavanderiaDAO;
import pe.edu.pucp.lothel.ventas.dao.ServicioDeMasajeDAO;
import pe.edu.pucp.lothel.ventas.dao.itemDAO;
import pe.edu.pucp.lothel.ventas.model.Alimento;
import pe.edu.pucp.lothel.ventas.model.Bebida;
import pe.edu.pucp.lothel.ventas.model.CategoriaAlimento;
import pe.edu.pucp.lothel.ventas.model.CuidadoPersonal;
import pe.edu.pucp.lothel.ventas.model.Item;
import pe.edu.pucp.lothel.ventas.model.Pedido;
import pe.edu.pucp.lothel.ventas.model.Producto;
import pe.edu.pucp.lothel.ventas.model.ServicioDeLavanderia;
import pe.edu.pucp.lothel.ventas.model.ServicioDeMasaje;
import pe.edu.pucp.lothel.ventas.mysql.AlimentoMYSQL;
import pe.edu.pucp.lothel.ventas.mysql.BebidaMYSQL;
import pe.edu.pucp.lothel.ventas.mysql.CuidadoPersonalMYSQL;
import pe.edu.pucp.lothel.ventas.mysql.PedidoMYSQL;

import pe.edu.pucp.lothel.ventas.mysql.ProductoMYSQL;
import pe.edu.pucp.lothel.ventas.mysql.itemMYSQL;

import pe.edu.pucp.lothel.ventas.mysql.ServicioDeLavanderiaMYSQL;
import pe.edu.pucp.lothel.ventas.mysql.ServicioDeMasajeMYSQL;

/**
 *
 * @author Adrian Fujiki
 */
@WebService(serviceName = "VentasWS")
public class VentasWS {


    /**
     * This is a sample web service operation
     */

    private ProductoDAO daoProducto;

    /**************************************************************************/
    /*****************************Alimentos****************************/
    /**************************************************************************/
    
    private AlimentoDAO daoAlimento;
    
    @WebMethod(operationName = "listarAlimentosPorNombre")
    public ArrayList<Alimento> listarAlimentosPorNombre(String nombre_buscado) {
        ArrayList<Alimento> alimentos=new ArrayList<>();
        
        try{
            daoAlimento=new AlimentoMYSQL();
            alimentos=daoAlimento.listarAlimentosPorNombre(nombre_buscado);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return alimentos; 
    }
    
    @WebMethod(operationName = "listarAlimentos")
    public ArrayList<Alimento> listarAlimentos() {
        ArrayList<Alimento> alimentos=new ArrayList<>();
        
        try{
            daoAlimento=new AlimentoMYSQL();
            alimentos=daoAlimento.listarAlimentos();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return alimentos; 
    }
    
    @WebMethod(operationName = "registrarAlimento")
    public int registrarAlimento(@WebParam(name="alimento")Alimento alimento) {
        int resultado=0;
        alimento.setDisponibilidad(true);
        //alimento.setCategoria(CategoriaAlimento.PLATO);
        try{
            daoAlimento=new AlimentoMYSQL();
            resultado=daoAlimento.insertar(alimento);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    @WebMethod(operationName = "eliminarAlimento")
    public int eliminarAlimento(int idAlimento) {
        int resultado=0;
        try{
            daoAlimento=new AlimentoMYSQL();
            resultado=daoAlimento.eliminar(idAlimento);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    @WebMethod(operationName = "modificarAlimento")
    public int modificarAlimento(@WebParam(name="alimento")Alimento alimento) {
        int resultado=0;
        try{
            daoAlimento=new AlimentoMYSQL();
            resultado=daoAlimento.modificar(alimento);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    /**************************************************************************/
    /**********************************Bebidas*********************************/
    /**************************************************************************/
    
    private BebidaDAO daoBebida;
    
    @WebMethod(operationName = "listarBebidasPorNombre")
    public ArrayList<Bebida> listarBebidasPorNombre(String nombre_buscado) {
        ArrayList<Bebida> bebidas=new ArrayList<>();
        
        try{
            daoBebida=new BebidaMYSQL();
            bebidas=daoBebida.listarBebidasPorNombre(nombre_buscado);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return bebidas; 
    }
    
    @WebMethod(operationName = "listarBebidas")
    public ArrayList<Bebida> listarBebidas() {
        ArrayList<Bebida> bebidas=new ArrayList<>();
        try{
            daoBebida=new BebidaMYSQL();
            bebidas=daoBebida.listarBebidas();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return bebidas; 
    }
    
    @WebMethod(operationName = "registrarBebida")
    public int registrarBebida(@WebParam(name="bebida")Bebida bebida) {
        int resultado=0;
        bebida.setDisponibilidad(true);
        
        try{
            daoBebida=new BebidaMYSQL();
            resultado=daoBebida.insertar(bebida);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    @WebMethod(operationName = "eliminarBebida")
    public int eliminarBebida(int idBebida) {
        int resultado=0;
        try{
            daoBebida=new BebidaMYSQL();
            resultado=daoBebida.eliminar(idBebida);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    @WebMethod(operationName = "modificarBebida")
    public int modificarBebida(@WebParam(name="bebida")Bebida bebida) {
        int resultado=0;
        try{
            daoBebida=new BebidaMYSQL();
            resultado=daoBebida.modificar(bebida);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    /**************************************************************************/
    /******************* Cuidado Personal *****************************/
    /**************************************************************************/
    private CuidadoPersonalDAO daoCuidado;

    @WebMethod(operationName = "listarCuidadoPersonalPorNombre")
    public ArrayList<CuidadoPersonal> listarCuidadoPersonalPorNombre(String nombre_buscado) {
        ArrayList<CuidadoPersonal> cuidados=new ArrayList<>();
        try{
            daoCuidado=new CuidadoPersonalMYSQL();
            cuidados=daoCuidado.listarCuidadosPersonalesPorNombre(nombre_buscado);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return cuidados; 
    }
    
    @WebMethod(operationName = "listarCuidadoPersonal")
    public ArrayList<CuidadoPersonal> listarCuidadoPersonal() {
        ArrayList<CuidadoPersonal> cuidados=new ArrayList<>();
        try{
            daoCuidado=new CuidadoPersonalMYSQL();
            cuidados=daoCuidado.listarCuidadosPersonales();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return cuidados; 
    }
    
    @WebMethod(operationName = "registrarCuidadoPersonal")
    public int registrarCuidadoPersonal(@WebParam(name="cuidadoPersonal")CuidadoPersonal cuidadoPersonal) {
        int resultado=0;
        cuidadoPersonal.setDisponibilidad(true);
        
        try{
            daoCuidado=new CuidadoPersonalMYSQL();
            resultado=daoCuidado.insertar(cuidadoPersonal);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    @WebMethod(operationName = "eliminarCuidadoPersonal")
    public int eliminarCuidadoPersonal(int idCuidadoPersonal) {
        int resultado=0;
        try{
            daoCuidado=new CuidadoPersonalMYSQL();
            resultado=daoCuidado.eliminar(idCuidadoPersonal);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    @WebMethod(operationName = "modificarCuidadoPersonal")
    public int modificarCuidadoPersonal(@WebParam(name="cuidadoPersonal")CuidadoPersonal cuidadoPersonal) {
        int resultado=0;
        try{
            daoCuidado=new CuidadoPersonalMYSQL();
            resultado=daoCuidado.modificar(cuidadoPersonal);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    
    /**************************************************************************/
    /*******************Listar Producto Por Nombre*****************************/
    /**************************************************************************/
    
    @WebMethod(operationName = "listarPorNombre")
    public ArrayList<Producto> listarPorNombre(@WebParam(name = "nombreBusqueda") String nombreBusqueda) {
        ArrayList<Producto> productos=new ArrayList<>();
        try{
            daoProducto=new ProductoMYSQL();
            productos=daoProducto.ListarPorNombre(nombreBusqueda);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return productos; 
    }
    
    
    /**************************************************************************/
    /*********************************Pedido***********************************/
    /**************************************************************************/
    
    private PedidoDAO daoPedido;
    
    @WebMethod(operationName = "registarPedido")
    public int registrarPedido(@WebParam(name = "pedido")Pedido pedido) {
        int resultado = 0;
        try {
            daoPedido = new PedidoMYSQL();
            resultado = daoPedido.insertar(pedido);
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "registarServicio")
    public int registarServicio(@WebParam(name = "pedido")Pedido pedido) {
        int resultado = 0;
        try {
            daoPedido = new PedidoMYSQL();
            resultado = daoPedido.insertarServicio(pedido);
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
 
    @WebMethod(operationName = "listarPedidosPorHuesped")
    public ArrayList<Pedido> listarPedidosPorHuesped(@WebParam(name = "idHuesped") int idHuesped) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try {
            daoPedido = new PedidoMYSQL();
            pedidos = daoPedido.listarPedidosPorCliente(idHuesped);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return pedidos;
 
    }
    
    /**************************************************************************/
    /**********************************Item************************************/
    /**************************************************************************/

    private itemDAO daoItem;
    @WebMethod(operationName = "ingresarCalificacion")
    public int ingresarCalificacion(@WebParam(name="item")Item item, @WebParam(name="puntaje")int puntaje) {
        int resultado = 0;
        try {
            daoItem=new itemMYSQL();
            resultado=daoItem.ingresarCalificacion(item, puntaje);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    /**************************************************************************/
    /**********************************Servicios************************************/
    /**************************************************************************/
    
    private ServicioDeLavanderiaDAO daoServicioLavanderia;
    @WebMethod(operationName = "insertarServicioDeLavanderia")
    public int insertarServicioDeLavanderia(@WebParam(name="item")ServicioDeLavanderia servicioDeLavanderia) {
        int resultado = 0;
        try {
            daoServicioLavanderia = new ServicioDeLavanderiaMYSQL();
            resultado = daoServicioLavanderia.insertar(servicioDeLavanderia);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    private ServicioDeMasajeDAO daoServicioMasaje;
    @WebMethod(operationName = "insertarServicioDeMasaje")
    public int insertarServicioDeMasaje(@WebParam(name="item")ServicioDeMasaje servicioDeMasaje) {
        int resultado = 0;
        try {
            daoServicioMasaje = new ServicioDeMasajeMYSQL();
            resultado = daoServicioMasaje.insertar(servicioDeMasaje);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return resultado;
    }


}
