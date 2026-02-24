package manager;
import exceptions.CourseFullException;
import exceptions.StudentAlreadyEnrolledException;
import model.Course;
import model.Student;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import persistence.FileManager;

public class UniversityManager {

    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();

    public void registerStudent(Student student) {
        students.add(student);
    }

    public void createCourse(Course course) {
        courses.add(course);
    }

    public void enrollStudentInCourse(Student student, Course course)
            throws CourseFullException, StudentAlreadyEnrolledException {
        course.enrollStudent(student);
    }

    public double averageGpaByDepartment(String department) {
        double total = 0;
        int count = 0;

        for (Student s : students) {
            if (s.getDepartment().equalsIgnoreCase(department)) {
                total += s.getGpa();
                count++;
            }
        }

        if (count == 0) {
            return 0;
        }

        return total / count;
    }

    public Student getTopStudent() {
        if (students.isEmpty()) {
            return null;
        }

        Student top = students.get(0);

        for (Student s : students) {
            if (s.getGpa() > top.getGpa()) {
                top = s;
            }
        }

        return top;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Student findStudentById(String studentId) {
        for (Student s : students) {
            if (s.getStudentId().equals(studentId)) {
                return s;
            }
        }
        return null;
    }

    public Course findCourseByCode(String courseCode) {
        for (Course c : courses) {
            if (c.getCourseCode().equalsIgnoreCase(courseCode)) {
                return c;
            }
        }
        return null;
    }

    public void printStudentRecord(Student student) {
        System.out.println("\n--- Student Record ---");
        System.out.println("Name: " + student.getName());
        System.out.println("Department: " + student.getDepartment());
        System.out.println("GPA: " + student.getGpa());

        if (student.getCourses().isEmpty()) {
            System.out.println("No enrolled courses.");
        } else {
            System.out.println("Courses:");
            for (Course c : student.getCourses().keySet()) {
                Double grade = student.getCourses().get(c);
                String gradeText = (grade == null) ? "Not graded" : grade.toString();
                System.out.println("- " + c.getCourseCode() + " (" + gradeText + ")");
            }
        }
    }

    public void loadEnrollments() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FileManager.ENROLLMENTS_FILE));

        String line = reader.readLine();
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) continue;

            String[] parts = line.split(",");
            String studentId = parts[0];
            String courseCode = parts[1];
            String gradeText = (parts.length >= 3) ? parts[2] : "";

            Student s = findStudentById(studentId);
            Course c = findCourseByCode(courseCode);

            if (s != null && c != null) {
                try {
                    enrollStudentInCourse(s, c);
                } catch (Exception ignored) {

                }

                if (!gradeText.isEmpty()) {
                    double grade = Double.parseDouble(gradeText);
                    s.addGrade(c, grade);
                }
            }
        }

        reader.close();
    }

    public void printDeansList() {
        System.out.println("\n--- Dean's List (GPA > 3.5) ---");

        boolean found = false;

        for (Student s : students) {
            if (s.getGpa() > 3.5) {
                System.out.println(s.getName() + " - GPA: " + s.getGpa());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No students qualified.");
        }
    }


}