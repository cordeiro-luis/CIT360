/*
 * This is the Sample for JSON

 */
package cit360.urlHttp;

import com.google.gson.Gson;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;


/**
 *
 * @author cordeirol
 */
public class Sample {
 
    private static final String TEST_SERVER = "https://jsonplaceholder.typicode.com/todos/1";
    
    //simulate a host that no longer exists
    private static final String TEST_SERVER_WITH_PROBLEM  = "https://xxxxx.jsonplaceholder.typicode.com/todos/1";
    
    
    
    public static void testURLHttp() {
        
        
        //TEST 1 - Sunny Day
        System.out.println("TEST 1 - Sunny Day\n---------------------");
        Sample.callURLHttp(TEST_SERVER);
        
        //TEST 2 - URL is not available
        System.out.println("\n\nTEST 2 - Rainy Day\n---------------------");
        Sample.callURLHttp(TEST_SERVER_WITH_PROBLEM);
        
   
    }
     
    
    public static void callURLHttp(String URLString) {
            
        //My JSON parser           
        Gson myJsonParser = new Gson();
        String myJsonStr = null;
        
        try {    
            
            // go an HTTP GET by given ULR to test json data
            URL jsonTestServer = new URL(URLString);
            URLConnection urlConnection = jsonTestServer.openConnection();
            
            //now cast HTTP connection
            HttpURLConnection connection = (HttpURLConnection)urlConnection;
            
            connection.setDoOutput(false); //we only need to GET
            connection.setDoInput(true);
            connection.setUseCaches(false); //just that we aways go to the server
            
            connection.connect();
                        
            //just for fun, let's see the actual HTTP received headers.
            //This "functional" operations notation is new to me. Interesting stuff.
            connection.getHeaderFields().entrySet().stream().forEach((k) -> {
                k.getValue().stream().forEach((v) -> {
                    String key = k.getKey();
                    if (key==null) {key="";} else {key=key+":";}
                    System.out.println(key+ v);
                });
            });
                       
            //get the payload size
            int length = connection.getContentLength();
            
            //get the payloas encoding from the proper header first and then from the content type 
            String encoding = connection.getContentEncoding();
            if (encoding == null) encoding = connection.getContentType().split("=")[1];
            
            //we could even check if the type is "jason" an throw an exception if not
                       
            //create a byte array to accomodate the payload (input stream)
            byte[] response = new byte[length];
            InputStream is = connection.getInputStream();
            
            int bytes = is.read(response);
            
            //close the HTTP connection
            connection.disconnect();
           
            myJsonStr = new String(response, Charset.forName(encoding));
            
        } catch (Exception ex) {
             System.out.println("Exception \n"+ex.toString());
             return;
        }
              
              
        //Let's create my POJO now 
        DummyJson myJsonInstance =  myJsonParser.fromJson(myJsonStr, DummyJson.class);
        
        
        System.out.println("---------------------\nGet the POJO contents from the HTTP JSON results");
        System.out.println("userId: "+myJsonInstance.getUserId() );
        System.out.println("id: "+myJsonInstance.getId() );
        System.out.println("title: "+myJsonInstance.getTitle() );
        System.out.println("completed: "+myJsonInstance.getCompleted() );
             
    }
}
