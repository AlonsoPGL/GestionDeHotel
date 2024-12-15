/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.lothel.principal;

/**
 *
 * @author Adrian Fujiki
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import pe.edu.pucp.lothel.gestreserva.model.Habitacion;
import pe.edu.pucp.lothel.gestreserva.model.ReservaHabitacion;
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
import pe.edu.pucp.lothel.gestreserva.dao.FamiliarDAO;
import pe.edu.pucp.lothel.gestreserva.dao.HabitacionDAO;
import pe.edu.pucp.lothel.gestreserva.dao.MatrimonialDAO;
import pe.edu.pucp.lothel.gestreserva.dao.SimpleDAO;
import pe.edu.pucp.lothel.gestreserva.model.Familiar;
import pe.edu.pucp.lothel.gestreserva.model.Matrimonial;
import pe.edu.pucp.lothel.gestreserva.model.Simple;
import pe.edu.pucp.lothel.gestreserva.mysql.FamiliarMYSQL;
import pe.edu.pucp.lothel.gestreserva.mysql.HabitacionMYSQL;
import pe.edu.pucp.lothel.gestreserva.mysql.MatrimonialMYSQL;
import pe.edu.pucp.lothel.gestreserva.mysql.SimpleMYSQL;
import pe.edu.pucp.lothel.rrhh.dao.AdministradorDAO;
import pe.edu.pucp.lothel.rrhh.dao.CuentaDAO;
import pe.edu.pucp.lothel.rrhh.dao.HuespedDAO;
import pe.edu.pucp.lothel.rrhh.dao.RecepcionistaDAO;
import pe.edu.pucp.lothel.rrhh.dao.PersonalDeLavanderiaDAO;
import pe.edu.pucp.lothel.rrhh.dao.PersonalDeMasajesDAO;
import pe.edu.pucp.lothel.rrhh.model.Administrador;
import pe.edu.pucp.lothel.rrhh.model.Cuenta;
import pe.edu.pucp.lothel.rrhh.model.Huesped;
import pe.edu.pucp.lothel.rrhh.model.Persona;
import pe.edu.pucp.lothel.rrhh.model.PersonalDeLavanderia;
import pe.edu.pucp.lothel.rrhh.model.PersonalDeMasajes;
import pe.edu.pucp.lothel.rrhh.model.Recepcionista;
import pe.edu.pucp.lothel.rrhh.model.TipoCuenta;
import pe.edu.pucp.lothel.rrhh.model.TipoTurno;
import pe.edu.pucp.lothel.rrhh.mysql.AdministradorMYSQL;
import pe.edu.pucp.lothel.rrhh.mysql.CuentaMYSQL;
import pe.edu.pucp.lothel.rrhh.mysql.HuespedMYSQL;
import pe.edu.pucp.lothel.rrhh.mysql.RecepcionistaMYSQL;
import pe.edu.pucp.lothel.rrhh.mysql.PersonalDeLavanderiaMYSQL;
import pe.edu.pucp.lothel.rrhh.mysql.PersonalDeMasajesMYSQL;
import pe.edu.pucp.lothel.ventas.dao.BebidaDAO;
import pe.edu.pucp.lothel.ventas.dao.EmpresaProveedoraDAO;
import pe.edu.pucp.lothel.ventas.dao.PedidoDAO;
import pe.edu.pucp.lothel.ventas.dao.ProductoDAO;
import pe.edu.pucp.lothel.ventas.dao.ServicioDeLavanderiaDAO;
import pe.edu.pucp.lothel.ventas.dao.ServicioDeMasajeDAO;
import pe.edu.pucp.lothel.ventas.dao.documentoPagoDAO;
import pe.edu.pucp.lothel.ventas.model.Bebida;
import pe.edu.pucp.lothel.ventas.model.CategoriaBebida;
import pe.edu.pucp.lothel.ventas.model.DocumentoDePago;
import pe.edu.pucp.lothel.ventas.model.EmpresaProveedora;
import pe.edu.pucp.lothel.ventas.model.EstadoPedido;
import pe.edu.pucp.lothel.ventas.model.EstadoServicio;
import pe.edu.pucp.lothel.ventas.model.Item;
import pe.edu.pucp.lothel.ventas.model.Pedido;
import pe.edu.pucp.lothel.ventas.model.Producto;
import pe.edu.pucp.lothel.ventas.model.ServicioDeLavanderia;
import pe.edu.pucp.lothel.ventas.model.ServicioDeMasaje;
import pe.edu.pucp.lothel.ventas.model.TipoDocumento;
import pe.edu.pucp.lothel.ventas.mysql.BebidaMYSQL;
import pe.edu.pucp.lothel.ventas.mysql.EmpresaProveedoraMYSQL;
import pe.edu.pucp.lothel.ventas.mysql.PedidoMYSQL;
import pe.edu.pucp.lothel.ventas.mysql.ProductoMYSQL;
import pe.edu.pucp.lothel.ventas.mysql.ServicioDeMasajeMYSQL;
import pe.edu.pucp.lothel.ventas.mysql.documentoPagoMYSQL;
import pe.edu.pucp.lothel.ventas.mysql.ServicioDeLavanderiaMYSQL;
import pe.edu.pucp.lothel.rrhh.dao.HuespedDAO;
import pe.edu.pucp.lothel.rrhh.model.Huesped;
import pe.edu.pucp.lothel.rrhh.mysql.HuespedMYSQL;
import pe.edu.pucp.lothel.ventas.dao.AlimentoDAO;
import pe.edu.pucp.lothel.ventas.dao.CuidadoPersonalDAO;
import pe.edu.pucp.lothel.ventas.model.Alimento;
import pe.edu.pucp.lothel.ventas.model.CuidadoPersonal;
import pe.edu.pucp.lothel.ventas.mysql.AlimentoMYSQL;
import pe.edu.pucp.lothel.ventas.mysql.CuidadoPersonalMYSQL;
/**
 *
 * @author efeproceres
 */
public class Principal {
    public static void main(String [] args) throws ParseException{
//        EspacioDAO espacioDAO = new EspacioMYSQL();
//        int resultado=0 ;  
//        Espacio espacio= new Espacio(3,"a",122,true);
//         resultado = espacioDAO.insertar(espacio);
//        if(resultado==1){
//           System.out.println("Se registro");
//        }
//        else{
//           System.out.println("No se registro");
//        }
//        espacio.setIdEspacio(3);
//        espacio.setAforo(40);
//        resultado = espacioDAO.modificar(espacio);
//        resultado=espacioDAO.eliminar(espacio.getIdEspacio());
//        ArrayList<Espacio>espacios=new ArrayList<>();
//        espacios=espacioDAO.listarEspacios();
//        
//        for(Espacio esp:espacios){
//            System.out.println(esp.getIdEspacio()+"\n");    
//        }    
        
//        int resultado;
//        EventoDAO eventoDAO=new EventoMYSQL();
//        
//        
//        Evento evento=new Evento("CONCIERTO","CONCIERTO DE UN ARTISTA",100,new Date(),EstadoEvento.PROGRAMADO,administrador);
//        resultado=0 ;  
//        resultado=eventoDAO.insertar(evento);
//        if(resultado==1){
//             System.out.println("se inserto");
//        }else {
//            System.out.println("NO se modifico evento");
//        }
//        evento.setNombre("EVENTO 2");
//        //evento.setIdEvento(3);
//        resultado=eventoDAO.modificar(evento);
//        if(resultado==1){
//             System.out.println("se inserto");
//        } else {
//            System.out.println("NO se modifico evento");
//        }
//        
//        resultado=eventoDAO.eliminar(3);
//        if(resultado==1){
//             System.out.println("se inserto");
//        }else {
//            System.out.println("NO se modifico evento");
//        }
//        ArrayList<Evento>eventos=new ArrayList<>();
//        eventos=eventoDAO.listarEventos();
//         for(Evento ev:eventos){
//            System.out.println(ev.getNombre()+"\n");    
//        }
        
//        Cuenta cuenta=new Cuenta("user9999","aaaaaaa",TipoCuenta.ACTIVA);
//        Administrador administrador=new Administrador("GERENTE",new Date(),true,"20202020","JUAN","FLORES","MONTOYA",new Date(),"2020202@correo","+519999999",true,cuenta);
//        Evento evento=new Evento("CONCIERTO","CONCIERTO DE UN ARTISTA",100,new Date(),EstadoEvento.PROGRAMADO,administrador);
        //evento.setIdEvento(3);
        //Espacio espacio= new Espacio(3,"a",122,true);
        //espacio.setIdEspacio(3);
        
       /* ReservaEspacioDAO reservaespacioDAO = new ReservaEspacioMYSQL();

        //public ReservaEspacio(Date fechaInicio, Date fechaFin, boolean estado, Espacio espacio, Evento evento)
        ReservaEspacio reserva=new ReservaEspacio(new Date(),new Date(),true,espacio,evento);

        resultado=reservaespacioDAO.insertar(reserva);

        if(resultado==1){
            System.out.println("se inserto");
        }
        espacio.setIdEspacio(4);
        reserva.setEspacio(espacio);
        //System.out.println(reserva.getEvento().getIdEvento());
        
        resultado=reservaespacioDAO.modificar(reserva);
        if(resultado==1){
            System.out.println("se inserto");
        }
        resultado=reservaespacioDAO.eliminar(reserva.getIdReservaEspacio());
        if(resultado==1){
            System.out.println("se inserto");
        } */
        //resultado=reservaespacioDAO.listarReservas());
        
        /*ArrayList<ReservaEspacio> reservasEspacio = new ArrayList<>();
        reservasEspacio = reservaespacioDAO.listarReservas();   
        System.out.println("RESERVAS DE ESPACIOS:");
        for(ReservaEspacio rE : reservasEspacio) {
            System.out.println(rE.getIdReservaEspacio());
        }
        */
        
// A EDITAR: 
//        int resultado=0;
//
//        EmpresaProveedora emp1 = new EmpresaProveedora("18892739", "The Coca-Cola Company", "institucional@cocacola.com", true);
//        EmpresaProveedoraDAO daoEmpresa = new EmpresaProveedoraMYSQL();
//        resultado = daoEmpresa.insertar(emp1);
//        if(resultado != 0){
//            System.out.println("La empresa  se ha registrado con exito");
//        }
//        
//        resultado = 0;
//        BebidaDAO daoBebida = new BebidaMYSQL();
//        
//        Bebida bebida1 = new Bebida(10, 30, "Gaseosa Coca-Cola Personal 500ml", "Coca-Cola Personal", 2.5, 0, true, true, CategoriaBebida.GASEOSAS, emp1);
//        resultado = daoBebida.insertar(bebida1);
//        if(resultado != 0){
//            System.out.println("La bebida se ha registrado con exito");
//        }
//        
//        resultado = 0;
//        Bebida bebida2 = new Bebida(10, 60, "Gaseosa Inca Kola Personal 500ml", "Inca Kola Personal", 2.5, 0, true, true, CategoriaBebida.GASEOSAS, emp1);
//        resultado = daoBebida.insertar(bebida2);
//        if(resultado != 0){
//            System.out.println("La bebida se ha registrado con exito");
//        }
//        
//        resultado = 0;
//        
//        Bebida bebida3 = new Bebida(10, 30, "Gaseosa Coca-Cola Personal 500ml Sin Azucar", "Coca-Cola Zero Personal", 2.7, 0, true, true, CategoriaBebida.GASEOSAS, emp1);
//        resultado = daoBebida.insertar(bebida3);
//        if(resultado != 0){
//            System.out.println("La bebida se ha registrado con exito");
//        }
//        
//        resultado = 0;
//        Bebida bebida4 = new Bebida(10, 60, "Gaseosa Inca Kola Personal 500ml Sin azucar", "Inca Kola Personal", 2.7, 0, true, true, CategoriaBebida.GASEOSAS, emp1);
//        resultado = daoBebida.insertar(bebida4);
//        if(resultado != 0){
//            System.out.println("La bebida se ha registrado con exito");
//        }
        // FIN EDITAR
        
        
        /*
        Cuenta cuenta=new Cuenta();
        cuenta.setContrasnha("ola");
        cuenta.setIdCuenta(1);
        cuenta.setIdUsuario("pp");
        cuenta.setTipocuenta(TipoCuenta.CLIENTE);
        
        AdministradorDAO daoadmin=new AdministradorMYSQL();
        //public Administrador(String rol, Date fechaContratacion, boolean activo, String dni, String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaRegistro, String correo, String celular, boolean estado, Cuenta cuenta) {
        Administrador ad=new Administrador("admin",new Date(),true,"20202020","juan","perez","gomez",new Date(),"AAAAAA@hotmail.com","+51949494949",false,cuenta);
        
        int resu=daoadmin.insertar(ad);
        
        if(resu!=0)System.out.println("se ingreso");
        
        
        RecepcionistaDAO daorecepcionista=new RecepcionistaMYSQL();
        //public Recepcionista(double deuda, TipoTurno turno, boolean estadoPS, Date fechaContratacion, boolean activo, String dni, String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaRegistro, String correo, String celular, boolean estado, Cuenta cuenta,Administrador administrador)
        Recepcionista recepcionista=new Recepcionista(100.0,TipoTurno.TARDE,true,new Date(),true,"46464646","ccccccccccca","rrrrrrrrrrr","aaaaaaaaa",new Date(),"@hotmail.com","77777777",true,cuenta,ad);
            
        int res=daorecepcionista.insertar(recepcionista);
        if(res!=0)System.out.println("se ha registrado");
        
        ArrayList<Recepcionista>recep=new ArrayList<>();
        recep=daorecepcionista.listarRecepcionistas();
        
        for(Recepcionista re:recep){
            System.out.println(re.getNombre());
        }
        
        //Pruebas personal de Lavanderia
        /*
        Cuenta cuenta=new Cuenta();
        AdministradorDAO daoadmin=new AdministradorMYSQL();
        //public Administrador(String rol, Date fechaContratacion, boolean activo, String dni, String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaRegistro, String correo, String celular, boolean estado, Cuenta cuenta) {
        Administrador ad=new Administrador("admin",new Date(),true,"20202020","juan","perez","gomez",new Date(),"AAAAAA@hotmail.com","+51949494949",false,cuenta);
        
        int resu=daoadmin.insertar(ad);
        
        if(resu!=0)System.out.println("se ingreso");
        
        PersonalDeLavanderiaDAO daoPerLav=new PersonalDeLavanderiaMYSQL();
        PersonalDeLavanderia perLav=new PersonalDeLavanderia(true,TipoTurno.MAÑANA,
                true,new Date(),true,"15467354","Roberto","Angulo",
                "Ramirez",new Date(),"ramirez.123@hotmail.com","9450564",true,cuenta,ad);
        
        int res=daoPerLav.insertar(perLav);
        if(res!=0)System.out.println("se ha registrado");*/
        
        /*
        //Prueba Listar Lavanderos
        PersonalDeLavanderiaDAO daoPerLav=new PersonalDeLavanderiaMYSQL();
        ArrayList<PersonalDeLavanderia>listLav=new ArrayList<>();
        listLav=daoPerLav.listarLavanderos();
        
        for(PersonalDeLavanderia lav:listLav){
            System.out.println(lav.getNombre());
        }
        */
        //Prueba modificar Lavandero
        /*
        PersonalDeLavanderiaDAO daoPerLav=new PersonalDeLavanderiaMYSQL();
        ArrayList<PersonalDeLavanderia>listLav=new ArrayList<>();
        listLav=daoPerLav.listarLavanderos();

        PersonalDeLavanderia pLav=new PersonalDeLavanderia();
        for(PersonalDeLavanderia lav:listLav){
            pLav.setIdPersona(lav.getIdPersona());  //Del personal ultimo nivel 
        }
        pLav.setAutorizacionDeRiesgoBiologico(true);
        daoPerLav.modificar(pLav);
        */
        //Prueba eliminar Lavandero
        /*
        PersonalDeLavanderiaDAO daoPerLav=new PersonalDeLavanderiaMYSQL();
        daoPerLav.eliminar(2);*/
        
        //Pruebas personal de Masajes
        /*Cuenta cuenta=new Cuenta();
        AdministradorDAO daoadmin=new AdministradorMYSQL();
        //public Administrador(String rol, Date fechaContratacion, boolean activo, String dni, String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaRegistro, String correo, String celular, boolean estado, Cuenta cuenta) {
        Administrador ad=new Administrador("admin",new Date(),true,"20202020","juan","perez","gomez",new Date(),"AAAAAA@hotmail.com","+51949494949",false,cuenta);
        
        int resu=daoadmin.insertar(ad);
        
        if(resu!=0)System.out.println("se ingreso");
        
        PersonalDeMasajesDAO daoPerMas=new PersonalDeMasajesMYSQL();
        PersonalDeMasajes perMas=new PersonalDeMasajes("Masajes con piedras calientes",TipoTurno.NOCHE,
                true,new Date(),true,"15467354","Lorenzo","Amauta",
                "Magnolio",new Date(),"pedro.143@hotmail.com","951654",true,cuenta,ad);
        
        int res=daoPerMas.insertar(perMas);
        if(res!=0)System.out.println("se ha registrado");
        */
        //Listar Masajistas
        /*PersonalDeMasajesDAO daoPerMas=new PersonalDeMasajesMYSQL();
        ArrayList<PersonalDeMasajes>listMas=new ArrayList<>();
        listMas=daoPerMas.listarMasajistas();
        
        for(PersonalDeMasajes mas:listMas){
            System.out.println(mas.getNombre());
        }*/
        //Modificar registro
        /*PersonalDeMasajesDAO daoPerMas=new PersonalDeMasajesMYSQL();
        ArrayList<PersonalDeMasajes>listMas=new ArrayList<>();
        listMas=daoPerMas.listarMasajistas();

        PersonalDeMasajes pMas=new PersonalDeMasajes();
        for(PersonalDeMasajes mas:listMas){
            pMas.setIdPersona(mas.getIdPersona());  //Del personal ultimo nivel 
        }
        pMas.setEspecializacion("Masajes abdominales");
        daoPerMas.modificar(pMas);*/
        
        //Eliminar masajista
        /*PersonalDeMasajesDAO daoPerMas=new PersonalDeMasajesMYSQL();
        daoPerMas.eliminar(3);*/
        
        
        
        /*Cuenta cuentaAux=new Cuenta();
        cuentaAux.setContrasnha("sauu");
        //cuentaAux.setIdCuenta(2);
        cuentaAux.setIdUsuario("peter");
        cuentaAux.setTipocuenta(TipoCuenta.CLIENTE);
        Huesped huesped=new Huesped(true,"46464646","PEDRO","CASTILLO","NOC",new Date(),"wa@hotmail.com","11111111",true,cuentaAux);
        HuespedDAO daoHuesped=new HuespedMYSQL();
        int resul=daoHuesped.insertar(huesped);
        if(resul==1)
            System.out.println("inserta");
        daoHuesped.eliminar(1);
           ///
        ArrayList<Huesped>huespedes=new ArrayList<>();
        huespedes=daoHuesped.listarHuespedes();
        
        for(Huesped h:huespedes){
            System.out.println(h.getNombre());
        }
        huesped.setCorreo("ajd2@pucp.com");
        huesped.setCelular("9562626248");
        huesped.getCuenta().setContrasnha("contrasenia");
        huesped.getCuenta().setIdUsuario("usuario");
        daoHuesped.modificar(huesped);
        
        
        /*-----------------------PRUEBA MARCELO---------------------------      */
        
       /* Habitacion habitacion = new Habitacion();
        //ReservaHabitacion res = new ReservaHabitacion();
        
        int marcelo = 0;
        DocumentoDePago doc = new DocumentoDePago(TipoDocumento.BOLETA);
        documentoPagoDAO daoDocumentoDePago = new documentoPagoMYSQL();
        marcelo = daoDocumentoDePago.insertar(doc);
        if(marcelo != 0) System.out.println("Se inserto documento correctamente");
        else System.out.println("ocurrio un error");
   */
// MODIFICAR DOCUMENTO DE PAGO
//        marcelo = 0;
//        doc.setTipo(TipoDocumento.FACTURA);
//        marcelo = daoDocumentoDePago.modificar(doc);
//        if(marcelo != 0) System.out.println("Se modifico documento correctamente");
//        else System.out.println("ocurrio un error");
      
// LISTAR DOCUMENTOS DE PAGO
//        ArrayList<DocumentoDePago> docs = new ArrayList<>();
//        docs = daoDocumentoDePago.listarDocumentos();
//        for(DocumentoDePago docu: docs) {
//            System.out.println(docu.getIdDocumentoPago() + " - " + docu.getTipo().toString());
//        }

// INSERTAR PEDIDO     
      /*  Pedido ped = new Pedido(new Date(), EstadoPedido.Pendiente, 200.0,doc);
        PedidoDAO daoPedido = new PedidoMYSQL();
        marcelo = daoPedido.insertar(ped);
        
        if(marcelo != 0) System.out.println("Se inserto peiddo correctamente");
        else System.out.println("ocurrio un error");
        
        System.out.println("el id del pedido es: " + ped.getIdPedido());

// MODIFICAR PEDIDO
        ped.setMontoAcumulado(10.0);
        ped.setEstado(EstadoPedido.Confirmada);
        marcelo = daoPedido.modificar(ped);
        
        if(marcelo != 0) System.out.println("Se modifico pedido correctamente");
        else System.out.println("ocurrio un error");

        
        
// LISTAR PEDIDO
        ArrayList<Pedido> peds = new ArrayList<>();
        peds = daoPedido.listarPedidos();
        for(Pedido pedi:peds) {
            System.out.println(pedi.getIdPedido() + " - " + pedi.getEstado().toString() + " - " + pedi.getMontoAcumulado());
        }
        
// INSERTAR SERVICIO DE LAVANDERIA
        ServicioDeLavanderia servlav = new ServicioDeLavanderia("anotacion", "en proceso", "sin incidencias pero si" , "servicio ultralimpio", "servlav",20.0, 5.0 );
        ServicioDeLavanderiaDAO daoServicioDeLavanderia = new ServicioDeLavanderiaMYSQL();
        marcelo = daoServicioDeLavanderia.insertar(servlav);
        if(marcelo != 0) System.out.println("Se inserto un servicio de lavanderia correctamente");
        else System.out.println("hubo carencias");
     
 // MPDIFICAR SERVICIO DE LAVANDERIA
        servlav.setEstado("faltan dos prendas");
        marcelo = daoServicioDeLavanderia.modificar(servlav);
        if(marcelo != 0) System.out.println("Se modifico un servicio de lavanderia correctamente");
        else System.out.println("hubo carencias");
        System.out.println("AAAAAAAAAAAAAAA");
        
// LISTAR SERVICIO DE LAVANDERIA
        ArrayList<ServicioDeLavanderia> lservl = new ArrayList<>();
        lservl= daoServicioDeLavanderia.listarServicios();
        for(ServicioDeLavanderia serv : lservl) {
            System.out.println(serv.getIdIteam() + " - " + serv.getIncidencia() );
        }*/
      
      //Probando la insercion y procedure de obtenerCuenta
      /*CuentaDAO daoCuenta=new  CuentaMYSQL();
        Persona per=daoCuenta.obtenerCuentaUserPass("admin", "admin");
        System.out.println(per.getNombre());
        System.out.println(per.getApellidoPaterno());
        System.out.println(per.getApellidoMaterno());*/
      
        /*Cuenta cuentaAux=new Cuenta();
        cuentaAux.setPassword("huesped");
        cuentaAux.setUser("huesped");
        cuentaAux.setTipocuenta(TipoCuenta.HUESPED);
        Huesped huesped=new Huesped(true,"25648798","Andres","Sarmiento","Melano",new Date(),"melanoSar@chopmail.com","948756245",true,cuentaAux);
        HuespedDAO daoHuesped=new HuespedMYSQL();
        int resul=daoHuesped.insertar(huesped); //Verificar si existe la cuenta
        if(resul==1)
            System.out.println("inserta");
        //daoHuesped.eliminar(1);
           ///
        ArrayList<Huesped>huespedes=new ArrayList<>();
        huespedes=daoHuesped.listarHuespedes();
        
        for(Huesped h:huespedes){
            System.out.println(h.getNombre());
        }*/
        
        /*Cuenta cuentaAux=new Cuenta();
        cuentaAux.setPassword("admin1");
        cuentaAux.setUser("admin1");*/

        /*Cuenta cuentaAux=new Cuenta();
        cuentaAux.setPassword("admin");
        cuentaAux.setUser("admin");
        cuentaAux.setTipocuenta(TipoCuenta.ADMINISTRADOR);
        Administrador administrador=new Administrador("Administrador de Eventos",new Date(),true,"989898","Freddy","Paz","Romero",new Date(),"f.paz@hotmail.pe.com","99858483",true,cuentaAux);
        AdministradorDAO daoAdministrador=new AdministradorMYSQL();
        int resul=daoAdministrador.insertar(administrador);
        if(resul==1)
            System.out.println("inserta");
        //daoHuesped.eliminar(1);
           ///
        /*ArrayList<Administrador>administradores=new ArrayList<>();
        administradores=daoAdministrador.listarAdministradores();
        
        for(Administrador a:administradores){
            System.out.println(a.getNombre());
        }*/
        
        /*Administrador ad=new Administrador();
        ad.setIdPersona(15);
        ad.getIdAdministrador();
        Cuenta cuentaAux=new Cuenta();
        cuentaAux.setPassword("recepcionista");
        cuentaAux.setUser("recepcionista");
        cuentaAux.setTipocuenta(TipoCuenta.RECEPCIONISTA);
        RecepcionistaDAO daoPerLav=new RecepcionistaMYSQL();
        Recepcionista perLav=new Recepcionista(15.5,TipoTurno.MAÑANA,
                true,new Date(),true,"165498","Alfredo","Reyes",
                "Moran",new Date(),"mReyes.lip@hotmail.com","48982646",true,cuentaAux,ad);
        
        int res=daoPerLav.insertar(perLav);
        if(res!=0)System.out.println("se ha registrado");
        

        /*CuentaDAO daoCuenta=new CuentaMYSQL();
        Persona per=daoCuenta.obtenerCuentaUserPass("recepcionista", "recepcionista");
        System.out.println(per.getNombre());*/
        /*Cuenta cuenta=new Cuenta();
        cuenta.setPassword("lav");
        cuenta.setUser("lav");
        cuenta.setTipocuenta(TipoCuenta.PERSONAL_DE_LAVANDERIA);
        
        PersonalDeLavanderia p=new PersonalDeLavanderia();
        p.setActivo(true);
        p.setApellidoMaterno("no c lo que hago");
        p.setApellidoPaterno("fujiki");
        p.setAutorizacionDeRiesgoBiologico(true);
        p.setCelular("44444444442");
        p.setDni("55555555");
        p.setNombre("Adrian");
        p.setFechaContratacion(new Date());
        p.setFechaRegistro(new Date());
        p.setCorreo("agfdafsdklfda.com");
        p.setTurno(TipoTurno.TARDE);
        p.setCuenta(cuenta);
        p.setAdministrador(administrador);
        PersonalDeLavanderiaDAO daoPersonal;
        daoPersonal=new PersonalDeLavanderiaMYSQL();
        int a=daoPersonal.insertar(p);*/
        
        
/*
        CuentaDAO daoCuenta=new CuentaMYSQL();
        Persona per=daoCuenta.obtenerCuentaUserPass("huesped", "huesped");
        System.out.println(per.getNombre());
        */
        
        /*
        Administrador ad=new Administrador();
        ad.setIdPersona(15);
        ad.getIdAdministrador();
        Cuenta cuentaAux=new Cuenta();
        cuentaAux.setPassword("lavandero");
        cuentaAux.setUser("lavandero");
        cuentaAux.setTipocuenta(TipoCuenta.PERSONAL_DE_LAVANDERIA);
        PersonalDeLavanderiaDAO daoPerLav=new PersonalDeLavanderiaMYSQL();
        PersonalDeLavanderia perLav=new PersonalDeLavanderia(true,TipoTurno.MAÑANA,
                true,new Date(),true,"165498","Manuel","Urrutia",
                "Moran",new Date(),"ManuelUrT.lip@Chopmail.com","9849714",true,cuentaAux,ad);
        
        int res=daoPerLav.insertar(perLav);
        if(res!=0)System.out.println("se ha registrado");
        
        CuentaDAO daoCuenta=new CuentaMYSQL();
        Persona per=daoCuenta.obtenerCuentaUserPass("lavandero", "lavandero");
        System.out.println(per.getNombre());*/
        /*
        Administrador ad=new Administrador();
        ad.setIdPersona(15);
        ad.getIdAdministrador();
        Cuenta cuentaAux=new Cuenta();
        cuentaAux.setPassword("masajista");
        cuentaAux.setUser("masajista");
        cuentaAux.setTipocuenta(TipoCuenta.PERSONAL_DE_MASAJES);
        PersonalDeMasajesDAO daoPerLav=new PersonalDeMasajesMYSQL();
        PersonalDeMasajes perLav=new PersonalDeMasajes("Masajes de espalda",TipoTurno.NOCHE,
                true,new Date(),true,"165498","Pedro","Vasques",
                "Loren",new Date(),"PVasquez.rep@Chopmail.com","9845947",true,cuentaAux,ad);
        
        int res=daoPerLav.insertar(perLav);
        if(res!=0)System.out.println("se ha registrado");
        */
//        CuentaDAO daoCuenta=new CuentaMYSQL();
//        Persona per=daoCuenta.obtenerCuentaUserPass("masajista", "masajista");
//        System.out.println(per.getNombre());

 //SI FUNCIONA MI METODOOOO AHHHHHH
        /*ProductoDAO daoProducto = new ProductoMYSQL();
        ArrayList<Producto> productos = new ArrayList<>();
        productos = daoProducto.ListarPorNombre("ola");
        for(Producto p : productos) {
            System.out.println(p.getNombre());
        }*/
        
        /*CuentaDAO daoCuenta=new CuentaMYSQL();
        Persona per=daoCuenta.obtenerCuentaUserPass("masajista", "masajista");
        System.out.println(per.getNombre());*/
        
        //Probando insertarHuesped
        /*Cuenta cuentaAux=new Cuenta();
        cuentaAux.setPassword("huespedPrueba");
        cuentaAux.setUser("huespedPrueba");
        cuentaAux.setTipocuenta(TipoCuenta.HUESPED);
        Huesped huesped=new Huesped(true,"12345678","Ramon","Rybeiro","Canales",new Date(),"RamRyb@gmail.com","987654321",true,cuentaAux);
        HuespedDAO daoHuesped=new HuespedMYSQL();
        int resul=daoHuesped.insertar(huesped); //Verificar si existe la cuenta
        if(resul==1)
            System.out.println("inserta");
        //daoHuesped.eliminar(1);
           ///
        ArrayList<Huesped>huespedes=new ArrayList<>();
        huespedes=daoHuesped.listarHuespedes();
        
        for(Huesped h:huespedes){
            System.out.println(h.getNombre());
        }*/
        
        //estado del servicio COMPLETADO,EN_PROCESO,POR_CONFIRMAR
        
        /*ServicioDeMasajeDAO daomasaje;
        daomasaje= new ServicioDeMasajeMYSQL();
        
        ServicioDeMasaje serviciomasaje;
        serviciomasaje=new ServicioDeMasaje();
        serviciomasaje.setDescripcion("Masaje de espalda");
        
        serviciomasaje.setNombre("MASAJE");
        serviciomasaje.setPrecio(25.00);
        serviciomasaje.setCalificacion(75.00);    
        serviciomasaje.setEstado("EN_PROCESO"); 
        serviciomasaje.setIncidencia("NO C ");
        
        serviciomasaje.setHoraInicio(new Date());
        serviciomasaje.setHoraFin(new Date());
        daomasaje.insertar(serviciomasaje);*/
        
        /*PedidoDAO daoPedido;
        ArrayList<Pedido> pedidos=new ArrayList<>();
        ArrayList<Pedido> pedidosMasaje=new ArrayList<>();
        
            daoPedido=new PedidoMYSQL();
            pedidos=daoPedido.listarPedidos();
            
             for(Pedido p:pedidos){
                for(Item i:p.getItems()){
                    if(i instanceof ServicioDeMasaje){
                        if(((ServicioDeMasaje)i).getEstado().equals("POR_CONFIRMAR")){
                            pedidosMasaje.add(p);
                        }
                    }
                }
            }*/
        
        /*ServicioDeLavanderiaDAO daoServicioLavanderia=new ServicioDeLavanderiaMYSQL();
        ServicioDeLavanderia serviciolavanderia;
        for(int i=0;i<5;i++){
            serviciolavanderia=new ServicioDeLavanderia();
            serviciolavanderia.setDescripcion("lavado de ropa al seco");

            serviciolavanderia.setNombre("LAVADO");
            serviciolavanderia.setPrecio(25.00);
            serviciolavanderia.setCalificacion(75.00);    
            serviciolavanderia.setEstado("POR_CONFIRMAR"); 
            serviciolavanderia.setIncidencia("NO C ");
            serviciolavanderia.setAnotaciones("NO C x2");
        
       
            daoServicioLavanderia.insertar(serviciolavanderia);
        }*/
        
       /*PedidoDAO daoPedido;
        ArrayList<Pedido> pedidos=new ArrayList<>();
        ArrayList<Pedido> pedidosMasaje=new ArrayList<>();
        
            daoPedido=new PedidoMYSQL();
            pedidos=daoPedido.listarPedidos();
            
             for(Pedido p:pedidos){
                for(Item i:p.getItems()){
                    if(i instanceof ServicioDeMasaje){
                        if(((ServicioDeMasaje)i).getEstado().equals("EN_PROCESO")){
                            pedidosMasaje.add(p);
                        }
                    }
                }
            }
    }*/
       
       
       /*ServicioDeMasajeDAO daoServicioDeMasaje=new ServicioDeMasajeMYSQL();
        ServicioDeMasaje serviciomasaje;
        for(int i=0;i<9;i++){
            serviciomasaje=new ServicioDeMasaje();
            serviciomasaje.setDescripcion("masaje ");

            serviciomasaje.setNombre("LAVADO");
            serviciomasaje.setPrecio(25.00);
            serviciomasaje.setCalificacion(75.00);    
            serviciomasaje.setEstado("EN_PROCESO"); 
            serviciomasaje.setIncidencia("NO C ");
            serviciomasaje.setHoraInicio(new Date());
            serviciomasaje.setHoraFin(new Date());
       
            daoServicioDeMasaje.insertar(serviciomasaje);
        }
      */ 
       
//       PedidoDAO daoPedido;
//       ArrayList<Pedido> pedidos=new ArrayList<>();
//        ArrayList<Pedido> pedidosMasaje=new ArrayList<>();
//       
//
//            
//            daoPedido=new PedidoMYSQL();
//            pedidos=daoPedido.listarPedidosMasajista();
//            
//             for(Pedido p:pedidos){
//                for(Item i:p.getItems()){
//                    if(i instanceof ServicioDeMasaje){
//                        if(((ServicioDeMasaje)i).getEstado()==EstadoServicio.EN_PROCESO){
//                            pedidosMasaje.add(p);
//                        }
//                    }
//                }
//            }
        /*HuespedDAO daoHuesped = new HuespedMYSQL();
        ArrayList<Huesped> huespedes=new ArrayList<Huesped>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try{
            huespedes=daoHuesped.listarHuespedXPeriodo(sdf.parse("1-01-0001"),sdf.parse("30-09-2024"));
            for (Huesped aux : huespedes){
                System.out.println(aux.getIdPersona() + " " + aux.getNombre());
            }

        }catch(Exception ex){
            
        }*/
        
        
        /*HabitacionDAO daoHabitacion;
        ArrayList<Habitacion> habitaciones=new ArrayList<>();
        try{
            daoHabitacion=new HabitacionMYSQL();
            habitaciones=daoHabitacion.ListarTodasHabitaciones();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        */

        /*SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
<<<<<<< HEAD
        Date fechaINI = sdf.parse("01-01-0001");
        Date fechaFin = sdf.parse("30-09-2024");
=======
        //Date fechaINI = sdf.parse("01-01-0001");
        //Date fechaFin = sdf.parse("30-09-2024");
>>>>>>> 0893468a3bb04b787181c890a2bd4fd48f4b57a5
        FamiliarDAO daoFamiliar;
        ArrayList<Familiar> familiares = new ArrayList<>();
        
            try{
                daoFamiliar = new FamiliarMYSQL();
                familiares = daoFamiliar.listarHabitacionesFamiliarXPeriodo(sdf.parse("01-01-0001"), sdf.parse("30-09-2024"));
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            } 
        */

        
        /*BebidaDAO daoBebida=new BebidaMYSQL();
        ArrayList<Bebida>bebidas=daoBebida.listarBebidas();
>>>>>>> 0893468a3bb04b787181c890a2bd4fd48f4b57a5
        
        for(Bebida b:bebidas){
            System.out.println(b.getNombre()+"\n"); 
        }*/
        
        /*CuidadoPersonalDAO daoCuidadoPersonal=new CuidadoPersonalMYSQL();
        ArrayList<CuidadoPersonal>cuidados=daoCuidadoPersonal.listarCuidadosPersonales();
        
        for(CuidadoPersonal c:cuidados){
            System.out.println(c.getNombre()+"\n");
        }*/
        
        /*AlimentoDAO daoAlimento=new AlimentoMYSQL();
        ArrayList<Alimento>alimentos=daoAlimento.listarAlimentosPorNombre("");
        
        for(Alimento c:alimentos){
            System.out.println(c.getNombre()+"\n");
        }*/
        
        CuidadoPersonalDAO daoCuidadoPersonal=new CuidadoPersonalMYSQL();
        ArrayList<CuidadoPersonal>cuidados=daoCuidadoPersonal.listarCuidadosPersonalesPorNombre("dur");
        
        for(CuidadoPersonal c:cuidados){
            System.out.println(c.getNombre()+"\n");
        }
    }  
}
