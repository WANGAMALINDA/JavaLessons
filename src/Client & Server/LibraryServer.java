import java.net.*;
import java.io.*;

public class LibraryServer {

    public static void main(String args[]) throws Exception{

        ServerSocket server = new ServerSocket(8001);

        System.out.println(
        "Server Started");

        Socket client =
        server.accept();

        BufferedReader input =
        new BufferedReader(
        new InputStreamReader(
        client.getInputStream()));

        String request = input.readLine();

        System.out.println(
        "Client Requested "
        + request);

       client.close();

        server.close();
    }
}
