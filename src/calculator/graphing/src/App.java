package calculator.graphing.src;

import javax.swing.*;

/**
 * Created by Andr�s on 15/09/2015.
 * Clase que ejecuta la aplicaci�n.
 */
public class App{
    /**
     * M�todo main que inicializa nuestra aplicaci�n en un
     * thread de ejecuci�n.
     * @param args - argumentos recibidos de l�nea de comando.
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}