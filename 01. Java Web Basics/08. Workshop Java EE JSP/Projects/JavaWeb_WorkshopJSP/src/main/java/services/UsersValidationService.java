package services;

public interface UsersValidationService {
    boolean validateUser(String username, String email, String password, String confirmPassword);
}
