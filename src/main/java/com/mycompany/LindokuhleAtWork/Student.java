package com.mycompany.LindokuhleAtWork;

/**
 *
 * @author Lindokuhle Zwane
 */
public class Student {

    // declare instance variables
    private final int studentID;
    private final String studentName;
    private final int studentAge;
    private final String studentEmail;
    private final String studentCourse;

    // constructor for the declared variables
    public Student(int studentID, String studentName, int studentAge, String studentEmail, String studentCourse) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentEmail = studentEmail;
        this.studentCourse = studentCourse;
    }

    // getters methods
    public int getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getStudentCourse() {
        return studentCourse;
    }
}
