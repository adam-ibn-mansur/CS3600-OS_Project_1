import java.util.Scanner;
import java.net.Socket;
import java.io.IOException;

/**
    CMD line client for the date server. Requires the IP Address of the server as
    the sole argument. Exits after printing the response
 */

public class DateClient {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Pass the server IP as the sole command line argument");
            return;
        }
        var socket = new Socket(args[0], 59090);
        var in = new Scanner(socket.getInputStream());
        System.out.println("Server response: " + in.nextLine());
    }
}