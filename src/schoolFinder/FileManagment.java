
package schoolFinder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class FileManagment { 
   // Define the file path for storing requests   
private static final String REQUESTS_FILE = "requests.txt";
   // Method to initialize requests from file
public static List<Request> initRequestsFromFile() {
    List<Request> requests = new ArrayList<>();
    try (Scanner scanner = new Scanner(new File(REQUESTS_FILE))) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            //  the order of attributes in the file is: requestId, childId, schoolName, status
            int requestId = Integer.parseInt(parts[0]);
            int childId = Integer.parseInt(parts[1]);
            String schoolName = parts[2];
            String status = parts[3];

            // Create Child and School and request objeect 
            Child child = new Child(childId, "ChildName", 0, 0);  
            School school = new School(schoolName, "District", "LeaderName"); 
            Request request = new Request(requestId ,child, school, status);
            requests.add(request);
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    return requests;
}
 // Method to delete requests based on user input
public static void deleteRequests(Scanner scanner) {
    List<Request> requests = initRequestsFromFile();
    System.out.print("Enter child ID to delete requests: ");
    int childIdToDelete = scanner.nextInt();
    // Display requests related to the specified child ID
    displayRequestsForChild(requests, childIdToDelete);
    System.out.print("Enter request ID to delete: ");
    int requestIdToDelete = scanner.nextInt();
    
    // Remove the selected request from the list
    Request requestToDelete = findRequest(requests, childIdToDelete, requestIdToDelete);
    if (requestToDelete != null) {
        requests.remove(requestToDelete);
        saveRequestsToFile(requests);
        System.out.println("Request deleted successfully.");
    } else {
        System.out.println("No matching request found. No request deleted.");
    }
  
}
 // Method to display requests for a specific child
public static void displayRequestsForChild(List<Request> requests, int childId) {
    System.out.println("Requests for Child ID: " + childId);
    for (Request request : requests) {
        if (request.getChild().getChildId() == childId) {
            System.out.println(request.toFormattedString());
        }
    }
}
 // Method to find a request in the list based on child ID and request ID
private static Request findRequest(List<Request> requests, int childId, int requestId) {
    for (Request Request : requests) {
        if (Request.getChild().getChildId() == childId && Request.getRequestId() == (requestId)) {
            return Request;
        }
    }
    return null;
}
// Method to save the updated list of requests to the file
public static void saveRequestsToFile(List<Request> requests) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(REQUESTS_FILE))) {
        for (Request request : requests) {
            writer.println(request.toFormattedStringForFile());
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
  
        

    



