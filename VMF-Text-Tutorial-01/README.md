# VMF Tutorial 1

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
  id "eu.mihosoft.vmftext" version "0.1.1" // use latest version
}
```
Now we can (optionally) configure VMF-Text and specify which versions shall be used:

```gradle
vmfText {
    vmfVersion   = '0.1'   // (runtime version)
    antlrVersion = '4.7.1  // (runtime version)
}
```

The plugin adds a source set `src/main/vmf-text/` to our Gradle project intended for the grammar files and model definitions. 
In our first example we want to generate code for a very basic language we call `ArrayLang`. It just supports arrays, i.e., comma-separated integer values, enclosed in paranthesis:

    (1,2,3)
    
or

    (102,-3,57)
    
Here's the grammar file, which is located in `src/main/vmf-text/eu/mihosoft/vmftext/tutorial01/ArrayLang.g4` :    

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
> Task :vmfGenModelSources
 -> generating code for vmf model in package: eu/mihosoft/vmf/tutorial01/vmfmodel
```

### Using the Code

To use the code just use the generated code from your regular Java code, e.g, in `src/main/java`:

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

Congrats, you have successfully created your first VMF model. If you are lazy you can get the full project [here](https://github.com/miho/VMF-Tutorials/tree/master/VMF-Tutorial-01).

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-02/README.md)



