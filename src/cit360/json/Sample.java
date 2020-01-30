/*
 * This is the Sample for JSON

 */
package cit360.json;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author cordeirol
 */
public class Sample {
 
    
    public static void testJson() {
       
        //my main Gson instance for the below examples
        Gson myJsonParser = new Gson();
        
       
        //TEST 1 - Convert POJO to JSON string
        
        //create a "salute" instance of the HelloWorld class and populate it
        HelloWorld salute = new HelloWorld();
        salute.setName("Luis");
        
        
        //create a JSON string representing this obejct data 
        String strMyJson = myJsonParser.toJson(salute);

        //Output result
        System.out.println("TEST 1 POJO to JSON: "+strMyJson);
       
       
     
        //TEST 2: JSON to POJO (my object also has a method, let's put it to work
       
        //given my JSON string with my wife's name
        String strGivenJson = "{\"name\":\"Fátima\"}";
       
        //Let's create my POJO now
        HelloWorld helloWorldFromJson =  myJsonParser.fromJson(strGivenJson, HelloWorld.class);
       
       
        //Output result and see my POJO working with a method call
        System.out.println("TEST 2 JSON to POJO and method call to salute: "+helloWorldFromJson.getSalutation());
       
       
        //////////////////////////////
        // other experiments. Let's try a collection for fun
       
        // create a simple dictionary
        Map<String, String> words = new HashMap();

        words.put("Hash", " to talk about (something)");
        words.put("Academic", "relating to schools and education");
       
        //since Gson does not keep state (see javadocs) we can reuse it.
        String collectionToJsonStr = myJsonParser.toJson(words);
       
        System.out.println("OTHER TESTs, Collection to Json: "+collectionToJsonStr);
       
        //other "weird" experiments       
        //lets imagine we store in a json string an array of bytes like this below
        String myJsonArrayStr = "[72,101,108,108,111,32,87,111,114,108,100,33]";
       
        //Parse the JSON string directly into an byte array. This one made think a bit
        byte[] myBytArrayFromJSon = myJsonParser.fromJson(myJsonArrayStr, byte[].class);
      
        //What if the byte aray is an enconded UTF8 string?
        System.out.println("OTHER TESTs, JSON to primitive type array representing an encoded String: " + new String(myBytArrayFromJSon ));
       
    }
    
    
 
}
