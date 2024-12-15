/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.lothel.ventas.model;

import java.util.ArrayList;

/**
 *
 * @author efeproceres
 */
public class EmpresaProveedora {
    private int idEmpresa;
    private String ruc;
    private String razonSocial;
    private String correo;
    private boolean activo;
    private ArrayList<Producto> productos;

    public EmpresaProveedora() {
    }

    
    /*
    public EmpresaProveedora(int idEmpresa, String ruc, String razonSocial, String correo, boolean activo, ArrayList<Producto> productos) {
        this.idEmpresa = idEmpresa;
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.correo = correo;
        this.activo = activo;
        this.productos = productos;
    }*/
    public EmpresaProveedora( String ruc, String razonSocial, String correo, boolean activo) {
         this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.correo = correo;
        this.activo = activo;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    
    
}

