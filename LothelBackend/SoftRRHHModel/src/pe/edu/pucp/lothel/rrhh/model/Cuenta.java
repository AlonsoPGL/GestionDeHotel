/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pe.edu.pucp.lothel.rrhh.model;

public class Cuenta {
    
    private int idCuenta;
    private String user;
    private String password;
    private TipoCuenta tipocuenta;
    private Persona persona;

    public Cuenta(String idUsuario, String contrasnha, TipoCuenta tipocuenta,Persona persona) {
        this.user = idUsuario;
        this.password = contrasnha;
        this.tipocuenta = tipocuenta;
        this.persona=persona;
    }
    public Cuenta(){}
    public int getIdCuenta() {
        return idCuenta;
    }
    
    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String idUsuario) {
        this.user = idUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String contrasnha) {
        this.password = contrasnha;
    }

    public TipoCuenta getTipocuenta() {
        return tipocuenta;
    }

    public void setTipocuenta(TipoCuenta tipocuenta) {
        this.tipocuenta = tipocuenta;
    }
    
    
    
}
