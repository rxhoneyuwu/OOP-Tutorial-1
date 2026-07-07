package Java_Tutorial_3;

public class Person {
    private String name;
    private String id;

    // Constructor
    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    // Setters (optional but good practice)
    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Method to be overridden
    public void introduce() {
        System.out.println("I am a person.");
    }

    // Display person details
    public void displayInfo() {
        System.out.println("Name: " + name + ", ID: " + id);
    }
}