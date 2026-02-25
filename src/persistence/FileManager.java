package persistence;

import model.Course;
import model.Student;
import model.UndergraduateStudent;
import model.GraduateStudent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileManager {

    public static final String STUDENTS_FILE = "data/students.csv";
    public static final String COURSES_FILE = "data/courses.csv";
    public static final String ENROLLMENTS_FILE = "data/enrollments.csv";

    //  Create folder "data" if it doesn't exist
    private static void ensureDataFolderExists() throws IOException {
        Path dataDir = Paths.get("data");
        if (!Files.exists(dataDir)) {
            Files.createDirectories(dataDir);
        }
    }


    public static void saveStudents(List<Student> students) throws IOException {
        ensureDataFolderExists();

        BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENTS_FILE));

        writer.write("studentId,name,email,gpa,department,type,flatRate,perCreditRate,researchFee");
        writer.newLine();

        for (Student s : students) {
            String type = "";
            double flatRate = 0;
            double perCreditRate = 0;
            double researchFee = 0;

            if (s instanceof UndergraduateStudent) {
                type = "UNDERGRAD";
                flatRate = ((UndergraduateStudent) s).getFlatRate(); // needs getter
            } else if (s instanceof GraduateStudent) {
                type = "GRAD";
                perCreditRate = ((GraduateStudent) s).getPerCreditRate(); // needs getter
                researchFee = ((GraduateStudent) s).getResearchFee();     // needs getter
            } else {
                type = "STUDENT";
            }

            writer.write(
                    s.getStudentId() + "," +
                            s.getName() + "," +
                            s.getEmail() + "," +
                            s.getGpa() + "," +
                            s.getDepartment() + "," +
                            type + "," +
                            flatRate + "," +
                            perCreditRate + "," +
                            researchFee
            );
            writer.newLine();
        }

        writer.close();
    }
    //  SAVE COURSES
    public static void saveCourses(List<Course> courses) throws IOException {
        ensureDataFolderExists();

        BufferedWriter writer = new BufferedWriter(new FileWriter(COURSES_FILE));
        writer.write("courseCode,courseName,credits,maxStudents");
        writer.newLine();

        for (Course c : courses) {
            writer.write(
                    c.getCourseCode() + "," +
                            c.getCourseName() + "," +
                            c.getCredits() + "," +
                            c.getMaxStudents()
            );
            writer.newLine();
        }
        writer.close();
    }


    public static void saveEnrollments(List<Student> students) throws IOException {
        ensureDataFolderExists();

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

        String line = reader.readLine();
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) continue;

            String[] parts = line.split(",");

            String studentId = parts[0];
            String name = parts[1];
            String email = parts[2];
            double gpa = Double.parseDouble(parts[3]);
            String dept = parts[4];

            String type = parts.length > 5 ? parts[5] : "UNDERGRAD";

            double flatRate = parts.length > 6 ? Double.parseDouble(parts[6]) : 0;
            double perCreditRate = parts.length > 7 ? Double.parseDouble(parts[7]) : 0;
            double researchFee = parts.length > 8 ? Double.parseDouble(parts[8]) : 0;

            Student s;

            if (type.equalsIgnoreCase("GRAD")) {
                s = new GraduateStudent(name, email, studentId, gpa, dept, perCreditRate, researchFee);
            } else {
                s = new UndergraduateStudent(name, email, studentId, gpa, dept, flatRate);
            }

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