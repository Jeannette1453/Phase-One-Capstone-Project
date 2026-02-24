import model.Student;
import model.GraduateStudent;
import model.UndergraduateStudent;


import java.util.Scanner;
public class Main {

    public static void main(String[] args) {

                Scanner sc = new Scanner(System.in);

                System.out.println("1) Undergraduate");
                System.out.println("2) Graduate");
                System.out.print("Choose student type: ");
                int type = sc.nextInt();
                sc.nextLine();

                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Email: ");
                String email = sc.nextLine();

                System.out.print("Student ID: ");
                String studentId = sc.nextLine();

                System.out.print("GPA: ");
                double gpa = sc.nextDouble();
                sc.nextLine();

                System.out.print("Department: ");
                String dept = sc.nextLine();

                System.out.print("Credits: ");
                int credits = sc.nextInt();
                sc.nextLine();

                Student s;

                if (type == 1) {
                    System.out.print("Flat rate: ");
                    double flat = sc.nextDouble();
                    s = new UndergraduateStudent(name, email, studentId, gpa, dept, flat);
                } else {
                    System.out.print("Per credit rate: ");
                    double rate = sc.nextDouble();

                    System.out.print("Research fee: ");
                    double fee = sc.nextDouble();

                    s = new GraduateStudent(name, email, studentId, gpa, dept, rate, fee);
                }

                System.out.println("\nRole: " + s.getRole());
                System.out.println("Tuition = " + s.calculateTuition(credits));

                sc.close();
            }
        }

