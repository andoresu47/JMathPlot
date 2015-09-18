package calculator.parsing.src;

import calculator.tokenizing.src.FunctionToken;
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
    public boolean isValid() {
        return tokensList != null && parseExpression(tokensList);
    }

    /**
     * M�todo que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de "<expresi�n>" en la gram�tica.
     * @param tokensList - lista de tokens en notaci�n infija.
     * @return boolean - true si la forma es correcta, false en caso contrario.
     */
    private static boolean parseExpression(LinkedList<Token> tokensList){
        return parseNumber(tokensList)
                || parseVariable(tokensList)
                || parseParenthesisExpression(tokensList)
                || parseMinusExpression(tokensList)
                || parseFunctionExpression(tokensList)
                || parseExpressionSumExpression(tokensList)
                || parseExpressionMinusExpression(tokensList)
                || parseExpressionProductExpression(tokensList)
                || parseExpressionDivisionExpression(tokensList)
                || parseExpressionExponentialExpression(tokensList);
    }

    /**
     * M�todo que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de "<n�mero>" en la gram�tica*.
     * @param tokensList - lista de tokens en notaci�n infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseNumber(LinkedList<Token> tokensList){
        return tokensList.size() == 1 && tokensList.getFirst().getType() == Token.NUMBER;
    }

    /**
     * M�todo que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de "<variable>" en la gram�tica.
     * @param tokensList - lista de tokens en notaci�n infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseVariable(LinkedList<Token> tokensList){
        return tokensList.size() == 1 && tokensList.getFirst().getType() == Token.VARIABLE;
    }

    /**
     * M�todo que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de ""(" <expresi�n> ")"" en la gram�tica.
     * @param tokensList - lista de tokens en notaci�n infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseParenthesisExpression(LinkedList<Token> tokensList){
        if(tokensList.size() < 3){
            return false;
        }

        LinkedList<Token> newTokensList = new LinkedList<>(tokensList);
        boolean isLeftParenthesis = newTokensList.removeFirst().getType() == Token.LEFT_PARENTHESIS;
        boolean isRightParenthesis = newTokensList.removeLast().getType() == Token.RIGHT_PARENTHESIS;

        return isLeftParenthesis && parseExpression(newTokensList) && isRightParenthesis;
    }

    /**
     * M�todo que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de ""-" <expresi�n>" en la gram�tica.
     * @param tokensList - lista de tokens en notaci�n infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseMinusExpression(LinkedList<Token> tokensList){
        if(tokensList.size() < 2){
            return false;
        }

        LinkedList<Token> newTokensList = new LinkedList<>(tokensList);
        boolean isMinusSign = newTokensList.removeFirst().getType() == Token.MINUS;

        return isMinusSign && parseExpression(newTokensList);
    }

    /**
     * M�todo que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de "<funci�n> "(" <expresi�n> ")"" en la gram�tica.
     * @param tokensList - lista de tokens en notaci�n infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseFunctionExpression(LinkedList<Token> tokensList){
        if(tokensList.size() < 4){
            return false;
        }

        LinkedList<Token> newTokensList = new LinkedList<>(tokensList);
        boolean isFunction = newTokensList.removeFirst() instanceof FunctionToken;
        boolean isLeftParenthesis = newTokensList.removeFirst().getType() == Token.LEFT_PARENTHESIS;
        boolean isRightParenthesis = newTokensList.removeLast().getType() == Token.RIGHT_PARENTHESIS;

        return isFunction && isLeftParenthesis && parseExpression(newTokensList) && isRightParenthesis;
    }

    /**
     * M�todo que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de "<expresi�n> <+> <expresi�n>" en la gram�tica.
     * @param tokensList - lista de tokens en notaci�n infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseExpressionSumExpression(LinkedList<Token> tokensList) {
        if (tokensList.size() < 3) {
            return false;
        }

        LinkedList<Boolean> occurrences = new LinkedList<>();
        //Se inicializa en i = 1, y va hasta el tama�o menos uno, pues un operador
        //no puede estar ni al principio ni al final de una expresi�n.
        for (int i = 1; i < tokensList.size() - 1; i++) {

            int type = tokensList.get(i).getType();

            boolean isSum = (type == Token.PLUS);

            if (isSum) {
                LinkedList<Token> leftExpression = new LinkedList<>(tokensList.subList(0, i));
                LinkedList<Token> rightExpression = new LinkedList<>(tokensList.subList(i + 1, tokensList.size()));

                occurrences.addLast(parseExpression(leftExpression) && parseExpression(rightExpression));
            }
        }
        //Si en alguna ocurrencia del operador se cumple el modelo, se devuelve true.
        for (Boolean occurrence : occurrences) {
            if (occurrence) {
                return true;
            }
        }
        return false;
    }

    /**
     * M�todo que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de "<expresi�n> <-> <expresi�n>" en la gram�tica.
     * @param tokensList - lista de tokens en notaci�n infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseExpressionMinusExpression(LinkedList<Token> tokensList) {
        if (tokensList.size() < 3) {
            return false;
        }

        LinkedList<Boolean> occurrences = new LinkedList<>();

        for (int i = 1; i < tokensList.size() - 1; i++) {

            int type = tokensList.get(i).getType();

            boolean isDifference = (type == Token.MINUS);

            if (isDifference) {
                LinkedList<Token> leftExpression = new LinkedList<>(tokensList.subList(0, i));
                LinkedList<Token> rightExpression = new LinkedList<>(tokensList.subList(i + 1, tokensList.size()));

                occurrences.addLast(parseExpression(leftExpression) && parseExpression(rightExpression));
            }
        }
        for (Boolean occurrence : occurrences) {
            if (occurrence) {
                return true;
            }
        }
        return false;
    }

    /**
     * M�todo que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de "<expresi�n> <*> <expresi�n>" en la gram�tica.
     * @param tokensList - lista de tokens en notaci�n infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseExpressionProductExpression(LinkedList<Token> tokensList){
        if(tokensList.size() < 3){
            return false;
        }

        LinkedList<Boolean> occurrences = new LinkedList<>();

        for(int i = 1; i < tokensList.size() - 1; i++){

            int type = tokensList.get(i).getType();

            boolean isProduct = type == Token.PRODUCT;

            if(isProduct){
                LinkedList<Token> leftExpression = new LinkedList<>(tokensList.subList(0, i));
                LinkedList<Token> rightExpression = new LinkedList<>(tokensList.subList(i + 1, tokensList.size()));

                occurrences.addLast(parseExpression(leftExpression) && parseExpression(rightExpression));
            }
        }
        for (Boolean occurrence : occurrences) {
            if (occurrence) {
                return true;
            }
        }
        return false;
    }

    /**
     * M�todo que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de "<expresi�n> </> <expresi�n>" en la gram�tica.
     * @param tokensList - lista de tokens en notaci�n infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseExpressionDivisionExpression(LinkedList<Token> tokensList){
        if(tokensList.size() < 3){
            return false;
        }

        LinkedList<Boolean> occurrences = new LinkedList<>();

        for(int i = 1; i < tokensList.size() - 1; i++){

            int type = tokensList.get(i).getType();

            boolean isDivision = type == Token.DIVISION;

            if(isDivision){
                LinkedList<Token> leftExpression = new LinkedList<>(tokensList.subList(0, i));
                LinkedList<Token> rightExpression = new LinkedList<>(tokensList.subList(i + 1, tokensList.size()));

                occurrences.addLast(parseExpression(leftExpression) && parseExpression(rightExpression));
            }
        }
        for (Boolean occurrence : occurrences) {
            if (occurrence) {
                return true;
            }
        }
        return false;
    }

    /**
     * M�todo que se encarga de verificar que la sintaxis de una lista de tokens
     * siga la forma de "<expresi�n> <^> <expresi�n>" en la gram�tica.
     * @param tokensList - lista de tokens en notaci�n infija.
     * @return true - si la forma es correcta, false en caso contrario.
     */
    private static boolean parseExpressionExponentialExpression(LinkedList<Token> tokensList){
        if(tokensList.size() < 3){
            return false;
        }

        LinkedList<Boolean> occurrences = new LinkedList<>();

        for(int i = 1; i < tokensList.size() - 1; i++){

            int type = tokensList.get(i).getType();

            boolean isExponential = type == Token.EXP;

            if(isExponential){
                LinkedList<Token> leftExpression = new LinkedList<>(tokensList.subList(0, i));
                LinkedList<Token> rightExpression = new LinkedList<>(tokensList.subList(i + 1, tokensList.size()));

                occurrences.addLast(parseExpression(leftExpression) && parseExpression(rightExpression));
            }
        }
        for (Boolean occurrence : occurrences) {
            if (occurrence) {
                return true;
            }
        }
        return false;
    }
}
