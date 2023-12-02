
package schoolFinder;


public class Parent {
    private int ID;
    private String email;
    private String password;

    // Constructor
    public Parent(int ID, String email, String password) {
        this.ID = ID;
        this.email = email;
        this.password = password;
    }

    // Getter and Setter methods for each attribute
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    } 
    // Log in method
    public String logIn() {
        // Perform login logic
        return "User logged in successfully.";
    }

    // Log out method
    public String logOut() {
        // Perform logout logic
        return "User logged out successfully.";
    }
}
