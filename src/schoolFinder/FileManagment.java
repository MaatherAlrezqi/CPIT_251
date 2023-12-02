package schoolFinder;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileManagment {
    // Define the file path for storing requests   
    private static final String REQUESTS_FILE = "requests.txt";
    
    // Method to initialize requests from file
    public static List<Request> initRequestsFromFile() {
        List<Request> requests = new ArrayList<>();
        try ( Scanner scanner = new Scanner(new File(REQUESTS_FILE))) {
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
                Request request = new Request(requestId, child, school, status);
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
        try ( PrintWriter writer = new PrintWriter(new FileWriter(REQUESTS_FILE))) {
            for (Request request : requests) {
                writer.println(request.toFormattedStringForFile());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Method to update child data

    public static void updateChildData(int childID, String name, int age, int academicYear, String childData_FILE) {
        try {
            // Read existing child data from the file into a list
            List<Child> children = readChildDataFromFile(childData_FILE);

            // Find the child to update
            boolean found = false;
            for (Child child : children) {
                if (child.getChildId() == childID) {
                    // Update child data
                    child.setName(name);
                    child.setAge(age);
                    child.setAcademicYear(academicYear);
                    found = true;
                    break;
                }
            }

            if (found) {
                // Write the updated data back to the file
                writeChildDataToFile(children, childData_FILE);
                System.out.println("Child data updated successfully.");
            } else {
                System.out.println("Child with ID " + childID + " not found.");
            }

        } catch (IOException e) {
            // Handle IO exceptions
            e.printStackTrace();
        }
    }
    // Method to read child data from a file and return a list of Child objects

    private static List<Child> readChildDataFromFile(String childData_FILE) throws IOException {
        List<Child> children = new ArrayList<>();
        try ( Scanner scanner = new Scanner(new File(childData_FILE))) {
            // Read each line from the file and create Child objects
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                int childId = Integer.parseInt(parts[0]);
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);
                int academicYear = Integer.parseInt(parts[3]);

                // Create a Child object and add it to the list
                Child child = new Child(childId, name, age, academicYear);
                children.add(child);
            }
        }
        return children;
    }
    // Method to write a list of Child objects back to a file

    private static void writeChildDataToFile(List<Child> children, String childData_FILE) throws IOException {
        try ( PrintWriter writer = new PrintWriter(new FileWriter(childData_FILE))) {
            // Write each Child object as a line in the file
            for (Child child : children) {
                writer.println(child.getChildId() + "," + child.getName() + "," + child.getAge() + "," + child.getAcademicYear());
            }
        }
    }
    //Saves child data to a file
    public static void SaveChildDataToFile(int childID, String name, int age, int academicYear, String childData_FILE) {
        try {
            // Read existing child data from the file into a list
            List<Child> children = readChildDataFromFile(childData_FILE);

            // Check if the child is already registered
            for (Child child : children) {
                if (child.getChildId() == childID) {
                    System.out.println("Child with ID " + childID + " is already registered.");
                    return;
                }
            }

            // Create a new Child object
           Child child = new Child(childID, name, age, academicYear);

            // Add the new child to the list
            children.add(child);

            // Write the updated data back to the file
            writeChildDataToFile(children, childData_FILE);
        } catch (IOException e) {
            // Handle IO exceptions
            e.printStackTrace();
        }
    }
    
    // Method to read schools from file
    public static void readSchoolsFromFile(String filename, Map<String, School> schoolsMap) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] schoolInfo = line.split(",");
                String schoolName = schoolInfo[0].trim();
                String district = schoolInfo[1].trim();
                String leaderName = schoolInfo[2].trim();

                School school = new School(schoolName, district, leaderName);
                schoolsMap.put(schoolName, school);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read disabilities from file
    public static void readDisabilitiesFromFile(String filename, Map<String, Disability> disabilitiesMap) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String disabilityName = line.trim();
                Disability disability = new Disability(disabilityName);
                disabilitiesMap.put(disabilityName, disability);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read schools' disabilities from file
    public static void readSchoolsDisabilitiesFromFile(String filename, Map<String, List<String>> schoolsDisabilitiesMap) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] schoolDisabilitiesInfo = line.split(",");
                String schoolName = schoolDisabilitiesInfo[0].trim();
                String[] disabilities = schoolDisabilitiesInfo[1].trim().split(" - ");

                schoolsDisabilitiesMap.put(schoolName, Arrays.asList(disabilities));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
