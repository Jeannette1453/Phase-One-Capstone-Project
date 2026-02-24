import manager.UniversityManager;
import model.Student;
import model.UndergraduateStudent;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UniversityManager um = new UniversityManager();

        System.out.print("How many students do you want to register? ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= n; i++) {
            System.out.println("\n--- Student " + i + " ---");

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("Student ID: ");
            String id = sc.nextLine();

            System.out.print("GPA: ");
            double gpa = sc.nextDouble();
            sc.nextLine();

            System.out.print("Department: ");
            String dept = sc.nextLine();

            System.out.print("Flat rate: ");
            double flat = sc.nextDouble();
            sc.nextLine();

            Student s = new UndergraduateStudent(name, email, id, gpa, dept, flat);
            um.registerStudent(s);
        }

        System.out.print("\nEnter department to calculate average GPA: ");
        String deptSearch = sc.nextLine();

        double avg = um.averageGpaByDepartment(deptSearch);
        System.out.println("Average GPA in " + deptSearch + " = " + avg);

        Student top = um.getTopStudent();
        if (top != null) {
            System.out.println("Top Student: " + top.getName() + " (GPA: " + top.getGpa() + ")");
        } else {
            System.out.println("No students available.");
        }

        sc.close();
    }
}