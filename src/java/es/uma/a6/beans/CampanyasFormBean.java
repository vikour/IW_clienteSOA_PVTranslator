/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.a6.beans;

import es.uma.a6.ws.Campaña;
import es.uma.a6.ws.Modulo;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Carlos
 */
@Named(value = "campanyasFormBean")
@RequestScoped
public class CampanyasFormBean {

    @Inject
    private ConfigurationSessionBeans sesion;
    
    private boolean creationMode;
    private Campaña campaña;
    private Modulo modulo;
    private String crearEditar;
    private String error;

    /**
     * Creates a new instance of ModuloFormBeans
     */
    public CampanyasFormBean() {
    }
    
    @PostConstruct
    public void init(){
        error="";
        campaña = sesion.getCampañaSeleccionada();
        modulo = sesion.getModulo();
        if(campaña==null){
            campaña=new Campaña();
            creationMode=true;
            crearEditar="Crear";
        }else{
            creationMode=false;
            crearEditar="Editar";
        }
    }

    public Campaña getCampaña() {
        return campaña;
    }

    public void setCampaña(Campaña campaña) {
        this.campaña = campaña;
    }

    public String getCrearEditar() {
        return crearEditar;
    }

    public String getError() {
        return error;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }
    
    
    
    public void comprobarNombre(){
        
    }
    
    public String doBoton(){
        return null;
    }
    
    public String doAtras(){
        return "campanyas.xhtml";
    }
    
}
