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

        if(type > 0){
            assertEquals(key + 1, type);
        }else{
            assertEquals(-1, type);
        }
    }
}