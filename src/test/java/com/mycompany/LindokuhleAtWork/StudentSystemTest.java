
package com.mycompany.LindokuhleAtWork;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Acer
 */
public class StudentSystemTest {
    
   private StudentSystem studentSystem;

    @BeforeEach
    public void setUp() {
        studentSystem = new StudentSystem();
    }

    /**
     * Test of SaveStudent method, of class StudentManagement.
     */
    @Test
    public void TestSaveStudent() {
        Student student = new Student(1, "J.Bloggs", 19, "jbloggs@ymail.com", "disd");
        String result = studentSystem.SaveStudent(student);
        assertEquals("Student details saved successfully.", result);
    }

    /**
     * Test of SearchStudent method, of class StudentManagement.
     */
    @Test
    public void TestSearchStudent() {
        Student student = new Student(10111, "J.Bloggs", 19, "jbloggs@ymail.com", "disd");
        studentSystem.SaveStudent(student);
        String result = studentSystem.SearchStudent(10111);
        assertEquals("J.Bloggs", result);
    }

    /**
     * Test of SearchStudent_StudentNotFound method, of class StudentManagement.
     */
    @Test
    public void TestSearchStudent_StudentNotFound() {
        String result = studentSystem.SearchStudent(55555); // Assuming this ID doesn't exist
        assertEquals("Student not found.", result);
    }

    /**
     * Test of DeleteStudent method, of class StudentManagement.
     */
    @Test
    public void TestDeleteStudent() {
        Student student = new Student(10112, "J.Doe", 21, "jdoe@ymail.com", "disd");
        studentSystem.SaveStudent(student);
        String result = studentSystem.DeleteStudent(10112);
        assertEquals("Student with Student ID: 10112 was deleted!", result);
    }

    /**
     * Test of DeleteStudent_StudentNotFound method, of class StudentManagement.
     */
    @Test
    public void TestDeleteStudent_StudentNotFound() {
        String result = studentSystem.DeleteStudent(55555); // Assuming this ID doesn't exist
        assertEquals("Student not found.", result);
    }

    /**
     * Test of StudentAge_StudentAgeValid method, of class StudentManagement.
     */
    @Test
    public void TestStudentAge_StudentAgeValid() {
        String result = studentSystem.StudentAge_StudentAgeValid(19);
        assertEquals("Valid age.", result);
    }

    /**
     * Test of StudentAge_StudentAgeInvalid method, of class StudentManagement.
     */
    @Test
    public void TestStudentAge_StudentAgeInvalid() {
        String result = studentSystem.StudentAge_StudentAgeValid(10);
        assertEquals("You have entered an incorrect student age!!!\nPlease re-enter the student age (16 years or more)", result);
    }

    /**
     * Test of TestStudentAge_StudentAgeInvalidCharacter method, of class StudentManagement.
     */
    @Test
    public void TestStudentAge_StudentAgeInvalidCharacter() {
        String result = studentSystem.TestStudentAge_StudentAgeInvalidCharacter("notANumber");
        assertEquals("Invalid age. Please enter a valid age.", result);
    }
    
}
