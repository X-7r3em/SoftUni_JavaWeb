package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpRequestHandler {
    private String method;
    private String path;
    private String protocol;
    private Map<String, String> headers;
    private Map<String, String> parameters;

    public HttpRequestHandler() {
        this.headers = new HashMap<>();
        this.parameters = new HashMap<>();
    }

    public Map<String, String> handle(Socket socket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = in.readLine();
        String[] responseTokens = line.split(" ");
        method = responseTokens[0];
        path = responseTokens[1];
        protocol = responseTokens[2];
        while (!(line = in.readLine()).isEmpty()) {
            String[] tokens = line.split(": ");
            headers.put(tokens[0].toUpperCase(), tokens[1]);
        }

        int contentLength = headers.get("CONTENT-LENGTH") != null
                ? Integer.parseInt(headers.get("CONTENT-LENGTH"))
                : 0;
        char[] words = new char[contentLength];
        String paramStr;
        if (contentLength > 0) {
            in.read(words, 0, contentLength);
            paramStr = URLDecoder.decode(new String(words), StandardCharsets.UTF_8);
            parameters = convertParamStr(paramStr);
        }

        return Collections.unmodifiableMap(parameters);
    }

    private Map<String, String> convertParamStr(String paramStr) {
        return Arrays.stream(paramStr.split("&"))
                .map(p -> p.split("="))
                .collect(Collectors.toMap(e -> e[0], e -> e[1]));
    }
}
