package calculator.tokenizing;

/**
 * Created by Andr�s on 05/09/2015.
 * Clase encargada de manejar Tokens que sean variables.
 */
public class VariableToken extends Token {

    /**
     * Constructor que recibe un caracter y representa el
     * tipo del Token.
     * @param variable - caracter que se espera que represente
     *                 a la variable definida.
     */
    public VariableToken(char variable) {
        switch (variable){
            case 'x':
                type = VARIABLE;
                break;
            default:
                type = UNKNOWN_TOKEN;
        }
    }

    /**
     * M�todo que regresa el tipo del Token.
     *
     * @return int - entero correspondiente al
     * tipo del Token.
     */
    @Override
    public int getType() {
        return type;
    }
}
