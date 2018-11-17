/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.a6.beans.importador;

/**
 *
 * @author Vikour
 */
public interface IFormatoFicheroNotificable {
    
    public void alertFormatoFichero(String mensaje);
    public boolean confirmSobrescribirFormatoFichero(Object [] key);
    
}
