package eu.mihosoft.vmftext.tutorial01;

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