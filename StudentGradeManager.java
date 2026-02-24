import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentGradeManager {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentManager manager = new StudentManager();

    public static void main(String[] args) {

        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt();

            try {
                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> addGrade();
                    case 3 -> showStudents();
                    case 4 -> {
                        System.out.println("Exiting system...");
                        running = false;
                    }
                    default -> System.out.println("Invalid option.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error occurred.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=== Student Grade Manager ===");
        System.out.println("1. Add Student");
        System.out.println("2. Add Grade");
        System.out.println("3. Show Students");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student ID (format S1234): ");
        String id = scanner.nextLine();

        manager.addStudent(name, id);

        System.out.println("Student successfully added.");
    }

    private static void addGrade() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter grade (0–100): ");
        int grade = readInt();

        manager.addGrade(id, grade);

        System.out.println("Grade successfully added.");
    }

    private static void showStudents() {
        if (manager.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        for (Student student : manager.getAllStudents()) {
            System.out.println(student);
            System.out.println("--------------------------------");
        }
    }

    private static int readInt() {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Try again: ");
            }
        }
    }
}
