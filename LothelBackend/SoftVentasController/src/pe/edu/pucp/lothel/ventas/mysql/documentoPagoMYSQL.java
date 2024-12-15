/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.lothel.ventas.mysql;

import java.util.ArrayList;
import pe.edu.pucp.lothel.ventas.dao.documentoPagoDAO;
import pe.edu.pucp.lothel.ventas.model.DocumentoDePago;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import pe.edu.pucp.lothel.manager.DBManager;
import pe.edu.pucp.lothel.ventas.model.Pedido;
import pe.edu.pucp.lothel.ventas.model.TipoDocumento;
/**
 *
 * @author efeproceres
 */
public class documentoPagoMYSQL implements documentoPagoDAO{
    private Connection con;
    private PreparedStatement pst;
    private CallableStatement cs;
    private ResultSet rs;
    private Statement st;
    
    @Override
    public int insertar(DocumentoDePago documentoPago) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call INSERTAR_DOCUMENTOPAGO(?,?,?)}");
            cs.registerOutParameter("_idDocumentoDePago", java.sql.Types.INTEGER);
            cs.setString("_tipoDocumento", documentoPago.getTipo().toString());
            cs.setDate("_fechaEmision",  new java.sql.Date(documentoPago.getFechaEmision().getTime()));
            cs.executeUpdate();
            documentoPago.setIdDocumentoPago(cs.getInt("_idDocumentoDePago"));
            resultado = documentoPago.getIdDocumentoPago();   
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public int modificar(DocumentoDePago documentoPago) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            
            cs = con.prepareCall("{call MODIFICAR_DOCUMENTOPAGO(?,?)}");
            cs.setInt("_idDocumentoDePago", documentoPago.getIdDocumentoPago());
            cs.setString("_tipoDocumento", documentoPago.getTipo().toString());
            resultado = cs.executeUpdate();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;
    }

    @Override
    public int eliminar(int idDocumentoPago) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();

            cs = con.prepareCall("{call Eliminar_DocumentoPago(?)}");
            cs.setInt("_idDocumentoPago", idDocumentoPago);
            resultado = cs.executeUpdate();
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;    
    }

    @Override
    public ArrayList<DocumentoDePago> listarDocumentos() {
        ArrayList<DocumentoDePago> documentos =  new ArrayList<DocumentoDePago>();
        
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_DOCUMENTOPAGO()}");
            //String sql = "SELECT * FROM espacio";
            rs = cs.executeQuery();
            
            while(rs.next()){
                DocumentoDePago documento = new DocumentoDePago();
                Pedido pedido = new Pedido();
                documento.setIdDocumentoPago(rs.getInt("idDocumentoDePago"));
                documento.setTipo(TipoDocumento.valueOf(rs.getString("tipoDocumento")));
                documento.setFechaEmision(rs.getDate("fechaEmision"));
                documentos.add(documento);
            }   
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return documentos;
    }

}
