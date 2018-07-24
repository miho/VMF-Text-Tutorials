# VMF-Text Tutorial 1

[HOME](https://github.com/miho/VMF-Text-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Text-Tutorials/blob/master/VMF-Text-Tutorial-02/README.md)

## Defining your First Language

### What you will learn

In this tutorial you will learn how to

- setup a Gradle project for VMF-Text
- create a basic grammar (labeled ANTLR4 grammar)
- use the generated implementation

### Setting up a Gradle Project

Since VMF-Text comes with a convenient Gradle plugin it's easy to setup. We just have to add the VMF-Text plugin id, e.g. via

```gradle
plugins {
  id "eu.mihosoft.vmftext" version "0.1.2.6" // use latest version
}
```
Now we can (optionally) configure VMF-Text and specify which versions shall be used:

```gradle
vmfText {
    vmfVersion   = '0.1'   // (runtime version)
    antlrVersion = '4.7.1' // (runtime version)
}
```

The plugin adds a source set `src/main/vmf-text/` to our Gradle project intended for the grammar files and model definitions. 
In our first example we want to generate code for a very basic language we call `ArrayLang`. It just supports arrays, i.e., comma-separated integer values, enclosed in paranthesis:

    (1,2,3)
    
or

    (102,-3,57)
    
Here's the grammar file that accepts array string as shown above:

```antlr
grammar ArrayLang;

array:  '(' values+=INT (',' values+=INT)* ')' EOF;

INT: SIGN? DIGIT+
   ;

fragment SIGN :'-' ;
fragment DIGIT : [0-9];

WS
    : [ \t\r\n]+ -> channel(HIDDEN)
    ;

/*<!vmf-text!>
TypeMap() {
  (INT    -> java.lang.Integer) = 'java.lang.Integer.parseInt(entry.getText())'
}
*/
```

The source directories of our tutorial project looks like this:

```
└── src
    └── main
        ├── java/eu/mihosoft/vmftext/tutorial01/Main.java
        │   
        └── vmf-text/eu/mihosoft/vmftext/tutorial01/ArrayLang.g4
```

That is, the `ArrayLang.g4` is located in the package `eu.mihosoft.vmftext.tutorial01`, just like the `Main` class.

### Running the Code Generator

After we created our first grammar we are ready to run the code generator via the `vmfTextGenCode`task, e.g. via

```
./gradlew vmfTextGenCode
```

VMF should show the following output:

```
> Task :vmfTextGenCode
  -> processing file: VMF-Text-Tutorial-01/src/main/vmf-text/eu/mihosoft/vmftext/tutorial01/ArrayLang.g4

// a lot of grammar related compile output and finally:

> Task :run
-> original array: ( 1 , 2 , 3 )
-> modified array: ( 1 , 2 , 3 , 4 )
```

### Using the Code

To use the API just use the generated code from your regular Java code, e.g, in `src/main/java`:

```java
package eu.mihosoft.vmf.tutorial01;

import eu.mihosoft.vmftext.tutorial01.parser.ArrayLangModelParser;
import eu.mihosoft.vmftext.tutorial01.unparser.ArrayLangModelUnparser;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // create a model parser
        ArrayLangModelParser parser = new ArrayLangModelParser();

        // parse the array '(1,2,3')
        String code = "( 1 , 2 , 3 )";
        System.out.println("-> original array: " + code);
        ArrayLangModel model = parser.parse(code);

        // add a value at the end of the array
        model.getRoot().getValues().add(4);

        // create an unparser for writing arrays to string
        ArrayLangModelUnparser unparser = new ArrayLangModelUnparser();

        // unparse the modified array
        String newCode = unparser.unparse(model);
        System.out.println("-> modified array: " + newCode);
        
    }
}
```

Parsing a string is something you could have done with plain ANTLR. So why do we need this? First, we get al the benefits from [VMF](https://github.com/miho/VMF). The object graph isfully observable, supports undo/redo, cloning and a lot more. But most importantly, we get **unparsing** support. That is, we can write a modified version of the model back into its textual representation. Now let's go through the code from above:

First, we create a parser:

```java
ArrayLangModelParser parser = new ArrayLangModelParser();
```

Now, we parse a string:

```java
String code = "( 1 , 2 , 3 )";
System.out.println("-> original array: " + code);
ArrayLangModel model = parser.parse(code);
```

This yields a model instance which we can modify. Let's add a number to the end of the array:

```java
model.getRoot().getValues().add(4);
```

To create a textual representation of the modified model, we just have to create an unparser object

```java
ArrayLangModelUnparser unparser = new ArrayLangModelUnparser();
```

and call the unparse method

```java
String newCode = unparser.unparse(model);
```

Finally, we can print the text:

```java
System.out.println("-> modified array: " + newCode);
```

Congrats, you have successfully created your first VMF-Text language model + API. If you are lazy you can get the full project [here](https://github.com/miho/VMF-Text-Tutorials/tree/master/VMF-Text-Tutorial-01).

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-02/README.md)
