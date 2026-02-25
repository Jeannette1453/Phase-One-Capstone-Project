package model;

public class GraduateStudent extends Student {

    private double perCreditRate;
    private double researchFee;

    public GraduateStudent(String name, String email, String studentId, double gpa, String department,
                           double perCreditRate, double researchFee) {
        super(name, email, studentId, gpa, department);
        this.perCreditRate = perCreditRate;
        this.researchFee = researchFee;
    }

    public double getPerCreditRate() {
        return perCreditRate;
    }

    public double getResearchFee() {
        return researchFee;
    }

    @Override
    public double calculateTuition(int credits) {
        return (credits * perCreditRate) + researchFee;
    }

    @Override
    public String getRole() {
        return "Graduate Student";
    }
}