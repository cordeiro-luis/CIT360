/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cit360.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author cordeirol
 */
public class ManageAtomicVariableCallable implements Callable<Integer> {
    
    //note that i is a class variable and not an instance variable.
    //no explicit syncronized blocks are used here.
    public static final AtomicInteger i = new AtomicInteger(0);
    
    public static final String ADD = "add";
    public static final String SUBTRACT = "subtract";
    
    private final String clientType;
    
    //constructor
    public ManageAtomicVariableCallable(String clientType) {
        this.clientType=clientType;
    }
    
    private int addOne() {
        return i.incrementAndGet();
    }
    
    private int takeOne() {
        return i.decrementAndGet();
    }

    // run in the context of a thread
    // this is the big difference. Instead of a "void" it returns a value
    @Override
    public Integer call() {

        String tName = Thread.currentThread().getName();

        //just loop 100 times very fast
        int c=0;
        
        System.out.println(tName + " has this thread ID that does not change ever: " + Thread.currentThread().getId() );
        
        for (int i = 1; i <= 10; i++) {

            if(clientType.equals(ADD)) c=addOne();
            if(clientType.equals(SUBTRACT)) c=takeOne();
            
            //no sleep for simulating higer concurrency
        }
        
            try {
                //rest a bit
                Thread.sleep(3000); //sleep from 3 seconds
            } catch (InterruptedException ex) {
                //do nothing
            }

        
        
        return Integer.valueOf(c); //return the result
    }   
    
}
