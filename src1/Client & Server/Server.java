import java.io.IOException;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);

        System.out.println("Hello, server is running!");

        socket.close();
    }
}
