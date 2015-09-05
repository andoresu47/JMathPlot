package calculator.tokenizing;

/**
 * Created by Andr�s on 05/09/2015.
 * Clase encargada de manejar Tokens que sean n�meros.
 */
public class NumberToken extends Token {

    private double value;

    /**
     * Constructor que recibe un real y representa
     * el tipo del Token.
     * @param value - numero real con el que inicializar
     *              el Token.
     */
    public NumberToken(double value) {
        this.value = value;
        type = NUMBER;
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
