package http;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

public class ClientHandler extends Thread {
    Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            HttpRequestHandler requestHandler = new HttpRequestHandler();
            Map<String, String> params = requestHandler.handle(socket);
            HttpResponseHandler responseHandler = new HttpResponseHandler();
            responseHandler.handle(socket, params);
            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
