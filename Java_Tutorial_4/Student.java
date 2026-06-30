public class Student {

    // Private variables
    private String studentID;
    private String name;
    private double cgpa;
    private String programme;

    // Setter methods
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCGPA(double cgpa) {
        this.cgpa = cgpa;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    // Getter methods
    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public double getCGPA() {
        return cgpa;
    }

    public String getProgramme() {
        return programme;
    }
}