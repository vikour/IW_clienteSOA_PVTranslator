/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.a6.beans;

import es.uma.a6.ws.Modulo;
import es.uma.a6.ws.WSPVTranslator_Service;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author vikour
 */
@Named(value = "moduloFormBeans")
@Dependent
public class ModuloFormBeans {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/WSPV_Translator/WSPV_Translator.wsdl")
    private WSPVTranslator_Service service;

    @Inject private ConfigurationSessionBeans sesion;
    boolean creationMode;
    
    private Modulo m;
    
    /**
     * Creates a new instance of ModuloFormBeans
     */
    public ModuloFormBeans() {
    }
    
    @PostConstruct
    public void init(){
        m=sesion.getModulo();
        if(m==null){
            m = new Modulo();
            creationMode=true;
        }else{
            creationMode=false;
        }
    }
    
    
    /*
        Getters y setters
    */

    public Modulo getM() {
        return m;
    }

    public void setM(Modulo m) {
        this.m = m;
    }
    
    /*
        Método que, si no hay módulo seleccionado, creará uno nuevo y si hubiera uno seleccionado, lo modificará. 
    */
    
    public String cambioModulo(){
        if(creationMode==false){   //se modifica el módulo
            editModulo(m);
        }else{                                  //se crea un nuevo módulo
            createModulo(m);
        }
        return "index.xhtml";
    }
    
    public String atras(){
        return "index.xhtml";
    }

    private void editModulo(es.uma.a6.ws.Modulo entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        es.uma.a6.ws.WSPVTranslator port = service.getWSPVTranslatorPort();
        port.editModulo(entity);
    }

    private void createModulo(es.uma.a6.ws.Modulo entity) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        es.uma.a6.ws.WSPVTranslator port = service.getWSPVTranslatorPort();
        port.createModulo(entity);
    }
}
