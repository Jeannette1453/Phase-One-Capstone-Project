package model;
import java.util.ArrayList;
import java.util.List;

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
    public void enrollStudent(Student student) {

        if (roster.size() >= maxStudents) {
            System.out.println("Course is full!");
            return;
        }

        if (roster.contains(student)) {
            System.out.println("Student already enrolled!");
            return;
        }

        roster.add(student);
        student.enrollInCourse(this);

        System.out.println("Student enrolled successfully.");
    }
    @Override
    public String toString() {
        return courseCode + " - " + courseName + " (" + credits + " credits)";
    }
}