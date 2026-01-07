# JMathPlot

This project is primarily a compiler for mathematical expressions, but it is also an interactive GUI application that displays their graphical representation.

<img src="https://github.com/andoresu47/JMathPlot/blob/master/Demo.gif" width="680" height="534"/>

---

## What does this program do?

This application produces a **GUI that displays the graphical representation of a mathematical expression entered by the user** — in other words, a **function plotter**.

The mathematical expression is the program’s input, given as a string of characters organized so that it can be parsed using the following infix grammar:

```
<expression> ::= <number> |
                <variable> |
                "(" <expression> ")" |
                "-" <expression> |
                <function> "(" <expression> ")" |
                <expression> <operator> <expression>
                
                <number> ::= <decimal> | <digits>

                <decimal> ::= <digits> "." <digits> | <digits> "." | "." <digits>

                <digits> ::= <digit> | <digit> <digit-list>

                <digit-list> ::= "" | <digit> <digit-list>

                <digit> ::= "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9"

                <variable> ::= "x"

                <function> ::= "sin" | "cos" | "tan" | "cot" | "sec" | "csc" | "sqr"

                <operator> ::= "+" | "-" | "*" | "/" | "^"
```

The program validates that the input follows this grammar. If an error is detected, it prompts the user to re-enter the data. 

The interface also provides fields for specifying the **X and Y ranges** of the plot.

---

## Features

- **Plot** – Interprets the user input and draws the graph.  
- **Clear** – Clears the plot and restores the initial view.  
- **Save** – Saves the plot as an image (`.png`, `.jpg`, `.gif`, `.bmp`). The user must specify the extension.

The window can be resized by dragging its corners; the plot automatically scales while preserving its visual correctness.

---

## Implementation

The application consists of four main components:

1. **Tokenizer** – Splits the input string into valid tokens according to the grammar.  
2. **Parser** – Checks that the token sequence is syntactically correct.  
3. **Evaluation** – Evaluates the expression into a list of coordinates.  
4. **Plotting** – Converts coordinates to pixels and draws the graph.

---

## Interface Layout

- **Top** – Toolbar with **Clear** and **Save** buttons.  
- **Center** – Plotting area with coordinate axes and grid.  
- **Bottom** – Fields for X/Y ranges and the **Plot** button.

Axes may be hidden if the selected ranges exclude the origin, allowing focus on specific regions.

---

## How to Compile and Run

The project was developed in **IntelliJ IDEA 14.1.1**, but it can be run in any Java environment.  
It includes a `build.xml` file for **Apache ANT**.

After downloading the repository (keeping the directory structure intact), open a terminal in the project root and run:

- `ant` – Compiles and runs the program.  
- `ant test` – Runs all unit tests.  
- `ant doc` – Compiles the program and generates documentation.

---

## Notes

- All **class, function, and method names are in English** for consistency with Java’s default syntax.  
- All **comments and documentation are (for the moment) in Spanish**.
