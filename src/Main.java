import model.Instructor;
import model.Student;

public class Main {

    public static void main(String[] args) {

        Student s1 = new Student("Jeannette", "janet@gmail.com", "ST001", 3.6, "Software Engineering");
        Instructor i1 = new Instructor("Mr. Paul", "paul@gmail.com", "Software Engineering");

        System.out.println(s1.getName() + " - " + s1.getRole() + " - GPA: " + s1.getGpa());
        System.out.println(i1.getName() + " - " + i1.getRole() + " - Dept: " + i1.getDepartment());
    }
}