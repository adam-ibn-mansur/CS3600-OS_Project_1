import java.io.*;
import java.net.*;

public class MultiEchoServer
{
	private static ServerSocket servSocket;
	private static final int PORT = 1234;

	public static void main(String[] args) throws IOException
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

		do
		{
			//Wait for client...
			Socket client = servSocket.accept();

			System.out.println("\nNew client accepted.\n");

			//Create a thread to handle communication with
			//this client and pass the constructor for this
			//thread a reference to the relevant socket...
			ClientHandler handler = new ClientHandler(client);

			handler.start();//As usual, this method calls run.
		}while (true);
	}
}

class ClientHandler extends Thread
{
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;

	public ClientHandler(Socket socket)
	{
		//Set up reference to associated socket...
		client = socket;

		try
		{
			in = new BufferedReader(
					new InputStreamReader(
							client.getInputStream()));
			out = new PrintWriter(
						client.getOutputStream(),true);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void run()
	{
		try
		{
			String received;
			do
			{
				//Accept message from client on
				//the socket's input stream...
				received = in.readLine();

				//Echo message back to client on
				//the socket's output stream...
				out.println("ECHO: " + received);

			//Repeat above until 'QUIT' sent by client...
			}while (!received.equals("QUIT"));
		}

		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (client!=null)
				{
					System.out.println(
								"Closing down connection...");
					client.close();
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
