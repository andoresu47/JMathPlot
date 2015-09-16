package calculator.evaluating.src;

import calculator.tokenizing.src.FunctionToken;
import calculator.tokenizing.src.NumberToken;
import calculator.tokenizing.src.OperatorToken;
import calculator.tokenizing.src.Token;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Andr�s on 13/09/2015.
 * Clase que toma expresiones en notaci�n infija, las
 * convierte a posfija y las eval�a.
 */
public class Evaluate {
    /**
     * M�todo encargado de generar una lista de coordenadas a
     * partir de la evaluaci�n de una expresi�n para cierto rango y
     * n�mero de puntos en el eje x.
     * @param infixTokens - lista de tokens v�lida, en notaci�n infija.
     * @param xMin - l�mite izquierdo de la evaluaci�n.
     * @param xMax - l�mite derecho de la evaluaci�n.
     * @param numberOfPoints - n�mero de puntos a evaluar.
     * @return LinkedList<Coordinate> - lista de coordenadas resultante de la
     * evaluaci�n de la expresi�n.
     */
    public static LinkedList<Coordinate> generatePoints(LinkedList<Token> infixTokens, double xMin, double xMax, int numberOfPoints){
        double difference = xMax - xMin;
        double step = difference/numberOfPoints;

        LinkedList<Token> postfix = infixToPostfix(infixTokens);
        LinkedList<Coordinate> coordinates = new LinkedList<>();

        double x = xMin;
        double evaluation;
        while (x <= xMax){
            try {
                evaluation = postfixEvaluation(postfix, x);
                coordinates.addLast(new Coordinate(x, evaluation));
            }catch (ArithmeticException e){
                //No hace nada
            }
            x = x + step;
        }
        return coordinates;
    }

    /**
     * M�todo que se encarga de convertir una lista de tokens en notaci�n
     * infija, a una lista de tokens en notaci�n posfija. Esto, con el fin de
     * poder evaluarla f�cilmente.
     * @param infixTokens - lista de tokens en notaci�n infija.
     * @return LinkedList<Token> - lista de tokens en notaci�n posfija.
     */
    public static LinkedList<Token> infixToPostfix(LinkedList<Token> infixTokens){
        Stack<Token> operatorStack = new Stack<>();

        LinkedList<Token> convertedInfixTokens = unaryMinusConverter(infixTokens);
        LinkedList<Token> postfixTokens = new LinkedList<>();

        for (Token present : convertedInfixTokens) {
            if (present instanceof FunctionToken) {
                operatorStack.push(present);
            }
            if (present.getType() == Token.NUMBER) {
                postfixTokens.addLast(present);
            }
            if (present.getType() == Token.VARIABLE) {
                postfixTokens.addLast(present);
            }
            if (present.getType() == Token.PLUS ||
                    present.getType() == Token.MINUS ||
                    present.getType() == Token.PRODUCT ||
                    present.getType() == Token.DIVISION ||
                    present.getType() == Token.EXP) {
                OperatorToken operator1 = (OperatorToken) present;
                OperatorToken operator2;

                while (!operatorStack.isEmpty()) {
                    operator2 = (OperatorToken) operatorStack.peek();
                    if ((operator1.getAssociativity() == OperatorToken.LEFT
                            && operator1.getPrecedence() <= operator2.getPrecedence())
                            || (operator1.getAssociativity() == OperatorToken.RIGHT
                            && operator1.getPrecedence() < operator2.getPrecedence())) {
                        postfixTokens.addLast(operatorStack.pop());
                    } else {
                        break;
                    }
                }
                operatorStack.push(present);
            }
            if (present.getType() == Token.LEFT_PARENTHESIS) {
                operatorStack.push(present);
            }
            if (present.getType() == Token.RIGHT_PARENTHESIS) {
                while(operatorStack.peek().getType() != Token.LEFT_PARENTHESIS){
                    postfixTokens.addLast(operatorStack.pop());
                }
                operatorStack.pop();
                if(operatorStack.peek() instanceof FunctionToken){
                    postfixTokens.addLast(operatorStack.pop());
                }
            }
        }
        while(!operatorStack.isEmpty()){
            postfixTokens.addLast(operatorStack.pop());
        }
        return postfixTokens;
    }

    /**
     * M�todo que eval�a una expresi�n representada por una lista
     * de Tokens en notaci�n posfija. Para esto, se hace uso de una pila.
     * @param postfixTokens - lista de tokens en notaci�n posfija.
     * @param currentEvaluation - valor que tomar� la variable, en caso de existir una.
     * @return double - resultado de la evaluaci�n de la expresi�n.
     * @throws ArithmeticException si se detecta una potencia fraccionaria de un
     * n�mero negativo, o alguna divisi�n entre cero.
     */
    public static double postfixEvaluation(LinkedList<Token> postfixTokens, double currentEvaluation) throws ArithmeticException{
        Stack<Token> numbersStack = new Stack<>();

        for(Token present : postfixTokens){
            if(present.getType() == Token.NUMBER){
                numbersStack.push(present);
            }
            if(present.getType() == Token.VARIABLE){
                Token evaluation = new NumberToken(currentEvaluation);
                numbersStack.push(evaluation);
            }
            if(present instanceof FunctionToken){
                double value = (Double)numbersStack.pop().getValue();

                double solved = 0;

                switch(present.getType()){
                    case Token.SIN:
                        solved = Math.sin(value);
                        break;
                    case Token.COS:
                        solved = Math.cos(value);
                        break;
                    case Token.TAN:
                        solved = Math.tan(value);
                        break;
                    case Token.COT:
                        solved = (1.0/Math.tan(value));
                        break;
                    case Token.SEC:
                        solved = (1.0/Math.cos(value));
                        break;
                    case Token.CSC:
                        solved = (1.0/Math.sin(value));
                        break;
                    case Token.SQRT:
                        if(value >= 0){
                            solved = Math.sqrt(value);
                        }else{
                            throw new ArithmeticException();
                        }
                        break;
                }
                Token result = new NumberToken(solved);
                numbersStack.push(result);
            }
            if(present instanceof OperatorToken){
                double operand1 = (Double)numbersStack.pop().getValue();
                double operand2 = (Double)numbersStack.pop().getValue();

                double solved = 0;

                switch(present.getType()){
                    case Token.PLUS:
                        solved = operand2 + operand1;
                        break;
                    case Token.MINUS:
                        solved = operand2 - operand1;
                        break;
                    case Token.PRODUCT:
                        solved = operand2 * operand1;
                        break;
                    case Token.DIVISION:
                        if(operand1 == 0){
                            throw new ArithmeticException();
                        }else{
                            solved = operand2 / operand1;
                        }
                        break;
                    case Token.EXP:
                        if(operand2 < 0 && operand1 < 1 && operand1 > 0){
                            throw new ArithmeticException();
                        }else{
                            solved = Math.pow(operand2, operand1);
                        }
                        break;
                }
                Token result = new NumberToken(solved);
                numbersStack.push(result);
            }
        }
        double result = (Double)numbersStack.pop().getValue();

        return result;
    }

    /**
     * M�todo que toma una expresi�n (tokenizada) en gram�tica infija,
     * y la convierte a otra que vive dentro de la misma gram�tica. Pero con
     * la posibilidad de poder ser expresada tambi�n en una gram�tica posfija,
     * utilizando el m�todo "infixToPosfix" dentro de esta misma clase.
     * @param infixTokens - lista de tokens a ser convertida.
     * @return LinkedList<Token> - lista de tokens en la forma correcta para
     * poder ser convertida a notaci�n posfija.
     */
    public static LinkedList<Token> unaryMinusConverter(LinkedList<Token> infixTokens){
        LinkedList<Token> convertedInfixTokens = new LinkedList<>();

        for(int i = 0; i < infixTokens.size(); i++){
            Token present = infixTokens.get(i);
            if(present.getType() == Token.MINUS){
                if(i == 0){
                    if(infixTokens.get(i + 1).getType() == Token.LEFT_PARENTHESIS
                            || infixTokens.get(i + 1) instanceof FunctionToken){
                        int balanced = 0;
                        convertedInfixTokens.addLast(new OperatorToken('('));
                        convertedInfixTokens.addLast(new NumberToken(0));
                        while(present.getType() != Token.RIGHT_PARENTHESIS || balanced != 0){
                            if(present.getType() == Token.LEFT_PARENTHESIS){
                                balanced++;
                            }
                            convertedInfixTokens.addLast(present);
                            i++;
                            present = infixTokens.get(i);
                            if(present.getType() == Token.RIGHT_PARENTHESIS){
                                balanced--;
                            }
                        }
                        convertedInfixTokens.addLast(present);
                        convertedInfixTokens.addLast(new OperatorToken(')'));
                    }else{
                        convertedInfixTokens.addLast(new OperatorToken('('));
                        convertedInfixTokens.addLast(new NumberToken(0));
                        convertedInfixTokens.addLast(present);
                        convertedInfixTokens.addLast(infixTokens.get(i+1));
                        convertedInfixTokens.addLast(new OperatorToken(')'));
                        i++;
                    }
                }else{
                    if(infixTokens.get(i-1) instanceof OperatorToken){
                        if(infixTokens.get(i + 1).getType() == Token.LEFT_PARENTHESIS
                                || infixTokens.get(i + 1) instanceof FunctionToken){
                            int balanced = 0;
                            convertedInfixTokens.addLast(new OperatorToken('('));
                            convertedInfixTokens.addLast(new NumberToken(0));
                            while(present.getType() != Token.RIGHT_PARENTHESIS || balanced != 0){
                                if(present.getType() == Token.LEFT_PARENTHESIS){
                                    balanced++;
                                }
                                convertedInfixTokens.addLast(present);
                                i++;
                                present = infixTokens.get(i);
                                if(present.getType() == Token.RIGHT_PARENTHESIS){
                                    balanced--;
                                }
                            }
                            convertedInfixTokens.addLast(present);
                            convertedInfixTokens.addLast(new OperatorToken(')'));
                        }else{
                            convertedInfixTokens.addLast(new OperatorToken('('));
                            convertedInfixTokens.addLast(new NumberToken(0));
                            convertedInfixTokens.addLast(present);
                            convertedInfixTokens.addLast(infixTokens.get(i+1));
                            convertedInfixTokens.addLast(new OperatorToken(')'));
                            i++;
                        }
                    }else{
                        convertedInfixTokens.addLast(present);
                    }
                }
            }
            else{
                convertedInfixTokens.addLast(present);
            }
        }
        return convertedInfixTokens;
    }
}
