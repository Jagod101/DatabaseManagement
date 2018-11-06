/*
 * Student Name: Zach Jagoda
 * Student ID: 2274813
 * Student Email: jagod101@mail.chapman.edu
 * CPSC408 - Database Management
 * Assignment 3 - Student Database (Java)
 */

import java.util.Scanner;

public class menu {
    public void displayMenu() {
        System.out.println("\n ---- STUDENT DATABASE MENU ---- ");
        System.out.println("1. Display All Students With All Attributes");
        System.out.println("2. Create New Student");
        System.out.println("3. Update Student Major");
        System.out.println("4. Update Student Faculty Advisor");
        System.out.println("5. Update Student Major AND Faculty Advisor");
        System.out.println("6. Delete Student");
        System.out.println("7. Search For Student by Major");
        System.out.println("8. Search For Student by GPA");
        System.out.println("9. Search For Student by Faculty Advisor");
        System.out.println("\n0. Quit Program");
    }
}
