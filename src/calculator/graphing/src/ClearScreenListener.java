package calculator.graphing.src;

import java.util.EventListener;

/**
 * Created by Andr�s on 18/09/2015.
 * Interfaz que funge como intermediario entre
 * las clases "Toolbar" y "GraphArea" para que
 * al presionar el boton de "Limpiar" en la barra de
 * herramientas, esto tenga efecto sobre el �rea de graficado
 * de la aplicaci�n.
 * Se utiliza la interfaz para mantener a todas las clases
 * lo mas independientes posible, unas de otras.
 */
public interface ClearScreenListener extends EventListener{
    /**
     * M�todo que se encarga de realizar acciones debidas sobre la aplicaci�n
     * dado que se hizo click sobre el bot�n de limpiar la pantalla.
     */
    public void clearScreenEventOccurred();
}
