package manager;

import exceptions.CourseFullException;
import exceptions.StudentAlreadyEnrolledException;
import model.Course;
import model.Student;
import java.util.ArrayList;
import java.util.List;

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


}