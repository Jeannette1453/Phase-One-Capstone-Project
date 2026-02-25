package model;
import java.util.HashMap;
import java.util.Map;
public abstract class Student extends Person {

    private String studentId;
    private double gpa;
    private String department;
    private Map<Course, Double> courses = new HashMap<>();


    public Student(String name, String email, String studentId, double gpa, String department) {
        super(name, email);
        this.studentId = studentId;
        this.gpa = gpa;
        this.department = department;
    }

    public String getStudentId() {
        return studentId;
    }
    public double getGpa() {
        return gpa;
    }
    public String getDepartment() {
        return department;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public void enrollInCourse(Course course) {
        courses.put(course, null);
    }

    public void addGrade(Course course, double grade) {
        if (courses.containsKey(course)) {
            courses.put(course, grade);
        } else {
            System.out.println("Student not enrolled in this course");
        }
    }

    public Map<Course, Double> getCourses() {
        return courses;
    }
    public int getTotalCredits() {
        int total = 0;
        for (Course c : courses.keySet()) {
            total += c.getCredits();
        }
        return total;
    }
    public String gradeToLetter(Double grade) {
        if (grade == null) return "Not graded";
        if (grade >= 80) return "A";
        if (grade >= 70) return "B";
        if (grade >= 60) return "C";
        if (grade >= 50) return "D";
        return "F";
    }

    public abstract double calculateTuition(int credits);

    @Override
    public String getRole() {
        return "Student";
    }

}