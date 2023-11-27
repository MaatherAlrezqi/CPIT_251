
package schoolFinder;
// Define a class representing a Child with various attributes
public class Child {
    private int childId;
    private String name;
    private int age;
    private int academicYear;
// Constructor to initialize a Child object
    public Child(int childId, String name, int age, int academicYear) {
        this.childId = childId;
        this.name = name;
        this.age = age;
        this.academicYear = academicYear;
    }
// Getters and setters for accessing and modifying private attributes
    public int getChildId() {
        return childId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(int academicYear) {
        this.academicYear = academicYear;
    }
// toString method to provide a string representation of a Child object
     @Override
     public String toString() {
        return "Child ID: " + childId +
                "\nName: " + name +
                "\nAge: " + age +
                "\nAcademic Year: " + academicYear ;
    }
}