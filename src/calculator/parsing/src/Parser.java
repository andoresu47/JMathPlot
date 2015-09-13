package calculator.parsing.src;

import calculator.tokenizing.src.Token;

import java.util.LinkedList;

/**
 * Created by Andr�s on 12/09/2015.
 * Clase encargada de verificar la sintaxis (en este caso infija)
 * de las fichas en la lista previamente formada.
 */
public class Parser {

    private LinkedList<Token> tokensList;

    /**
     * Constructor que recibe una lista de tokens en notaci�n
     * infija, y la asigna a su lista propia.
     * @param tokensList - lista de tokens en notaci�n infija.
     */
    public Parser(LinkedList<Token> tokensList){
        this.tokensList = tokensList;
    }

    /**
     * M�todo que se encarga de determinar si una lista de tokens
     * contiene la sintaxis correcta de gram�tica infija.
     * @return boolean - true si la sitaxis es correcta, false en otro caso.
     */
    public boolean isValid(){
        return false;
    }

    /**
     * M�todo que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de "<expresi�n>" en la gram�tica.
     * @param tokensList - lista de tokens en notaci�n infija.
     * @return boolean - true si la forma es correcta, false en caso contrario.
     */
    private static boolean parseExpression(LinkedList<Token> tokensList){
        return false;
    }

    /**
     * M�todo que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de "<n�mero>" en la gram�tica*.
     * @param tokensList - lista de tokens en notaci�n infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseNumber(LinkedList<Token> tokensList){
        return false;
    }

    /**
     * M�todo que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de "<variable>" en la gram�tica.
     * @param tokensList - lista de tokens en notaci�n infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseVariable(LinkedList<Token> tokensList){
        return false;
    }

    /**
     * M�todo que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de ""(" <expresi�n> ")"" en la gram�tica.
     * @param tokensList - lista de tokens en notaci�n infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseParenthesisExpression(LinkedList<Token> tokensList){
        return false;
    }

    /**
     * M�todo que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de ""-" <expresi�n>" en la gram�tica.
     * @param tokensList - lista de tokens en notaci�n infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseMinusExpression(LinkedList<Token> tokensList){
        return false;
    }

    /**
     * M�todo que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de "<funci�n> "(" <expresi�n> ")"" en la gram�tica.
     * @param tokensList - lista de tokens en notaci�n infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseFunctionExpression(LinkedList<Token> tokensList){
        return false;
    }

    /**
     * M�todo que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de "<expresi�n> <operador> <expresi�n>" en la gram�tica.
     * @param tokensList - lista de tokens en notaci�n infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseExpressionOperatorExpression(LinkedList<Token> tokensList){
        return false;
    }
}
