/*
 * Kazuki Shin
 * Gallatin
 * 4/3/17
 */


/**
 * Runner for the Synchronized Queue
 */
public class ProducerConsumerRunner
{
	/*
	 * @param args not used
	 */
   public static void main(String args[])
   {
      SynchronizedQueue q = new SynchronizedQueue(QUEUE_MAX_SIZE);
      ProducerRunnable producer = new ProducerRunnable(q, ITERATIONS);
      ConsumerRunnable consumer = new ConsumerRunnable(q, ITERATIONS);
      Thread t1 = new Thread(producer);
      Thread t2 = new Thread(consumer);
      t1.start();
      t2.start();
   }

   private static int QUEUE_MAX_SIZE = 10;
   private static int ITERATIONS = 100;
}
