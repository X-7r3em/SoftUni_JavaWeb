package http;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static util.Constants.NEW_LINE;

public class HttpResponseHandler {
    public void handle(Socket socket, Map<String, String> params) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
        String paramStr = params.entrySet()
                .stream()
                .reduce(new StringBuilder(),
                        (acc, el) -> acc.append(el.getKey()).append(":").append(el.getValue()).append(System.lineSeparator()),
                        (sb1, sb2) -> sb1.append(sb2.toString())).toString();
        String body = "<html><title>Hello</title>" + NEW_LINE +
                "Welcome to our site" + NEW_LINE +
                "<form method=\"POST\">" + NEW_LINE +
                "<input type=\"text\" name=\"name\"/>" + NEW_LINE +
                "<input type=\"text\" name=\"age\"/>" + NEW_LINE +
                "<button>Submit</button>" + NEW_LINE +
                "</form>" + NEW_LINE +
                paramStr + NEW_LINE +
                "</html>" + NEW_LINE;
        String header = "HTTP/1.1 200 OK" + NEW_LINE +
                "Server: MyCustomServer/1.0" + NEW_LINE +
                "Content-Length: " + body.getBytes().length + NEW_LINE +
                "Content-Type: text/html" + NEW_LINE +
                "HTTP/1.0 200 OK" + NEW_LINE +
                NEW_LINE;

        String resp = header + body;

        out.write(resp);
        out.flush();
        out.close();
    }
}
