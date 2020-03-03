package bg.softuni.heroes.errors;

public class NoSuchHeroException extends RuntimeException {
    public NoSuchHeroException(String message) {
        super(message);
    }
}
