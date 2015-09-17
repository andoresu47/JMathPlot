package calculator.graphing.src;

import java.util.EventListener;

/**
 * Created by Andr�s on 16/09/2015.
 * Interfaz que funge como intermediario entre
 * las clases "FieldSet" y "GraphArea" para que
 * al presionar el boton de "Graficar" en el �rea de
 * opciones esto tenga efecto sobre el �rea de graficado
 * de la aplicaci�n.
 * Se utiliza la interfaz para mantener a todas las clases
 * lo mas independientes posible, unas de otras.
 */
public interface InputListener extends EventListener{
    /**
     * M�todo que se encarga de realizar acciones debidas con la
     * expresi�n introducida por el usuario, junto con el rango en X,
     * para llevar a cabo la evaluaci�n y posterior graficaci�n de la
     * funci�n.
     * @param expression - cadena introducida por el usuario que se espera
     *                   sea una funci�n matem�tica v�lida, en notaci�n infija.
     * @param xMin - cadena que representa el valor m�nimo del rango en X.
     * @param xMax - cadena que representa el valor m�ximo del rango en X.
     */
    public void inputEventOccurred(String expression, String xMin, String xMax);
}
