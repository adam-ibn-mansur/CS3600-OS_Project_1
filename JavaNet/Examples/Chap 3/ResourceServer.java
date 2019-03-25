import java.io.*;
import java.net.*;

public class ResourceServer
{
	private static ServerSocket servSocket;
	private static final int PORT = 1234;

	public static void main(String[] args)	throws IOException
	{
		try
		{
			servSocket = new ServerSocket(PORT);
		}
		catch (IOException e)
		{
			System.out.println("\nUnable to set up port!");
			System.exit(1);
		}

		//Create a Resource object with 
		//a starting resource level of 1...
		Resource item = new Resource(1);	

		//Create a Producer thread, passing a reference 
		//to the Resource object as an argument to the 
		//thread constructor...		
		Producer producer = new Producer(item);

		//Start the Producer thread running...
		producer.start();
		do
		{
			//Wait for a client to make connection...
			Socket client = servSocket.accept(); 
			System.out.println("\nNew client accepted.\n");

			//Create a ClientHandler thread to handle all
			//subsequent dialogue with the client, passing 
			//references to both the client's socket and
			//the Resource object...
			ClientHandler handler = 
						new ClientHandler(client,item);

			//Start the ClientHandler thread running...
			handler.start();
		}while (true);		//Server will run indefinitely.
	}
}
