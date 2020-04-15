package echo.client;

import java.io.IOException;

import static util.Constants.*;

public class ClientRunner {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.startConnection(HOST, PORT);
        System.out.println(client.sendMessage("ECHO ME"));
        System.out.println(client.sendMessage("ECHO ME 2"));
        System.out.println(client.sendMessage("."));
    }
}
