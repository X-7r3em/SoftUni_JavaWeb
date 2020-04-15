package http;

import static util.Constants.*;

public class HttpServerRunner {
    public static void main(String[] args) {
        HttpServer server = new HttpServer(PORT);
        server.start();
    }
}
