/*
 * This is the Sample for Collections
 */
package cit360.collections;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author cordeirol
 */
public class Sample {

    /**
     * @description: Test collections
     * @author cordeirol
     */
    public static void testCollections() {

        System.out.println("------start test collections--------");

        testHashMap();
        testTreeMap();
        testArrayList();
        testTreeSet();
        testLinkedList();
             
        System.out.println("------end test collections--------");
        
    }

    /**
     * @description: Test HashMap for dictionary
     * @author cordeirol
     */
    private static void testHashMap() {

        System.out.println("------test HashMap--------");
        System.out.println("--What if we want a dictionary for a quick lookup of words. This is acomplished with a HasMap.");

        // create a simple dictionary
        Map<String, String> words = new HashMap();

        words.put("Hash", " to talk about (something)");
        words.put("Academic", "relating to schools and education");
        //... and so on

        //get a word meaning
        String meaning = words.get("Academic");

        System.out.println("The meaning of Academic is: " + meaning);

        System.out.print("\n");
        
    }

    
    
    /**
     * @description: Test TreeMap for sorted dictionary
     * @author cordeirol
     */    
    private static void testTreeMap() {

        System.out.println("------test TreeMap--------");
        System.out.println("--What if we want to sort in a special way? This is authomatic with a TreeMap");

        // create a simple dictionary
        Map<Integer, String> positions = new TreeMap<>();

        //add entris randomly
        positions.put(Integer.valueOf(9), "IX");
        positions.put(Integer.valueOf(1), "I");
        positions.put(Integer.valueOf(5), "V");
        positions.put(Integer.valueOf(3), "III");
        //... and so on

        
        System.out.println("--Print the entries sorted using the key set...");
        
        //print out the position meanings  in order. An extract the key set is used to interate. this Set is rodered.
        Set keys = positions.keySet();
        for (Object value : keys) {
            System.out.println( positions.get(value) );
        }
            
        System.out.print("\n");
        
        System.out.println("--The same can be done with the entry set... it will inlude the keys");
        
        //print out the position meanings  in order. An extract the key set is used to interate. this Set is rodered.
        Set entries = positions.entrySet();
        for (Object value : entries) {
            System.out.println( value );
        }
            
        System.out.print("\n");
        
    }
    
    
    /**
     * @description: Test ArrayList
     * @author cordeirol
     */
    private static void testArrayList() {

        System.out.println("------test ArrayList--------");

        // create a simple word list
        List<String> words = new ArrayList<>();  //no predefined size is necessary
        words.add("Hash");
        words.add("Academic");
        //... and so on

        //get the second word
        String word = words.get(1);

        System.out.println("The second word is: " + word);

        //get the full list of words
        System.out.println("The word list is: ");
        for (String value : words) {
            System.out.println("word: " + value);
        }

        //or we can loop the List and fetch the values on the Map
        System.out.print("\n");
    }

    
    /**
     * @description: Test TreeMap for sorted dictionary
     * @author cordeirol
     */    
    private static void testTreeSet() {

        System.out.println("------test TreeSet--------");
        System.out.println("--What if we want a Set that is sorted? This is authomatic with a TreeSet");

        // create a simple dictionary
        Set<Integer> positions = new TreeSet<>();

        //add entries randomly
        positions.add(10);  //the add method accepts a primitive
        positions.add(Integer.valueOf(1));
        positions.add(Integer.valueOf(5));
        
        positions.add( new Integer(3) );
        
        
        //let try to add another instance of an object that represents the same int
        positions.add( new Integer(3) );
        positions.add( new Integer(3) );
        positions.add( new Integer(3) );
        
        //... and so on
        
        //print out the position in order.
        for (Integer value : positions) {
            System.out.println( value.toString() );
        }

        System.out.println("--The Set does not allow multipe objects (instances) that are comparable, to be a part of the set");
                
        System.out.print("\n");

    }


    /**
     * @description: Test TreeMap for sorted dictionary
     * @author cordeirol
     */    
    private static void testLinkedList() {

        System.out.println("------test LinkedList--------");
        System.out.println("--What if we want a Set that is not sorted and works in a FIFO amnner? This is authomatic with a LinkedList");

        // create a simple dictionary
        List<Integer> positions = new LinkedList<>();

        //add entries randomly
        positions.add(Integer.valueOf(10));
        positions.add(Integer.valueOf(1));
        positions.add(Integer.valueOf(5));
 
        positions.add( new Integer(3) );
               
        //let try to add other instances of an object that represents the same int. This is allowed
        positions.add( new Integer(3) );
        positions.add( new Integer(3) );
        positions.add( new Integer(3) );
        
        //... and so on
        
        //print out the position in order.
        for (Integer value : positions) {
            System.out.println( value.toString() );
        }

        System.out.println("--The List does allows multipe objects (instances) that are comparable, to be a part of the set. Observe the FIFO behavior.");
                
        System.out.print("\n");

    }
}
