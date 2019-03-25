import java.net.*;
import java.io.*;

public class IPFinder 
{
	public static void main(String[] args) throws IOException 
	{
		String host;
		BufferedReader input =
		  new BufferedReader(
			new InputStreamReader(System.in));

		System.out.print("\n\nEnter host name: ");
		host = input.readLine();
		try 
		{
			InetAddress address = InetAddress.getByName(host);
		System.out.println("IP address: " + address.toString());
		}
     	catch (UnknownHostException e)
     	{
			System.out.println("Could not find " + host);
		}
	}
}

