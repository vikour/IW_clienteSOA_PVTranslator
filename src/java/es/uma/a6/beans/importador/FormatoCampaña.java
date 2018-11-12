/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.a6.beans.importador;

import es.uma.a6.ws.Campaña;
import es.uma.a6.ws.Modulo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class FormatoCampaña extends FormatoFichero{

    private boolean sobreescribir;

    @Override
    public Object leer(File file) {
        BufferedReader br = null;
        Campaña campaña = null;
        
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "ISO-8859-15"));
            
            campaña = leerInfoBasica(br);
        } 
        catch (Exception ex) {
            System.out.println("PERNE");
        }
        
        return campaña;
    }

    private String readNotEmptyLine(BufferedReader br) throws IOException {
        String aux = null;
        
        do {
            aux = br.readLine();
        } while (aux !=null && aux.isEmpty());
        
        return aux;
    }

    private String extractValue(String line) {
        line = line.trim();
        return line.split("\t")[1];
    }

    @Override
    public void escribir(File file, Object objeto) {
       // no se exporta
    }
    
    @Override
    public String getExtension() {
        return "xls";
    }

    private Campaña leerInfoBasica(BufferedReader br) throws IOException {
        String line;
        Modulo modulo = null;
        Campaña campaña = null;
        String value;

        line = readNotEmptyLine(br);
        
        if (!line.contains("Módulo"))
            throw new RuntimeException("Error de formato");
        
        value = extractValue(line);
        modulo = crearModuloSiNoExiste(value);

        // leer campaña
        line = readNotEmptyLine(br);
        value = extractValue(line);
        campaña = crearCampañaSiNoExiste(value, modulo);
        
        return campaña;
    }
    
    private Campaña crearCampañaSiNoExiste(String name, Modulo modulo) {
        Campaña campaña = null;
        
        campaña = findCampanya(name);
        
        if (campaña == null) {
            campaña = new Campaña();
            
            campaña.setNombre(name);
            campaña.setModulo(modulo);
            
            try {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(new Date());
            campaña.setFecha(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            }
            catch (DatatypeConfigurationException ex) {
                System.out.println("PERNE");
            }
            
            createCampanya(campaña);
        }
        
        return campaña;
    }
    
    private Modulo crearModuloSiNoExiste(String name) {
        Modulo modulo = null;
        
        modulo = findModuloByNombre(name);
        
        if (modulo == null) {
            modulo = new Modulo();
            modulo.setNombre(name);
            createModulo(modulo);
        }
        
        return modulo;
    }

    private static Modulo findModuloByNombre(java.lang.String nombre) {
        es.uma.a6.ws.WSPVTranslator_Service service = new es.uma.a6.ws.WSPVTranslator_Service();
        es.uma.a6.ws.WSPVTranslator port = service.getWSPVTranslatorPort();
        return port.findModuloByNombre(nombre);
    }

    private static void createModulo(es.uma.a6.ws.Modulo entity) {
        es.uma.a6.ws.WSPVTranslator_Service service = new es.uma.a6.ws.WSPVTranslator_Service();
        es.uma.a6.ws.WSPVTranslator port = service.getWSPVTranslatorPort();
        port.createModulo(entity);
    }

    private static void createCampanya(es.uma.a6.ws.Campaña entity) {
        es.uma.a6.ws.WSPVTranslator_Service service = new es.uma.a6.ws.WSPVTranslator_Service();
        es.uma.a6.ws.WSPVTranslator port = service.getWSPVTranslatorPort();
        port.createCampanya(entity);
    }

    private static Campaña findCampanya(java.lang.Object id) {
        es.uma.a6.ws.WSPVTranslator_Service service = new es.uma.a6.ws.WSPVTranslator_Service();
        es.uma.a6.ws.WSPVTranslator port = service.getWSPVTranslatorPort();
        return port.findCampanya(id);
    }

}

