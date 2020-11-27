/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoraGeneral;
    import persistencia.ControladoraPersistencia;
    import ControladoraGeneral.ControladoraLogin;
    import Vista.GestionTasacion;
import Vista.Login;
import persistencia.CiudadJpaController;

/**
 *
 * @author matia
 */
public class ControladoraGneral {
    
    private  ControladoraLogin controladoralogin;
    private ControladoraCliente controladoracliente;
    private ContraladoraTasacion ContraladoraTasacione;
     private ControladoraPropiedad ContraladoraPropiedad;
     private ControladoraServicio ContraladoraServicio;
     private ControladoraEdificio ControladoraEdificios;
     private ControladoraCiudad  ControladoreCiudad;
    public ControladoraGneral(){
       
        this.controladoralogin = new ControladoraLogin();
        this.controladoracliente= new ControladoraCliente();
        this.ContraladoraTasacione = new ContraladoraTasacion();
        this.ContraladoraPropiedad = new ControladoraPropiedad();
        this.ContraladoraServicio= new ControladoraServicio();
        this.ControladoraEdificios= new ControladoraEdificio();
        this.ControladoreCiudad= new ControladoraCiudad();
    }

    public ControladoraLogin getControladoralogin() {
        return controladoralogin;
    }

    public ControladoraCliente getControladoracliente() {
        return controladoracliente;
    }

    public ContraladoraTasacion getContraladoraTasacione() {
        return ContraladoraTasacione;
    }

    public ControladoraPropiedad getContraladoraPropiedad() {
        return ContraladoraPropiedad;
    }

    public ControladoraServicio getContraladoraServicio() {
        return ContraladoraServicio;
    }

    public ControladoraEdificio getControladoraEdificios() {
        return ControladoraEdificios;
    }

    public ControladoraCiudad getControladoreCiudad() {
        return ControladoreCiudad;
    }
    
    
}         

