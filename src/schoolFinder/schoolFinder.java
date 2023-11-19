
package schoolFinder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class schoolFinder {
 public static void main(String[] args) {
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
                    deleteRequests(scanner);
                    break;
                case 3:
                    updateRequest(scanner);
                    break;
                case 4:
                    showRequestStatus(scanner);
                    break;
                case 0:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 0);

        scanner.close();
    }

    private static void registerChild(Scanner scanner) {
        System.out.println("Choose the type of disability:");
        String disability = chooseDisability(scanner);

        System.out.println("Choose the city:");
        String city = scanner.next();

        System.out.println("Choose the school name:");
        School school = chooseSchool(scanner, disability, city);

        if (school != null) {
            System.out.println("Child Information:");
            String childName = scanner.next();
            int age = scanner.nextInt();
            int academicYear = scanner.nextInt();

            // Create Child object
            Child child = new Child(childName, age, academicYear);

            // Create Request object
            Request request = new Request(child, school, "Pending");

            // Send application to the school
            sendApplication(request);
        }
    }

    private static void deleteRequests(Scanner scanner) {
      
      //  System.out.println("Deleting requests...");
    }

    private static void updateRequest(Scanner scanner) {
        
        //System.out.println("Updating requests...");
    }

    private static void showRequestStatus(Scanner scanner) {
      
        // System.out.println("Showing request status...");
    }

    private static String chooseDisability(Scanner scanner) {
        // Read disabilities from a file
        try (BufferedReader reader = new BufferedReader(new FileReader("disabilities.txt"))) {
            String line;
            Map<Integer, String> disabilityMap = new HashMap<>();

            int index = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println(index + ". " + line);
                disabilityMap.put(index, line);
                index++;
            }

            int choice = scanner.nextInt();
            return disabilityMap.get(choice);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static School chooseSchool(Scanner scanner, String disability, String city) {
        // Read school information from a file
        try (BufferedReader reader = new BufferedReader(new FileReader("schools.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] schoolInfo = line.split(",");
                String schoolName = schoolInfo[0];
                String schoolCity = schoolInfo[1];
                String schoolLeader = schoolInfo[2];

                if (schoolCity.equalsIgnoreCase(city) && acceptsDisability(schoolName, disability)) {
                    System.out.println("School Information:");
                    System.out.println("Name: " + schoolName);
                    System.out.println("District: " + schoolCity);
                    System.out.println("Leader: " + schoolLeader);

                    return new School(schoolName, schoolCity, schoolLeader);
                }
            }

            System.out.println("No schools found in the specified city that accept the given disability.");
            return null;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean acceptsDisability(String schoolName, String disability) {
        // Check if the school accepts the given disability
        try (BufferedReader reader = new BufferedReader(new FileReader("school_disabilities.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] schoolDisabilityInfo = line.split(",");
                String school = schoolDisabilityInfo[0];
                String acceptedDisability = schoolDisabilityInfo[1];

                if (school.equalsIgnoreCase(schoolName) && acceptedDisability.equalsIgnoreCase(disability)) {
                    return true;
                }
            }

            return false;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void sendApplication(Request request) {
        
        System.out.println("Application sent to " + request.getSchool().getName() + " for " + request.getChild().getName());
    }
    
}
