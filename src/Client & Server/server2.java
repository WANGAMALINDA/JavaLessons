import java.io.*;
import java.net.*;
import java.util.*;

class server2 {
    public static void main(String[] args) throws IOException{
        
        ServerSocket server;
        Socket socket;
        PrintWriter send;
        BufferedReader receive;
        String msg;

        server = new ServerSocket( 8080);

        System.out.println("Server waiting...");

        for(;;){

            socket = server.accept();
            receive = new BufferedReader(new InputStreamReader(socket.getInputStream()));                   
            send = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            msg = receive.readLine();

            send.println("Your message has been received -> " + msg);
            send.flush();

            socket.close();
            receive.close();
            send.close();
        }


    }
}
