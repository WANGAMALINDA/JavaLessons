import java.io.*;
import java.net.*;

class Client2 {

    public static void main(String[] args) throws IOException{

        Socket socket;
        BufferedReader in;
        BufferedReader key;
        PrintWriter out;
        String request;
        
        socket = new Socket("localhost", 8080);
        
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        key = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Send a messsage to the server: ");
        
        request = key.readLine();

        out.println(request);

        out.flush();

        System.out.println("Server respoense: \n"+ in.readLine());

        socket.close();
        in.close();
        key.close();
        out.close();
        
    }
}
