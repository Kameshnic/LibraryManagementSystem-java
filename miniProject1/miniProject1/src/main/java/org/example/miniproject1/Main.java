package org.example.miniproject1;

import org.example.miniproject1.Controller.AdminController;
import org.example.miniproject1.Controller.AuthorController;
import org.example.miniproject1.Controller.StudentController;
import org.example.miniproject1.Util.DBConnection;

import java.util.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the System!");
        System.out.println("Please choose your role:");
        System.out.println("1. Admin");
        System.out.println("2. Author");
        System.out.println("3. Student");
        System.out.print("Enter your choice (1 or 2 or 3): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        DBConnection connection=new DBConnection();

        switch (choice) {
            case 1:
                AdminController adminController= new AdminController(connection.getConnection());
                adminController.displayOptions();
                break;
            case 2:
                AuthorController authorController = new AuthorController();
                authorController.displayMenu();
                break;
            case 3:
                StudentController studentController=new StudentController();
                studentController.displayMenu();
            default:
                System.out.println("Invalid choice. Please select either 1 or 2.");
        }

    }
    }

