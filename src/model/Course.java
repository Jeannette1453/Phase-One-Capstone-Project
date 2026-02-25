package model;
import java.util.ArrayList;
import java.util.List;
import exceptions.CourseFullException;
import exceptions.StudentAlreadyEnrolledException;

public class Course {

    private String courseCode;
    private String courseName;
    private int credits;
    private int maxStudents;

    private List<Student> roster = new ArrayList<>();

    public Course(String courseCode, String courseName, int credits, int maxStudents) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.maxStudents = maxStudents;
    }

    public String getCourseCode() {

        return courseCode;
    }

    public String getCourseName() {

        return courseName;
    }

    public int getCredits() {

        return credits;
    }

    public int getMaxStudents() {

        return maxStudents;
    }

    public List<Student> getRoster() {

        return roster;
    }
    public void enrollStudent(Student student)
            throws CourseFullException, StudentAlreadyEnrolledException {

        if (roster.size() >= maxStudents) {
            throw new CourseFullException("Course " + courseCode + " is full!");
        }

        if (roster.contains(student)) {
            throw new StudentAlreadyEnrolledException("Student already enrolled in " + courseCode);
        }

        roster.add(student);
        student.enrollInCourse(this);
    }

    @Override
    public String toString() {
        return courseCode + " - " + courseName + " (" + credits + " credits)";
    }
}