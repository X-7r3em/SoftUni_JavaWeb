package app.util;

import java.io.*;

public class FileUtilImpl implements FileUtil {
    public String readFile(String path) throws IOException {
        BufferedReader bfr = new BufferedReader(new FileReader(path));
        StringBuilder output = new StringBuilder();
        String line = bfr.readLine();
        while (line != null) {
            output.append(line).append(System.lineSeparator());

            line = bfr.readLine();
        }

        return output.toString().trim();
    }
}
