/*
 * This is the producer
 */
package cit360.threads;

import cit360.utils.TimeDate;
import java.util.Calendar;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cordeirol
 */
public class Producer implements Runnable {

    private int maxProducerCycles = 0;
    private Repository repo;

    public Producer(Repository repo, int maxProducerCycles) {
        this.maxProducerCycles = maxProducerCycles;
        this.repo = repo;
    }

    @Override
    public void run() {

        Random randNumber = new Random();
        String tName = Thread.currentThread().getName();

        for (int i = 1; i <= maxProducerCycles; i++) {

            Integer rndN = randNumber.nextInt(20);
            System.out.println(tName + " is making item #" + i + " with content: " + rndN + " at " + TimeDate.getTimestamp());

            // let me start to producing the item        
            Integer work = new Integer(rndN);
            repo.putFromWorkToBeDone(work);

            
            synchronized (repo) {
                //now warn consumers that a work item is ready. 
                repo.notifyAll();
            }
             
            System.out.println(tName + " is resting");

            try {
                //rest a bit
                Thread.sleep(randNumber.nextInt(2000) + 1000); //sleep from 1 second up to 3 seconds
            } catch (InterruptedException ex) {
                //do nothing
            }

        }

        //at the end of these cycles the thread will complete it's work and exit
    }

}
