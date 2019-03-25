import java.io.*;
import java.net.*;

class ClientThread extends Thread
{
   private Socket client;
   private Resource item;
   private BufferedReader in;
   private PrintWriter out;

   public ClientThread(Socket socket, Resource resource)
   {
      client = socket;
      item = resource;

      try
      {
         //Create input and output streams on the socket...
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
         String request = "";
         do
         {
            request = in.readLine();
            if (request.equals("1"))
            {
               item.takeOne();//If none available,
                              //wait until resource(s)
                              //available (and thread is 
                              //at front of thread queue).
               out.println("Request granted.");
            }
         }while (!request.equals("0"));
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
                        "Closing down connection...");
            client.close();
         }
         catch(IOException ioEx)
         {
            ioEx.printStackTrace();
         }
      }
   }
}
