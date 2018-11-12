/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.a6.beans;


import es.uma.a6.ws.Campaña;
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
@Named(value = "campanyasBeans")
@RequestScoped
public class CampanyasBeans {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/WSPV_Translator/WSPV_Translator.wsdl")
    private WSPVTranslator_Service service;
    
    @Inject
    private ConfigurationSessionBeans sesion;
    
    private Modulo modulo;
    private List<Campaña> campañas;
    
    public CampanyasBeans() {
    }
    
    @PostConstruct
    public void init(){
        
        modulo = sesion.getModulo();
        campañas= this.findCampanyaByModulo(modulo);
        //campañas = this.findAllCampanya(); //pruebas
    }

    public Modulo getModulo() {
        return modulo;
    }

    public List<Campaña> getCampañas() {
        return campañas;
    }
    
    public void doRemove(Campaña c){
        campañas.remove(c);
        this.removeCampanya(c);        
    }
    
    public String doNuevo(){
        sesion.setCampañaSeleccionada(null);
        return "campanyasForm.xhtml";
    }
    
    public String doEditar(Campaña c){
        sesion.setCampañaSeleccionada(c);
        return "campanyasForm.xhtml";
    }
    
    public String doVer(Campaña c){
        // Not Implemented yet
        return null;
    }
    
    
    
    
    
    /*
        WS
    */

    private java.util.List<es.uma.a6.ws.Campaña> findCampanyaByModulo(es.uma.a6.ws.Modulo m) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        es.uma.a6.ws.WSPVTranslator port = service.getWSPVTranslatorPort();
        return port.findCampanyaByModulo(m);
    }

    
    private void removeCampanya(es.uma.a6.ws.Campaña entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        es.uma.a6.ws.WSPVTranslator port = service.getWSPVTranslatorPort();
        port.removeCampanya(entity);
    }    
    
    /*
    private java.util.List<es.uma.a6.ws.Campaña> findAllCampanya() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        es.uma.a6.ws.WSPVTranslator port = service.getWSPVTranslatorPort();
        return port.findAllCampanya();
    }
    */
}
