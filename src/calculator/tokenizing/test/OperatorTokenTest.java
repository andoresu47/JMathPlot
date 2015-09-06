package calculator.tokenizing.test;

import calculator.tokenizing.src.OperatorToken;
import calculator.tokenizing.src.Token;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Andr�s on 05/09/2015.
 * Clase de prueba para la clase "OperatorToken".
 */
public class OperatorTokenTest {

    /**
     * M�todo que prueba el m�todo "getType" para la clase
     * "OperatorToken". Realmente se traduce en probar su
     * constructor.
     * @throws Exception si ocurre alguna anomal�a.
     */
    @Test
    public void testGetType() throws Exception {
        String operatorsSet = "+-*/^()#";             //Se define el conjunto de caracteres posibles. Se incluye a "#" para
        //contemplar todos los casos.
        int limit = operatorsSet.length();            //Se toma la longitud del arreglo.

        Random random = new Random();
        int key = random.nextInt(limit);              //Se toma un n�mero aleatorio en el rango de 0 a limit (excluyendo limit).

        char current = operatorsSet.charAt(key);      //Con key, se toma el caracter correspondiente.

        Token operator = new OperatorToken(current);

        //El valor de "type" estar� en el conjunto {-1,2,3,4,5,6,7,8}
        int type = operator.getType();

        if(type > 0){
            //Se le suma un 2 por la declaraci�n de constantes en la clase "Token"
            assertEquals(key + 2, type);
        }else{
            assertEquals(-1, type);
        }
    }

    /**
     * M�todo de prueba para el m�todo "getValue".
     * @throws Exception si ocurre alguna anomal�a.
     */
    @Test
    public void testGetValue() throws Exception {
        char test = '+';

        Token operator = new OperatorToken(test);

        char returnValue = (char)operator.getValue();

        assertEquals(test, returnValue);
    }
}