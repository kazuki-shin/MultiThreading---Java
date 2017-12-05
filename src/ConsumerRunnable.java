/*
 * Kazuki Shin
 * Gallatin
 * 4/3/17
 */

/**
   This class will keep removing things from the queue
*/
public class ConsumerRunnable implements Runnable
{
	// add instance fields (see constructor)
	private SynchronizedQueue queue;
	private int max;
	
   /**
      Constructs the consumer with a queue and count.
      @param aQueue the queue that the consumer is going to consume from
      @param count the number of times that consumer is going to consume
   */
   public ConsumerRunnable(SynchronizedQueue aQueue, int aCount)
   {
     queue = aQueue;
     max = aCount;
   }

	/**
	 * Goes through the specified number of iterations, removing strings from the queue.
	 * Displays the message "Removing: " and the string. This method also uses the interrupted() 
	 * method to make sure that this thread has not been interrupted.  It has no "clean up" to do, 
	 * but will cease performing iterations if it has been interrupted.  No delay is necessary.
	 */ 
   public void run()
   {
      try
   	  {
	   	  for(int i = 1; i <= max && !Thread.interrupted(); i++)
	      {
	      	String temp = queue.remove();
	      	System.out.println ("Removing: " + temp);
	      	
	      }
   	  }
   	  catch(InterruptedException e){
   	  }
   }

}
