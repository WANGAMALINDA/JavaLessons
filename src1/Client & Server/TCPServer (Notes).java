import java.io.*;
import java.net.*;
import java.util.*;

class TCPServer {
    public static void main(String args[]) throws IOException {

        // --- Declare the objects we'll need ---
        ServerSocket server;   // listens for incoming client connections on a port
        Socket recSocket;      // represents the connection to ONE specific client
        PrintWriter out;       // used to send text lines to the client
        BufferedReader in;     // used to read text lines from the client
        String msg;            // holds the message text as it's processed

        // 1. Open a server socket bound to port 8001 and start listening.
        //    Nothing has connected yet — this just reserves the port.
        server = new ServerSocket(8001);

        System.out.println("Server started. Waiting for clients on port 8001...");

        // 2. Loop forever so the server can serve many clients, one after another.
        for (;;) {

            // 3. Block (wait) here until a client actually connects.
            //    Once one does, accept() returns a NEW Socket representing
            //    just that client's connection.
            recSocket = server.accept();

            // 4. Build a reader over the incoming byte stream from this client.
            //    getInputStream()  -> raw bytes coming FROM the client
            //    InputStreamReader -> converts bytes into characters
            //    BufferedReader    -> lets us call readLine() conveniently
            in = new BufferedReader(
                    new InputStreamReader(recSocket.getInputStream()));

            // 5. Build a writer over the outgoing byte stream to this client.
            //    getOutputStream()  -> raw bytes going TO the client
            //    OutputStreamWriter -> converts characters into bytes
            //    PrintWriter        -> lets us call println() conveniently
            out = new PrintWriter(
                    new OutputStreamWriter(recSocket.getOutputStream()));

            // 6. Read one line of text sent by the client, e.g. "Hello, Server!"
            msg = in.readLine();

            // 7. Very simple "protocol": decide how to respond based on the message.
            if (msg.equals("Date")) {
                // Client asked for the date -> build a reply with today's date.
                msg = "Client asked for " + msg + " and the current date is "
                        + (new Date()).toString();
            } else if (msg.equals("Quit")) {
                // Client asked to quit -> shut the whole server process down.
                System.exit(1);
            } else {
                // Anything else -> treat it as a normal chat message and echo it back.
                // (This is the line the coding activity actually needs.)
                msg = "Message received: " + msg;
            }

            // 8. Send the reply back to the client as a line of text.
            out.println(msg);

            // 9. Force any buffered output to actually go out over the network now
            //    (PrintWriter buffers by default, so without flush() the client
            //    might not receive anything yet).
            out.flush();

            // 10. Clean up everything related to THIS client's connection.
            in.close();
            out.close();
            recSocket.close();

            // The loop then goes back to step 3 and waits for the next client.
            // Note: we never close "server" itself, so it keeps listening.
        }
    }
}
