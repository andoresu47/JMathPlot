package calculator.graphing.src;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Andr�s on 15/09/2015.
 * Clase que controla la interacci�n de todos
 * los elementos presentes en la aplicaci�n.
 */
public class MainFrame extends JFrame {
    private GraphArea graphArea;
    private FieldSet optionsArea;
    private Toolbar toolbar;
    private int widthOfGraphArea = 670,
                heightOfGraphArea = 500;

    /**
     * Constructor del controlador. Se inicializan los distintos elementos
     * y se organizan en el Frame.
     */
    public MainFrame(){
        super("Graficador");

        setLayout(new BorderLayout());

        graphArea = new GraphArea(new WeightHeightListener() {
            /**
             * M�todo que determina las acciones a llevar a cabo al generarse
             * un cambio en el tama�o de la ventana de la aplicaci�n. Se avisar�
             * al �rea de opciones el nuevo tama�o (ancho y alto en pixeles) de la
             * zona de graficaci�n.
             * @param newWidth - nuevo valor del ancho del �rea de graficaci�n.
             * @param newHeight - nuevo valor del alto del �rea de graficaci�n.
             */
            @Override
            public void windowSizeChangeOccurred(int newWidth, int newHeight) {
                optionsArea.setGraphWidth(newWidth);
                optionsArea.setGraphHeight(newHeight);
                setWidth(newWidth);
                setHeight(newHeight);
            }
        });
        optionsArea = new FieldSet();
        toolbar = new Toolbar();

        optionsArea.setInputListener(new InputListener() {
            /**
             * M�todo que determina las acciones a llevar a cabo al hacer click sobre el
             * bot�n de "Graficar" de la zona de opciones. Se validar� la expresi�n como
             * una f�rmula v�lida en notaci�n infija, se generar�n los tokens y se evaluar�
             * en varios puntos determinados por el ancho de la ventana y por el rango de
             * valores deseado para el eje X.
             * @param expression - cadena que representa la f�rmula matem�tica a tokenizar,
             *                   validar y evaluar.
             * @param xMin - valor m�nimo del rango en el eje X.
             * @param xMax - valor m�ximo del rango en el eje X.
             */
            @Override
            public void inputEventOccurred(String expression, String xMin, String xMax) {

            }
        });

        add(toolbar, BorderLayout.PAGE_START);
        add(graphArea, BorderLayout.CENTER);
        add(optionsArea, BorderLayout.PAGE_END);

        this.getContentPane().setPreferredSize(new Dimension(670, 500));
        this.pack();
        //this.setSize(670, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * M�todo que asigna un entero a la variable de ancho del
     * �rea de graficaci�n.
     * @param width - entero que representa ( en pixeles) el nuevo valor del
     *              ancho del �rea de graficaci�n.
     */
    public void setWidth(int width) {
        this.widthOfGraphArea = width;
    }

    /**
     * M�todo que asigna un entero a la variable de alto del
     * �rea de graficaci�n.
     * @param height - entero que representa (en pixeles) el nuevo valor de
     *               la altura del �rea de graficaci�n.
     */
    public void setHeight(int height) {
        this.heightOfGraphArea = height;
    }
}
