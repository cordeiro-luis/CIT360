/*
 * Class representing the place where work item are saved
 */
package cit360.threads;

import java.util.PriorityQueue;

/**
 *
 * @author cordeirol
 */
public class Repository {

    
    //The workToBeDone static, the repository is a wrapper so the syncronization
    //and the wait, notify work (instance level)
    private static PriorityQueue workToBeDone = new PriorityQueue();

    public Repository() {
    }

    //Methods used by the threads. Access needs to be synchronized to
    //avoid collisions
    public synchronized Integer getFromWorkToBeDone() {
        return (Integer)workToBeDone.poll();
    }

    public synchronized void putFromWorkToBeDone(Integer todo) {

        workToBeDone.offer(todo);
        return;
    }

}
