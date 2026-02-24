package persistence;
import model.Course;
import model.Student;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
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
}