package schoolFinder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class inclusiveSchoolFinderTest {

    public inclusiveSchoolFinderTest() {
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

    @Test
    public void testRegisterChild() throws IOException {
        // Set up a mock input for the scanner
        String input = "1\n"
                + // Choose option 1 for "Register Child"
                "1\n"
                + // Choose the first disability
                "1\n"
                + // Choose the first school
                "123\n"
                + // Enter child ID
                "Amal\n"
                + // Enter child name
                "8\n"
                + // Enter child age
                "5\n"
                + // Enter academic year
                "0\n"; // Choose option 0 to exit

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Execute the main method (this will use the mock input)
        inclusiveSchoolFinder.main(new String[]{});

        assertEquals(1, inclusiveSchoolFinder.requests.size());
        assertEquals("Amal", inclusiveSchoolFinder.requests.get(0).getChild().getName());
    }

    @Test
    public void testChooseDisability() {
        Map<String, Disability> disabilitiesMap = new HashMap<>();
        disabilitiesMap.put("1", new Disability("Autism"));
        disabilitiesMap.put("2", new Disability("ADHD"));

        inclusiveSchoolFinder inclusiveSchoolFinder = new inclusiveSchoolFinder();
        inclusiveSchoolFinder.disabilitiesMap.putAll(disabilitiesMap);
        // Mock user input
        Scanner scanner = new Scanner("1");
        // Call the method
        String result = inclusiveSchoolFinder.chooseDisability(scanner);
        // Verify the result
        assertEquals("Down Syndrome", result);
    }

    @Test
    public void testChooseSchool() {
        Map<String, School> schoolsMap = new HashMap<>();
        schoolsMap.put("1", new School("Al-Bayan school", "Al-Andalus District", "Fatimah Ali"));
        schoolsMap.put("2", new School("Al-Baraem school", "Sharafiyah District", "Auhood Mohamed"));

        Map<String, List<String>> schoolsDisabilitiesMap = new HashMap<>();
        schoolsDisabilitiesMap.put("Al-Bayan school", Arrays.asList("Cerebral Palsy", "Visual Impairment", "Hearing Impairment"));
        schoolsDisabilitiesMap.put("Al-Baraem school", Arrays.asList("ADHD", "Visual Impairment", "Hearing Impairment"));

        inclusiveSchoolFinder inclusiveSchoolFinder = new inclusiveSchoolFinder();
        inclusiveSchoolFinder.schoolsMap.putAll(schoolsMap);
        inclusiveSchoolFinder.schoolsDisabilitiesMap.putAll(schoolsDisabilitiesMap);

        // Mock user input
        Scanner scanner = new Scanner("1");

        // Call the method
        School result = inclusiveSchoolFinder.chooseSchool(scanner, "Cerebral Palsy");

        // Add debug output
        System.out.println("Result school: " + result);

        // Verify the result
        assertEquals("Al-Bayan school", result.getName());

    }
}
