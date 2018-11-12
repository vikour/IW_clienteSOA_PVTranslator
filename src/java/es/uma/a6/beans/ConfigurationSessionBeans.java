/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.a6.beans;

import es.uma.a6.ws.Campaña;
import es.uma.a6.ws.Modulo;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;


/**
 *
 * @author vikour
 */
@Named(value = "configurationSessionBeans")
@SessionScoped
public class ConfigurationSessionBeans implements Serializable {

    private Modulo moduloSeleccionado;
    private Campaña campañaSeleccionada;

    
    public ConfigurationSessionBeans() {
    }
    
    @PostConstruct
    public void init(){
        moduloSeleccionado=null;
        
    }
    
    public Modulo getModulo(){
        return moduloSeleccionado;
    }
    
    public void setModulo(Modulo m){
        moduloSeleccionado=m;
    }
    
    public Campaña getCampañaSeleccionada() {
        return campañaSeleccionada;
    }

    public void setCampañaSeleccionada(Campaña campañaSeleccionada) {
        this.campañaSeleccionada = campañaSeleccionada;
    }
    
    
}
