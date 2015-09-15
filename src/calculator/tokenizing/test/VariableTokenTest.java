package calculator.tokenizing.test;

import calculator.tokenizing.src.Token;
import calculator.tokenizing.src.VariableToken;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Andr�s on 05/09/2015.
 * Clase de prueba para la clase "VariableToken".
 */
public class VariableTokenTest {

    /**
     * M�todo que prueba el m�todo "getType" para la clase
     * "VariableToken". Realmente se traduce en probar su
     * constructor.
     * @throws Exception si ocurre alguna anomal�a.
     */
    @Test
    public void testGetType() throws Exception {

        String variablesSet = "x#";

        int limit = variablesSet.length();

        Random random = new Random();
        int key = random.nextInt(limit);

        char current = variablesSet.charAt(key);

        Token variable = new VariableToken(current);

        //El valor de "type" estar� en el conjunto {-1,1}
        int type = variable.getType();
        System.out.println(current);

        if(type > 0){
            assertEquals(key + 1, type);
        }else{
            assertEquals(-1, type);
        }
    }

    /**
     * M�todo para probar el m�todo "getValue"
     * @throws Exception si ocurre alguna anomal�a.
     */
    @Test
    public void testGetValue() throws Exception {
        char test = 'x';

        Token variable = new VariableToken(test);

        char returnValue = (char)variable.getValue();

        assertEquals(test, returnValue);
    }

    /**
     * M�todo para probar el m�todo "getEvaluation"
     * @throws Exception si ocurre alguna anomal�a.
     */
    @Test
    public void testGetEvaluation() throws Exception {
        char test = 'x';

        VariableToken variable = new VariableToken(test);

        variable.setEvaluation(4.56);

        double actualValue = variable.getEvaluation();

        assertEquals(4.56, actualValue, 0.0001);
    }

    /**
     * M�todo de prueba para los m�todos "equals" y "hashCode".
     * @throws Exception si ocurre alguna anomal�a.
     */
    @Test
    public void testEquals_Symmetric() throws Exception {
        VariableToken variable1 = new VariableToken('x');
        VariableToken variable2 = new VariableToken('x');

        assertTrue(variable1.equals(variable2) && variable2.equals(variable1));
        assertTrue(variable1.hashCode() == variable2.hashCode());
    }
}