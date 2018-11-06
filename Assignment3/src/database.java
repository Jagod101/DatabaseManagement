/*
 * Student Name: Zach Jagoda
 * Student ID: 2274813
 * Student Email: jagod101@mail.chapman.edu
 * CPSC408 - Database Management
 * Assignment 3 - Student Database (Java)
 */

import java.sql.*;
import java.util.*;

public class database {
    public Connection conn = null;

    private String driverLocation = "com.mysql.cj.jdbc.Driver";
    private String conn_url = "jdbc:mysql://localhost:3306/javaconvert?serverTimezone=America/Los_Angeles";
    private String conn_user = "";
    private String conn_pass = "";

    public database() {
        connectToDatabase();
    }

    private void connectToDatabase() {
        try {
            Class.forName(driverLocation);
            conn = DriverManager.getConnection(conn_url, conn_user, conn_pass);
            System.out.println("Successfully Connected to the Database");
        }
        catch (Exception e) {
            System.out.println("Error Connecting to Database");
            e.printStackTrace();
        }
    }

    public void promptSelect() {
        boolean loop = true;
        menu m = new menu();
        database studentdb = new database();

        while (loop) {
            m.displayMenu();
            int x = getInt("Please Enter a Menu Option: ");

            switch (x) {
                case (0): //Quit
                    System.out.println(" ---- EXITING PROGRAM ----");
                    loop = false;
                    break;
                case (1): //Print All Students
                    studentdb.displayStudents();
                    break;
                case (2): //Add a Student
                    studentdb.createStudent();
                    break;
                case (3): //Update Student Major
                    studentdb.updateMajor();
                    break;
                case (4): //Update Student Faculty Advisor
                    studentdb.updateFacultyAdvisor();
                    break;
                case (5): //Update Student Major and Faculty Advisor
                    studentdb.updateStudent();
                    break;
                case (6): //Delete Student
                    studentdb.deleteStudent();
                    break;
                case (7): //Search Student by Major
                    studentdb.searchMajor();
                    break;
                case (8): //Search Student by GPA
                    studentdb.searchGPA();
                    break;
                case (9): //Search Student by Faculty Advisor
                    studentdb.searchFacultyAdvisor();
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }
    }

    private void displayStudents() {
        try {
            System.out.println(" ---- Displaying All Students ---- ");
            PreparedStatement p = conn.prepareStatement("SELECT * FROM student");
            ResultSet rs = p.executeQuery();
            displayResults(rs);
            rs.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void createStudent() {
        try {
            System.out.println(" ---- Creating New Student ---- ");

            //int id = getInt("Student ID: ");
            String firstName = getString("Enter First Name: ");
            String lastName = getString("Enter Last Name: ");
            float gpa = getFloat("Enter GPA: ");
            String major = getString("Enter Major: ");
            String FacultyAdvisor = getString("Enter Faculty Advisor: ");

            PreparedStatement p = conn.prepareStatement("INSERT INTO student" +
                    "(FirstName, LastName, GPA, Major, FacultyAdvisor) VALUES (?,?,?,?,?);");
            p.setString(1, firstName);
            p.setString(2, lastName);
            p.setFloat(3, gpa);
            p.setString(4, major);
            p.setString(5, FacultyAdvisor);
            p.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void updateMajor() {
        try {
            System.out.println(" ---- Updating Student Major ---- ");

            int id = getInt("Enter Student ID to Update Info: ");
            String newMajor = getString("Enter New Student Major: ");

            PreparedStatement p = conn.prepareStatement("UPDATE student SET Major = ? WHERE id = ?;");
            p.setString(1, newMajor);
            p.setInt(2, id);
            p.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void updateFacultyAdvisor() {
        try {
            System.out.println(" ---- Updating Student Faculty Advisor ---- ");

            int id = getInt("Enter Student ID to Update Info: ");
            String newFA = getString("Enter New Student Faculty Advisor: ");

            PreparedStatement p = conn.prepareStatement("UPDATE student SET FacultyAdvisor = ? WHERE id = ?;");
            p.setString(1, newFA);
            p.setInt(2, id);
            p.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void updateStudent() {
        try {
            System.out.println(" ---- Updating Student Major AND Faculty Advisor ---- ");

            int id = getInt("Enter Student ID to Update Info: ");
            String newMajor = getString("Enter New Student Major: ");
            String newFA = getString("Enter New Student Faculty Advisor: ");

            PreparedStatement p = conn.prepareStatement("UPDATE student SET Major = ?, FacultyAdvisor = ? WHERE id = ?;");
            p.setString(1, newMajor);
            p.setString(2, newFA);
            p.setInt(3, id);
            p.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteStudent() {
        try {
            System.out.println(" ---- Deleting Student ---- ");

            int id = getInt("Enter Student ID to Delete: ");
            PreparedStatement p = conn.prepareStatement("DELETE FROM student WHERE id = ?");
            p.setInt(1, id);
            p.executeUpdate();
        }
        catch(Exception e) {

        }
    }

    private void searchMajor() {
        try {
            System.out.println(" ---- Searching for Students by Major ---- ");

            String majorSearch = getString("Enter Major to Search: ");
            PreparedStatement p = conn.prepareStatement("SELECT * FROM Student WHERE Major = ? ");
            p.setString(1, majorSearch);
            ResultSet rs = p.executeQuery();
            displayResults(rs);
            rs.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void searchGPA() {
        try {
            System.out.println(" ---- Searching for Students by GPA ---- ");

            Float gpaSearch  = getFloat("Enter GPA to Search: ");
            PreparedStatement p = conn.prepareStatement("SELECT * FROM student WHERE GPA = ? ");
            p.setFloat(1, gpaSearch);
            ResultSet rs = p.executeQuery();
            displayResults(rs);
            rs.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void searchFacultyAdvisor() {
        try {
            System.out.println(" ---- Searching for Students by Faculty Advisor ---- ");

            String FASearch = getString("Enter Faculty Advisor to Search: ");
            PreparedStatement p = conn.prepareStatement("SELECT * FROM student WHERE FacultyAdvisor = ? ");
            p.setString(1, FASearch);
            ResultSet rs = p.executeQuery();
            displayResults(rs);
            rs.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void displayResults(ResultSet rs) {
        try {
            while(rs.next()) {
                int id = rs.getInt("id");
                String fName = rs.getString("FirstName");
                String lName = rs.getString("LastName");
                float gpa = rs.getFloat("GPA");
                String major = rs.getString("Major");
                String fAdvisor = rs.getString("FacultyAdvisor");

                System.out.print("ID: " + id + "\t\t" +
                        " First Name: " + fName +  "\t\t" +
                        " Last Name: " + lName +  "\t\t" +
                        " GPA: " + gpa +  "\t\t" +
                        " Major: " + major +  "\t\t" +
                        " Faculty Advisor: " + fAdvisor + "\n");
            }
            rs.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    private static String getString(String input) {
        System.out.print(input);
        Scanner temp = new Scanner(System.in);
        return temp.nextLine();
    }

    private static float getFloat(String input) {
        while (true) {
            String temp = getString(input);
            try {
                return Float.parseFloat(temp);
            }
            catch(Exception e) {
                System.out.println("Please Enter a Float");
            }
        }
    }

    private static int getInt(String input) {
        while (true) {
            String temp = getString(input);
            try {
                return Integer.parseInt(temp);
            }
            catch(Exception e) {
                System.out.println("Please Enter an Integer");
            }
        }
    }
}
