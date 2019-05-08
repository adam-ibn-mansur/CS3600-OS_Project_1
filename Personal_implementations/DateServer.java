import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
    Basic TCP server. When a client connets, it sends the client the current datetime, 
    then closes the connection. This is essentially the most barebones server a person 
    could write. You could train an animal to do this, but it'd be cheaper and less 
    time-consuming for humans, I guess. Do keep into consideration that a client has 
    to be completely served its date before the server will be able to handle another client.
 */

public class DateServer {
    public static void main(String[] args) throws IOException {
        try (var listener = new ServerSocket(59090)) {
            System.out.println("The date server is running...");
            while (true) {
                try (var socket = listener.accept()) {
                    var out = new PrintWriter(socket.getOutputStream(), true);
                    out.println(new Date().toString());
                }
            }
        }
    }
}