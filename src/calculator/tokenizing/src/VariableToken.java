package calculator.tokenizing.src;

/**
 * Created by Andr�s on 05/09/2015.
 * Clase encargada de manejar Tokens que sean variables.
 */
public class VariableToken extends Token {

    private char value;
    private double evaluation;

    /**
     * Constructor que recibe un caracter y representa el
     * tipo del Token.
     * @param variable - caracter que se espera que represente
     *                 a la variable definida.
     */
    public VariableToken(char variable) {
        value = variable;

        switch (variable){
            case 'x':
                type = VARIABLE;
                evaluation = 0;
                break;
            default:
                type = UNKNOWN_TOKEN;
                evaluation = 0;
        }
    }

    /**
     * M�todo que regresa el valor del token.
     *
     * @return Character - valor del token.
     */
    @Override
    public Character getValue() {
        return value;
    }

    /**
     * M�todo que regresa la evaluaci�n actual de la variable.
     *
     * @return double - evaluaci�n actual de la variable.
     */
    public double getEvaluation() {
        return evaluation;
    }

    /**
     * M�todo que establece el valor expl�cito que tomar�
     * la variable.
     * @param evaluation - evaluaci�n actual de la variable.
     */
    public void setEvaluation(double evaluation) {
        this.evaluation = evaluation;
    }

    /**
     * M�todo que determina si dos tokens son iguales.
     *
     * @return boolean - true si son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VariableToken that = (VariableToken) o;

        return value == that.value;

    }

    @Override
    public int hashCode() {
        return (int) value;
    }
}
