package com.mycompany.LindokuhleAtWork;

import java.util.*;

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

        System.out.println("Enter (1) to launch menu or any other key to exit");
        String choice = sc.nextLine().trim(); // Read user input

        if (!choice.equals("1")) {
            System.out.println("Exiting Application.");
            return; // Exit the application
        }

        // Enter the main menu loop
        while (true) {
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
                        System.exit(0); // Exit the application immediately
                    }
                    // Invalid choice
                    default ->
                        System.out.println("Invalid choice. Please select a valid menu option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number corresponding to a menu option.");
            }
        }
    }

    // Displays the menu options to the user.
    private void displayMenu() {
        System.out.println("-----------------------------------------------");
        System.out.println("Please select one of the following menu items: ");
        System.out.println("(1) Capture a new student.");
        System.out.println("(2) Search for a student.");
        System.out.println("(3) Delete a student.");
        System.out.println("(4) Print student report.");
        System.out.println("(5) Exit Application.");
        System.out.println("-----------------------------------------------");
    }

    // Adds a student directly to the list for testing purposes.
    public void addStudent(Student student) {
        objStudent.add(student); // // Add the student to the list
    }

    // Method to validate user input with attempts
    private String validateInputWithAttempts(String prompt, String errorMessage, String regex, int maxAttempts) {
        int attempts = 0;
        String input;

        while (attempts < maxAttempts) {
            System.out.print(prompt);
            input = sc.nextLine().trim();

            if (input.matches(regex)) {
                return input; // Valid input
            }

            System.out.println(errorMessage);
            attempts++;
        }

        System.out.println("-----------------------------------------------");
        System.out.println("Too many invalid attempts. Operation terminated.");
        return null; // Return null if attempts are exhausted
    }

    // Add student method using validateInputWithAttempts
    private void addStudent() {
        System.out.println("Enter the following details to add a new student:");

        // Validate student ID
        String studentIDInput = validateInputWithAttempts(
                "Enter the 8-digit student ID: ",
                "Invalid Student ID. It must be exactly 8 digits and unique.",
                "\\d{8}", 4
        );

        if (studentIDInput == null || !isStudentIDUnique(Integer.parseInt(studentIDInput))) {
            return;
        }
        int studentID = Integer.parseInt(studentIDInput);

        // Validate student name
        String studentName = validateInputWithAttempts(
                "Enter the student name: ",
                "Invalid input. Student name must only contain letters and spaces, and cannot be empty.",
                "[a-zA-Z ]{2,}", 4 // This regex ensures that the name has at least two letters
        );

        if (studentName == null) {
            return;
        }

        // Validate student surname
        String studentSurname = validateInputWithAttempts(
                "Enter the student surname: ",
                "Invalid input. Student surname must only contain letters and spaces, and cannot be empty.",
                "[a-zA-Z ]{2,}+", 4 // This regex ensures that the surname has at least two letters
        );

        if (studentSurname == null) {
            return;
        }

        // Validate student age
        int studentAge = Integer.parseInt(validateInputWithAttempts(
                "Enter the student age (16+): ",
                "Invalid age. Please enter a valid number greater than or equal to 16.",
                "[1-9][0-9]*", 4
        ));

        if (studentAge < 16) {
            System.out.println("Age must be 16 or older. Operation terminated.");
            return;
        }

        // Validate student email
        String studentEmail = validateInputWithAttempts(
                "Enter the student email: ",
                "Invalid email format. Please enter a valid email address.",
                "[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}", 4
        );

        if (studentEmail == null || !isValidEmail(studentEmail)) {
            System.out.println("Invalid email format. Please enter a valid email address.");
            return;
        }

        // Validate student course
        String studentCourse = validateInputWithAttempts(
                "Enter the student course: ",
                "Invalid course name. Only letters, spaces, and numbers are allowed.",
                "[a-zA-Z0-9 ]{2,}+", 4 // This regex ensures the course name has at least two characters
        );

        if (studentCourse == null) {
            return;
        }

        // Add the student to the list
        objStudent.add(new Student(studentID, studentName, studentSurname, studentAge, studentEmail, studentCourse));
        System.out.println("Student details saved successfully.");
    }

    // Search student method using validateInputWithAttempts
    private void searchStudent() {
        // Validate student ID input with attempts
        String searchID = validateInputWithAttempts(
                "Enter the 8-digit student ID to search: ",
                "Invalid Student ID. It must be exactly 8 digits.",
                "\\d{8}", 4
        );

        if (searchID == null) {
            return; // Exit if the user failed to provide valid input after 4 attempts
        }

        int searchIDInt = Integer.parseInt(searchID); // Convert validated string ID to integer

        // Using the SearchStudent method to get the student name or a "Student not found" message
        String studentName = SearchStudent(searchIDInt);

        if (!studentName.equals("Student not found.")) {
            // If the student is found, display full details
            Student foundStudent = null;

            // Search for the student to retrieve full details
            for (Student studentRecord : objStudent) {
                if (studentRecord.getStudentID() == searchIDInt) {
                    foundStudent = studentRecord; // Student found
                    break;
                }
            }

            if (foundStudent != null) {
                // Display the details of the found student
                System.out.println("--------------------------------------------------");
                System.out.println("Student details found:\nSTUDENT ID: ST" + foundStudent.getStudentID()
                        + "\nSTUDENT NAME: " + foundStudent.getStudentName()
                        + "\nSTUDENT SURNAME: " + foundStudent.getStudentSurname()
                        + "\nnSTUDENT AGE: " + foundStudent.getStudentAge()
                        + "\nSTUDENT EMAIL: " + foundStudent.getStudentEmail()
                        + "\nSTUDENT COURSE: " + foundStudent.getStudentCourse());
            }
        } else {
            // If the student wasn't found, print an appropriate message
            System.out.println("--------------------------------------------------");
            System.out.println("No student found with the ID ST" + searchIDInt + "!");  // Student not found
            System.out.println("--------------------------------------------------");
        }
    }

    // Delete student method using validateInputWithAttempts
    private void deleteStudent() {
        // Validate input for the student ID (ensure it's exactly 8 digits)
        String deleteID = validateInputWithAttempts(
                "Enter the 8-digit student ID to delete: ",
                "Invalid Student ID. It must be exactly 8 digits.",
                "\\d{8}", 4
        );

        if (deleteID == null) {
            return;
        }

        // Convert deleteID to int for comparison
        int deleteIDInt = Integer.parseInt(deleteID);
        
        // Check if the student exists and confirm deletion
        boolean studentFound = false; // Initialize the removed flag

        // Search for the student to delete
        for (Student studentReport : objStudent) {
            // Compare the student ID (assumed to be an integer) with the deleteID
            if (studentReport.getStudentID() == deleteIDInt) {
                studentFound = true; // Mark student as found
                
                // Confirm and delete the student if found
                System.out.println("Are you sure you want to delete student with ID ST" + studentReport.getStudentID() + " from the system? Yes(y)/ No(n): ");
                String confirm = sc.nextLine();
                
                if (confirm.equalsIgnoreCase("y")) {
                    // Call the DeleteStudent method to perform the deletion
                    String result = DeleteStudent(deleteIDInt);
                    System.out.println("-------------------------------------------------");
                    System.out.println(result);
                } else if (confirm.equalsIgnoreCase("n")) {
                    System.out.println("Deletion canceled.");
                } else {
                    System.out.println("Invalid input. Deletion canceled.");
                }
                break; // Exit the loop after processing the student
            }
        }

        // If no student is found, inform the user
        if (!studentFound) {
            System.out.println("No student found with the ID ST" + deleteIDInt + ".");
        }
    }

    // Prints a report of all students in the system.
    private void printReport() {
        // Check if the list of students is empty
        if (objStudent.isEmpty()) {
            System.out.println("No students available in the system.");
            return; // Exit the method
        }

        StringBuilder report = new StringBuilder();
        int studentCount = 1; // Initialize a counter for student numbering

        for (Student studentReport : objStudent) {
            // Generate a report with student details
            report.append("\n-------------------------------------\n")
                    .append("STUDENT ").append(studentCount).append("\n") // Include student numbering
                    .append("-------------------------------------\n") // Line after student number
                    .append("STUDENT ID: ST").append(studentReport.getStudentID()).append("\n")
                    .append("STUDENT NAME: ").append(studentReport.getStudentName()).append("\n")
                    .append("STUDENT SURNAME: ").append(studentReport.getStudentSurname()).append("\n")
                    .append("STUDENT AGE: ").append(studentReport.getStudentAge()).append("\n")
                    .append("STUDENT EMAIL: ").append(studentReport.getStudentEmail()).append("\n")
                    .append("STUDENT COURSE: ").append(studentReport.getStudentCourse()).append(".");
            studentCount++; // Increment the counter
        }
        System.out.println(report.toString());
    }

    // Checks if a given student ID is unique in the list.
    private boolean isStudentIDUnique(int id) {
        for (Student student : objStudent) {
            if (student.getStudentID() == id) {
                System.out.println("Student with ID ST" + id + " already exists.");
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
                return "Student with Student ID: ST" + deleteID + " was deleted!"; // Return success message
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
