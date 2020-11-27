/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoraGeneral;

import DiagramaDeClases.Ciudad;
import DiagramaDeClases.Cliente;
import DiagramaDeClases.Edificio;
import DiagramaDeClases.Empleado;
import DiagramaDeClases.Propiedad;
import DiagramaDeClases.Tasacion;
import DiagramaDeClases.TipoPropiedad;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import persistencia.CiudadJpaController;
import persistencia.ClienteJpaController;
import persistencia.ControladoraPersistencia;
import persistencia.EdificioJpaController;

import persistencia.TipoPropiedadJpaController;
import persistencia.PropiedadJpaController;
import persistencia.exceptions.NonexistentEntityException;
/**
 *
 * @author matia
 */
public class ControladoraPropiedad extends ControladoraPersistencia {
     private TipoPropiedadJpaController jpatipopropiedad;
     private PropiedadJpaController jpapropiedad;
      private ClienteJpaController jpaCLiente;
        private EdificioJpaController jpaEdificio;
      
     public ControladoraPropiedad() {
         this.jpapropiedad= new PropiedadJpaController(getEmf());
         this.jpatipopropiedad = new TipoPropiedadJpaController(getEmf());
         this.jpaCLiente = new ClienteJpaController(getEmf());
         this.jpaEdificio= new EdificioJpaController(getEmf());
         
     }
     
     
     
       public List<Propiedad> getpropiedad(){
            return this.jpapropiedad.findPropiedadEntities();
        }
     
       
       public List<TipoPropiedad> gettipopropiedad() {

        return this.jpatipopropiedad.findTipoPropiedadEntities();
        
    }
       public List<Cliente>getcliente(){
           return this.jpaCLiente.findClienteEntities();
       }
     
       public List<Edificio>getedificio(){
           return this.jpaEdificio.findEdificioEntities();
       }
     
     
       public TipoPropiedad buscarPorNombretipo(String nombre) throws Exception{
         return this.jpatipopropiedad.buscarPorNombretipo(nombre);
     
        }
       
       public Propiedad buscarPorNombreestado(String estado) throws Exception{
         return this.jpapropiedad.buscarPorNombreEstado(estado);
     
       }
       
        public Propiedad buscarPorNombrepropiedad(String nombre) throws Exception{
         return this.jpapropiedad.buscarPorNombreUsuario(nombre);
     
        }
//    public void crearpro(String direccion , String numeropiso, String nombre, String estado,double propio,double metro,Cliente cli,TipoPropiedad propi,Edificio edi){
      
        
        
        
            
            
            
        public void crearestado(String estado) throws Exception{
        
            if(this.buscarPorNombretipo(estado)!=null){
                throw new Exception("El estado de propiedad:"+ estado+"Ya fue agregada");
            }
            
            Propiedad pro = new Propiedad(estado);
            this.jpapropiedad.create(pro);
     
         }

        
         public Propiedad buscarPorPropiedad(String nombre) throws Exception{
            return this.jpapropiedad.buscarPorNombreUsuario(nombre);
     
         }
        
        
       public void CrearPropiedad(String Direccion, String numerodepiso, String nombre, String Estado, double metroCuadradoPropio, double metroCuadradoComun, Cliente clientes, TipoPropiedad tipropiedad, Ciudad ciudades, Edificio edificios) throws Exception{
         
             if(this.buscarPorNombrepropiedad(nombre)!=null){
                throw new Exception("El nombre de:"+ nombre+"Ya esta registrado");
              }
           
           Propiedad propied = new Propiedad(Direccion, numerodepiso, nombre, Estado, metroCuadradoPropio, metroCuadradoComun, clientes, tipropiedad, ciudades, edificios);
           this.jpapropiedad.create(propied);
           
           
       }
        
     
        public void creartipo(String tipo) throws Exception{
        
         if(this.buscarPorNombretipo(tipo)!=null){
         throw new Exception("El Tipo de propiedad:"+ tipo+"Ya fue agregada");
         }
         
         TipoPropiedad tip = new TipoPropiedad(tipo);
         this.jpatipopropiedad.create(tip);
     
         }
     
      public  TipoPropiedad traertipo(int id){
        return this.jpatipopropiedad.findTipoPropiedad(id);
    }
    
      
    public void borrartipo(int codigo) throws NonexistentEntityException {
        this.jpatipopropiedad.destroy(codigo);
    }
    
    
}
