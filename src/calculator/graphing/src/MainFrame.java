package calculator.graphing.src;

import calculator.evaluating.src.Coordinate;
import calculator.evaluating.src.Evaluate;
import calculator.graphing.customExceptions.*;
import calculator.parsing.src.Parser;
import calculator.tokenizing.src.ExpressionTokenizer;
import calculator.tokenizing.src.Token;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

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

    private LinkedList<Token> postfixTokens = null;

    private double xMin,
                   xMax,
                   yMin,
                   yMax;

    /**
     * Constructor del controlador. Se inicializan los distintos elementos
     * y se organizan en el Frame.
     */
    public MainFrame(){
        super("Graficador");

        setLayout(new BorderLayout());

        graphArea = new GraphArea();
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
            public void inputEventOccurred(String expression, String xMin, String xMax, String yMin, String yMax) {
                try {
                    logicHandler(expression, xMin, xMax, yMin, yMax);
                } catch (XMinException e) {
                    optionsArea.setXminErrorText("Valor invalido.");
                } catch (XMaxException e) {
                    optionsArea.setXmaxErrorText("Valor invalido.");
                } catch (YMinException e) {
                    optionsArea.setYminErrorText("Valor invalido.");
                } catch (YMaxException e) {
                    optionsArea.setYmaxErrorText("Valor invalido.");
                } catch (SyntaxException e) {
                    optionsArea.setExpressionErrorText("Error de sintaxis.");
                }
            }
        });

        graphArea.setWeightHeightListener(new WeightHeightListener() {
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
                if(postfixTokens != null){
                    reDrawGraph();
                }
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

    /**
     * M�todo que se encarga de filtrar la informaci�n introducida por el usuario en
     * la interfaz gr�fica. Esto, de manera que se verifica si los datos fueron introducidos
     * correctamente o no. De ser as�, se ejecuta el resto del programa normalmente, pero
     * si se detecta alguna entrada inv�lida, se detiene la graficaci�n; ie, ni siquiera
     * se calculan los puntos, y se muestra en pantalla el error que sucedi� debido al input
     * del usuario.
     * @param newExpression - cadena que representa la funci�n introducida por el usuario en la
     *                      interfaz gr�fica.
     * @param newXMin - cadena que representa la cota m�nima en X definida por el usuario.
     * @param newXMax - cadena que representa la cota m�xima en X definida por el usuario.
     * @param newYMin - cadena que representa la cota m�nima en Y definida por el usuario.
     * @param newYMax - cadena que representa la cota m�xima en Y definida por el usuario.
     * @throws XMinException si la cadena introducida para xMin no es un real.
     * @throws XMaxException si la cadena introducida para xMax no es un real.
     * @throws YMinException si la cadena introducida para yMin no es un real.
     * @throws YMaxException si la cadena introducida para yMax no es un real.
     * @throws SyntaxException si el parser detecta un error en la sintaxis de la f�rmula.
     */
    public void logicHandler(String newExpression, String newXMin, String newXMax, String newYMin, String newYMax)
            throws XMinException,XMaxException, YMinException, YMaxException, SyntaxException {
        try{
            xMin = Double.parseDouble(newXMin);
        }catch(NumberFormatException e){
            throw new XMinException();
        }
        try{
            xMax = Double.parseDouble(newXMax);
        }catch(NumberFormatException e){
            throw new XMaxException();
        }try{
            yMin = Double.parseDouble(newYMin);
        }catch(NumberFormatException e){
            throw new YMinException();
        }
        try{
            yMax = Double.parseDouble(newYMax);
        }catch(NumberFormatException e){
            throw new YMaxException();
        }
        ExpressionTokenizer tokenizer = new ExpressionTokenizer(newExpression);
        LinkedList<Token> infixTokens = tokenizer.getTokensList();
        Parser parser = new Parser(infixTokens);
        if(!parser.isValid()){
            throw new SyntaxException();
        }
        LinkedList<Token> postfixTokens = Evaluate.infixToPostfix(infixTokens);
        this.postfixTokens = postfixTokens;
        reDrawGraph();
    }

    /**
     * M�todo que se encarga de hacerle saber al �rea de trazado de la gr�fica los puntos
     * que se van a graficar. No obstante, si se detecta que una lista de puntos est� vac�a,
     * significa que la expresi�n introducida no es v�lida, pero s� tiene sintaxis correcta.
     * Tal ser�a el caso de una raiz cuadrada de n�mero negativo. Esto se le hace saber al usuario
     * en caso de que ocurra.
     */
    public void reDrawGraph(){
        LinkedList<Coordinate> rawPoints = Evaluate.generatePoints(postfixTokens, xMin, xMax, widthOfGraphArea);
        if(rawPoints.isEmpty()){
            optionsArea.setExpressionErrorText("Expresion invalida.");
        }else{
            LinkedList<Coordinate> rescaledPoints = rescalePoints(rawPoints);
            graphArea.setCoordinates(rescaledPoints);
        }
    }

    /**
     * M�todo que se encarga de transformar coordenadas del plano cartesiano a coordenadas
     * en t�rminos de pixeles. Coordenadas que ya pueden ser trazadas directamente por el
     * �rea encargada del graficado.
     * @param rawPoints - coordenadas cartesianas obtenidas de evaluar la expresi�n introducida
     *                  por el usuario.
     * @return LinkedList<Coordinate> - lista de coordenadas transformadas en t�rminos de pixeles.
     */
    private LinkedList<Coordinate> rescalePoints(LinkedList<Coordinate> rawPoints){
        LinkedList<Coordinate> rescaled = new LinkedList<>();
        double abstractX,
                abstractY,
                newXinPixels,
                newYinPixels;
        double xDifference = xMax - xMin,
                yDifference = yMax - yMin;
        double xTransformationFactor = widthOfGraphArea/xDifference,
                yTransformationFactor = heightOfGraphArea/yDifference;
        double xOrigin = widthOfGraphArea/2,
                yOrigin = heightOfGraphArea/2;
        double midPointX = (xMax + xMin)/2,
                midPointY = (yMax + yMin)/2;

        for(Coordinate present : rawPoints){
            abstractX = present.getxCoordinate();
            abstractY = present.getyCoordinate();
            newXinPixels = ((abstractX - midPointX) * xTransformationFactor) + xOrigin;
            newYinPixels = yOrigin + ((midPointY - abstractY) * yTransformationFactor);
            rescaled.addLast(new Coordinate(newXinPixels, newYinPixels));
        }
        graphArea.setCenter(new Coordinate(midPointX*xTransformationFactor, midPointY*yTransformationFactor));
        return rescaled;
    }
}
