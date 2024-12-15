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
import pe.edu.pucp.lothel.gestreserva.dao.FamiliarDAO;
import pe.edu.pucp.lothel.gestreserva.dao.HabitacionDAO;
import pe.edu.pucp.lothel.gestreserva.dao.MatrimonialDAO;
import pe.edu.pucp.lothel.gestreserva.dao.ReservaHabitacionDAO;
import pe.edu.pucp.lothel.gestreserva.dao.SimpleDAO;
import pe.edu.pucp.lothel.gestreserva.model.Familiar;
import pe.edu.pucp.lothel.gestreserva.model.Habitacion;
import pe.edu.pucp.lothel.gestreserva.model.Matrimonial;
import pe.edu.pucp.lothel.gestreserva.model.ReservaHabitacion;
import pe.edu.pucp.lothel.gestreserva.mysql.FamiliarMYSQL;
import pe.edu.pucp.lothel.gestreserva.model.Simple;
import pe.edu.pucp.lothel.gestreserva.mysql.HabitacionMYSQL;
import pe.edu.pucp.lothel.gestreserva.mysql.MatrimonialMYSQL;
import pe.edu.pucp.lothel.gestreserva.mysql.ReservaHabitacionMYSQL;
import pe.edu.pucp.lothel.gestreserva.mysql.SimpleMYSQL;
import pe.edu.pucp.lothel.rrhh.dao.HuespedDAO;
import pe.edu.pucp.lothel.rrhh.model.Huesped;
import pe.edu.pucp.lothel.rrhh.mysql.HuespedMYSQL;



/**
 *
 * @author Adrian Fujiki
 */
@WebService(serviceName = "ReservasWS")
public class ReservasWS {
    private FamiliarDAO daofamiliar;
    private MatrimonialDAO  daomatrimonial;
    private SimpleDAO daoSimple;
    private ReservaHabitacionDAO daoReservaHabitacion;
    private HabitacionDAO daoHabitacion;
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "ListarTodasHabitaciones")
    public ArrayList<Habitacion> ListarTodasHabitaciones() {
        ArrayList<Habitacion> habitaciones=new ArrayList<>();
        try{
            daoHabitacion=new HabitacionMYSQL();
            habitaciones=daoHabitacion.ListarTodasHabitaciones();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return habitaciones;
        
    }
    
    
    @WebMethod(operationName = "ListarFamiliar")
    public ArrayList<Familiar> ListarFamiliar() {
        ArrayList<Familiar> familiares=new ArrayList<>();
        try{
            daofamiliar=new FamiliarMYSQL();
            familiares=daofamiliar.listarHabitacionesFamiliares();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return familiares;
        
    }
    
    @WebMethod(operationName = "ListarMatrimoniales")
    public ArrayList<Matrimonial> ListarMatrimoniales() {
        ArrayList<Matrimonial> matrimoniales=new ArrayList<>();
        try{
            daomatrimonial=new MatrimonialMYSQL();
            matrimoniales=daomatrimonial.listarHabitacionesMatrimoniales();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return matrimoniales;    
    }
    
    
    @WebMethod(operationName = "ListarSimples")
    public ArrayList<Simple> ListarSimples() {
        ArrayList<Simple> simples=new ArrayList<>();
        try{
            daoSimple=new SimpleMYSQL();
            simples=daoSimple.listarHabitacionesSimples();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return simples;    
    }
    
    private ReservaHabitacionDAO daoReserva;

    @WebMethod(operationName = "insertarReservaHabitacion")
    public int insertarReservaHabitacion(@WebParam(name="reserva")ReservaHabitacion reserva) {
        int resultado=0;
        try{
            daoReserva=new ReservaHabitacionMYSQL();
            resultado=daoReserva.insertar(reserva);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }  
    
    @WebMethod(operationName = "ListarReservaXIDHuesped")
    public ArrayList<ReservaHabitacion> ListarReservaXIDHuesped(@WebParam(name="idHuesped")int idHuesped) {
        ArrayList<ReservaHabitacion> reservas=new ArrayList<>();
        try{
            daoReservaHabitacion=new ReservaHabitacionMYSQL();
            reservas=daoReservaHabitacion.listarXIDHuesped(idHuesped);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return reservas;    
    }
    
    @WebMethod(operationName = "eliminarReservaHabitacion")
    public int eliminarReservaHabitacion(@WebParam(name="idReserva")int idReserva) {
        int resultado=0;
        try{
            daoReserva=new ReservaHabitacionMYSQL();
            resultado=daoReserva.eliminar(idReserva);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }  
    
    @WebMethod(operationName = "listarHabitacionXidHuesped")
    public Habitacion listarHabitacionXidHuesped(@WebParam(name="idReserva")int idReserva,
            @WebParam(name="idHabitacion")int idHabitacion,@WebParam(name="idHuesped")int idHuesped) {
        Habitacion habitacion=new Habitacion();
        try{
            daoReserva=new ReservaHabitacionMYSQL();
            habitacion=daoReserva.listarHabitacionxHuesped(idReserva,idHabitacion,idHuesped);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return habitacion; 
    } 
    
    
    // PARTE MARCELO:
    
    @WebMethod(operationName= "listarReservasEnCurso")
    public ArrayList<ReservaHabitacion> listarReservasEnCurso() {
        ArrayList<ReservaHabitacion> reservas = new ArrayList<>();
        try{
            daoReserva = new ReservaHabitacionMYSQL();
            reservas = daoReserva.listarReservasEnCurso();
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return reservas;
    }

    //parte del reporte de habitaciones
    //private MatrimonialDAO  daomatrimonial;
    //private SimpleDAO daoSimple;
    //private ReservaHabitacionDAO daoReservaHabitacion;
    @WebMethod(operationName= "ListarSimplePorFechaYTipo")
    public ArrayList<Simple> ListarSimplePorFechaYTipo(@WebParam(name="fechaDesde")Date fechaIni,@WebParam(name="fechaHasta")Date FechaFin) {
        
        ArrayList<Simple> simples = new ArrayList<>();
        
            try{
                daoSimple = new SimpleMYSQL();
                simples = daoSimple.listarHabitacionesSimplesXPeriodo(fechaIni, FechaFin);
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }   
        
        return simples;
    }
    
    @WebMethod(operationName= "ListarFamiliarPorFechaYTipo")
    public ArrayList<Familiar> ListarFamiliarPorFechaYTipo(@WebParam(name="fechaDesde")Date fechaIni,@WebParam(name="fechaHasta")Date FechaFin) {
        
        ArrayList<Familiar> familiares = new ArrayList<>();
        
            try{
                daofamiliar = new FamiliarMYSQL();
                familiares = daofamiliar.listarHabitacionesFamiliarXPeriodo(fechaIni, FechaFin);
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }   
        
        return familiares;
    }
    
    @WebMethod(operationName= "ListarMatrimonialPorFechaYTipo")
    public ArrayList<Matrimonial> ListarMatrimonialPorFechaYTipo(@WebParam(name="fechaDesde")Date fechaIni,@WebParam(name="fechaHasta")Date FechaFin) {
        
        ArrayList<Matrimonial> matrimoniales = new ArrayList<>();
        
            try{
                daomatrimonial = new MatrimonialMYSQL();
                matrimoniales = daomatrimonial.listarHabitacionesMatrimonialXPeriodo(fechaIni, FechaFin);
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }   
        
        return matrimoniales;
    }
    
    

    
    
    
    @WebMethod(operationName= "ListarHabitacionesInicio")
    public ArrayList<Habitacion> ListarHabitacionesInicio() {
        
        ArrayList<Habitacion> habitaciones=new ArrayList<>();
        try{
            daoHabitacion=new HabitacionMYSQL();
            habitaciones=daoHabitacion.ListarTodasHabitaciones();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return habitaciones;
    }
    
    
    
    
    
}



