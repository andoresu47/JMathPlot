# JMathPlot
Primer proyecto de "Modelado y Programación". Este consiste en un compilador sencillo para expresiones matemáticas. 
La especificación completa se encuentra en la liga: http://aztlan.fciencias.unam.mx/~canek/2016-1-myp/proyecto1.html

#¿Qué hace este programa y cómo funciona?

Este programa, o como prefiero llamarle, aplicación; consiste de varios archivos que trabajan en conjunto para producir un
resultado final. Este resultado final es una GUI que muestra la representación gráfica de una expresión matemática introducida 
por un usuario; es decir, se trata de un graficador de funciones. 

La expresión matemática discutida antes es la entrada del programa, y consiste de una cadena de caracteres organizados de manera que 
esta pueda derivarse por medio de la siguiente gramática infija:

\<expresión> ::= \<número> |
                \<variable> |
                "(" \<expresión> ")" |
                "-" \<expresión> |
                \<función> "(" <expresión> ")" |
                \<expresión> <operador> <expresión>

\<número> ::= \<decimal> | \<dígitos>

\<decimal> ::= \<dígitos> "." \<dígitos> | \<dígitos> "." | "." \<dígitos>

\<dígitos> ::= \<dígito> | \<dígito> \<lista-dígitos>

\<lista-dígitos> ::= "" | \<dígito> \<lista-dígitos>

\<dígito> ::= "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9"

\<variable> ::= "x"

\<función> ::= "sin" | "cos" | "tan" | "cot" | "sec" | "csc" | "sqr"

\<operador> ::= "+" | "-" | "*" | "/" | "^"

El programa valida que la entrada sea correcta, por lo que en caso de que se cometa un error, solamente se solicitarán nuevos datos. 

La interfaz también cuenta con otros campos de interacción con el usuario. Se tienen campos de texto en los que el usuario 
puede (y debe) introducir tanto el rango en X como en Y de la grafica que se espera obtener. 

La interfaz cuenta con tres botones, los cuales definen las acciones que puede llevar a cabo el usuario con el programa. Estos son: 
* Graficar: Se interpretan los datos introducidos por el usuario y desencadena la visualización de esta evaluación.
* Limpiar: Se borra del cambo de visualización cualquier gráfica previa, y se reestablece la vista inicial.
* Guardar: Se abre una ventana en donde el usuario puede navegar en los directorios de su máquina para guardar una imagen 
de la gráfica generada en formatos: *.png, *.jpg, *.gif, *.btm. La terminación debe introducirla el usuario explícitamente.

Otro aspecto importante acerca del programa, es que se puede modificar el tamaño de visualización símplemente al arrastrar 
una de las esquinas de la ventana de la aplicación. Automáticamente se reescalará todo el contenido que haya dentro; preservando 
la validez de la representación visual de la expresión matemática. 

# ¿Cómo está implementado?
Básicamente, se tiene la interacción de 4 componentes principales en la implementación de esta aplicación. Estos son:
* Tokenizer: Se divide la cadena introducida por el usuario en "fichas" propias de la gramática antes mencionada; es decir, que 
sólo se tenga una lista de "fichas" válidas.
* Parser: Se verifica que la sintáxis de la lista de tokens sea válida según la gramática. 
* Evaluación: Se evalúa la expresión una vez que se probó que es correcta en cuanto a sintaxis. Esta evaluación se traduce en 
una lista de coordenadas.
* Graficación: Se toma la lista de coordenadas, se transforma a una lista de coordenadas pero de pixeles y se procede a trazar 
en la región de graficado cada punto, lo que supone el trazado de la gráfica una vez dibujados todos los puntos.

# ¿Cómo se visualiza?
Se divide la ventana de la aplicación en tres partes. La primera, ubicada en la parte superior consiste de los botones "Limpiar" y "guardar" 
puestos a manera de una pequeña barra de herramientas. En el área central de la ventana, se tiene la región destinada para el 
trazado de las gráficas. Mientras que en la región inferior, se tienen las distintas opciones discutidas antes, en las que el 
usuario puede interactuar introduciendo datos; así mismo, aquí se encuentra el botón de "Graficar". 

Por otra parte, en cuanto al área de trazado de gráficas, se tiene definido un eje coordenado con respecto al cual se trazan 
los puntos. Este eje coordenado divide en 4 a la pantalla. Así mismo, se tiene también una malla de coordenadas que sirven para dar 
referencia del espacio que abarcan las distintas gráficas. Dados los rangos X y Y, es posible que no se visualice el eje coordenado 
pues es posible para el usuario querer ver una región específica del plano que se encuentre alejada del origen. Por lo
que en ese aspecto no se tiene ninguna limitante.

# ¿Cómo se compila y se ejecuta?
Esta aplicación se desarrolló utilizando el IDE "IntelliJ IDEA 14.1.1"; no obstante, al ser un proyecto en java, es directo
hacer el cambio a trabajar en otro entorno, por ejemplo, la línea de comando. Así bien, este programa puede compilarse y 
ejecutarse con ANT haciéndo uso del archivo "build.xml" que se incluye en este repositorio. 

Para hacer uso de esta herramienta primero deben bajarse los archivos tal cual se tienen en el repositorio; es decir, conservando
la relación de directorios. Una vez hecho esto, úbicándose en el directorio donde se pusieron los archivois, desde la línea de 
comando se puede ejecutar ANT por medio de los siguientes comandos:
* ant : es la opción por default. Compila el programa y lo corre. 
* ant test : corre todas las pruebas unitarias programadas y muestra si se pasaron correctamente o no.
* ant doc : además de realizar la compilación, genera la documentación del proyecto. 

# Notas importantes 
Dentro del programa; ie, en el código se nombraron todas las clases, funciones y métodos en inglés. Esto, pues a veces resulta
desagradable estar combinando idiomas ya que los comandos de Java son por default en ingles. No obstante, todos los comentarios y
la documentación están en español.
