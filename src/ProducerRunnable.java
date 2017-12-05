/*
 * Kazuki Shin
 * Gallatin
 * 4/3/17
 */

import  java.util.Date;

/**
   This class will keep putting things into the queue.
*/
public class ProducerRunnable implements Runnable
{   
	// add instance fields (see constructor)
	private SynchronizedQueue queue;
	private int max;
   
   	
   /**
      Constructs the producer with a queue and count.
      @param aQueue the queue that the producer is going to produce to
      @param aCount the number of time that producer is going to produce
   */
   public ProducerRunnable(SynchronizedQueue aQueue, int aCount)
   {
   	queue = aQueue;
   	max = aCount;
   }

	/**
	 * Goes through the specified number of iterations, adding new strings to the queue
	 * with the Date().toString() method.  Displays the message "Adding: " and the string.
	 * This method also uses the interrupted() method to make sure that this thread has not
	 * been interrupted.  It has no "clean up" to do , but will cease performing iterations
	 * if it has been interrupted.  No delay is necessary.
	 */ 
   public void run()
   {
   	  try
   	  {
	   	  for(int i = 1; i <= max && !Thread.interrupted(); i++)
	      {
	      	Date d = new Date();
	      	String temp = d.toString();
	      	System.out.println ("Adding: " + temp);
	      	queue.add(temp);
	      }
   	  }
   	  catch(InterruptedException e){
   	  }
      
   }


}
