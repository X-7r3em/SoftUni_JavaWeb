package echo.server;

import java.io.IOException;

import static util.Constants.*;

public class EchoServerRunner {
    public static void main(String[] args) throws IOException {
        EchoServer server = new EchoServer();
        server.startServer(PORT);
    }
}
