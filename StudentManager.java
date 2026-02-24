import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StudentManager {

    private final Map<String, Student> students = new HashMap<>();

    public void addStudent(String name, String id) {
        if (students.containsKey(id)) {
            throw new IllegalArgumentException("Student ID already exists.");
        }

        Student student = new Student(name, id);
        students.put(id, student);
    }

    public void addGrade(String id, int grade) {
        Student student = students.get(id);

        if (student == null) {
            throw new IllegalArgumentException("Student not found.");
        }

        student.addGrade(grade);
    }

    public Collection<Student> getAllStudents() {
        return students.values();
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }
}
