package calculator.graphing.src;

import javax.swing.*;

/**
 * Created by Andr�s on 15/09/2015.
 * Clase que ejecuta la aplicaci�n.
 */
public class App{

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}