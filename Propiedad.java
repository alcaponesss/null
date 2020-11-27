/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiagramaDeClases;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author matia
 */
@Entity
@Table(name="propiedad")
public class Propiedad implements Serializable {
    
    @Id
    @Column(name="codigos")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    @Column(name="direccion")
    private String Direccion;
    @Column(name="numerodepisos")
    private String numerodepiso;
    @Column(name="nombres")
    private String nombre;
    @Column(name="estados")
    private String Estado;
    @Column(name="Disponible")
    private String Disponible;
    @Column(name="Metro_Cuadrado_Propio")
    private double metroCuadradoPropio;
    @Column(name ="Metro_Cuadrado_Comun")
    private double metroCuadradoComun;
    
    
    
    @ManyToOne
    @JoinColumn(name="Id_cliente")
    private Cliente clientes;
    
    

    @OneToMany(mappedBy = "propiedad")
    private List<Alquiler>alquiler;
    
    @OneToOne
    @JoinColumn(name="Id_Ventas")
    private Venta  ventas;
    
    
    @ManyToOne
    @JoinColumn(name="Id_Propiedad")
    private Anuncio anuncios;

    
    @OneToOne
    @JoinColumn(name = "tasacion_fk")
    private Tasacion tasacion;
    
  
    @ManyToOne
    @JoinColumn(name="propiedades")
    private TipoPropiedad tipropiedad;
    
    @OneToMany(mappedBy = "propiedades")
    private List<PagoExpensa>pagoexpensa;
    
    @ManyToOne
    @JoinColumn(name="ciudad")
    private Ciudad ciudades;
    
    @ManyToOne
    @JoinColumn(name = "propiedades_DeEdificio")
    private Edificio edificios;
    public Propiedad() {
    }

   

   

    public Propiedad(String Direccion, String numerodepiso, String nombre, String Estado, double metroCuadradoPropio, double metroCuadradoComun, Cliente clientes, TipoPropiedad tipropiedad, Ciudad ciudades, Edificio edificios) {
        this.Direccion = Direccion;
        this.numerodepiso = numerodepiso;
        this.nombre = nombre;
        this.Estado = Estado;
        this.metroCuadradoPropio = metroCuadradoPropio;
        this.metroCuadradoComun = metroCuadradoComun;
        this.clientes = clientes;
        this.tipropiedad = tipropiedad;
        this.ciudades = ciudades;
        this.edificios = edificios;
    }

   
    
    
    
   
    
    

    public Propiedad(String Estado) {
        this.Estado = Estado;
    }
    
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getNumerodepiso() {
        return numerodepiso;
    }

    public void setNumerodepiso(String numerodepiso) {
        this.numerodepiso = numerodepiso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getDisponible() {
        return Disponible;
    }

    public void setDisponible(String Disponible) {
        this.Disponible = Disponible;
    }

    public double getMetroCuadradoPropio() {
        return metroCuadradoPropio;
    }

    public void setMetroCuadradoPropio(double metroCuadradoPropio) {
        this.metroCuadradoPropio = metroCuadradoPropio;
    }

    public double getMetroCuadradoComun() {
        return metroCuadradoComun;
    }

    public void setMetroCuadradoComun(double metroCuadradoComun) {
        this.metroCuadradoComun = metroCuadradoComun;
    }

    public Cliente getClientes() {
        return clientes;
    }

    public void setClientes(Cliente clientes) {
        this.clientes = clientes;
    }

    public List<Alquiler> getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(List<Alquiler> alquiler) {
        this.alquiler = alquiler;
    }

    public Venta getVentas() {
        return ventas;
    }

    public void setVentas(Venta ventas) {
        this.ventas = ventas;
    }

    public Anuncio getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(Anuncio anuncios) {
        this.anuncios = anuncios;
    }

    public Tasacion getTasacion() {
        return tasacion;
    }

    public void setTasacion(Tasacion tasacion) {
        this.tasacion = tasacion;
    }

    public TipoPropiedad getTipropiedad() {
        return tipropiedad;
    }

    public void setTipropiedad(TipoPropiedad tipropiedad) {
        this.tipropiedad = tipropiedad;
    }

    public List<PagoExpensa> getPagoexpensa() {
        return pagoexpensa;
    }

    public void setPagoexpensa(List<PagoExpensa> pagoexpensa) {
        this.pagoexpensa = pagoexpensa;
    }

    public Ciudad getCiudades() {
        return ciudades;
    }

    public void setCiudades(Ciudad ciudades) {
        this.ciudades = ciudades;
    }

    public Edificio getEdificios() {
        return edificios;
    }

    public void setEdificios(Edificio edificios) {
        this.edificios = edificios;
    }
}
    