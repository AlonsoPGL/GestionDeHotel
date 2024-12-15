package pe.edu.pucp.lothel.ventas.mysql;

import java.util.ArrayList;
import pe.edu.pucp.lothel.ventas.dao.EmpresaProveedoraDAO;
import pe.edu.pucp.lothel.ventas.model.EmpresaProveedora;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import pe.edu.pucp.lothel.manager.DBManager;

/**
 *
 * @author gumar
 */
public class EmpresaProveedoraMYSQL implements EmpresaProveedoraDAO{
    
    private Connection con;
    private CallableStatement cs;
    private ResultSet rs;

    @Override
    public int insertar(EmpresaProveedora empresaProveedora) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            cs  = con.prepareCall("{call INSERTAR_EMPRESA_PROVEEDORA(?,?,?,?,?)}");
            cs.registerOutParameter("_idEmpresaProveedora", java.sql.Types.INTEGER);
            cs.setString("_ruc", empresaProveedora.getRuc());
            cs.setString("_razonSocial", empresaProveedora.getRazonSocial());
            cs.setString("_correo", empresaProveedora.getCorreo());
            cs.setBoolean("_activo", empresaProveedora.isActivo());
            cs.executeUpdate();
            empresaProveedora.setIdEmpresa(cs.getInt("_idEmpresaProveedora"));
            resultado = empresaProveedora.getIdEmpresa();
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try{con.close();}catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public int modificar(EmpresaProveedora empresaProveedora) {
        int resultado = 0;
        try {
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call MODIFICAR_EMPRESA_PROVEEDORA(?,?,?,?,?)}");
            cs.setInt("_idEmpresaProveedora", empresaProveedora.getIdEmpresa());
            cs.setString("_ruc", empresaProveedora.getRuc());
            cs.setString("_razonSocial", empresaProveedora.getRazonSocial());
            cs.setString("_correo", empresaProveedora.getCorreo());
            cs.setBoolean("_activo", empresaProveedora.isActivo());
            resultado = cs.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return resultado;

    }

    @Override
    public int eliminar(int idEmpresa) {
        int resultado = 0;
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call ELIMINAR_EMPRESA_PROVEEDORA(?)}");
            cs.setInt("_idEmpresaProveedora", idEmpresa);
            resultado = cs.executeUpdate();
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}            
        }
        return resultado;
    }

    @Override
    public ArrayList<EmpresaProveedora> listarEmpresas() {
        ArrayList<EmpresaProveedora> empresasProveedoras = new ArrayList<EmpresaProveedora>();
        try{
            con = DBManager.getInstance().getConnection();
            cs = con.prepareCall("{call LISTAR_EMPRESAS_PROVEEDORAS()}");
            //String sql = "SELECT * FROM EMPRESA_PROVEEDORA";
            rs = cs.executeQuery();
            
            while(rs.next()) {
                EmpresaProveedora empresaProveedora = new EmpresaProveedora();
                empresaProveedora.setIdEmpresa(rs.getInt("idEmpresaProveedora"));
                empresaProveedora.setRuc(rs.getString("ruc"));
                empresaProveedora.setRazonSocial(rs.getString("razonSocial"));
                empresaProveedora.setCorreo(rs.getString("correo"));
                empresaProveedora.setActivo(rs.getBoolean("activo"));
                empresasProveedoras.add(empresaProveedora);  
            }
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{rs.close();}catch(Exception ex){System.out.println(ex.getMessage());}
            try{con.close();}catch(Exception ex){System.out.println(ex.getMessage());}
        }
        return empresasProveedoras;
    }
    
}


