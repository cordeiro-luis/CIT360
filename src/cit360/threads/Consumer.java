/*
 * This is a consumer
 */
package cit360.threads;

import cit360.utils.TimeDate;
import java.util.Calendar;
import java.util.Random;

/**
 *
 * @author cordeirol
 */
public class Consumer implements Runnable {

    private Repository repo;

    public Consumer(Repository repo) {
        this.repo = repo;
    }

    @Override
    public void run() {

        Random randNumber = new Random();
        String tName = Thread.currentThread().getName();

        int i = 0;

        do {

            System.out.println(tName + " is waiting for work items at " + TimeDate.getTimestamp());

            try {

                synchronized (repo) {
                    repo.wait(randNumber.nextInt(9000) + 1000); //wait up to ten seconds  
                }
            } catch (InterruptedException ex) {
                //do nothing
            }

            Integer workItem = repo.getFromWorkToBeDone();

            
            if (workItem == null) {
                i++;
            } else {
                System.out.println(tName + " is processing work item # " + workItem.toString() + " at " + TimeDate.getTimestamp());
                i=0; //reset
            }

        } while (i <= 10); //only wait ten times, at the end exit

        System.out.println(tName + " found no work items after ten tries. Giving up.");

    }

}
