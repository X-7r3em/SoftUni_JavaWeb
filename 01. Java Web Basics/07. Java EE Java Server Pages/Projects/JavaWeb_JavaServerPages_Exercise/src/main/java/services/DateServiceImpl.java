package services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateServiceImpl implements DateService {
    public String getTimeNow() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dateTimeFormatter.format(date);
    }
}
