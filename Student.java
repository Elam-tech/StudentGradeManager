import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student {

    private final String name;
    private final String id;
    private final List<Integer> grades;

    public Student(String name, String id) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        if (!id.matches("S\\d{4}")) {
            throw new IllegalArgumentException("ID must be in format S1234.");
        }

        this.name = name.trim();
        this.id = id.trim();
        this.grades = new ArrayList<>();
    }

    public void addGrade(int grade) {
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("Grade must be between 0 and 100.");
        }
        grades.add(grade);
    }

    public double getAverage() {
        if (grades.isEmpty()) return 0.0;

        int sum = 0;
        for (int g : grades) {
            sum += g;
        }
        return (double) sum / grades.size();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    // Defensive copy (no external mutation allowed)
    public List<Integer> getGrades() {
        return Collections.unmodifiableList(grades);
    }

    @Override
    public String toString() {
        return String.format("%s (%s) | Grades: %s | Avg: %.2f",
                name, id, grades, getAverage());
    }
}
