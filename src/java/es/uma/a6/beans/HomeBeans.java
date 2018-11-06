/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.a6.beans;


import es.uma.a6.wsmodulo.Modulo;
import es.uma.a6.wsmodulo.WSModulo_Service;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author vikour
 */
@Named(value = "homeBeans")
@RequestScoped
public class HomeBeans {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/WS_Modulo/WS_Modulo.wsdl")
    private WSModulo_Service service;
    @Inject ConfigurationSessionBeans config;
    
    private List<Modulo> modulos;
    
    private Modulo moduloSeleccionado;
    
    

    /**
     * Creates a new instance of HomeBeans
     */
    public HomeBeans() {
    }
    
    @PostConstruct
    public void init(){
        modulos=this.findAll();
    }

    public List<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }

    public Modulo getModuloSeleccionado() {
        return moduloSeleccionado;
    }

    public void setModuloSeleccionado(Modulo moduloSeleccionado) {
        this.moduloSeleccionado = moduloSeleccionado;
    }
    
    

    private java.util.List<es.uma.a6.wsmodulo.Modulo> findAll() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        es.uma.a6.wsmodulo.WSModulo port = service.getWSModuloPort();
        return port.findAll();
    }
    public String doBorrar(Modulo m){
        
        remove(m);
        return "index.xhtml";
        
    }
    
    public void doEditar(Modulo m){
        
        
    }

    private void remove(es.uma.a6.wsmodulo.Modulo entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        es.uma.a6.wsmodulo.WSModulo port = service.getWSModuloPort();
        port.remove(entity);
    }
    
    
    
    
    
}
