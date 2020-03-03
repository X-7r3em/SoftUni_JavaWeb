package bg.softuni.errorhandling.web.models;

public class RestException extends Throwable {
    public RestException(String message) {
        super(message);
    }
}
