package Java_Tutorial_3;

public class Lecturer extends Person {
    private String department;  // Additional attribute for lecturer

    // Constructor
    public Lecturer(String name, String id) {
        super(name, id);
        this.department = "Unknown";
    }

    // Overloaded constructor with department
    public Lecturer(String name, String id, String department) {
        super(name, id);
        this.department = department;
    }

    // Getter and Setter for department
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public void introduce() {
        System.out.println("I am a lecturer.");
        System.out.println("Department: " + department);
    }

    // Additional lecturer-specific method
    public void teach() {
        System.out.println(getName() + " is teaching.");
    }
}