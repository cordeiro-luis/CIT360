/*
 * This is the sample for Threads
 * 
 * In this sample one thread created objects that are put in a queue
 * two consumer thread will check this queue and if nothing is there wait
 * This is the typical consumer-producer that I love!
 * In my work I usualy decouple processing often using queues (not java)
 * In this example let's play with a priority queue (never used it)
 * 
 *`There are other ways to do this. In reality the queue could be a syncronized
 * one In this sample, I want to demonstrate the wait and notify capabilities.
 */
package cit360.threads;

import cit360.utils.TimeDate;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author cordeirol
 */
public class Sample {

    public static void testThreads() {

        System.out.println("\n\n\n\n------start atomic variable thread--------");

        // and we need at least two thread, to make it more interesting lets us four (in pairs)
        Thread incrementerA = new Thread(new ManageAtomicVariable(ManageAtomicVariable.ADD), "\"Incrementer A\"");
        incrementerA.start();

        Thread decrementerA = new Thread(new ManageAtomicVariable(ManageAtomicVariable.SUBTRACT), "\"Decrementer A\"");
        decrementerA.start();

        Thread incrementerB = new Thread(new ManageAtomicVariable(ManageAtomicVariable.ADD), "\"Incrementer B\"");
        incrementerB.start();

        Thread decrementerB = new Thread(new ManageAtomicVariable(ManageAtomicVariable.SUBTRACT), "\"Decrementer B\"");
        decrementerB.start();

        //observe the last thread result. It will always be zero with an even
        //mumber of incrementers and decrementers
        System.out.println("\n\n\n\n------end creating all atomic variable threads--------");

        //are the thread all completed? Check this before starting a new test so the output is not confusing
        boolean repeat = true;
        do {

            if (incrementerA.getState() == Thread.State.TERMINATED
                    && incrementerB.getState() == Thread.State.TERMINATED
                    && decrementerA.getState() == Thread.State.TERMINATED
                    && decrementerB.getState() == Thread.State.TERMINATED) {
                repeat = false;
            } else {
                //just wait two seconds
                try {
                    //rest a bit
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    //do nothing
                }

            }

        } while (repeat);

        
        System.out.println("\n\n\n\n------start test for producer consumer threads\n\n--------");

        //new Repository
        Repository repo = new Repository();

        // we need to set up a producer of objects (it could be more)
        Thread producer = new Thread(new Producer(repo, 10), "\"Producer\"");
        producer.start();

        // and we need at least two consumers, to make it more interesting    
        Thread consumerA = new Thread(new Consumer(repo), "\"Consumer A\"");
        consumerA.start();

        // and we need at least two consumers, to make it more interesting    
        Thread consumerB = new Thread(new Consumer(repo), "\"Consumer B\"");
        consumerB.start();

        //are the thread all completed? Check this before starting a new test so the output is not confusing
        repeat = true;
        do {

            if (producer.getState() == Thread.State.TERMINATED
                    && consumerA.getState() == Thread.State.TERMINATED
                    && consumerB.getState() == Thread.State.TERMINATED) {
                repeat = false;
            } else {
                //just wait two seconds
                try {
                    //rest a bit
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    //do nothing
                }

            }

        } while (repeat);

        System.out.println("\n\n\n\n------end test for producer consumer threads--------");


        System.out.println("\n\n\n\n------start test for Executor --------");

        //lets use the executor to try again the Atomc Variables. We will use the exact
        //same "runnables" as before
        // this creates a cache with threads that can be reused
        ExecutorService executor = Executors.newCachedThreadPool();

        executor.execute(new ManageAtomicVariable(ManageAtomicVariable.ADD));
        executor.execute(new ManageAtomicVariable(ManageAtomicVariable.SUBTRACT));

        try {
            //rest a bit
            Thread.sleep(10000); //sleep from 10 seconds
        } catch (InterruptedException ex) {
            //do nothing
        }
        
        System.out.println("\n\n------end test for Executor --------");

        
        // Experiment a Callable
        System.out.println("\n\n\n\n------start test for Executor with a Callable --------");
        
        
        //reusing the same executors cache for the Callables
        
        //at this time the previous two threads completed, so lets reuse them
        Future<Integer> futureAdd = executor.submit(new ManageAtomicVariableCallable(ManageAtomicVariable.ADD));
        Future<Integer> futureSubtract = executor.submit(new ManageAtomicVariableCallable(ManageAtomicVariable.SUBTRACT));
        
        System.out.println("\n\n------Observe the timestamp of the \"get\" --------");
        
        try {
            System.out.println("\n\n----"+TimeDate.getTimestamp()+"--Result for futureAdd: "+futureAdd.get());
            System.out.println("\n\n----"+TimeDate.getTimestamp()+"--Result for futureSubtract: "+futureSubtract.get());
            
            System.out.println("\n\n------Final Value --------"+ManageAtomicVariableCallable.i);
            
            
        } catch (InterruptedException | ExecutionException ex) {
            //do nothing
        }
        //do nothing
        
        //the executor will not stop listening, so we need to shutdown it
        //it will wait until all task complete
        try {
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        } finally {
            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }


        System.out.println("\n\n\n\n------end test for Executor with a Callable --------");
        
    }
    
}
