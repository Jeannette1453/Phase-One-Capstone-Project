package persistence;
import model.Course;
import model.Student;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FileManager {
    public static final String STUDENTS_FILE = "data/students.csv";
    public static final String COURSES_FILE = "data/courses.csv";

    public static void saveStudents(List<Student> students) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENTS_FILE));

        writer.write("studentId,name,email,gpa,department");
        writer.newLine();

        for (Student s : students) {
            writer.write(s.getStudentId() + "," + s.getName() + "," + s.getEmail() + "," + s.getGpa() + "," + s.getDepartment());
            writer.newLine();
        }

        writer.close();
    }

    public static void saveCourses(List<Course> courses) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(COURSES_FILE));

        writer.write("courseCode,courseName,credits,maxStudents");
        writer.newLine();

        for (Course c : courses) {
            writer.write(c.getCourseCode() + "," + c.getCourseName() + "," + c.getCredits() + "," + c.getMaxStudents());
            writer.newLine();
        }

        writer.close();
    }

    public static final String ENROLLMENTS_FILE = "data/enrollments.csv";

    public static void saveEnrollments(List<Student> students) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(ENROLLMENTS_FILE));

        writer.write("studentId,courseCode,grade");
        writer.newLine();

        for (Student s : students) {
            for (Map.Entry<Course, Double> entry : s.getCourses().entrySet()) {
                Course c = entry.getKey();
                Double grade = entry.getValue();
                String gradeText = (grade == null) ? "" : grade.toString();

                writer.write(s.getStudentId() + "," + c.getCourseCode() + "," + gradeText);
                writer.newLine();
            }
        }

        writer.close();
    }
}