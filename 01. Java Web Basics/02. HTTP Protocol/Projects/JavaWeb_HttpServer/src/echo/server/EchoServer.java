package echo.server;

import java.io.*;
import java.net.*;

public class EchoServer {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public void startServer(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            new ClientHandler(serverSocket.accept()).start();
        }
    }
}
