import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class AuthService {

    private final Map<String, User> users = new HashMap<>();

    public AuthService() {
        // Default admin account (password: admin123)
        register("admin", "admin123", "ADMIN");
    }

    public void register(String username, String password, String role) {
        String hash = hashPassword(password);
        users.put(username, new User(username, hash, role));
    }

    public User login(String username, String password) {
        User user = users.get(username);
        if (user == null) return null;

        String hash = hashPassword(password);

        if (user.getPasswordHash().equals(hash)) {
            return user;
        }

        return null;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing error.");
        }
    }
}