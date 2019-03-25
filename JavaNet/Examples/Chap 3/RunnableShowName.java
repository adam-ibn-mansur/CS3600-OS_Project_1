public class RunnableShowName implements Runnable
{
   	public static void main(String[] args)
   	{
    	Thread thread1 = new Thread(new RunnableShowName());
    	Thread thread2 = new Thread(new RunnableShowName());
	/*
		As an alternative to the above 2 lines, the following
		(more long-winded) code could have been used:

      	RunnableShowName runnable1 = new RunnableShowName();
      	RunnableShowName runnable2 = new RunnableShowName();

      	Thread thread1 = new Thread(runnable1);
      	Thread thread2 = new Thread(runnable2);
	*/
      	thread1.start();
      	thread2.start();
   	}

   	public void run()
  	{
      	int  pause;
      	for (int i=0; i<10; i++)
      	{
         	try
         	{
            	//Use static method currentThread to get
            	//reference to current thread and then call
            	//method getName on that reference...
            	System.out.println(
              		 Thread.currentThread().getName()
                        	+ " being executed.");
           		pause = (int)(Math.random() * 3000);

            	//Call static method sleep...
            	Thread.sleep(pause);
         	}
         	catch (InterruptedException e)
         	{
            	System.out.println(e);
         	}
      	}
   	}
}
