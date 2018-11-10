/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.a6.beans;


import es.uma.a6.ws.Modulo;
import es.uma.a6.ws.WSPVTranslator_Service;
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

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/WSPV_Translator/WSPV_Translator.wsdl")
    private WSPVTranslator_Service service;

    @Inject ConfigurationSessionBeans config;
    
    private List<Modulo> modulos;
    
    private Modulo moduloSeleccionado;
    
    

    /**
     * Creates a new instance of HomeBeans
     */
    
    public HomeBeans() {
    }
    /*
    @PostConstruct
    public void init(){
       modulos=this.findAllModulo();
        moduloSeleccionado=null;
    }*/

    @PostConstruct
    public void initialize() {

        config.setModulo(null);
        modulos = findAllModulo();

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
    
    public void doBorrar(Modulo m){
        
        removeModulo(m);
        modulos=findAllModulo();
        //return "index.xhtml";
        
    }
    
    
    public String doEditar(Modulo m){
        config.setModulo(m);
        return "moduloForm.xhtml";
        
    }
    
    public String doNew(){
        config.setModulo(null);
        return "moduloForm.xhtml";
        
    }
    
    public String goCampanya(Modulo m){
        config.setModulo(m);
        return "campanyas.xhtml";
    }

    private java.util.List<es.uma.a6.ws.Modulo> findAllModulo() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        es.uma.a6.ws.WSPVTranslator port = service.getWSPVTranslatorPort();
        return port.findAllModulo();
    }

    private void removeModulo(es.uma.a6.ws.Modulo entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        es.uma.a6.ws.WSPVTranslator port = service.getWSPVTranslatorPort();
        port.removeModulo(entity);
    }

}
