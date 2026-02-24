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

    public List<Student> getStudents() {
        return students;
    }

    public List<Course> getCourses() {
        return courses;
    }
}