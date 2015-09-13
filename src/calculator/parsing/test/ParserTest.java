package calculator.parsing.test;

import calculator.parsing.src.Parser;
import calculator.tokenizing.src.ExpressionTokenizer;
import calculator.tokenizing.src.NumberToken;
import calculator.tokenizing.src.Token;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Andr�s on 13/09/2015.
 * Clase de prueba para la clase "Parser".
 */
public class ParserTest {
    /**
     * M�todo que prueba "isValid".
     * Se verifica que para una entrada v�lida el resultado sea
     * el correcto; lo mismo para una entrada inv�lida.
     * @throws Exception si ocurre alguna anomal�a.
     */
    @Test
    public void testIsValid() throws Exception {
        String clean = "sin(x + 4.56)",
                dirty = "sinx + 4.56)(";

        ExpressionTokenizer expTokenizer;
        Parser parser;

        Random random = new Random();
        int bound = random.nextInt(2);

        if(bound == 0){
            expTokenizer = new ExpressionTokenizer(clean);
            parser = new Parser(expTokenizer.getTokensList());

            assertTrue(parser.isValid());

        }else{
            expTokenizer = new ExpressionTokenizer(dirty);
            parser = new Parser(expTokenizer.getTokensList());

            assertFalse(parser.isValid());
        }
    }
}