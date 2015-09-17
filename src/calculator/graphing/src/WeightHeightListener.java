package calculator.graphing.src;

/**
 * Created by Andr�s on 16/09/2015.
 * Interfaz que funge como intermediario entre
 * las clases "FieldSet" y "GraphArea" para que
 * al cambiar el tama�o de la ventana, se muestre en
 * el campo de opciones cu�l es el alto y ancho de la
 * nueva ventana modificada.
 * Se utiliza la interfaz para mantener a todas las clases
 * lo mas independientes posible, unas de otras.
 */
public interface WeightHeightListener {
    /**
     * M�todo que determina las acciones a realizar tras recibir
     * nuevos valores del ancho y alto de un �rea de trabajo de la
     * aplicaci�n.
     * @param newWidth - nuevo valor (en pixeles) del ancho.
     * @param newHeight - nuevo valor (en pixeles) del alto.
     */
    public void windowSizeChangeOccurred(int newWidth, int newHeight);
}
