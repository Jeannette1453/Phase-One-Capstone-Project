package model;

public abstract class Student extends Person {

    private String studentId;
    private double gpa;
    private String department;

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

    public abstract double calculateTuition(int credits);

    @Override
    public String getRole() {
        return "Student";
    }
}