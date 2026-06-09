import java.util.Scanner;
public class StudentGrade {
    public static void main(String[] arg) {
        Scanner input = new Scanner(System.in);

        // Student information
        System.out.print("Enter Student Name: ");
        String name = input.nextLine();

        System.out.print("Enter Student ID: ");
        String id = input.nextLine();

        System.out.print("Enter Coding Marks: ");
        double marks = input.nextDouble();

        // Grade information
        String grade;
        if (marks >=80) {
            grade = "PASS";
        } else {
            grade = "FAIL";
        }

        // Output
        System.out.println("\nHello" + name + " & " + id);
        System.out.println("Coding marks is: " + grade);

        input.close();

    }
}