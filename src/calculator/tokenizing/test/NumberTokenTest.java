package calculator.tokenizing.test;

import calculator.tokenizing.src.NumberToken;
import calculator.tokenizing.src.Token;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Andr�s on 05/09/2015.
 * Clase de prueba para la clase "NumberToken".
 */
public class NumberTokenTest {

    /**
     * M�todo que prueba el m�todo "getType" para la clase
     * "NumberToken". Realmente se traduce en probar su
     * constructor.
     * @throws Exception si ocurre alguna anomal�a.
     */
    @Test
    public void testGetType() throws Exception {

        Random random = new Random();

        double testDouble = random.nextDouble();

        Token number = new NumberToken(testDouble);

        int type = number.getType();

        assertEquals(0, type);
    }

    /**
     * M�todo que prueba el m�todo "getValue" para la clase
     * "NumberToken".
     * @throws Exception si ocurre alguna anomal�a.
     */
    @Test
    public void testGetValue() throws Exception {
        Random random = new Random();

        double testDouble = random.nextDouble();

        NumberToken number = new NumberToken(testDouble);

        double value = number.getValue();

        assertEquals(value, testDouble, 0.001);
    }
}