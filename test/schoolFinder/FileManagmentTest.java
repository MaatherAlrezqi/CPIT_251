package schoolFinder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maath
 */
public class FileManagmentTest {

    public FileManagmentTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of deleteRequests method, of class FileManagment.
     */
    @Test
    public void testDeleteRequests() {
        // Set up a test scenario  
        Request request1 = new Request(1, new Child(123, "sara", 8, 4), new School("Al-Bayan school", "Al-Andalus District", "Fatimah Ali"), "PENDING");
        Request request2 = new Request(2, new Child(345, "nora", 10, 6), new School("Al-Baraem school", "Sharafiyah District", "Auhood Mohamed"), "PENDING");

        // Create a temporary file for testing  
        String testFilePath = "test_requests.txt";
        // Write sample data to the file  
        try (PrintWriter writer = new PrintWriter(new FileWriter(testFilePath))) {
            writer.println(request1.toFormattedStringForFile());
            writer.println(request2.toFormattedStringForFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner("123\n1\n");
        // Call the deleteRequests method  
        FileManagment.deleteRequests(scanner, testFilePath);
        // Check that the request was deleted  
        List<Request> updatedRequests = FileManagment.initRequestsFromFile(testFilePath);
        assertEquals(1, updatedRequests.size()); // Expecting only one request after deletion  
        // Clean up: Delete the temporary file  
        new File(testFilePath).delete();
    }

    /**
     * Test of updateChildData method, of class FileManagment.
     */
   @Test 
   public void testUpdateChildData() { 
    // Set up a test scenario 
    int childID = 1; // Assuming a child with ID 1 exists in the test data 
    String name = "NORAH"; 
    int age = 9; 
    int academicYear = 3; 
    String childData_FILE = "test_child_data.txt"; // Provide a valid file path 
    // Create a temporary file for testing 
    String testFilePath = "test_child_data.txt"; 
    try (PrintWriter writer = new PrintWriter(new FileWriter(testFilePath))) { 
        // Sample child data 
        writer.println("1,NORAH,9,3"); 
    } catch (IOException e) { 
        e.printStackTrace(); 
    } 
    // Call the updateChildData method
     FileManagment.UpdateChildData(childID, name, age, academicYear, childData_FILE); 
    // Read the updated child data from the file 
    try { 
        List<Child> updatedChildren = FileManagment.ReadChildDataFromFile(childData_FILE); 
        // Check that the child data was updated 
        boolean found = false; 
        for (Child child : updatedChildren) { 
            if (child.getChildId() == childID && child.getName().equals(name) 
                    && child.getAge() == age && child.getAcademicYear() == academicYear) { 
                found = true; 
                break; 
            } 
        } 
        assertTrue("Child data was not updated.", found); 
    } catch (IOException e) { 
        e.printStackTrace(); // Handle the exception as needed 
    } 
    // Clean up: Delete the temporary file 
    new File(testFilePath).delete(); 
}
    @Test
    public void testShowRequestStatus() {
        Request request1 = new Request(1, new Child(001, "neno", 8, 4), new School("Al-Bayan school", "Al-Andalus District", "Fatimah Ali"), "Accepted");
        Request request2 = new Request(2, new Child(002, "hala", 10, 6), new School("Al-Baraem school", "Sharafiyah District", "Auhood Mohamed"), "PENDING");

        // make sure that request1 have information not Null
        var expectedOutput = request1.toFormattedString();
        assertNotNull(expectedOutput);

        // make sure that request1 have same status as exsepted
        assertEquals("Accepted", request1.getStatus());
    }
}
