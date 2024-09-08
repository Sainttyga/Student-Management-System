package com.mycompany.LindokuhleAtWork;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Lindokuhle Zwane
 */
public class StudentSystem {

    // List to hold student objects
    private final List<Student> objStudent = new ArrayList<>();

    // Scanner for user input
    private final Scanner sc = new Scanner(System.in);

    /**
     * Starts the application menu. The user can choose options to manage
     * student records.
     */
    public void startMenu() {
        while (true) {
            System.out.println("Enter (1) to launch menu or any other key to exit");
            String choice = sc.nextLine();
            if (!choice.equals("1")) {
                System.out.println("Exiting Application.");
                break; // Exit the application
            }
            displayMenu(); // Display available options
            try {
                int menuChoice = Integer.parseInt(sc.nextLine()); // Get menu choice
                switch (menuChoice) {
                    case 1 ->
                        addStudent(); // Option to add a student
                    case 2 ->
                        searchStudent(); // Option to search for a student
                    case 3 ->
                        deleteStudent(); // Option to delete a student
                    case 4 ->
                        printReport(); // Option to print the student report
                    case 5 -> {
                        System.out.println("Exiting Application.");
                        return; // Exit the application
                    }
                    // Invalid choice
                    default ->
                        System.out.println("Invalid choice. Please select a valid menu option.");
                }
                // Handle non-numeric input
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number corresponding to a menu option.");
            }
        }
    }

    // Displays the menu options to the user.
    private void displayMenu() {
        System.out.println("Please select one of the following menu items: ");
        System.out.println("(1) Capture a new student.");
        System.out.println("(2) Search for a student.");
        System.out.println("(3) Delete a student.");
        System.out.println("(4) Print student report.");
        System.out.println("(5) Exit Application.");
    }

    // Adds a student directly to the list for testing purposes.
    public void addStudent(Student student) {
        objStudent.add(student); // // Add the student to the list
    }

    // Prompts the user to enter student details and adds them to the list.
    private void addStudent() {
        try {
            int studentID;
            // Loop until a unique student ID is entered
            do {
                System.out.print("Enter the student ID: ");
                studentID = Integer.parseInt(sc.nextLine());
                if (!isStudentIDUnique(studentID)) {
                    System.out.println("Student ID already exists. Please enter a unique ID.");
                }
            } while (!isStudentIDUnique(studentID));

            System.out.print("Enter the student name: ");
            String studentName = sc.nextLine();

            // Loop until a valid age is entered
            int studentAge;
            while (true) {
                try {
                    System.out.print("Enter the student age: ");
                    studentAge = Integer.parseInt(sc.nextLine());
                    if (studentAge < 16) {
                        System.out.println("You have entered an incorrect student age! Please re-enter the student age (16 years or more).");
                    } else {
                        break; // Valid age, exit loop
                    }
                    // Handle non-numeric age input
                } catch (NumberFormatException e) {
                    System.out.println("Invalid age. Please enter a valid age.");
                }
            }

            String studentEmail;
            // Loop until a valid email is entered
            do {
                System.out.print("Enter the student email: ");
                studentEmail = sc.nextLine();
                if (!isValidEmail(studentEmail)) {
                    System.out.println("Invalid email format. Please re-enter the email.");
                }
            } while (!isValidEmail(studentEmail));

            System.out.print("Enter the student course: ");
            String studentCourse = sc.nextLine();

            // Create a new student object and add it to the list
            objStudent.add(new Student(studentID, studentName, studentAge, studentEmail, studentCourse));
            System.out.println("Student details saved successfully.");
            // Handle parsing errors
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    //  Searches for a student by ID and displays their details.
    private void searchStudent() {
        System.out.print("Enter the student ID to search: ");
        int searchID = Integer.parseInt(sc.nextLine());
        Student foundStudent = null; // Variable to hold the found student

        // Search for a student by ID
        for (Student studentRecord : objStudent) {
            // Search for a student by ID
            if (studentRecord.getStudentID() == searchID) {
                foundStudent = studentRecord; // Student found
                break;
            }
        }
        if (foundStudent != null) {
            // Display the details of the found student
            System.out.println("--------------------------------------------------");
            System.out.println("Student details found:\nSTUDNT ID: " + foundStudent.getStudentID() + "\nSTUDNT NAME: " + foundStudent.getStudentName()
                    + "\nAGE: " + foundStudent.getStudentAge() + "\nSTUDNT EMAIL: " + foundStudent.getStudentEmail() + "\nSTUDNT COURSE: " + foundStudent.getStudentCourse());
            System.out.println("--------------------------------------------------");
        } else {
            System.out.println("--------------------------------------------------");
            System.out.println("Student with Student ID: " + searchID + " was not found!");  // Student not found
            System.out.println("--------------------------------------------------");
        }
    }

    // Deletes a student by ID after confirmation.
    private void deleteStudent() {
        System.out.print("Enter the student ID to delete: ");
        int deleteID = Integer.parseInt(sc.nextLine());
        boolean removed = false; // Flag to check if a student was removed

        // Search for the student to delete
        for (int i = 0; i < objStudent.size(); i++) {
            Student studentReport = objStudent.get(i);
            if (studentReport.getStudentID() == deleteID) {
                // Confirm and delete the student if found
                System.out.print("Are you sure you want to delete student " + studentReport.getStudentID() + " from the system? Yes (y) to delete.");
                String confirm = sc.nextLine();
                if (confirm.equalsIgnoreCase("y")) {
                    objStudent.remove(i); // Remove the student
                    removed = true; // Set flag to true
                    System.out.println("--------------------------------------------------");
                    System.out.println("Student with Student ID: " + studentReport.getStudentID() + " was deleted!");
                    System.out.println("--------------------------------------------------");
                } else {
                    System.out.println("Deletion canceled.");
                }
                break; // Exit the loop after processing the student
            }
        }
        if (!removed) {
            System.out.println("Student with ID: " + deleteID + " not found."); // Student not found
        }
    }

    // Prints a report of all students in the system.
    private void printReport() {
        StringBuilder report = new StringBuilder();
        int studentCount = 1; // Initialize a counter for student numbering
        for (Student studentReport : objStudent) {
            // Generate a report with student details
            report.append("STUDENT ").append(studentCount).append("\n") // Include student numbering
                    .append("-------------------------------------\n") // Line after student number
                    .append("STUDENT ID: ").append(studentReport.getStudentID()).append("\n")
                    .append("STUDENT NAME: ").append(studentReport.getStudentName()).append("\n")
                    .append("STUDENT AGE: ").append(studentReport.getStudentAge()).append("\n")
                    .append("STUDENT EMAIL: ").append(studentReport.getStudentEmail()).append("\n")
                    .append("STUDENT COURSE: ").append(studentReport.getStudentCourse()).append("\n")
                    .append("-------------------------------------\n"); // Line after student details
            studentCount++; // Increment the counter
        }
        System.out.println(report.toString());
    }

    // Checks if a given student ID is unique in the list.
    private boolean isStudentIDUnique(int id) {
        for (Student student : objStudent) {
            if (student.getStudentID() == id) {
                return false; // ID already exists
            }
        }
        return true; // ID is unique
    }

    // Validates the format of an email address.
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains("."); // Basic email validation
    }

    // Unit testing helper methods
    //  Searches for a student by ID and returns their name.
    public String SearchStudent(int searchID) {
        for (Student studentRecord : objStudent) {
            if (studentRecord.getStudentID() == searchID) {
                return studentRecord.getStudentName(); // Return student name if found
            }
        }
        return "Student not found."; // Student not found
    }

    // Deletes a student by ID and returns a message.
    public String DeleteStudent(int deleteID) {
        for (int i = 0; i < objStudent.size(); i++) {
            Student studentReport = objStudent.get(i);
            if (studentReport.getStudentID() == deleteID) {
                objStudent.remove(i); // Remove the student
                return "Student with Student ID: " + deleteID + " was deleted!"; // Return success message
            }
        }
        return "Student not found."; // Student not found
    }

    // Saves a student object to the list if the ID is unique.
    public String SaveStudent(Student student) {
        if (isStudentIDUnique(student.getStudentID())) {
            objStudent.add(student); // Add student if ID is unique
            return "Student details saved successfully.";
        }
        return "Student ID already exists."; // ID already exists
    }

    // Validates the age of a student.
    public String StudentAge_StudentAgeValid(int studentAge) {
        if (studentAge >= 16) {
            return "Valid age."; // Age is valid
        } else {
            return "You have entered an incorrect student age!!!\nPlease re-enter the student age (16 years or more)"; // Age is invalid
        }
    }

    // Checks if the input for student age is a valid integer.
    public String TestStudentAge_StudentAgeInvalidCharacter(String studentAgeStr) {
        try {
            Integer.valueOf(studentAgeStr); // Try to parse the string to an integer
            return "Valid age input."; // Input is valid
        } catch (NumberFormatException e) {
            return "Invalid age. Please enter a valid age."; // Input is invalid
        }
    }
}
