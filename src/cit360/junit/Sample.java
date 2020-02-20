/*
 * This class exposes several methods so JUnit tests can be created
 * from all assert methods
 * The methods are going to be created to git assert methods. Normal
 * programs would be different
 */
package cit360.junit;

/**
 *
 * @author cordeirol
 */
public class Sample {
    

    /**
     * Test1 - check string. The argument "fail" simulates a bad test
     *
     * @return a string
     */
    public static String getString(boolean fail) {
        
        // the return
        String result = null;
        
        if (!fail) result = "OK";
        
        return result;
        
    }

    /**
     * Test2 - check string array. The argument "fail" simulates a bad test
     *
     * @return a string array
     */
    public static String[] getStringArray(boolean fail) {
        
        // the return
        String[] result = null;
        
        if (!fail) result = new String[]{"OK", "OK"};
        
        return result;
        
    }

    /**
     * Test3 - check null. The argument "instantiate" affects the output
     * Test4 - will be used to compare two objects
     *
     * @return an object
     */
    public static Object getObject(boolean instantiate) {
        
        // the return
        Object result = null;
        
        if (instantiate) result = new Object();
        
        return result;
        
    }
    
}
