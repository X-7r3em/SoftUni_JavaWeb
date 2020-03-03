package utilities;

import java.io.FileWriter;
import java.io.IOException;

public class LoggingUtilImpl implements LoggingUtil {
    private static final String PATH = "D:\\SoftUni\\07. Java Web\\01. Java Web Basics\\07. Java EE Java Server Pages\\Projects\\JavaServerPagesExercise\\src\\main\\webapp\\logs.txt";

    @Override
    public void log(String log) throws IOException {
        FileWriter fileWriter = new FileWriter(PATH, true);
        fileWriter.append(log).append(System.lineSeparator());
        fileWriter.close();
    }
}
