package model;

public class Student extends Person {

    private String studentId;
    private double gpa;
    private String department;

    public Student(String fullName, String email, String studentId, double gpa, String department) {
        super(fullName, email);
        this.studentId = studentId;
        this.gpa = gpa;
        this.department = department;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String getRole() {
        return "STUDENT";
    }
}