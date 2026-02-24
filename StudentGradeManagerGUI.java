import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StudentGradeManagerGUI extends JFrame {

    private final StudentManager manager = new StudentManager();

    private final JTextField nameField = new JTextField();
    private final JTextField idField = new JTextField();
    private final JTextField gradeField = new JTextField();

    private final JTextArea outputArea = new JTextArea();

    private final User currentUser;

    public StudentGradeManagerGUI(User user) {
         this.currentUser = user;
        setTitle("Student Grade Manager");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

JLabel userLabel = new JLabel("Logged in as: " + currentUser.getUsername());
    add(userLabel, BorderLayout.WEST);


        // Top Panel (Input Section)
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Student Information"));

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Student ID (S1234):"));
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Grade (0-100):"));
        inputPanel.add(gradeField);

        JButton addStudentBtn = new JButton("Add Student");
        JButton addGradeBtn = new JButton("Add Grade");

        inputPanel.add(addStudentBtn);
        inputPanel.add(addGradeBtn);

        add(inputPanel, BorderLayout.NORTH);

        // Center Panel (Output)
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Student Records"));

        add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel (Actions)
        JPanel bottomPanel = new JPanel();

        JButton showBtn = new JButton("Show All Students");
        JButton clearBtn = new JButton("Clear Output");

        bottomPanel.add(showBtn);
        bottomPanel.add(clearBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        // Button Actions
        addStudentBtn.addActionListener(this::handleAddStudent);
        addGradeBtn.addActionListener(this::handleAddGrade);
        showBtn.addActionListener(e -> displayStudents());
        clearBtn.addActionListener(e -> outputArea.setText(""));
    }


    private void handleAddStudent(ActionEvent e) {
        try {
            String name = nameField.getText();
            String id = idField.getText();

            manager.addStudent(name, id);

            JOptionPane.showMessageDialog(this, "Student added successfully.");
            nameField.setText("");
            idField.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleAddGrade(ActionEvent e) {
        try {
            String id = idField.getText();
            int grade = Integer.parseInt(gradeField.getText());

            manager.addGrade(id, grade);

            JOptionPane.showMessageDialog(this, "Grade added successfully.");
            gradeField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Grade must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayStudents() {
        outputArea.setText("");

        if (manager.isEmpty()) {
            outputArea.append("No students available.\n");
            return;
        }

        for (Student s : manager.getAllStudents()) {
            outputArea.append(s.toString() + "\n");
            outputArea.append("------------------------------------------------\n");
        }
    }

    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        User user = new User("Admin", "1234", "ADMIN");  // or whatever constructor you have
        new StudentGradeManagerGUI(user).setVisible(true);
    });
}
}