public class ThreadHelloCount
{
	public static void main(String[] args)
	{
		HelloThread hello = new HelloThread();
		CountThread count = new CountThread();

		hello.start();
		count.start();
	}
}

class HelloThread extends Thread
{
	public void run()
	{
		int  pause;

		for (int i=0; i<5; i++)
		{
			try
			{
				System.out.println("Hello!");
				pause = (int)(Math.random() * 3000);
				sleep(pause);
			}
			catch (InterruptedException e)
			{
				System.out.println(e.toString());
			}
		}
	}
}

class CountThread extends Thread
{
	int  pause;

	public void run()
	{
		for (int i=0; i<5; i++)
		{
			try
			{
				System.out.println(i+1);
				pause = (int)(Math.random() * 3000);
				sleep(pause);
			}
			catch (InterruptedException e)
			{
					System.out.println(e.toString());
			}
		}
	}
}
