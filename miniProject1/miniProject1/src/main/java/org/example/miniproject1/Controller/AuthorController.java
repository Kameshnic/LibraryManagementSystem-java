package org.example.miniproject1.Controller;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.miniproject1.Exceptions.AuthorException.*;
import org.example.miniproject1.Exceptions.Credentials.*;
import org.example.miniproject1.Interfaces.AuthorControllerInterface;
import org.example.miniproject1.Model.Author;
import org.example.miniproject1.Model.Book;
import org.example.miniproject1.Model.Genre;
import org.example.miniproject1.Model.Student;
import org.example.miniproject1.Service.*;

import javax.swing.text.View;
import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AuthorController implements AuthorControllerInterface {
    private final AuthorService authorService;
    private final GenreService genreService;
    private Author loggedinAuthor;
    private InfoService infoService;
    Scanner scanner;

    StudentService studentService=new StudentService();
    private MailService<Student> mailService;

    public AuthorController() throws SQLException {
        this.authorService = new AuthorService();
        Scanner scanner =new Scanner(System.in);
        this.infoService = new InfoService();
        this.genreService = new GenreService();
        this.mailService=new MailService<>();
    }

    @Override
    public void displayMenu() {
        Scanner s = new Scanner(System.in);
        int choice;
        System.out.println("Welcome to E-library(Author):");
        System.out.println("1)Register");
        System.out.println("2)Login");
        System.out.println("3)Exit");
        try {
            System.out.println("Enter the choice:");
            choice = s.nextByte();
            switch (choice) {
                case 1:
                    registerAuthor();
                    break;
                case 2:
                    Login();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    displayMenu();
            }
        } catch (InputMismatchException | SQLException E) {
            System.out.println(E);
        }
    }

    @Override
    public void Login() throws SQLException {
        Scanner s = new Scanner(System.in);
        System.out.print("Username: ");
        String username = s.next();
        System.out.print("Password: ");
        String password = s.next();
        if (authorService.login(username, password)) {
            System.out.println("Login successful!");
            loggedinAuthor = authorService.getAuthorByUsername(username);
            authorOperations();
        } else {
            System.out.println("Invalid credentials. Please try again.");
            displayMenu();
        }
    }

    public void registerAuthor() {
        Scanner scanner = new Scanner(System.in);
        CredentialService credentialService = new CredentialService();
        try {
            System.out.println("Registering a new Author");

            System.out.print("First Name: ");
            String firstName = scanner.nextLine();
            credentialService.validateName(firstName);

            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();
            credentialService.validateName(lastName);

            System.out.print("Pen Name: ");
            String penName = scanner.nextLine();
            credentialService.validateName(penName);

            System.out.print("Biography: ");
            String biography = scanner.nextLine();

            System.out.print("Age: ");
            int age = scanner.nextInt();
            credentialService.validateAge(age);

            System.out.print("DOB (YYYY-MM-DD): ");
            String dob = scanner.next();
            LocalDate dateOfBirth;
            try {
                dateOfBirth = credentialService.validateDateOfBirth(dob);
            } catch (InvalidDateException e) {
                System.out.println(e.getMessage());
                return;
            }

            scanner.nextLine();

            System.out.print("Gender: ");
            String gender = scanner.nextLine();

            System.out.print("Email ID: ");
            String emailId = scanner.nextLine();
            credentialService.validateEmail(emailId);

            System.out.print("Mobile Number: ");
            long mobile;
            try {
                mobile = scanner.nextLong();
                credentialService.validateMobileNumber(mobile);
            } catch (InputMismatchException e) {
                System.out.println("Invalid mobile number. Please enter only digits.");
                scanner.nextLine();
                return;
            }

            scanner.nextLine();

            System.out.print("Username: ");
            String userName = scanner.nextLine();
            try {
                credentialService.validateUniqueUsername(userName, authorService);
            } catch (UsernameAlreadyExistsException e) {
                System.out.println(e.getMessage());
                return;
            }

            System.out.print("Password: ");
            String password = scanner.nextLine();
            credentialService.validatePassword(password);

            System.out.print("Enter Author Card Number: ");
            String authorCardNumber = scanner.nextLine();
            credentialService.validateAuthorCardNumber(authorCardNumber);

            System.out.print("Location: ");
            String location = scanner.nextLine();

            Author author = new Author(firstName, lastName, userName, password, age, dateOfBirth, gender, emailId, mobile, penName, biography, location);
            if (authorService.registerAuthor(author)) {
                System.out.println("Registration for the author is successful! You can now log in.");
            } else {
                System.out.println("Registration failed. Please try again.");
            }

        } catch (InvalidNameException | InvalidAgeException | InvalidEmailException |
                 InvalidMobileNumberException | InvalidPasswordException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred during registration: " + e.getMessage());
        } finally {
            displayMenu();
        }
    }
    void showAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
            if (students.isEmpty()) {
                System.out.println("No students found.");

            } else {
                System.out.println("List of Students:");
                System.out.println("----------------------------------------------------------");
                for (Student student : students) {
                    System.out.println("Name: " + student.getFirstName() + " " + student.getLastName());
                    System.out.println("Username: " + student.getUsername());
                    System.out.println("Email: " + student.getEmailId());
                    System.out.println("Mobile: " + student.getMobile());
                    System.out.println("Age: " + student.getAge());
                    System.out.println("Date of Birth: " + student.getDob());
                    System.out.println("Location: " + student.getLocation());
                    System.out.println("Points: " + student.getPoints());
                    System.out.println("----------------------------------------------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving students: " + e.getMessage());
        }
    }

    @Override
    public void authorOperations(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome Author! Select an operation:");
        System.out.println("1. View Books");
        System.out.println("2. publish  Books");
        System.out.println("3. View profile");
        System.out.println("4. View Published Book");
        System.out.println("5. View Likes for published books");
        System.out.println("6. Research Paper");
        System.out.println("7. LeaderBoard");
        System.out.println("8. View Authors");
        System.out.println("9. Send Mail to Students");
        System.out.println("10.exit");
        try {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                   viewBooks();
                    break;
                case 2:
                    publishBook();
                    break;
                case 3:
                    viewProfile();
                    break;
                case 4:
                    viewBooks(loggedinAuthor.getFirstName());
                    break;
                case 5:
                    viewLikes(loggedinAuthor.getFirstName());
                    break;
                case 6:
                    showResearchPage();
                    break;
                case 7:
                    leaderBoard();
                    break;
                case 8:
                    showAuthors();
                    break;
                case 9:
                    sendMailToStudent();
                    break;
                case 10:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    authorOperations();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
            authorOperations();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void leaderBoard(){
        authorService.showLeaderBoard();
        authorOperations();
    }
    private void viewLikes(String username) throws SQLException {
        List<Book>likeCount=authorService.getBookLikesByAuthor(username);
        for(Book book:likeCount){
            System.out.println("Title:"+book.getTitle());
            System.out.println("Likes:"+book.getLikes());
            System.out.println("----------------------");
        }
        authorOperations();
    }

    public void viewBooks(String username) throws SQLException {
        List<Book>getPublisherBooksByLoggedInAuthor=authorService.getBooksByLoggedInAuthor(username);
        for(Book book:getPublisherBooksByLoggedInAuthor){
            System.out.println("Book Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Is Premium: " + book.getIsPremium());
            System.out.println("Domain: " + book.getDomain());
            System.out.println("Short Summary: " + book.getShortSummary());
            System.out.println("Long Summary: " + book.getLongSummary());
            System.out.println("Points Required: " + book.getPoints());
            System.out.println("File Path: " + book.getFilePath());
            System.out.println("Copies Available: " + book.getCopies());
            System.out.println("Genre ID: " + book.getGenreId());
            System.out.println("--------------------------------------");
        }
        authorOperations();
    }

    public void viewBooks() throws SQLException{
        List<Book> books=studentService.getAllBooks();
        if (books == null || books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("Available Books:");
            for (Book book : books) {
                System.out.println("Book ID"+book.getBookId());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Domain: " + book.getDomain());
                System.out.println("Points Required: " + book.getPointsRequired());
                System.out.println("Short Summary: " + book.getShortSummary());
                System.out.println("Long Summary: " + book.getLongSummary());
                System.out.println("Book Copies"+book.getCopies());
                System.out.println("---------------------");
            }
        }
        authorOperations();

    }
    public void showAuthors() {
        try {
            List<Author> authors = authorService.getAllAuthors();
            if (authors.isEmpty()) {
                System.out.println("No authors found.");
                displayMenu();
            } else {
                System.out.println("----------------------------------------------------------");
                System.out.println("List of Authors:");
                for (Author author : authors) {
                    System.out.println("Name: " + author.getFirstName() + " " + author.getLastName());
                    System.out.println("Username: " + author.getUsername());
                    System.out.println("Email: " + author.getEmailId());
                    System.out.println("Pen Name: " + author.getPenName());
                    System.out.println("Biography: " + author.getBiography());
                    System.out.println("Age: " + author.getAge());
                    System.out.println("Location: " + author.getLocation());
                    System.out.println("----------------------------------------------------------");
                }
                authorOperations();
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving authors: " + e.getMessage());
        }
    }

    public void showResearchPage() {
        researchPage();
    }

    private void researchPage() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("---- Research and News Menu ----");
            System.out.println("1. Explore Research Papers");
            System.out.println("2. Save Research Papers to File");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter domain (e.g., 'machine learning', 'AI'): ");
                    String domain = scanner.nextLine().replace(" ", "+");
                    String response = infoService.fetchResearchPapers(domain);
                    infoService.printResearchPapers(response);
                }

                case 2 -> {
                    System.out.print("Enter domain to save research papers: ");
                    String domain = scanner.nextLine().replace(" ", "+");
                    String response = infoService.fetchResearchPapers(domain);
                    saveToFile(response, "D:\\ResearchPapers_" + domain + ".txt");
                }
                case 3-> authorOperations();
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
        scanner.close();
    }
    private void saveToFile(String content, String fileName) {
        try (FileWriter writer = new FileWriter(new File(fileName))) {
            writer.write(content);
            System.out.println("File saved as " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    private void viewProfile() {
        System.out.println("Welcome to the Profile Page Author");
        System.out.println("I THINK YOU ARE DOING GOOD ");
        System.out.println("WELCOME");
        System.out.println("-------------------------------------------------");
        System.out.println("Name: " + loggedinAuthor.getFirstName() + " " + loggedinAuthor.getLastName());
        System.out.println("Username: " + loggedinAuthor.getUsername());
        System.out.println("Email: " + loggedinAuthor.getEmailId());
        System.out.println("Pen Name: " + loggedinAuthor.getPenName());
        System.out.println("Biography: " + loggedinAuthor.getBiography());
        System.out.println("Age: " + loggedinAuthor.getAge());
        System.out.println("Location: " + loggedinAuthor.getLocation());
        System.out.println("-------------------------------------------------");
        authorOperations();
    }


    public void displayGenres() throws SQLException {
        List<Genre> genres = genreService.getAllGenres();
        System.out.println("Available Genres:");
        for (Genre genre : genres) {
            System.out.println(genre.getId() + ". " + genre.getName());
        }
    }


    public void publishBook() throws SQLException {
         Scanner scanner=new Scanner(System.in);
        System.out.print("Enter the book title: ");
        String title = scanner.nextLine();

        System.out.print("Is this a premium book? (true/false): ");
        int isPremium = scanner.nextInt();

        scanner.nextLine();
        System.out.print("Enter a short summary of the book: ");
        String shortSummary = scanner.nextLine();

        System.out.print("Enter a long summary of the book: ");
        String longSummary = scanner.nextLine();

        System.out.print("Enter the points required to unlock this book: ");
        int pointsRequired = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the file path for the book: ");
        String filePath = scanner.nextLine();
        System.out.print("Enter the number of copies available: ");
        int copies = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Choose a genre:");
        displayGenres();
        System.out.print("Enter the genre ID: ");
        int genreId = scanner.nextInt();
        scanner.nextLine();
        LocalDate today = LocalDate.now();
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        Book newBook = new Book(title, loggedinAuthor.getFirstName(), isPremium, genreId, shortSummary, longSummary, pointsRequired, filePath, copies,today);
        authorService.publishBook(newBook,content);
        System.out.println("Book '" + title + "' published under genre ID: " + genreId + " with " + pointsRequired + " points.");
        authorOperations();
    }



    public Author getAuthorObject(String authorName) {
        try {
            List<Author> authors = authorService.getAllAuthors();
            if (authors.isEmpty()) {
                System.out.println("No authors found.");
            } else {
                for (Author author : authors) {
                    if (author.getUsername().equalsIgnoreCase(authorName)) {
                        return author;
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving authors: " + e.getMessage());
        }

        return null;

    }


    public void sendMailToStudent() throws SQLException {
        StudentController studentController=new StudentController();
        Scanner scanner = new Scanner(System.in);
        showAllStudents();
        System.out.print("Enter student's name: ");
        String studentName=scanner.nextLine();
        Student student=studentController.getStudentObject(studentName);
        System.out.print("Enter subject: ");
        String subject = scanner.nextLine();
        System.out.print("Enter your message: ");
        String message = scanner.nextLine();
        if(student!=null)
        {
            mailService.sendEmail(student, subject, message);
        }
        else {
            System.out.println("no email availabel for tha student");
        }
        authorOperations();
    }
}
