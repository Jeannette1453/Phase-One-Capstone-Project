package persistence;
import model.Course;
import model.Student;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

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

    public static ArrayList<Student> loadStudents() throws IOException {
        ArrayList<Student> students = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(STUDENTS_FILE));

        String line = reader.readLine(); // skip header
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) continue;

            String[] parts = line.split(",");
            String studentId = parts[0];
            String name = parts[1];
            String email = parts[2];
            double gpa = Double.parseDouble(parts[3]);
            String dept = parts[4];


            Student s = new model.UndergraduateStudent(name, email, studentId, gpa, dept, 0);
            students.add(s);
        }

        reader.close();
        return students;
    }

    public static ArrayList<Course> loadCourses() throws IOException {
        ArrayList<Course> courses = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(COURSES_FILE));

        String line = reader.readLine();
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) continue;

            String[] parts = line.split(",");
            String code = parts[0];
            String name = parts[1];
            int credits = Integer.parseInt(parts[2]);
            int max = Integer.parseInt(parts[3]);

            Course c = new Course(code, name, credits, max);
            courses.add(c);
        }

        reader.close();
        return courses;
    }
}