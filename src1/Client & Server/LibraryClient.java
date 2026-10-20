import java.net.*;
import java.io.*;

public class LibraryClient {

    public static void main(String args[])  throws Exception{

        Socket socket = new Socket("localhost", 8001);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        String bookCode;
        System.out.print("Enter Book Code: ");

        DataInputStream dis = new DataInputStream(System.in);

        bookCode = dis.readLine();

        output.println(bookCode);

        String response = input.readLine();

        System.out.println("Server Response: "+ response);

        socket.close();

    }
}
