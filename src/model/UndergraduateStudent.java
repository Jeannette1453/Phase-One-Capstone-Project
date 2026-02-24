package model;

public class UndergraduateStudent extends Student {

    private double flatRate;

    public UndergraduateStudent(String name, String email, String studentId, double gpa, String department, double flatRate) {
        super(name, email, studentId, gpa, department);
        this.flatRate = flatRate;
    }

    @Override
    public double calculateTuition(int credits) {
        return flatRate;
    }

    @Override
    public String getRole() {
        return "Undergraduate Student";
    }
}