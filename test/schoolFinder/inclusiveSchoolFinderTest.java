/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package schoolFinder;


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

/**
 *
 * @author susus
 */
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

    /**
     * Test of main method, of class inclusiveSchoolFinder.
   
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        inclusiveSchoolFinder.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */ 
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
    assertEquals("Autism", result);
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
