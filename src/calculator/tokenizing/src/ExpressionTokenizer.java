package calculator.tokenizing.src;

import java.util.LinkedList;

/**
 * Created by Andr�s on 05/09/2015.
 * Clase encargada de desmembrar una expresi�n (String) en
 * sus componentes (llam�mosle a estas, "fichas"; o en
 * ingl�s, "Tokens").
 */
public class ExpressionTokenizer {

    private LinkedList<Token> tokensList;

    /**
     * Constructor que recibe la cadena a desmembrar en
     * tokens.
     * @param expression - expresi�n que se descompondr�
     *                   en tokens.
     */
    public ExpressionTokenizer(String expression) {

    }

    /**
     * M�todo que devuelve la lista de tokens que se cre�
     * a partir de la expresi�n con la que se construy�
     * el objeto.
     * @return LinkedList<Token> - lista de tokens con
     * la descomposici�n de una cadena.
     */
    public LinkedList<Token> getTokensList() {
        return tokensList;
    }
}
