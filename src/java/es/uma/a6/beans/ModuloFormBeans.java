/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.a6.beans;

import es.uma.a6.ws.Modulo;
import es.uma.a6.ws.WSPVTranslator_Service;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author vikour
 */
@Named(value = "moduloFormBeans")
@RequestScoped
public class ModuloFormBeans {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/WSPV_Translator/WSPV_Translator.wsdl")
    private WSPVTranslator_Service service;

    @Inject private ConfigurationSessionBeans sesion;
    boolean creationMode=true;
    
    private Modulo m;
    private String errorN;
    private String errorA;
    private String errorB;
    private String errorG;
    private String errorK;
    
    /**
     * Creates a new instance of ModuloFormBeans
     */
    public ModuloFormBeans() {
    }
    
    @PostConstruct
    public void init(){
        
        errorN="";
        errorA="";
        errorB="";
        errorG="";
        errorK="";
        
        m=sesion.getModulo();
        if(m!=null){
            creationMode=false;
        }else{
            m = new Modulo();
            creationMode=true;
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

    public String getErrorN() {
        return errorN;
    }

    public String getErrorA() {
        return errorA;
    }

    public String getErrorB() {
        return errorB;
    }

    public String getErrorG() {
        return errorG;
    }

    public String getErrorK() {
        return errorK;
    }
    
    /*
        Método que, si no hay módulo seleccionado, creará uno nuevo y si hubiera uno seleccionado, lo modificará. 
    */
    
    public void comprobarError(){
        if(m.getNombre().equals("")){
            errorN="Error: escriba un nombre para el módulo";
        }else if(findModuloByNombre(m.getNombre())!=null){
            errorN="Error: existe un módulo con ese nombre";
        }else{
            errorN="";
        }
    }
    
    public void comprobarA(){
        if(m.getAlpha()<0){
            errorA="Error: valor invalido para alpha";
        }else{
            errorA="";
        }
    }
    
    public void comprobarB(){
        if(m.getBeta()<0){
            errorB="Error: valor invalido para beta";
        }else{
            errorB="";
        }
    }
    
    public void comprobarG(){
        if(m.getGamma()<0){
            errorG="Error: valor invalido para gamma";
        }else{
            errorG="";
        }
    }
    
    public void comprobarK(){
        if(m.getKappa()<0){
            errorK="Error: valor invalido para kappa";
        }else{
            errorK="";
        }
    }
    
    
    
    public String doCrearEditar(){
        String next="";
        if(m.getKappa()>=0 && m.getAlpha()>=0 && m.getBeta()>=0 && m.getGamma()>=0 && findModuloByNombre(m.getNombre())==null && !m.getNombre().equals("")){
            if(creationMode==false){                //se modifica el módulo
                editModulo(m);
            }else{                                  //se crea un nuevo módulo
                createModulo(m);
            }
            next="index.xhtml";
        }else{
            next="moduloForm.xhtml";
        }
        return next;
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

    private Modulo findModuloByNombre(java.lang.String nombre) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        es.uma.a6.ws.WSPVTranslator port = service.getWSPVTranslatorPort();
        return port.findModuloByNombre(nombre);
    }

    

    
}
