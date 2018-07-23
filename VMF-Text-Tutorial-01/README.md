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
Now we can (optionally) configure VMF-Text and specify which version shall be used:

```gradle
vmfText {
    version      = '0.1.1' // use desired VMF version
    vmfVersion   = '0.1'   //
    antlrVersion = '4.7.1  //
}
```

The plugin adds a source set `src/main/vmf-text/` to our Gradle project intended for the grammar files and model definitions. 
In our first example we want to generate code for a very basic language we call `ArrayLang`. It just supports arrays, i.e., comma-separated integer values, enclosed in paranthesis:

    (1,2,3)
    
or

    (102,-3,57)
    
Here's the grammar file, which is located in `src/main/vmf-text/eu/mihosoft/vmftext/tutorial01/ArrayLang.g4` :    

```java
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
src
├── main/java ...
│         └── ...
│   
└── vmf/java
          ├── /eu/mihosoft/vmf/tutorial01/vmfmodel/Parent.java
          └── ...
```

### Running the Code Generator

After we created our first model definition we are ready to run the code generator via the `vmfGenModelSource`task, e.g. via

```
./gradlew vmfGenModelSources
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

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // create a new parent instance
        Parent parent = Parent.newInstance();
        
        // set parent's name
        parent.setName("My Name");
        
        // check that name is set
        if("My Name".equals(parent.getName())) {
          System.out.println("name is correctly set");
        } else {
          System.out.println("something went wrong :(");
        }
        
    }
}
```

Congrats, you have successfully created your first VMF model. If you are lazy you can get the full project [here](https://github.com/miho/VMF-Tutorials/tree/master/VMF-Tutorial-01).

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-02/README.md)



