# StudentManagementApp

## Overview

**StudentManagementApp** is a Java-based application designed to manage student records. Users can perform operations such as adding students, searching for students, deleting students, and generating reports. The application uses a console-based interface to interact with users.

## Features

- **Add Student**: Users can add new student records with details such as ID, name, age, email, and course. The application validates for unique student IDs and proper email formats.
- **Search Student**: Users can search for a student by ID and view their details.
- **Delete Student**: Users can delete student records by specifying the student ID and confirm the deletion.
- **Print Report**: Users can generate and display a report of all student records in the system.

## Classes

### `Student`

Represents a student with the following attributes:

- **Student ID**: Unique identifier for the student.
- **Student Name**: Full name of the student.
- **Student Age**: Age of the student.
- **Student Email**: Email address of the student.
- **Student Course**: Course the student is enrolled in.

### `StudentSystem`

The main class that manages student operations and user interactions:

- **Main Method**: Starts the application and presents the menu to the user.
- **Add Student**: Method to add new students, including validation for unique IDs and email format.
- **Search Student**: Method to search for students by ID.
- **Delete Student**: Method to delete student records by ID.
- **Print Report**: Method to print a report of all students.
- **Validation Methods**: Includes methods to check if an ID is unique and if an email is valid.

### `StudentManagement`

Contains the entry point for the application and launches the `StudentSystem`:

- **Main Method**: Displays the initial menu and starts the `StudentSystem`.

### `StudentSystemTest`

JUnit test class to validate the functionality of the `StudentSystem` class. It includes tests for:

- Adding students.
- Searching for students.
- Deleting students.
- Validating student age and input formats.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- A Java IDE such as IntelliJ IDEA or Eclipse

### Installation

1. **Clone the repository**:
    ```bash
    https://github.com/YourUsername/StudentManagementApp.git
    ```

2. **Open the project in your IDE**:
    - Import the project into your preferred Java IDE.

### Running the Application

1. **Compile and Run**:
    - Run the `StudentManagement` class. This will start the application and display the menu.

2. **Interact with the Console**:
    - Follow the prompts to add, search, delete students, or generate reports.

### Running Tests

1. **Execute JUnit Tests**:
    - Run the `StudentSystemTest` class to execute all the unit tests. Your IDE should provide an option to run JUnit tests directly.

## Usage

1. **Add Student**: Enter student details including ID, name, age, email, and course.
2. **Search Student**: Enter a student ID to retrieve and view student details.
3. **Delete Student**: Enter a student ID to delete the record after confirmation.
4. **Print Report**: Generate and display a report of all students in the system.

### Example Input

When prompted to add a student, enter the following details:
- Student ID: `1`
- Student Name: `John Doe`
- Student Age: `20`
- Student Email: `john.doe@example.com`
- Student Course: `Computer Science`

For searching a student:
- Student ID: `1`

For deleting a student:
- Student ID: `1`

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please submit an issue or create a pull request.

## Acknowledgements

- This project uses Java for console-based interaction.
- JUnit 5 is used for unit testing.

---

Feel free to adjust any specific details as needed!
