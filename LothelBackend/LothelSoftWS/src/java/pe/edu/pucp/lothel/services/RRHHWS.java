/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.lothel.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.awt.Image;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JRException;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import pe.edu.pucp.lothel.manager.DBManager;
import pe.edu.pucp.lothel.rrhh.dao.CuentaDAO;
import pe.edu.pucp.lothel.rrhh.dao.HuespedDAO;
import pe.edu.pucp.lothel.rrhh.dao.PersonalDeLavanderiaDAO;
import pe.edu.pucp.lothel.rrhh.dao.PersonalDeMasajesDAO;
import pe.edu.pucp.lothel.rrhh.dao.RecepcionistaDAO;
import pe.edu.pucp.lothel.rrhh.model.Huesped;
import pe.edu.pucp.lothel.rrhh.model.Persona;
import pe.edu.pucp.lothel.rrhh.model.PersonalDeLavanderia;
import pe.edu.pucp.lothel.rrhh.model.PersonalDeMasajes;
import pe.edu.pucp.lothel.rrhh.model.TipoTurno;
import pe.edu.pucp.lothel.rrhh.mysql.CuentaMYSQL;
import pe.edu.pucp.lothel.rrhh.mysql.HuespedMYSQL;
import pe.edu.pucp.lothel.rrhh.mysql.PersonalDeLavanderiaMYSQL;
import pe.edu.pucp.lothel.rrhh.mysql.PersonalDeMasajesMYSQL;
import pe.edu.pucp.lothel.ventas.dao.PedidoDAO;
import pe.edu.pucp.lothel.ventas.dao.ServicioDAO;
import pe.edu.pucp.lothel.ventas.dao.ServicioDeLavanderiaDAO;
import pe.edu.pucp.lothel.ventas.model.Item;
import pe.edu.pucp.lothel.ventas.model.Pedido;
import pe.edu.pucp.lothel.ventas.model.Servicio;
import pe.edu.pucp.lothel.ventas.model.ServicioDeLavanderia;
import pe.edu.pucp.lothel.ventas.model.ServicioDeMasaje;
import pe.edu.pucp.lothel.ventas.mysql.PedidoMYSQL;
import pe.edu.pucp.lothel.ventas.mysql.ServicioMYSQL;
import pe.edu.pucp.lothel.rrhh.model.Recepcionista;
import pe.edu.pucp.lothel.rrhh.mysql.RecepcionistaMYSQL;
import pe.edu.pucp.lothel.servlet.Reporte;
import pe.edu.pucp.lothel.ventas.model.EstadoServicio;

/**
 *
 * @author Adrian Fujiki
 */
@WebService(serviceName = "RRHHWS")
public class RRHHWS {

    /**
     * This is a sample web service operation
     */
    
     /**************************************************************************/
    /*****************************Huesped****************************/
    /**************************************************************************/

    private HuespedDAO daoHuesped;

    @WebMethod(operationName = "registrarHuesped")
    public int registrarHuesped(@WebParam(name="huesped")Huesped huesped) {
        int resultado=0;
        try{
            daoHuesped=new HuespedMYSQL();
            resultado=daoHuesped.insertar(huesped);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }  
    
    @WebMethod(operationName = "listarHuespedesXPeriodo")
    public ArrayList<Huesped> listarHuespedesXPeriodo(@WebParam(name="fechaInicio")Date fechaInicio,
            @WebParam(name="fechaFin")Date fechaFin) {
        ArrayList<Huesped> huespedes=new ArrayList<>();
        try{
            daoHuesped=new HuespedMYSQL();
            huespedes=daoHuesped.listarHuespedXPeriodo(fechaInicio, fechaFin);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return huespedes; 
    }
    
    @WebMethod(operationName = "modificarHuesped")
    public int modificarHuesped(@WebParam(name="huesped")Huesped huesped) {
        int resultado=0;
        try{
            daoHuesped=new HuespedMYSQL();
            resultado=daoHuesped.modificar(huesped);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    //Daos para obterner persona segun el usuario y contraseña
    
    /**************************************************************************/
    /*****************************Cuenta****************************/
    /**************************************************************************/
    
    private CuentaDAO daoCuenta;
    
    private Persona persona;
    @WebMethod(operationName = "obtenerCuentaUserPass")
    public Persona obtenerCuentaUserPass(String usuario,String contrasena) {
        try{
            daoCuenta=new CuentaMYSQL();
            persona=daoCuenta.obtenerCuentaUserPass(usuario,contrasena);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return persona; 
    }
    
    
    
    /**************************************************************************/
    /*****************************Lavandero****************************/
    /**************************************************************************/
    
    private PersonalDeLavanderiaDAO daoLavandero;
    
    @WebMethod(operationName = "listarLavanderos")
    public ArrayList<PersonalDeLavanderia> listarLavanderos() {
        ArrayList<PersonalDeLavanderia> lavanderos=new ArrayList<>();
        try{
            daoLavandero=new PersonalDeLavanderiaMYSQL();
            lavanderos=daoLavandero.listarLavanderos();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return lavanderos; 
    }
    
    @WebMethod(operationName = "registrarLavandero")
    public int registrarLavandero(@WebParam(name="lavandero")PersonalDeLavanderia lavandero) throws ParseException {
        int resultado=0;
        lavandero.setActivo(true);
        lavandero.setEstado(true);
        
            
        try{
            daoLavandero=new PersonalDeLavanderiaMYSQL();
            resultado=daoLavandero.insertar(lavandero);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    @WebMethod(operationName = "eliminarLavandero")
    public int eliminarLavandero(int idLavandero) {
        int resultado=0;
        try{
            daoLavandero=new PersonalDeLavanderiaMYSQL();
            resultado=daoLavandero.eliminar(idLavandero);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    @WebMethod(operationName = "modificarLavandero")
    public int modificarLavandero(@WebParam(name="lavandero")PersonalDeLavanderia lavandero) {
        int resultado=0;
        try{
            daoLavandero=new PersonalDeLavanderiaMYSQL();
            resultado=daoLavandero.modificar(lavandero);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    
 
    /**************************************************************************/
    /*****************************Masajista****************************/
    /**************************************************************************/
    private PersonalDeMasajesDAO daoMasajista;
    
    @WebMethod(operationName = "listarMasajistas")
    public ArrayList<PersonalDeMasajes> listarMasajistas() {
        ArrayList<PersonalDeMasajes> masajistas=new ArrayList<>();
        try{
            daoMasajista=new PersonalDeMasajesMYSQL();
            masajistas=daoMasajista.listarMasajistas();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return masajistas; 
    }
    
    @WebMethod(operationName = "registrarMasajista")
    public int registrarMasajista(@WebParam(name="masajista")PersonalDeMasajes masajista) throws ParseException {
        int resultado=0;
        masajista.setActivo(true);
        masajista.setEstado(true);
        
            
        try{
            daoMasajista=new PersonalDeMasajesMYSQL();
            resultado=daoMasajista.insertar(masajista);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    @WebMethod(operationName = "eliminarMasajista")
    public int eliminarMasajista(int idMasajista) {
        int resultado=0;
        try{
            daoMasajista=new PersonalDeMasajesMYSQL();
            resultado=daoMasajista.eliminar(idMasajista);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    @WebMethod(operationName = "modificarMasajista")
    public int modificarMasajista(@WebParam(name="masajista")PersonalDeMasajes masajista) {
        int resultado=0;
        try{
            daoMasajista=new PersonalDeMasajesMYSQL();
            resultado=daoMasajista.modificar(masajista);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    /**************************************************************************/
    /*****************************Recepcionista****************************/
    /**************************************************************************/
    private RecepcionistaDAO daoRecepcionista;
    
    @WebMethod(operationName = "listarRecepcionistas")
    public ArrayList<Recepcionista> listarRecepcionistas() {
        ArrayList<Recepcionista> recepcionistas=new ArrayList<>();
        try{
            daoRecepcionista=new RecepcionistaMYSQL();
            recepcionistas=daoRecepcionista.listarRecepcionistas();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return recepcionistas; 
    }
    
    @WebMethod(operationName = "registrarRecepcionista")
    public int registrarRecepcionista(@WebParam(name="recepcionista")Recepcionista recepcionista) throws ParseException {
        int resultado=0;
        recepcionista.setActivo(true);
        recepcionista.setEstado(true);
        
            
        try{
            daoRecepcionista=new RecepcionistaMYSQL();
            resultado=daoRecepcionista.insertar(recepcionista);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    @WebMethod(operationName = "eliminarRecepcionista")
    public int eliminarRecepcionista(int idRecepcionista) {
        int resultado=0;
        try{
            daoRecepcionista=new RecepcionistaMYSQL();
            resultado=daoRecepcionista.eliminar(idRecepcionista);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    @WebMethod(operationName = "modificarRecepcionista")
    public int modificarRecepcionista(@WebParam(name="recepcionista")Recepcionista recepcionista) {
        int resultado=0;
        try{
            daoRecepcionista=new RecepcionistaMYSQL();
            resultado=daoRecepcionista.modificar(recepcionista);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    //codigo para el masajista 
    @WebMethod(operationName = "ListarServiciosMasajista")
    public ArrayList<Pedido> ListarServiciosMasajista() {
        
        ArrayList<Pedido> pedidos=new ArrayList<>();
        ArrayList<Pedido> pedidosMasaje=new ArrayList<>();
        try{
            daoPedido=new PedidoMYSQL();
            pedidos=daoPedido.listarPedidosMasajista();
            
             for(Pedido p:pedidos){
                for(Item i:p.getItems()){
                    if(i instanceof ServicioDeMasaje){
                        if(((ServicioDeMasaje)i).getEstado()==EstadoServicio.POR_CONFIRMAR){
                            pedidosMasaje.add(p);
                        }
                    }
                }
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return pedidosMasaje;

    }
    
    
    @WebMethod(operationName = "ListarServiciosMasajistaPorEntregar")
    public ArrayList<Pedido> ListarServiciosMasajistaPorEntregar() {
        
        ArrayList<Pedido> pedidos=new ArrayList<>();
        ArrayList<Pedido> pedidosMasaje=new ArrayList<>();
        try{
            daoPedido=new PedidoMYSQL();
            pedidos=daoPedido.listarPedidosMasajista();
            
             for(Pedido p:pedidos){
                for(Item i:p.getItems()){
                    if(i instanceof ServicioDeMasaje){
                        if(((ServicioDeMasaje)i).getEstado()==EstadoServicio.EN_PROCESO){
                            pedidosMasaje.add(p);
                        }
                    }
                }
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return pedidosMasaje;

    }
    
    @WebMethod(operationName = "modificarPedidoPendienteMasajista")
    public int modificarPedidoPendienteMasajista(@WebParam(name="pedido")Pedido pedido,@WebParam(name="cadena")String cadena) {
        int resultado=0;
        try{
            ArrayList<Pedido> pedidos=new ArrayList<>();
            daoPedido=new PedidoMYSQL();
            pedidos=daoPedido.listarPedidosMasajista();
            daoservicio=new ServicioMYSQL();
            for(Pedido p:pedidos){
                if(p.getIdPedido()==pedido.getIdPedido()){
                    for(Item i:p.getItems()){
                        if(i instanceof ServicioDeMasaje){
                            if(cadena.equals("EN_PROCESO")){
                                ((Servicio)i).setEstado(EstadoServicio.EN_PROCESO);
                            }else if(cadena.equals("POR_CONFIRMAR")){
                                ((Servicio)i).setEstado(EstadoServicio.POR_CONFIRMAR);
                            }else{
                                ((Servicio)i).setEstado(EstadoServicio.COMPLETADO);
                            }
                            resultado=daoservicio.modificar(((Servicio)i));
                            break;
                        }
                    }
                }
                
            }
                
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    
    @WebMethod(operationName = "AgregardescripcionMasaje")
    public void AgregardescripcionMasaje(@WebParam(name="pedido")Pedido pedido, @WebParam(name="cadena")String cadena ) {
        
        
        try{
            ArrayList<Pedido> pedidos=new ArrayList<>();
            daoPedido=new PedidoMYSQL();
            pedidos=daoPedido.listarPedidosMasajista();
            daoservicio=new ServicioMYSQL();
            for(Pedido p:pedidos){
                if(p.getIdPedido()==pedido.getIdPedido()){
                    for(Item i:p.getItems()){
                        if(i instanceof ServicioDeMasaje){
                            ((Servicio)i).setIncidencia(cadena);
                            daoservicio.modificar(((Servicio)i));
                            break;
                        }
                    }
                }
                
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        

    }
       /*Operaciones relacionadas con la pagina de Lavandero*/
    
    private ServicioDeLavanderiaDAO daoServicioLavanderia;
    private PedidoDAO daoPedido;
    private ServicioDAO daoservicio;
    private ServicioDeLavanderia servicio;
    @WebMethod(operationName = "ListarServiciosLavandero")
    public ArrayList<Pedido> ListarServiciosLavandero() {
        
        ArrayList<Pedido> pedidos=new ArrayList<>();
        ArrayList<Pedido> pedidosLavanderia=new ArrayList<>();
        try{
            daoPedido=new PedidoMYSQL();
            pedidos=daoPedido.listarPedidosLavanderia();
            
             for(Pedido p:pedidos){
                for(Item i:p.getItems()){
                    if(i instanceof ServicioDeLavanderia){
                        if(((ServicioDeLavanderia)i).getEstado()==EstadoServicio.POR_CONFIRMAR){
                            pedidosLavanderia.add(p);
                        }
                    }
                }
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return pedidosLavanderia;

    }
    
    @WebMethod(operationName = "modificarPedidoPendiente")
    public int modificarPedidoPendiente(@WebParam(name="pedido")Pedido pedido,@WebParam(name="cadena")String cadena) {
        int resultado=0;
        try{
            ArrayList<Pedido> pedidos=new ArrayList<>();
            daoPedido=new PedidoMYSQL();
            pedidos=daoPedido.listarPedidosLavanderia();
            daoservicio=new ServicioMYSQL();
            for(Pedido p:pedidos){
                if(p.getIdPedido()==pedido.getIdPedido()){
                    for(Item i:p.getItems()){
                        if(i instanceof ServicioDeLavanderia){
                            if(cadena.equals("EN_PROCESO")){
                                ((Servicio)i).setEstado(EstadoServicio.EN_PROCESO);
                            }else if(cadena.equals("POR_CONFIRMAR")){
                                ((Servicio)i).setEstado(EstadoServicio.POR_CONFIRMAR);
                            }else{
                                ((Servicio)i).setEstado(EstadoServicio.COMPLETADO);
                            }
                            
                            resultado=daoservicio.modificar(((Servicio)i));
                        }
                    }
                }
                
            }
                
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado; 
    }
    
    @WebMethod(operationName = "ListarServiciosLavanderoPorEntregar")
    public ArrayList<Pedido> ListarServiciosLavanderoPorEntregar() {
        
        ArrayList<Pedido> pedidos=new ArrayList<>();
        ArrayList<Pedido> pedidosLavanderia=new ArrayList<>();
        servicio=new ServicioDeLavanderia();
        try{
            daoPedido=new PedidoMYSQL();
            pedidos=daoPedido.listarPedidosLavanderia();
            
            for(Pedido p:pedidos){
                for(Item i:p.getItems()){
                    if(i instanceof ServicioDeLavanderia){
                        if(((ServicioDeLavanderia)i).getEstado()==EstadoServicio.EN_PROCESO){
                            pedidosLavanderia.add(p);
                        }
                    }
                }
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return pedidosLavanderia;

    }
    
    
    @WebMethod(operationName = "Agregardescripcion")
    public void Agregardescripcion(@WebParam(name="pedido")Pedido pedido, @WebParam(name="cadena")String cadena ) {
        
        
        try{
            ArrayList<Pedido> pedidos=new ArrayList<>();
            daoPedido=new PedidoMYSQL();
            pedidos=daoPedido.listarPedidosLavanderia();
            daoservicio=new ServicioMYSQL();
            for(Pedido p:pedidos){
                if(p.getIdPedido()==pedido.getIdPedido()){
                    for(Item i:p.getItems()){
                        if(i instanceof ServicioDeLavanderia){
                            ((Servicio)i).setIncidencia(cadena);
                            daoservicio.modificar(((Servicio)i));
                            break;
                        }
                    }
                }
                
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }   
    
    //Para el reporte de habitaciones :
    @WebMethod(operationName = "GenerarReporteHabitaciones")
    public byte[] GenerarReporteHabitaciones(@WebParam(name="fechaInicio")Date fechaInicio, @WebParam(name="fechaFin")Date fechaFin ) {
        byte[] reporteBytes=null;
        try{
           Connection con=DBManager.getInstance().getConnection();
                JasperReport reporte= (JasperReport)JRLoader.loadObject(RRHHWS.class.getResource("/pe/edu/pucp/lothel/reportes/ReporteLothelHabitacion.jasper"));
                
                String rutaImagen=RRHHWS.class.getResource("/pe/edu/pucp/lothel/image/loogo.png").getPath();
                rutaImagen = rutaImagen.replace("%20"," ");
                String rutaSubreporte=RRHHWS.class.getResource("/pe/edu/pucp/lothel/reportes/SubReporteHabitaciones.jasper").getPath();
                rutaSubreporte = rutaSubreporte.replace("%20"," ");
                String reporteGrafico=RRHHWS.class.getResource("/pe/edu/pucp/lothel/reportes/ReporteGrafico.jasper").getPath();
                 reporteGrafico = reporteGrafico.replace("%20"," ");
                String reporteGraficoXtipo=RRHHWS.class.getResource("/pe/edu/pucp/lothel/reportes/GraficoPorTipo.jasper").getPath();
                reporteGraficoXtipo = reporteGraficoXtipo.replace("%20"," ");
                Image image=(new ImageIcon(rutaImagen)).getImage();
                
                HashMap hm=new HashMap();
                hm.put("FechaHasta",fechaFin);
                hm.put("Desde", fechaInicio);
                hm.put("ImagenLogo",image);
                hm.put("rutaSubreporte", rutaSubreporte);
                hm.put("rutaGrafico", reporteGrafico);
                hm.put("rutaGraficoPorTipo", reporteGraficoXtipo);
                
                JasperPrint jp=JasperFillManager.fillReport(reporte,hm,con);  
                con.close();
                reporteBytes=JasperExportManager.exportReportToPdf(jp);
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return reporteBytes;
    }
    
    //Para el reporte de habitaciones :
    @WebMethod(operationName = "GenerarReporteHuespedes")
    public byte[] GenerarReporteHuespedes(@WebParam(name="fechaInicio")Date fechaInicio, @WebParam(name="fechaFin")Date fechaFin ) {
        byte[] reporteBytes=null;
        try{
           Connection con=DBManager.getInstance().getConnection();
                JasperReport reporte= (JasperReport)JRLoader.loadObject(RRHHWS.class.getResource("/pe/edu/pucp/lothel/reportes/reportePorHuesped.jasper"));
                
                String rutaImagen=RRHHWS.class.getResource("/pe/edu/pucp/lothel/image/loogo.png").getPath();
                rutaImagen = rutaImagen.replace("%20"," ");
                String rutaSubreporte=RRHHWS.class.getResource("/pe/edu/pucp/lothel/reportes/ReporteHuespedSubConsulta.jasper").getPath();
                rutaSubreporte = rutaSubreporte.replace("%20"," ");
                Image image=(new ImageIcon(rutaImagen)).getImage();
                
                HashMap hm=new HashMap();
                hm.put("fechaSalida",fechaFin);
                hm.put("fechaEntrada", fechaInicio);
                hm.put("imagenLogo",image);
                hm.put("rutaSubReporte", rutaSubreporte);
                
                JasperPrint jp=JasperFillManager.fillReport(reporte,hm,con);  
                con.close();
                reporteBytes=JasperExportManager.exportReportToPdf(jp);
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return reporteBytes;
    }
}
