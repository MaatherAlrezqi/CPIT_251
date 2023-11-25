
package schoolFinder;

public class Child {
    private int childId;
    private String name;
    private int age;
    private int academicYear;

    public Child(int childId, String name, int age, int academicYear) {
        this.childId = childId;
        this.name = name;
        this.age = age;
        this.academicYear = academicYear;
    }

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
}