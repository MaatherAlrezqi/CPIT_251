package schoolFinder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class inclusiveSchoolFinder {
    private static List<Request> requests = new ArrayList<>();
    private static Map<String, School> schoolsMap = new HashMap<>();
    private static Map<String, Disability> disabilitiesMap = new HashMap<>();
    private static Map<String, List<String>> schoolsDisabilitiesMap = new HashMap<>();
    //Define the file path for storing chid information   
    public static final String childData_FILE = "childData.txt";
    
    public static void main(String[] args) {
        readSchoolsFromFile("schools.txt");
        readDisabilitiesFromFile("disabilities.txt");
        readSchoolsDisabilitiesFromFile("school_disabilities.txt");
        
      
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Select an option:");
            System.out.println("1. Register Child");
            System.out.println("2. Delete Requests");
            System.out.println("3. Update Requests");
            System.out.println("4. Show Request Status");
            System.out.println("0. Exit");

            choice = scanner.nextInt();

        switch (choice) {
            case 1:
                registerChild(scanner);
                break;
            case 2:
                FileManagment.deleteRequests(scanner);
                break;
            case 3:
                //Enter new Chid Information for update
                System.out.println("Update Child Information:");
                System.out.print("Enter child ID:");
                int childID = scanner.nextInt();
                System.out.print("Enter New child name: ");
                String name = scanner.next();
                System.out.print("Enter New child age: ");
                int age = scanner.nextInt();
                System.out.print("Enter New academic year: ");
                int academicYear = scanner.nextInt();
                // call Method to update Child Information
                FileManagment.updateChildData(childID, name, age, academicYear,childData_FILE);
                break;
            case 4:
                showRequestStatus(scanner);
                break;
            case 0:
                // Save requests to file before exiting
                FileManagment.saveRequestsToFile(requests);
                System.out.println("Exiting the system. Goodbye!");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        } while (choice != 0);

        scanner.close();
    }

    private static void readSchoolsFromFile(String filename) {
        try ( BufferedReader reader = new BufferedReader(new FileReader(filename))) {
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

    private static void readDisabilitiesFromFile(String filename) {
        try ( BufferedReader reader = new BufferedReader(new FileReader(filename))) {
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

    private static void readSchoolsDisabilitiesFromFile(String filename) {
        try ( BufferedReader reader = new BufferedReader(new FileReader(filename))) {
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

    private static void registerChild(Scanner scanner) {
        System.out.println("Choose the type of disability:");
        String disabilityName = chooseDisability(scanner);

        // Check if a valid disability is chosen
        if (disabilityName != null) {
            School chosenSchool = chooseSchool(scanner, disabilityName);

            if (chosenSchool != null) {
                System.out.println("Child Information:");
                System.out.print("Enter child ID: ");
                int childId = scanner.nextInt(); 
                System.out.print("Enter child name: ");
                String childName = scanner.next();
                System.out.print("Enter child age: ");
                int age = scanner.nextInt();
                System.out.print("Enter academic year: ");
                int academicYear = scanner.nextInt();
               
                int requestId = Request.generateRequestId();
              Child child = new Child(childId, childName, age, academicYear);
            // creating a Request object ,add it to the requests list
            Request request = new Request(requestId ,child, chosenSchool, "Pending");
            requests.add(request);
            // Display the requestId to the user 
            System.out.println("Child registered successfully! Request ID: " + requestId);
            sendApplication(request);
               
            }
        }
    }
   
    private static void showRequestStatus(Scanner scanner) {

        // System.out.println("Showing request status...");
    }

private static String chooseDisability(Scanner scanner) {
    System.out.println("Available Disabilities:");
    
    int index = 1;
    for (Disability disability : disabilitiesMap.values()) {
        System.out.println(index + ". " + disability.getName());
        index++;
    }

    System.out.println("Enter the number corresponding to the desired disability:");
    int choice = scanner.nextInt();

    // Check if the choice is valid
    if (choice >= 1 && choice <= disabilitiesMap.size()) {
        // Retrieve the disability name based on the user's choice
        int currentIndex = 1;
        for (Disability disability : disabilitiesMap.values()) {
            if (currentIndex == choice) {
                return disability.getName();
            }
            currentIndex++;
        }
    }
   
    System.out.println("Invalid choice. Please try again.");
    return null;
}
    private static School chooseSchool(Scanner scanner, String disabilityName) {
        System.out.println("Available Schools that accept " + disabilityName + ":");

        // Iterate through schools to find those that accept the chosen disability
        List<School> eligibleSchools = new ArrayList<>();
        int index = 1;
        for (School school : schoolsMap.values()) {
            String schoolDisabilities = getSchoolDisabilitiesAsString(school.getName());
            if (acceptsDisability(school.getName(), disabilityName)) {
                eligibleSchools.add(school);
                System.out.println(index + ". " + school.getName());
                displaySchoolInfo(school); // Display school information
                System.out.println(); // Add an empty line for clarity
                index++;
            }
        }

        // Check if there are eligible schools
        if (eligibleSchools.isEmpty()) {
            System.out.println("No schools found that accept the given disability.");
            return null;
        }

        // Ask the user to choose a school
        System.out.println("Enter the number corresponding to the desired school:");
        int choice = scanner.nextInt();

        // Check if the choice is valid
        if (choice >= 1 && choice <= eligibleSchools.size()) {
            School chosenSchool = eligibleSchools.get(choice - 1);
            return chosenSchool;
        } else {
            System.out.println("Invalid choice. Please try again.");
            return null;
        }
    }

    private static void displaySchoolInfo(School school) {
        System.out.println("School Information:");
        System.out.println("Name: " + school.getName());
        System.out.println("District: " + school.getDistrict());
        System.out.println("Leader: " + school.getLeaderName());
    }

    private static boolean acceptsDisability(String schoolName, String disabilityName) {
        List<String> acceptedDisabilities = schoolsDisabilitiesMap.get(schoolName);
        return acceptedDisabilities != null && acceptedDisabilities.contains(disabilityName);
    }

    private static String getSchoolDisabilitiesAsString(String schoolName) {
        List<String> acceptedDisabilities = schoolsDisabilitiesMap.get(schoolName);
        return acceptedDisabilities != null ? String.join(" - ", acceptedDisabilities) : null;
    }

    private static void sendApplication(Request request) {
        // Implement application sending logic here
        System.out.println("Application sent to " + request.getSchool().getName()
                + " for " + request.getChild().getName());
    }
    
}
