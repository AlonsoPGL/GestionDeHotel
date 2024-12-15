/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.lothel.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.lothel.evento.dao.EspacioDAO;
import pe.edu.pucp.lothel.evento.dao.EventoDAO;
import pe.edu.pucp.lothel.evento.dao.ReservaEspacioDAO;
import pe.edu.pucp.lothel.evento.model.Espacio;
import pe.edu.pucp.lothel.evento.model.EstadoEvento;
import pe.edu.pucp.lothel.evento.model.Evento;
import pe.edu.pucp.lothel.evento.model.ReservaEspacio;
import pe.edu.pucp.lothel.evento.mysql.EspacioMYSQL;
import pe.edu.pucp.lothel.evento.mysql.EventoMYSQL;
import pe.edu.pucp.lothel.evento.mysql.ReservaEspacioMYSQL;
import pe.edu.pucp.lothel.ventas.dao.EmpresaProveedoraDAO;
import pe.edu.pucp.lothel.ventas.model.EmpresaProveedora;
import pe.edu.pucp.lothel.ventas.mysql.EmpresaProveedoraMYSQL;

/**
 *
 * @author Adrian Fujiki
 */
@WebService(serviceName = "EventosWS")
public class EventosWS {
    
    /**************************************************************************/
    /*****************************EmpresaProveedora*************************************/
    /**************************************************************************/
    
    private EmpresaProveedoraDAO daoEmpresaProveedora;
    
    @WebMethod(operationName = "listarEmpresasProveedoras")
    public ArrayList<EmpresaProveedora> listarEmpresasProveedoras() {
        ArrayList<EmpresaProveedora> empresas=new ArrayList<>();
        try{
            daoEmpresaProveedora=new EmpresaProveedoraMYSQL();
            empresas=daoEmpresaProveedora.listarEmpresas();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return empresas; 
    }
    
    @WebMethod(operationName = "registrarEmpresaProveedora")
    public int registrarEmpresaProveedora(@WebParam(name="empresa")EmpresaProveedora empresa) {
        int resultado=0;
        empresa.setActivo(true);
        
        try{
            daoEmpresaProveedora=new EmpresaProveedoraMYSQL();
            resultado=daoEmpresaProveedora.insertar(empresa);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    @WebMethod(operationName = "eliminarEmpresaProveedora")
    public int eliminarEmpresaProveedora(int idEmpresa) {
        int resultado=0;
        try{
            daoEmpresaProveedora=new EmpresaProveedoraMYSQL();
            resultado=daoEmpresaProveedora.eliminar(idEmpresa);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    @WebMethod(operationName = "modificarEmpresaProveedora")
    public int modificarEmpresaProveedora(@WebParam(name="empresa")EmpresaProveedora empresa) {
        int resultado=0;
        try{
            daoEmpresaProveedora=new EmpresaProveedoraMYSQL();
            resultado=daoEmpresaProveedora.modificar(empresa);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    
    
    
    /**************************************************************************/
    /*****************************Espacios*************************************/
    /**************************************************************************/
    private EspacioDAO daoEspacio;
    
    @WebMethod(operationName = "listarEspacios")
    public ArrayList<Espacio> listarEspacios() {
        ArrayList<Espacio> espacios=new ArrayList<>();
        try{
            daoEspacio=new EspacioMYSQL();
            espacios=daoEspacio.listarEspacios();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return espacios; 
    }
    
    @WebMethod(operationName = "registrarEspacio")
    public int registrarEspacio(@WebParam(name="espacio")Espacio espacio) {
        int resultado=0;
        espacio.setDisponibilidad(true);
        
        try{
            daoEspacio=new EspacioMYSQL();
            resultado=daoEspacio.insertar(espacio);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    @WebMethod(operationName = "eliminarEspacio")
    public int eliminarEspacio(int idEspacio) {
        int resultado=0;
        try{
            daoEspacio=new EspacioMYSQL();
            resultado=daoEspacio.eliminar(idEspacio);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    @WebMethod(operationName = "modificarEspacio")
    public int modificarEspacio(@WebParam(name="espacio")Espacio espacio) {
        int resultado=0;
        try{
            daoEspacio=new EspacioMYSQL();
            resultado=daoEspacio.modificar(espacio);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    @WebMethod(operationName = "listarEspaciosPorNombre")
    public ArrayList<Espacio> listarEspaciosPorNombre(String nombre) {
        ArrayList<Espacio> espacios=new ArrayList<>();
        try{
            daoEspacio=new EspacioMYSQL();
            espacios=daoEspacio.listarEspaciosPorNombre(nombre);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return espacios; 
    }
    /**************************************************************************/
    /**********************************ReservaEspacio*********************************/
    /**************************************************************************/
    
    private ReservaEspacioDAO daoResEspacio;
    
    @WebMethod(operationName = "listarHorasDisponibles")
    public ArrayList<Integer> listarHorasDisponibles(@WebParam(name = "idEspacio")int idEspacio, 
            @WebParam(name="fechaReserva")Date fechaReserva) {
        ArrayList<Integer> horasDisponibles=new ArrayList<>();
        try{
            daoResEspacio=new ReservaEspacioMYSQL();
            horasDisponibles=daoResEspacio.listarHorasDisponibles(idEspacio, fechaReserva);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return horasDisponibles; 
    }

    @WebMethod(operationName = "listarReservasXIdEvento")
    public ArrayList<ReservaEspacio> listarReservasXIdEvento(@WebParam(name = "idEspacio")int idEvento
  ) {
        ArrayList<ReservaEspacio> reservas=new ArrayList<>();
        try{
            daoResEspacio=new ReservaEspacioMYSQL();
            reservas=daoResEspacio.listarReservasXIdEvento(idEvento);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return reservas; 
    }
    /**************************************************************************/
    /**********************************Eventos*********************************/
    /**************************************************************************/
    
    private EventoDAO daoEvento;
    
    @WebMethod(operationName = "listarEventos")
    public ArrayList<Evento> listarEventos() {
        ArrayList<Evento> eventos=new ArrayList<>();
        try{
            daoEvento=new EventoMYSQL();
            eventos=daoEvento.listarEventos();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return eventos; 
    }
    
    @WebMethod(operationName = "registrarEvento")
    public int registrarEvento(@WebParam(name="evento")Evento evento) {
        int resultado=0;
        evento.setEstado(EstadoEvento.PROGRAMADO); //aqui modificar java eventoestado
        
        try{
            daoEvento=new EventoMYSQL();
            resultado=daoEvento.insertar(evento);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    @WebMethod(operationName = "eliminarEvento")
    public int eliminarEvento(int idEvento) {
        int resultado=0;
        try{
            daoEvento=new EventoMYSQL();
            resultado=daoEvento.eliminar(idEvento); //aqui seria estadoEvento a ELIMINADO
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    @WebMethod(operationName = "modificarEvento")
    public int modificarEvento(@WebParam(name="evento")Evento evento) {
        int resultado=0;
        try{
            daoEvento=new EventoMYSQL();
            resultado=daoEvento.modificar(evento);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    @WebMethod(operationName = "listarEventosPorNombre")
    public ArrayList<Evento> listarEventosPorNombre(String nombre) {
        ArrayList<Evento> eventos=new ArrayList<>();
        try{
            daoEvento=new EventoMYSQL();
            eventos=daoEvento.listarEventosPorNombre(nombre);
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return eventos; 
    }
}
