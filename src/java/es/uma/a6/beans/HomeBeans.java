/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.a6.beans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author vikour
 */
@Named(value = "homeBeans")
@RequestScoped
public class HomeBeans {

    /**
     * Creates a new instance of HomeBeans
     */
    public HomeBeans() {
    }
    
}
