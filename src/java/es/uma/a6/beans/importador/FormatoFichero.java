
package es.uma.a6.beans.importador;

import java.io.File;
import java.io.IOException;

public abstract class FormatoFichero {
    
    protected IFormatoFicheroNotificable notificar;
    
    public void setFormatoNotificable(IFormatoFicheroNotificable ctr) {
        notificar = ctr;
    }
    
    public abstract Object leer(File file) throws IOException;
    public abstract void escribir(File file, Object object);
    public abstract String getExtension();
    
}
