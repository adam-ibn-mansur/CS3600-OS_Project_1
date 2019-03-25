import java.net.*;
import java.io.*;
import java.util.Date;

public class DaytimeServer
{
   public static void main(String[] args)
   {
      final int DAYTIME_PORT = 13;
      ServerSocket server;
      Socket socket;

      try
      {
         server = new ServerSocket(DAYTIME_PORT);

         do
         {
            socket = server.accept();
            PrintWriter out =
                new PrintWriter(socket.getOutputStream(),
								true);
            Date date = new Date();
            out.println(date);   //Method toString executed.
            socket.close();
         }while (true);
      }
      catch (IOException e)
      {
         System.out.println(e);
      }
   }
}
