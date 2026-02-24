import exceptions.CourseFullException;
import exceptions.StudentAlreadyEnrolledException;
import manager.UniversityManager;
import model.Course;
import model.Student;
import model.UndergraduateStudent;
import persistence.FileManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UniversityManager um = new UniversityManager();

        try {
            um.getStudents().addAll(FileManager.loadStudents());
            um.getCourses().addAll(FileManager.loadCourses());
            um.loadEnrollments();
            System.out.println(" Data loaded successfully.");
        } catch (Exception e) {
            System.out.println("No saved data yet (first run).");
        }
        while (true) {
            System.out.println("\n1) Register Student");
            System.out.println("2) Create Course");
            System.out.println("3) Enroll Student in Course");
            System.out.println("4) View Students");
            System.out.println("5) View Courses");
            System.out.println("6) View Student Record");
            System.out.println("7) Dean's List");
            System.out.println("8) Exit");

            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
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
                System.out.println("Student registered!");

            } else if (choice == 2) {
                System.out.print("Course Code: ");
                String code = sc.nextLine();

                System.out.print("Course Name: ");
                String cname = sc.nextLine();

                System.out.print("Credits: ");
                int credits = sc.nextInt();
                sc.nextLine();

                System.out.print("Max Students: ");
                int max = sc.nextInt();
                sc.nextLine();

                Course c = new Course(code, cname, credits, max);
                um.createCourse(c);
                System.out.println(" Course created!");

            } else if (choice == 3) {

                System.out.print("Enter Student ID: ");
                String studentId = sc.nextLine();

                System.out.print("Enter Course Code: ");
                String courseCode = sc.nextLine();

                Student s = um.findStudentById(studentId);
                Course c = um.findCourseByCode(courseCode);

                if (s == null) {
                    System.out.println("Student not found!");
                    continue;
                }
                if (c == null) {
                    System.out.println("Course not found!");
                    continue;
                }

                try {
                    um.enrollStudentInCourse(s, c);
                    System.out.println("Enrolled successfully!");
                } catch (CourseFullException | StudentAlreadyEnrolledException e) {
                    System.out.println("Enrollment failed: " + e.getMessage());
                }

            } else if (choice == 4) {
                if (um.getStudents().isEmpty()) {
                    System.out.println("No students yet.");
                } else {
                    for (Student s : um.getStudents()) {
                        System.out.println(s.getStudentId() + " - " + s.getName() + " - GPA: " + s.getGpa());
                    }
                }

            } else if (choice == 5) {
                if (um.getCourses().isEmpty()) {
                    System.out.println("No courses yet.");
                } else {
                    for (Course c : um.getCourses()) {
                        System.out.println(c.getCourseCode() + " - " + c.getCourseName() + " (Max: " + c.getMaxStudents() + ")");
                    }
                }

            } else if (choice == 6) {
                System.out.print("Enter Student ID: ");
                String id = sc.nextLine();

                Student s = um.findStudentById(id);

                if (s == null) {
                    System.out.println(" Student not found!");
                } else {
                    um.printStudentRecord(s);
                }

            } else if (choice == 7) {

                um.printDeansList();

            } else if (choice == 8) {

                try {
                    FileManager.saveStudents(um.getStudents());
                    FileManager.saveCourses(um.getCourses());
                    FileManager.saveEnrollments(um.getStudents());
                    System.out.println("Data saved. Bye!");
                } catch (Exception e) {
                    System.out.println(" Failed to save: " + e.getMessage());
                }
                break;

            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        sc.close();
    }
}