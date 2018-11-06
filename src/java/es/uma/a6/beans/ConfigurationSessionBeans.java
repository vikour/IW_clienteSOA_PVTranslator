/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.a6.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author vikour
 */
@Named(value = "configurationSessionBeans")
@SessionScoped
public class ConfigurationSessionBeans implements Serializable {

    /**
     * Creates a new instance of ConfigurationSessionBeans
     */
    public ConfigurationSessionBeans() {
    }
    
}
