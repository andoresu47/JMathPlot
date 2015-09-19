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

    private LinkedList<LinkedList<Coordinate>> graphsList;

    private Coordinate center;

    private WeightHeightListener weightHeightListener;

    private int pastWidth,
                pastHeight;

    /**
     * Constructor que inicializa los elementos de la clase.
     */
    public GraphArea(){
        graphsList = new LinkedList<>();
        center = new Coordinate(0, 0);
        pastWidth = 0;
        pastHeight = 0;
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
        int presentWidth = size.width ;
        int presentHeight = size.height;

        //Se dibujan los ejes, siempre tomando en cuenta el ancho y alto
        setGrid(presentWidth, presentHeight, g);
        setAxes(presentWidth, presentHeight, g);

        //Se dibujan en pantalla cada uno de los conjuntos de coordenadas de "graphList"
        if(!graphsList.isEmpty()){
            for (int i = 0; i < graphsList.size(); i++) {
                LinkedList<Coordinate> coordinates = graphsList.get(i);
                drawGraph(coordinates, g, i);
            }
        }

        //S�lo si hubo un cambio de tama�o de ventana, se hace la notificaci�n al MainFrame
        if(presentWidth != pastWidth || presentHeight != pastHeight){
            weightHeightListener.windowSizeChangeOccurred(presentWidth, presentHeight);
        }

        pastWidth = presentWidth;
        pastHeight = presentHeight;
    }

    /**
     * M�todo que agrega conjuntos de coordenadas al conjunto de gr�ficas de la clase,
     * y actualiza la vista de graficaci�n.
     * @param coordinates - conjunto de coordenadas de puntos.
     */
    public void addCoordinates(LinkedList<Coordinate> coordinates) {
        this.graphsList.addLast(coordinates);
        this.repaint();
    }

    /**
     * M�todo que inicializa en una lista vac�a el conjunto de
     * conjuntos de coordenadas de puntos a graficar.
     */
    public void clearAll() {
        this.graphsList.clear();
        this.repaint();
    }

    /**
     * Coordenadas en pixeles del punto medio del �rea de graficado. Estas se
     * usan para reescalar los ejes y la malla coordenados.
     * @param center - coordenadas en pizeles del nuevo centro.
     */
    public void setCenter(Coordinate center) {
        this.center = center;
    }

    /**
     * M�todo que asigna un escucha recibido como par�metro al escucha
     * definido en la estructura de la clase.
     * @param weightHeightListener - escucha a asignar al propio de la clase.
     */
    public void setWeightHeightListener(WeightHeightListener weightHeightListener) {
        this.weightHeightListener = weightHeightListener;
    }

    /**
     * M�todo que se encarga de definir los ejes coordenados, y los
     * reescala seg�n se modifique la ventana de visualizaci�n.
     * @param width - ancho actual del �rea de graficado.
     * @param height - largo actual del �rea de graficado.
     * @param g - instancia de "Graphics" necesaria para el trazado de pixeles.
     */
    private void setAxes(int width, int height, Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);

        g2d.setStroke(new BasicStroke(1));

        int xMidpoint = Math.round(width / 2) - (int)center.getxCoordinate(),
                yMidpoint = (int)center.getyCoordinate() + Math.round(height / 2);

        g2d.drawLine(0, yMidpoint, width, yMidpoint);
        g2d.drawLine(xMidpoint, 0, xMidpoint, height);

        g.setFont(new Font("Verdana", Font.PLAIN, 15));
        g.drawString("x", width - 20, height - 20);
        g.drawString("y", 20, 20);
    }

    /**
     * M�todo que se encarga de definir una malla coordenada, y la
     * reescala seg�n se modifique la ventana de visualizaci�n, o bien
     * la escala de valores en "x" y "y".
     * @param width - ancho actual del �rea de graficado.
     * @param height - largo actual del �rea de graficado.
     * @param g - instancia de "Graphics" necesaria para el trazado de pixeles.
     */
    private void setGrid(int width, int height, Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.LIGHT_GRAY);

        float[] dashingPattern = {2f, 2f};
        Stroke stroke = new BasicStroke(1f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 1.0f, dashingPattern, 2.0f);

        g2d.setStroke(stroke);

        int interval = 10;

        int xStep = Math.round(width/interval),
                yStep = Math.round(height/interval),
                xMidpoint = Math.round(width/2) - (int)center.getxCoordinate(),
                yMidpoint = Math.round(height/2) + (int)center.getyCoordinate();

        //Se hace esto de definir el centro como el punto de partida del trazado, puesto que los pixeles son n�meros
        //enteros, y no tienen la precisi�n que si tienen las coordenadas abstractas, entonces, si
        //no empezara por mis ejes coordenados, se tendr�a una malla desfasada con respecto a estos por
        //unos cuantos pixeles que, aunque es poco, se ve desagradable.
        int x, y;

        x = xMidpoint;
        while(x < width){
            g2d.drawLine(x, 0, x, height);
            x = x + xStep;
        }
        x = xMidpoint;
        while(x > 0){
            g2d.drawLine(x, 0, x, height);
            x = x - xStep;
        }

        y = yMidpoint;
        while(y < height){
            g2d.drawLine(0, y, width, y);
            y = y + yStep;
        }
        y = yMidpoint;
        while(y > 0){
            g2d.drawLine(0, y, width, y);
            y = y - yStep;
        }
    }

    /**
     * M�todo que se encarga de pintar el gr�fico en su �rea designada. Para
     * esto, se toma la lista de coordenadas de pixeles y se van dibujando una a
     * una, uniendolas por pares con l�neas rectas. Dado que con muchos puntos, el
     * efecto de la recta se pierde y si se pueden apreciar formas curvas.
     * @param g - instancia de "Graphics" necesaria para el trazado de pixeles.
     */
    private void drawGraph(LinkedList<Coordinate> coordinates, Graphics g, int index){

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(getColor(index));
        g2d.setStroke(new BasicStroke(2));

        int x1,
                x2,
                y1,
                y2;

        Coordinate currentPoint;

        for (int i = 1; i < coordinates.size(); i++) {
            currentPoint = coordinates.get(i-1);
            x1 = (int)Math.round(currentPoint.getxCoordinate());
            y1 = (int)Math.round(currentPoint.getyCoordinate());

            currentPoint = coordinates.get(i);
            x2 = (int)Math.round(currentPoint.getxCoordinate());
            y2 = (int)Math.round(currentPoint.getyCoordinate());

            //Condici�n para que no se dibuje una l�nea entre puntos separados por una as�ntota.
            if(Math.abs(y2 - y1) < 10000){
                g2d.drawLine(x1, y1, x2, y2);
            }
        }
    }

    /**
     * M�todo que regresa un color que depende de qu� gr�fica sea la que se esta trazando
     * seg�n su orden de inserci�n en la lista.
     * @param index - �ndice de la lista al que corresponde el color devuelto por el m�todo.
     * @return Color - color correspondiente con el �ndice de la lista en el que se encuentre
     * la gr�fica..
     */
    private Color getColor(int index) {
        switch(index){
            case 0:
                return Color.RED;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.CYAN;
            case 4:
                return Color.MAGENTA;
            case 5:
                return Color.ORANGE;
            case 6:
                return Color.PINK;
            default:
                return getColor(index - 7);
        }
    }
}