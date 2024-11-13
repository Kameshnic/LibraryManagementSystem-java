package org.example.miniproject1.Controller;
import org.example.miniproject1.Exceptions.Credentials.*;
import org.example.miniproject1.Interfaces.AuthorControllerInterface;
import org.example.miniproject1.Interfaces.StudentControllerInterface;
import org.example.miniproject1.Model.*;
import org.example.miniproject1.Service.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AdminController {
    private AdminService adminService;
    Scanner scanner;
    public AdminController(Connection connection) throws SQLException {
        scanner=new Scanner(System.in);
        this.adminService = new AdminService();
    }

    public void createStudent() {
        Student student = null;
        boolean isValid = false;
        CredentialService credentialService=new CredentialService();
        String firstName="";
        do{
            try {
                System.out.println("Enter First Name");
                scanner.nextLine();
                firstName = scanner.nextLine();
                credentialService.validateName(firstName);
                isValid=true;
            }
            catch (InvalidNameException e) {
                    System.out.println("Error: " + e.getMessage());
                    isValid = false;
                }
            }while(!isValid);
        String lastName = "";
        do {
            try {
                System.out.print("Last Name: ");
                lastName = scanner.nextLine();
                credentialService.validateName(lastName);
                isValid = true;
            } catch (InvalidNameException e) {
                System.out.println("Error: " + e.getMessage());
                isValid = false;
            }
        } while (!isValid);
        int age = 0;
        do {
            try {
                System.out.print("Age: ");
                age = scanner.nextInt();
                credentialService.validateAge(age);
                isValid = true;
            } catch (InvalidAgeException e) {
                System.out.println("Error: " + e.getMessage());
                isValid = false;
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                isValid = false;
            }
        } while (!isValid);


        LocalDate dateOfBirth = null;
        do {
            try {
                System.out.print("DOB (YYYY-MM-DD): ");
                String dob = scanner.next();
                dateOfBirth = credentialService.validateDateOfBirth(dob);
                isValid = true;
            } catch (InvalidDateException e) {
                System.out.println("Error: " + e.getMessage());
                isValid = false;
            }
        } while (!isValid);


        System.out.print("Gender: ");
        String gender = scanner.next();


        String emailId = "";
        do {
            try {
                System.out.print("Email ID: ");
                emailId = scanner.next();
                credentialService.validateEmail(emailId);
                isValid = true;
            } catch (InvalidEmailException e) {
                System.out.println("Error: " + e.getMessage());
                isValid = false;
            }
        } while (!isValid);
        long mobile = 0;
        do {
            try {
                System.out.print("Mobile Number: ");
                mobile = scanner.nextLong();
                credentialService.validateMobileNumber(mobile);
                isValid = true;
            } catch (InvalidMobileNumberException e) {
                System.out.println("Error: " + e.getMessage());
                isValid = false;
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                isValid = false;
            }
        } while (!isValid);


        System.out.print("Username: ");
        String username = scanner.next();


        String password = "";
        do {
            try {
                System.out.print("Password: ");
                password = scanner.next();
                credentialService.validatePassword(password);
                isValid = true;
            } catch (InvalidPasswordException e) {
                System.out.println("Error: " + e.getMessage());
                isValid = false;
            }
        } while (!isValid);


        scanner.nextLine();
        System.out.print("Enter the location: ");
        String location = scanner.nextLine();

        int points = 0;
        student = new Student(firstName, lastName, age, dateOfBirth, gender, emailId, mobile, username, password, points, location);

        try {
             if(adminService.addStudent(student)) {
                 System.out.println("Added Successfully");
             }
             else{
                 System.out.println("Error");
             }
             displayOptions();
        } catch (SQLException e) {
            System.out.println("Error creating student: " + e.getMessage());
            displayOptions();
        }
    }

    public void displayOptions() {
        System.out.println("\n--- Admin Options ---");
        System.out.println("1. Create Student");
        System.out.println("2. Delete Student");
        System.out.println("3. Create Author");
        System.out.println("4. Delete Author");
        System.out.println("5. Create Book");
        System.out.println("6. Delete Book");
        System.out.println("7. Create Quiz");
        System.out.println("8. Delete Quiz");
        System.out.println("9. Exit");
        System.out.print("Enter your choice: ");
        try {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createStudent();
                    break;
                case 2:
                    deleteStudent();
                    break;
                case 3:
                    createAuthor();
                    break;
                case 4:
                    deleteAuthor();
                    break;
                case 5:
                    createBook();
                    break;
                case 6:
                    deleteBook();
                    break;
                case 7:
                    createQuiz();
                    break;
                case 8:
                    deleteQuiz();
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    displayOptions();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            displayOptions();
        }
    }


    public void deleteStudent() {
        System.out.println("Enter the username of the student to be deleted");
        scanner.nextLine();
        String username=scanner.nextLine();
        try {
            if(adminService.deleteStudent(username)){
                System.out.println("Deleted Successfully");
                displayOptions();
            }
            else{
                System.out.println("Invalid Username");
                displayOptions();
            }
        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
            displayOptions();
        }
    }



    public void createAuthor() {
        Author author = null;
        CredentialService credentialService = new CredentialService();
        boolean isValid;

        System.out.println("Registering a new author");
        String firstName = "";
        do {
            try {
                System.out.print("First Name: ");
                scanner.nextLine();
                firstName = scanner.nextLine();
                credentialService.validateName(firstName);
                isValid = true;
            } catch (InvalidNameException e) {
                System.out.println("Error: " + e.getMessage());
                isValid = false;
            }
        } while (!isValid);

        String lastName = "";
        do {
            try {
                System.out.print("Last Name: ");
                lastName = scanner.nextLine();
                credentialService.validateName(lastName);
                isValid = true;
            } catch (InvalidNameException e) {
                System.out.println("Error: " + e.getMessage());
                isValid = false;
            }
        } while (!isValid);

        int age = 0;
        do {
            try {
                System.out.print("Age: ");
                age = scanner.nextInt();
                credentialService.validateAge(age);
                isValid = true;
            } catch (InvalidAgeException e) {
                System.out.println("Error: " + e.getMessage());
                isValid = false;
            }
        } while (!isValid);

        LocalDate dateOfBirth = null;
        do {
            try {
                System.out.print("DOB (YYYY-MM-DD): ");
                String dob = scanner.next();
                dateOfBirth = credentialService.validateDateOfBirth(dob);
                isValid = true;
            } catch (InvalidDateException e) {
                System.out.println("Error: " + e.getMessage());
                isValid = false;
            }
        } while (!isValid);

        System.out.print("Gender: ");
        String gender = scanner.next();
        String emailId = "";
        do {
            try {
                System.out.print("Email ID: ");
                emailId = scanner.next();
                credentialService.validateEmail(emailId);
                isValid = true;
            } catch (InvalidEmailException e) {
                System.out.println("Error: " + e.getMessage());
                isValid = false;
            }
        } while (!isValid);

        long mobileNumber = 0;
        do {
            try {
                System.out.print("Mobile Number: ");
                mobileNumber = scanner.nextLong();
                credentialService.validateMobileNumber(mobileNumber);
                isValid = true;
            } catch (InvalidMobileNumberException e) {
                System.out.println("Error: " + e.getMessage());
                isValid = false;
            }
        } while (!isValid);

        scanner.nextLine();
        System.out.print("Pen Name: ");
        String penName = scanner.nextLine();

        System.out.print("Biography: ");
        String biography = scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.next();

        String password = "";
        do {
            try {
                System.out.print("Password: ");
                password = scanner.next();
                credentialService.validatePassword(password);
                isValid = true;
            } catch (InvalidPasswordException e) {
                System.out.println("Error: " + e.getMessage());
                isValid = false;
            }
        } while (!isValid);

        scanner.nextLine();
        System.out.print("Location: ");
        String location = scanner.nextLine();


        author = new Author(firstName, lastName, username, password, age, dateOfBirth, gender, emailId, mobileNumber, penName, biography, location);

        try {
            if(adminService.addAuthor(author)) {
                System.out.println("Added Successfully");
            }
            else{
                System.out.println("Error");
            }
            displayOptions();
        } catch (SQLException e) {
            System.out.println("Error creating author: " + e.getMessage());
            displayOptions();
        }
    }


    public void deleteAuthor() {
        System.out.println("Enter the username of the author to be deleted");
        scanner.nextLine();
        String userName=scanner.nextLine();
        try {
            if(adminService.deleteAuthor(userName)){
                System.out.println("Deleted Successfully");
                displayOptions();
            }
            else{
                System.out.println("Invalid Username");
                displayOptions();
            }
        } catch (SQLException e) {
            System.out.println("Error deleting author: " + e.getMessage());
            displayOptions();
        }
    }



    public void createBook() {
        Scanner scanner = new Scanner(System.in);
        Book book = null;
        try {

            System.out.println("Enter book details to create a new book:");
            System.out.print("Title: ");
            String title = scanner.nextLine();
            System.out.print("Author: ");
            String author = scanner.nextLine();

            System.out.print("Is Premium (1 for Yes, 0 for No): ");
            int isPremium = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Domain: ");
            String domain = scanner.nextLine();

            System.out.print("Short Summary: ");
            String shortSummary = scanner.nextLine();

            System.out.print("Long Summary: ");
            String longSummary = scanner.nextLine();

            System.out.print("Points Required: ");
            int pointsRequired = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            System.out.print("File Path: ");
            String filePath = scanner.nextLine();

            System.out.print("Copies: ");
            int copies = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            System.out.print("Genre ID: ");
            int genreId = scanner.nextInt();

            System.out.print("Likes: ");
            int likes = scanner.nextInt();
            scanner.nextLine();
            book = new Book(title, author, isPremium, domain, shortSummary, longSummary, pointsRequired, filePath, copies, genreId, likes);
             if(adminService.addBook(book)){
                 System.out.println("Book Added Successfully");
                 displayOptions();
             }
             else{
                 System.out.println("Error");
                 displayOptions();
             }
        } catch (SQLException e) {
            System.out.println("Error creating book: " + e.getMessage());

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data type.");
           displayOptions();
        }
    }

    public void deleteBook() {
        System.out.println("Enter the Title of the book");
        scanner.nextLine();
        String title=scanner.nextLine();
        try {
             if(adminService.deleteBook(title)){
                 System.out.println("Deleted Book");
                 displayOptions();
             }
             else{
                 System.out.println("Invalid Book Title");
                 displayOptions();
             }
        } catch (SQLException e) {
            System.out.println("Error deleting book: " + e.getMessage());

        }
    }
    public void createQuiz() {
        System.out.print("Enter correct answer: ");
        scanner.nextLine();
        String correctAnswer = scanner.nextLine();
        System.out.println("Correct Answer: " + correctAnswer);
        System.out.print("Enter Option A: ");
        String optionA = scanner.nextLine();

        System.out.print("Enter Option B: ");
        String optionB = scanner.nextLine();

        System.out.print("Enter Option C: ");
        String optionC = scanner.nextLine();

        System.out.print("Enter Option D: ");
        String optionD = scanner.nextLine();

        System.out.print("Enter question text: ");
        String questionText = scanner.nextLine();

        Quiz quiz = new Quiz(correctAnswer, optionA, optionB, optionC, optionD, questionText);


        try {
             if(adminService.addQuiz(quiz)){
                 System.out.println("Quiz added");
                 displayOptions();
             }
             else{
                 System.out.println("Error");
                 displayOptions();
             }
        } catch (SQLException e) {
            System.out.println("Error creating quiz: " + e.getMessage());
          displayOptions();
        }
    }

    public void deleteQuiz() {
        System.out.println("Enter the quiz Id");
        int quizId=scanner.nextInt();
        try {
            if(adminService.deleteQuiz(quizId)){
                System.out.println("Deleted Successfully");
                displayOptions();
            }
            else{
                System.out.println("Error");
                displayOptions();
            }
        } catch (SQLException e) {
            System.out.println("Error deleting quiz: " + e.getMessage());
         displayOptions();
        }
    }



}
