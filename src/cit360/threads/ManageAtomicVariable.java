/*
 * This is a class to showcase an atomic variable 
 *
 * It avoid the need for a syncronized methods 
 */
package cit360.threads;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author cordeirol
 */
public class ManageAtomicVariable implements Runnable {
    
    //note that i is a class variable and not an instance variable.
    //no explicit syncronized blocks are used here.
    private static final AtomicInteger i = new AtomicInteger(0);
    
    public static final String ADD = "add";
    public static final String SUBTRACT = "subtract";
    
    private final String clientType;
    
    //constructor
    public ManageAtomicVariable(String clientType) {
        this.clientType=clientType;
    }



    
    private int addOne() {
        return i.incrementAndGet();
    }
    
    private int takeOne() {
        return i.decrementAndGet();
    }

    // run in the context of a thread
    @Override
    public void run() {

        String tName = Thread.currentThread().getName();

        //just loop 100 times very fast
        int c=0;
        
        
        System.out.println(tName + " has this thread ID that does not change ever: " + Thread.currentThread().getId() );
        
        for (int i = 1; i <= 10; i++) {

            if(clientType.equals(ADD)) c=addOne();
            if(clientType.equals(SUBTRACT)) c=takeOne();
            
            System.out.println(tName + " just executed. The current value is: " + c );
            
            //no sleep for simulating higer concurrency
        }
        
        System.out.println(tName + " just completed. What is the atomic variable value:" + c );
           
    }   
    
}
