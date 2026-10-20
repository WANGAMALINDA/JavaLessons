import java.io.*;
import java.net.*;

class TCPClient {
    public static void main(String args[]) throws IOException {

        // --- Declare the objects we'll need ---
        Socket socket;              // the connection to the server
        BufferedReader in;          // reads text lines coming back from the server
        PrintWriter out;            // sends text lines to the server
        BufferedReader keyboard;    // reads what the user types at the console
        String request;             // holds the message the user wants to send

        // 1. Create a socket and actively CONNECT to the server.
        //    "localhost" means "the server running on this same machine";
        //    replace with the server's real hostname/IP if it's on another machine.
        //    8001 must match the port the server is listening on.
        socket = new Socket("localhost", 8001);

        // 2. Build a writer over the socket's outgoing byte stream, so we can
        //    send text lines to the server (mirrors the server's "out").
        out = new PrintWriter(
                new OutputStreamWriter(socket.getOutputStream()));

        // 3. Build a reader over the socket's incoming byte stream, so we can
        //    read text lines the server sends back (mirrors the server's "in").
        in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        // 4. Wrap standard input (the keyboard) so we can read what the
        //    user types into the console.
        keyboard = new BufferedReader(new InputStreamReader(System.in));

        // 5. Prompt the user and read their message.
        System.out.print("Enter a message to send to the server: ");
        request = keyboard.readLine();   // e.g. "Hello, Server!"

        // 6. Send that message to the server over the socket.
        out.println(request);

        // 7. Force the buffered text to actually be sent over the network now.
        out.flush();

        // 8. Block (wait) here until the server sends its reply line back,
        //    then print it — this is where you'd see
        //    "Message received: Hello, Server!"
        System.out.println("Server responds: " + in.readLine());

        // 9. Clean up: close the streams and the connection.
        in.close();
        out.close();
        keyboard.close();
        socket.close();
    }
}
