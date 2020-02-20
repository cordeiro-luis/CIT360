/*
 * CIT 360 JUnit samples
 */
package cit360.junit;

import static org.hamcrest.CoreMatchers.isA;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cordeirol
 */
public class SampleTest {
    
    public SampleTest() {
    }

    /**
     * Test of getString method, of class Sample.
     * Test 1 (good test)
     */
    @Test
    public void testGetString11() {
        System.out.println("testGetString11 - test assert equals ");
        boolean fail = false; //this is just to showcase a failure case for CIT360
        
        String expResult = "OK";
        String result = Sample.getString(fail);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getString method, of class Sample.
     * Test 1 (good test)
     */
    @Test
    public void testGetString12() {
        System.out.println("testGetString12 - test assert condition");
              
        String result1 = Sample.getString(false);
        String result2 = Sample.getString(true);
        
        assertTrue(result1.equals("OK") && result2 == null);
        
    }

      
    /**
     * Test of getStringArray method, of class Sample.
     */
    @Test
    public void testGetStringArray21() {
        System.out.println("testGetStringArray21 - test assert equals for arrays");
        boolean fail = false;
        
        String[] expResult = new String[]{"OK", "OK"};
        String[] result = Sample.getStringArray(fail);
        assertArrayEquals(expResult, result);
        
    }

    
    /**
     * Test of getStringArray method, of class Sample.
     */
    @Test
    public void testGetStringArray22() {
        System.out.println("testGetStringArray22 - test assert match for a String array");
        boolean fail = false;
        
        String[] result = Sample.getStringArray(fail);
        assertThat(result, isA(String[].class));
        
    }
   
        
    /**
     * Test of getObject method, of class Sample.
     */
    @Test
    public void testGetObject3() {
        System.out.println("testGetObject3 - test assert object not null. Simulated a failure");
        boolean instantiate = true; //show case an unexpected result

        Object result = Sample.getObject(instantiate);
        assertNotNull("object is null when it shouldn't",result);
        
    }


    /**
     * Test of getObject method, of class Sample.
     */
    @Test
    public void testGetObject4() {
        System.out.println("testGetObject4 - test assert not the same for object instances");
        boolean instantiate = true; //show case an unexpected result

        Object result1 = Sample.getObject(instantiate);
        Object result2 = Sample.getObject(instantiate);
        
        assertNotSame("objects are not the same",result1, result2);
        
    }

}
