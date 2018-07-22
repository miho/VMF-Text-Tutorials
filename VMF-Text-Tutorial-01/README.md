# VMF Tutorial 1

[HOME](https://github.com/miho/VMF-Tutorials/blob/master/README.md) [NEXT ->](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-02/README.md)

## Defining your First Model

### What you will learn

In this tutorial you will learn how to

- setup a Gradle project for VMF
- create a basic model
- use the generated implementation

### Setting up a Gradle Project

Since VMF comes with a convenient Gradle plugin it's easy to setup. We just have to add the VMF plugin id, e.g. via

```gradle
plugins {
  id "eu.mihosoft.vmf" version "0.1.1" // use latest version
}
```
Now we can configure VMF and specify which version shall be used:

```gradle
vmf {
    version = '0.1' // use desired VMF version
}
```

The plugin adds a source set `src/vmf/java` to our Gradle project intended for the model definition. 
In our first example we want to generate code for a very basic model. It just consists of one interface `Parent` with a single String property `name`. Here's how we can define the model as Java interface:

```java
package eu.mihosoft.vmf.tutorial01.vmfmodel;

interface Parent {
    String getName();
}
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



