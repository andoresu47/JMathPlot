package calculator.parsing.src;

import calculator.tokenizing.src.FunctionToken;
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

    public static LinkedList<Token> infixToPostfix(LinkedList<Token> infixTokens){
        Stack<Token> operatorStack = new Stack<>();
        LinkedList<Token> postfixTokens = new LinkedList<>();

        for (Token present : infixTokens) {
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
     * de Tokens en notaci�n infija. Para esto, dicha lista se convierte a
     * notaci�n posfija y se realiza la evaluaci�n por medio de una pila.
     * @param infixTokens - lista de tokens en notaci�n infija.
     * @param currentEvaluation - valor que tomar� la variable, en caso de haber.
     * @return double - resultado de la evaluaci�n de la expresi�n.
     */
    public static double postfixEvaluation(LinkedList<Token> infixTokens, double currentEvaluation){

        return 0;
    }
}
