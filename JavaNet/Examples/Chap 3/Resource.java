class Resource
{
	private int numResources;
	private final int MAX = 5;

	public Resource(int startLevel)
	{
		numResources = startLevel;
	}

	public int getLevel()
	{
		return numResources;
	}

	public synchronized int addOne()
	{
		try
		{
			while (numResources >= MAX)
				wait();
			numResources++;

			//'Wake up' any waiting consumer...
			notify();
		}
		catch (InterruptedException e)
		{
			System.out.println(e);
		}
		return numResources;
	}

	public synchronized int takeOne()
	{
		try
		{
			while (numResources == 0)
				wait();
			numResources--;

			//'Wake up' any waiting producer...
			notify();
		}
		catch (InterruptedException e)
		{
			System.out.println(e);
		}
		return numResources;
	}
}
