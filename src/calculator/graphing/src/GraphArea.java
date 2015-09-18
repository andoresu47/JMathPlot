package calculator.graphing.src;

import calculator.evaluating.src.Coordinate;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Andr�s on 15/09/2015.
 * Clase que maneja el �rea de trazado de la gr�fica.
 */
public class GraphArea extends JPanel {

    private LinkedList<Coordinate> coordinates = null;

    private WeightHeightListener weightHeightListener;

    /**
     * Constructor que recibe como argumento a un escucha, que
     * se encarga de hacerle saber las dimensiones de esta ventana
     * al panel de opciones para cada cambio que haya en esta. Se
     * inicializa en el constructor, pues se necesita que se accione
     * desde que se crea una instancia de la clase.
     * @param weightHeightListener - escucha que maneja la comunicaci�n entre
     *                             esta clase y "FieldSet".
     */
    public GraphArea(WeightHeightListener weightHeightListener){
        setWeightHeightListener(weightHeightListener);
    }

    /**
     * M�todo que se encarga del trazado de pixeles en el �rea
     * destinada para el trazado de la gr�fica. Este m�todo comienza
     * a ejecutarse tan pronto se crea una instancia de la clase. As� mismo,
     * est� constantemente pendiente de cambios.
     * @param g - componente necesario para la sobreescritura de este m�todo, que
     *          se encarga de pintar cualquier elemento que se necesite sobre
     *          el �rea destinada para ello.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(Color.WHITE);

        Dimension size = getSize();
        int width = size.width ;
        int height = size.height;

        setAxes(width, height, g);

        if(coordinates != null){
            drawGraph(g);
        }

        weightHeightListener.windowSizeChangeOccurred(width, height);
    }

    /**
     * M�todo que inicializa el conjunto de coordenadas de puntos a graficar.
     * @param coordinates - coordenadas de puntos.
     */
    public void setCoordinates(LinkedList<Coordinate> coordinates) {
        this.coordinates = coordinates;
        this.repaint();
    }

    /**
     * M�todo que se encarga de definir los ejes coordenados, y los
     * reescala seg�n se modifique la ventana de visualizaci�n.
     * @param width - ancho actual del �rea de graficado.
     * @param height - largo actual del �rea de graficado.
     * @param g - instancia de "Graphics" necesaria para el trazado de pixeles.
     */
    public void setAxes(int width, int height, Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);

        g2d.drawLine(0, height / 2, width, height / 2);
        g2d.drawLine(width / 2, 0, width / 2, height);

        g.setFont(new Font("Verdana", Font.PLAIN, 15));
        g.drawString("x", width - 20, height / 2 + 20);
        g.drawString("y", width/2 - 20, 20);
    }

    /**
     * M�todo que se encarga de pintar el gr�fico en su �rea designada. Para
     * esto, se toma la lista de coordenadas de pixeles y se van dibujando una a
     * una, uniendolas por pares con l�neas rectas. Dado que con muchos puntos, el
     * efecto de la recta se pierde y si se pueden apreciar formas curvas.
     * @param g - instancia de "Graphics" necesaria para el trazado de pixeles.
     */
    public void drawGraph(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(2));

        int x1,x2,
            y1,y2;

        Coordinate currentPoint;

        for (int i = 1; i < coordinates.size(); i++) {
            currentPoint = coordinates.get(i-1);
            x1 = (int) currentPoint.getxCoordinate();
            y1 = (int) currentPoint.getyCoordinate();

            currentPoint = coordinates.get(i);
            x2 = (int) currentPoint.getxCoordinate();
            y2 = (int) currentPoint.getyCoordinate();

            //Condici�n para que no se dibuje una l�nea entre puntos separados por una as�ntota.
            if((y2 - y1) < 1000){
                g2d.drawLine(x1, y1, x2, y2);
            }
        }
    }

    /**
     * M�todo que asigna un escucha recibido como par�metro al escucha
     * definido en la estructura de la clase.
     * @param weightHeightListener - escucha a asignar al propio de la clase.
     */
    public void setWeightHeightListener(WeightHeightListener weightHeightListener) {
        this.weightHeightListener = weightHeightListener;
    }
}
