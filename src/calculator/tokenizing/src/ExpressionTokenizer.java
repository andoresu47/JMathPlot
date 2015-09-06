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
     * 
     */
    public ExpressionTokenizer() {
    }

    /**
     *
     * @return LinkedList<Token>
     */
    public LinkedList<Token> getTokensList() {
        return tokensList;
    }
}
