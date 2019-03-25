import java.io.*;
import java.net.*;

public class MultiEchoClient
{
	private static InetAddress host;
	private static final int PORT = 1234;
	private static Socket link;
	private static BufferedReader in;
	private static PrintWriter out;
	private static BufferedReader keyboard;

	public static void main(String[] args)	throws IOException
	{
		try
		{
			host = InetAddress.getLocalHost();
			link = new Socket(host, PORT);
			in = new BufferedReader(
					new InputStreamReader(
								link.getInputStream()));
			out = new PrintWriter(
								link.getOutputStream(),true);

			keyboard = new BufferedReader(
							new InputStreamReader(System.in));

			String message, response;
			do
			{
				System.out.print(
						"Enter message ('QUIT' to exit): ");
				message = keyboard.readLine();


				//Send message to server on
				//the socket's output stream...
				out.println(message);

				//Accept response from server on
				//the socket's input stream...
				response = in.readLine();

				//Display server's response to user...
				System.out.println(response);
			}while (!message.equals("QUIT"));
		}
		catch(UnknownHostException uhEx)
		{
			System.out.println(
						"\nHost ID not found!\n");
		}
		catch(IOException ioEx)
		{
			ioEx.printStackTrace();
		}
		finally
		{
			try
			{
				if (link!=null)
				{
					System.out.println(
							"Closing down connection...");
					link.close();
				}
			}
			catch(IOException ioEx)
			{
				ioEx.printStackTrace();
			}
		}
	}
}
