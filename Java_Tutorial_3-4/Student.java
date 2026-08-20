package Java_Tutorial_3;

public class Student extends Person {
    private String major;  // Additional attribute for student
    private int year;      // Year of study

    // Constructor
    public Student(String name, String id) {
        super(name, id);
        this.major = "Undeclared";
        this.year = 1;
    }

    // Overloaded constructor with major and year
    public Student(String name, String id, String major, int year) {
        super(name, id);
        this.major = major;
        this.year = year;
    }

    // Getters and Setters
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public void introduce() {
        System.out.println("I am a student.");
        System.out.println("Major: " + major + ", Year: " + year);
    }

    // Additional student-specific method
    public void study() {
        System.out.println(getName() + " is studying.");
    }
}