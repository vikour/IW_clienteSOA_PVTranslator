/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.a6.beans;

import es.uma.a6.beans.importador.FormatoCampaña;
import es.uma.a6.beans.importador.FormatoModulo;
import es.uma.a6.beans.importador.Importador;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Vikour
 */
@Named(value = "importarBean")
@RequestScoped
public class ImportarBean {

    private Part file;
    
    private static String VALID_CONTENT_TYPE = "application/x-zip-compressed";
    private static String VALID_EXTENSION = ".zip";

    @PostConstruct
    public void init() {
        file = null;
    }
    
    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    
    /**
     * Llamada del usuario que importará el fichero establecido como parámetro en
     * el formulario.
     */
    
    public void doImport() {
        File f = null;
        Importador importador;
        boolean importado = false;
        
        try {
            if (file != null) {
                f = subirFichero();
                importador = new Importador(new FormatoModulo(), f);
                importado = intentarImprotar(importador);
                
                if (!importado) {
                    importador.setFmt(new FormatoCampaña());
                    importado = intentarImprotar(importador);
                }
                
                if (!importado) 
                    FacesContext.getCurrentInstance()
                   .addMessage(null, new FacesMessage("Se esperaba un formato de módulo o campaña."));
            }
        }
        finally  {
            // Borramos el fichero subido si no es nulo, ya que el importador borra
            // los ficheros intermedios, no el fichero de importación.
            if (f != null)
                f.delete();
        }
        
    }
    
    /**
     * Intenta importar ejecutar el método importar del importador.
     * 
     * @param importador Objeto importador
     * 
     * @return Verdadero si consiguió importar el fichero, falso en otro caso.
     */
    
    private boolean intentarImprotar(Importador importador) {
        boolean exito = true;
        
        try {
            importador.importar();
        }
        catch (Exception ex) {
            exito = false;
        }
        return exito;
    }

    private File subirFichero() {
        File f = new File(file.getSubmittedFileName());
        
        try (InputStream input = file.getInputStream()) {
            Files.copy(input, f.toPath());
        }
        catch(Exception ex) {
            System.out.println("PENEE");
            file = null;
        }
        
        return f;
    }

    private boolean ficheroValido(Part file) {
        boolean error = false;
        
        if (!file.getContentType().equals(VALID_CONTENT_TYPE) || 
            !file.getSubmittedFileName().contains(VALID_EXTENSION)) 
        {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("Se esperaba un fichero comprimido en ZIP."));
            error = true;
        }
        
        return !error;
    }
    
    
    
}
