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
        tokensList = new LinkedList<>();
        char[] charArray = expression.toCharArray();
        int size = charArray.length;
        int i = 0;
        char present;
        StringBuffer sequence;

        while(i < size){
            present = charArray[i];
            if(present == ' ' || present == '\n' || present == '\t'){
                i++;
            }else{
                if(present == '+' || present == '-' || present == '*' || present == '/' || present == '^' ||
                        present == '(' || present == ')'){
                    tokensList.addLast(new OperatorToken(present));
                    i++;
                }else{
                    if(present == 'x'){
                        tokensList.addLast(new VariableToken(present));
                        i++;
                    }else{
                        if(present == 's' || present == 'c' || present == 't'){
                            sequence = new StringBuffer();
                            for(int j = 0; j < 3; j++){
                                sequence.append(present);
                                i++;
                                if(i < size){
                                    present = charArray[i];
                                }else{
                                    break;
                                }
                            }
                            String function = sequence.toString();
                            if(function.equals("sin") || function.equals("cos") || function.equals("tan") ||
                                    function.equals("cot") || function.equals("sec") || function.equals("csc") ||
                                    function.equals("sqr")){
                                tokensList.addLast(new FunctionToken(function));
                            }else{
                                tokensList = null;    //Al no ser v�lida la entrada, no se tendr�n tokens.
                                break;
                            }
                        }else{
                            if(Character.isDigit(present) || present == '.'){
                                sequence = new StringBuffer();

                                while(Character.isDigit(present) || present == '.'){
                                    sequence.append(present);
                                    i++;
                                    if(i < size){
                                        present = charArray[i];
                                    }else{
                                        break;
                                    }
                                }
                                String numberStr = sequence.toString();
                                if(isParsable(numberStr)){
                                    double number = Double.parseDouble(numberStr);
                                    tokensList.addLast(new NumberToken(number));
                                }else{
                                    tokensList = null;
                                    break;
                                }
                            }else{
                                tokensList = null;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * M�todo que devuelve la lista de tokens que se cre�
     * a partir de la expresi�n con la que se construy�
     * el objeto. Esta lista s�lo incluir� elementos permitidos
     * por la gram�tica, descartando caracteres ajenos.
     * @return LinkedList - lista de tokens permitidos
     * con la descomposici�n de una cadena. Null si la expresion
     * contiene alg�n valor que no sea uno de los tokens definidos.
     */
    public LinkedList<Token> getTokensList() {
        return tokensList;
    }

    /**
     * M�todo auxiliar en la determinaci�n de que una cadena
     * pueda ser transformada a un n�mero real.
     * @param input - cadena a verificar.
     * @return boolean - true si s� se puede, false en caso contrario.
     */
    private boolean isParsable(String input){
        boolean parsable = true;
        try{
            Double.parseDouble(input);
        }catch(Exception e){
            parsable = false;
        }

        return parsable;
    }
}
