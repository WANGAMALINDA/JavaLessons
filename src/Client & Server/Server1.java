import java.io.*;
import java.net.*;

public class Server1 {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(12345);
        System.out.println("Server waiting....");

        Socket clientSocket = server.accept();
        System.out.println("Server Connected....");

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        String massage = in.readLine();
        System.out.println("Received: " + massage);

        System.out.println("Massage received: " + massage);

        clientSocket.close();
        server.close();

    }
}