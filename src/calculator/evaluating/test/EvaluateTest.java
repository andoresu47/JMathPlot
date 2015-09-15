package calculator.evaluating.test;

import calculator.evaluating.src.Evaluate;
import calculator.parsing.src.Parser;
import calculator.tokenizing.src.ExpressionTokenizer;
import calculator.tokenizing.src.Token;
import org.junit.Test;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by Andr�s on 13/09/2015.
 * Clase de prueba para la clase "Evaluate".
 */
public class EvaluateTest {
    /**
     * M�todo que prueba �ntegramente el proceso de validaci�n y
     * evaluaci�n de una expresi�n, desde que es introducida en
     * su forma de cadena, hasta que se tiene el resultado final.
     * @throws Exception
     */
    @Test
    public void testWholeEvaluationProcess() throws Exception{
        String valid = "20+4-7^-sin(4+(x-1))+24";

        ExpressionTokenizer tokenizer = new ExpressionTokenizer(valid);
        LinkedList<Token> tokens = tokenizer.getTokensList();

        Parser parser = new Parser(tokens);

        LinkedList<Token> postfix = null;

        if(parser.isValid()){
            postfix = Evaluate.infixToPostfix(tokens);
        }

        double actualResult = Evaluate.postfixEvaluation(postfix, 2);
        double expected = 41.5377;

        assertEquals(expected, actualResult, 0.001);

    }

    /**
     * M�todo que prueba "infixToPostfix".
     * Se verifica que en efecto se transforme una lista de tokens
     * en notaci�n infija a una lista de tokens en notaci�n posfija.
     * @throws Exception si ocurre alguna anomal�a.
     */
    @Test
    public void testInfixToPostfix() throws Exception {
        String infixTokens = "3 + sqr(x * 2) / (- (5 + 1)) ^ 2 ^ 3",
               postfixTokens = "3 x 2 * sqr 0 5 1 + - 2 3 ^ ^ / +";

        ExpressionTokenizer tokenizer;

        tokenizer = new ExpressionTokenizer(infixTokens);
        LinkedList<Token> infix = tokenizer.getTokensList();

        tokenizer = new ExpressionTokenizer(postfixTokens);
        LinkedList<Token> expectedPostfix = tokenizer.getTokensList();

        LinkedList<Token> postfix = Evaluate.infixToPostfix(infix);

        assertEquals(expectedPostfix, postfix);
    }

    /**
     * M�todo que prueba "postfixEvaluation".
     * Se verifica que en efecto se eval�e correctamente una expresi�n
     * tokenizada en notaci�n posfija, sin errores aritm�ticos.
     * @throws Exception si ocurre alguna anomal�a.
     */
    @Test
    public void testCleanPostfixEvaluation() throws Exception {
        String postfixTokens = "x 3.1416 * sin 2 3 2 ^ ^ +";

        ExpressionTokenizer tokenizer = new ExpressionTokenizer(postfixTokens);
        LinkedList<Token> postfix = tokenizer.getTokensList();

        double actual = Evaluate.postfixEvaluation(postfix, 2);
        double expected = Math.sin(2 * Math.PI) + Math.pow(2, Math.pow(3, 2));

        assertEquals(expected, actual, 0.001);
    }

    /**
     * M�todo que prueba "postfixEvaluation".
     * Se verifica que en efecto se eval�e correctamente una expresi�n
     * tokenizada en notaci�n posfija, sin errores aritm�ticos.
     * @throws Exception si ocurre alguna anomal�a.
     */
    @Test(expected = ArithmeticException.class)
    public void testDirtyPostfixEvaluation() throws Exception {
        String postfixTokens = "0 x - sqr 2 3 2 ^ ^ +";

        ExpressionTokenizer tokenizer = new ExpressionTokenizer(postfixTokens);
        LinkedList<Token> postfix = tokenizer.getTokensList();

        double actual = Evaluate.postfixEvaluation(postfix, 2);
    }

    /**
     * M�todo que prueba "unaryMinusConverter".
     * Se verifica que en efecto se convierta una expresi�n de
     * gram�tica infija, a otra dentro de la misma gram�tica,
     * pero con posibilidad de tener una representaci�n en gram�tica
     * posfija.
     * @throws Exception si ocurre alguna anomal�a.
     */
    @Test
    public void testUnaryMinusConverter() throws Exception {
        //3 + sqr(x * 2) / (- (5 + 1)) ^ 2 ^ 3
        //3 + sqr(x * 2) / ((0- (5 + 1))) ^ 2 ^ 3
        String infixTokens = "20+4-7^-sin(x+(2-1))+24",
                convertedInfixTokens = "20+4-7^(0-sin(x+(2-1)))+24";

        ExpressionTokenizer tokenizer;

        tokenizer = new ExpressionTokenizer(infixTokens);
        LinkedList<Token> infix = tokenizer.getTokensList();


        tokenizer = new ExpressionTokenizer(convertedInfixTokens);
        LinkedList<Token> expectedConversion = tokenizer.getTokensList();

        LinkedList<Token> convertedTokens = Evaluate.unaryMinusConverter(infix);

        assertEquals(expectedConversion, convertedTokens);
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testGeneratePoints() throws Exception {

    }
}