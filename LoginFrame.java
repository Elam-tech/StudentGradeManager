import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private final AuthService authService = new AuthService();

    private final JTextField usernameField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();

    public LoginFrame() {
        setTitle("Login");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Username:"));
        add(usernameField);

        add(new JLabel("Password:"));
        add(passwordField);

        JButton loginButton = new JButton("Login");
        add(new JLabel());
        add(loginButton);

        loginButton.addActionListener(e -> attemptLogin());
    }

    private void attemptLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        User user = authService.login(username, password);

        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login successful!");

            this.dispose();

            StudentGradeManagerGUI app = new StudentGradeManagerGUI(user);
            app.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid credentials.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}