/*
 * Kazuki Shin
 * Gallatin
 * 4/3/17
 */ 

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;

/**
   This class implements the FIFO queue data structure using an ArrayList of Strings.
 */
public class SynchronizedQueue
{
	
	private ArrayList<String> queue;
   	private int size;
   	private static final int DEFAULT_SIZE = 10;
  	// add Lock and Condition fields, and instantiate in the constructors
  	private Lock lock;
  	private Condition queueStat;
	   
	   /** 
	      Constructs the maximum size of the queue to default size.
	   */
	   public SynchronizedQueue()
	   {
	   	queue = new ArrayList<String>();
	   	size = DEFAULT_SIZE;
	   	lock = new ReentrantLock();
	   	queueStat = lock.newCondition();
	   }
	
	   /**
	      Constructs the maximum size of the queue to the given size.
	      @param aSize the maximum size of the queue
	   */
	   public SynchronizedQueue(int aSize)
	   {
	   	queue = new ArrayList<String>();
	   	size = aSize;
	   	lock = new ReentrantLock();
	   	queueStat = lock.newCondition();
	   }
	
	   /**
	      Adds a string into the queue.  If the queue is full, prints the message "Add Waiting"
	      and waits for the queue to have space for new strings. After successfully adding a string,
	      sends a signal to other threads that the queue is no longer empty. Uses a lock to secure the 
	      queue while it is adding.
	      @param item the item to add
	   */
	   public void add(String item) throws InterruptedException
	   {
		   lock.lock();
	      try
	      {
	      	while(isFull())
	      	{
	      		System.out.println ("Add Waiting");
	      		queueStat.await();
	      	}
	      	queue.add(item);
	      	queueStat.signalAll();
	      }
	      finally
	      {
	    	  lock.unlock();
	      }
	   }
	
	   /**
	      Removes one item from the queue.  If the queue is empty, prints the message "Remove Waiting"
	      and waits for the queue to have strings in it to remove.  After successfully removing a string,
	      sends a signal to other threads that the queue is no longer full.  Uses a lock to secure the
	      queue while it is adding.
	      @return the first item of the queue
	   */
	   public String remove() throws InterruptedException
	   {
	   	  String temp = "";
	   	  lock.lock();
	      try
	      {
	      	while(isEmpty())
	      	{
	      		System.out.println ("Remove Waiting");
	      		queueStat.await();
	      	}
	      	temp = queue.remove(0);
	      	queueStat.signalAll();
	      }
	      finally
	      {
	    	  lock.unlock();
	      }
	      return temp;
	   }
	
	   /**
	      Check if the queue is empty.
	      @return true if the queue is empty, else false
	   */
	   public boolean isEmpty()
	   {
	      return queue.size() == 0;
	   }
	
	   /** 
	      Check if the queue is full.
	      @return true if queue size equals to maximum size, else false
	   */
	   public boolean isFull()
	   {
	      return queue.size() == size;
	   }


}