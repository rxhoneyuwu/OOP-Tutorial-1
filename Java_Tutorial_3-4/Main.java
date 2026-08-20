package Java_Tutorial_3;

public class Main {
    public static void main(String[] args) {
        // Creating objects
        Person p1 = new Person("Zek", "P001");
        Lecturer p2 = new Lecturer("Sara", "L001", "Computer Science");
        Student p3 = new Student("Ahmed", "S001", "Software Engineering", 3);

        // Student Information
        System.out.println("=== Student Information ===");
        p3.displayInfo();
        p3.introduce();
        p3.study();
        System.out.println();

        // Demonstrating polymorphism
        System.out.println("=== Polymorphism Example ===");
        Person[] people = new Person[3];
        people[0] = p1;
        people[1] = p2;
        people[2] = p3;

        for (Person person : people) {
            person.introduce();
            System.out.println();
        }
    }
}