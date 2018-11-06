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
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
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

    
    
    @EJB
    private ConfigurationSessionBeans sesion;
    
    private Modulo modulo;
    private List<Campaña> campañas;
    
    public CampanyasBeans() {
    }
    
    @PostConstruct
    public void init(){
        modulo = sesion.getModulo();
        campañas= this.findCampanyaByModulo(modulo);
    }

    private java.util.List<es.uma.a6.ws.Campaña> findCampanyaByModulo(es.uma.a6.ws.Modulo m) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        es.uma.a6.ws.WSPVTranslator port = service.getWSPVTranslatorPort();
        return port.findCampanyaByModulo(m);
    }
    
    
    
    

    
}
