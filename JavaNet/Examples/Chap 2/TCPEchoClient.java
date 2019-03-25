import java.io.*;
import java.net.*;

public class TCPEchoClient
{
	private static InetAddress host;
	private static final int PORT = 1234;

	public static void main(String[] args)
	{
		try
		{
			host = InetAddress.getLocalHost();
		}
		catch(UnknownHostException e)
		{
			System.out.println("Host ID not found!");
			System.exit(1);
		}
		run();
	}

	private static void run()
	{
		Socket link = null;				//Step 1.

		try
		{
			link = new Socket(host,PORT);		//Step 1.

			BufferedReader in = 
				new BufferedReader
					(new InputStreamReader
					   	(link.getInputStream()));//Step 2.

			PrintWriter out = new PrintWriter(
					link.getOutputStream(),true);	 //Step 2.

			//Set up stream for keyboard entry...
			BufferedReader userEntry =
					new BufferedReader
				 		(new InputStreamReader(System.in));

			String message, response;
			do
			{
				System.out.print("Enter message: ");
				message =  userEntry.readLine();
				out.println(message); 		//Step 3.
				response = in.readLine();		//Step 3.
				System.out.println("\nSERVER> " + response);
			}while (!message.equals("***CLOSE***"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		finally
		{
			try
			{
				System.out.println(
							"\n* Closing connection... *");
				link.close();				//Step 4.
			}
			catch(IOException e)
			{
				System.out.println("Unable to disconnect!");
				System.exit(1);
			}
		}
	}
}
