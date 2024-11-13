package org.example.miniproject1.Controller;
import org.example.miniproject1.Exceptions.Credentials.*;
import org.example.miniproject1.Interfaces.StudentControllerInterface;
import org.example.miniproject1.Model.*;
import org.example.miniproject1.Service.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
public class StudentController implements StudentControllerInterface {
    private final StudentService studentService;
    private Student loggedinStudent;
    Scanner scanner=new Scanner(System.in);
    private MailService<Author> mailService;
    LocationService locationService;
    public StudentController() throws SQLException {
        this.studentService = new StudentService();
        this.mailService=new MailService<>();
        locationService=new LocationService();

    }
    public void displayMenu() {

        int choice;
        System.out.println("Welcome to E-library(Student):");
        System.out.println("1)Register");
        System.out.println("2)Login");
        System.out.println("3)Exit");
        try {
            System.out.println("Enter the choice:");
            choice = scanner.nextByte();
            switch (choice) {
                case 1:
                    registerStudent();
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
    public void Login() throws SQLException {

        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();

        if (studentService.login(username, password))
        {
            System.out.println("Login successful!");
            loggedinStudent = studentService.getStudentByUsername(username);
            studentOperations();
        } else
        {
            System.out.println("Invalid credentials. Please try again.");
            displayMenu();
        }
    }
    private final CredentialService credentialService = new CredentialService();

    public void registerStudent() throws SQLException {
        Student student = null;
        boolean isValid = false;
        CredentialService credentialService=new CredentialService();
        String firstName="";
        do{
            try {
                scanner.nextLine();
                System.out.println("Enter First Name");
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
        if (studentService.registerStudent(student)) {
                System.out.println("Registration successful! You can now log in.");
            }
        else{
            System.out.println("Registration Failed!");
        }
        displayMenu();

    }
    private void showNearestAuthors() throws SQLException {
        String location= loggedinStudent.getLocation();
        double[] latLong = locationService.getLocation(location);
        Location closestAuthor=locationService.findClosestAuthor(latLong[0],latLong[1]);
        try {
            Location closestAuthor1 = locationService.findClosestAuthor(latLong[0], latLong[1]);
            if (closestAuthor != null) {
                System.out.println("Closest Author is: " + closestAuthor1.getName() +
                        " at " + closestAuthor1.getLocation());
                studentOperations();
            } else {
                System.out.println("No Authors found.");
                studentOperations();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showNearestStudents() throws SQLException {
        String location = loggedinStudent.getLocation();
        double[] latLong = locationService.getLocation(location);
        try {
            Location closestStudent = locationService.findClosestStudent(latLong[0], latLong[1],loggedinStudent.getUsername());
            if (closestStudent != null) {
                System.out.println("Closest Student is: " + closestStudent.getName() +
                        " at " + closestStudent.getLocation());
                studentOperations();
            } else {
                System.out.println("No students found.");
                studentOperations();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void studentOperations() {
        Scanner scanner = new Scanner(System.in);
        boolean isLoggedOut = false;
        while (!isLoggedOut)
        {
            System.out.println("Welcome Student! Select an operation:");
            System.out.println("1. View Books");
            System.out.println("2. Add Review");
            System.out.println("3. Like a Book");
            System.out.println("4. Get Reviews for a Book");
            System.out.println("5. Search for a Book");
            System.out.println("6. Take a Quiz");
            System.out.println("7.show all friends");
            System.out.println("8.show nearest author");
            System.out.println("9.show nearest friends");
            System.out.println("10.Buy Books");
            System.out.println("11.LeaderBoard");
            System.out.println("12.Borrow Books");
            System.out.println("13.Calculate Fine");
            System.out.println("14.Generate Report");
            System.out.println("15.Return Book");
            System.out.println("16.Send Mail to Author");
            System.out.println("17.logout");

            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        viewBooks();
                        break;
                    case 2:
                        addReview();
                        break;
                    case 3:
                        likeBook();
                        break;
                    case 4:
                        getReview();
                        break;
                    case 5:
                        searchBook();
                        break;
                    case 6:
                        quiz();
                        break;
                    case 7:
                        showAllStudents();
                        break;
                    case 8:
                        showNearestAuthors();
                        break;
                    case 9:
                        showNearestStudents();
                        break;
                    case 10:
                        buyBooks();
                        break;
                    case 11:
                        leaderBoard();
                        break;
                    case 12:
                        borrowBooks();
                        break;
                    case 13:
                        calcFine();
                        break;
                    case 14:
                        generateReport();
                        break;
                    case 15:
                        returnBook();
                        break;
                    case 16:
                        sendMailToAuthor();
                        break;
                    case 17:
                        isLoggedOut = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        studentOperations();
                }
            } catch (InputMismatchException | SQLException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                studentOperations();
            }

        }
        displayMenu();
    }

    private void returnBook() {
        scanner.nextLine();
        System.out.println("Enter the title of the book to be returned");
        String title=scanner.nextLine();
        studentService.returnBook(title,loggedinStudent.getUsername());
        studentOperations();

    }

    private void generateReport() {
        studentService.generateReport(loggedinStudent.getUsername());
        studentOperations();
    }

    private void calcFine(){
        studentService.calculateFine(loggedinStudent.getUsername());
        studentOperations();
    }
    private void borrowBooks() {
        scanner.nextLine();
        System.out.println("Enter the title of the book:");
        String title=scanner.nextLine();
        Boolean isBorrowed=studentService.borrowBooks(title,loggedinStudent.getUsername());
        studentOperations();
    }

    private void buyBooks() throws SQLException {
        scanner.nextLine();
        System.out.println("Enter the book title");
        String title = scanner.nextLine();
        boolean isBought = studentService.buyBooks(loggedinStudent.getUsername(), title);
        if (isBought) {
            System.out.println("Bought Books");
        }
        else{
            System.out.println("Insufficient points");
        }
        studentOperations();
    }
    private void  leaderBoard() throws SQLException {
        System.out.println("LeaderBoard:");
        List<Student> topStudents = studentService.getLeaderboard(5);
        for (Student student : topStudents) {
            System.out.println(student.getUsername() + " - " + student.getPoints() + " points");
        }
        studentOperations();

    }
    void showAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
            if (students.isEmpty()) {
                System.out.println("No students found.");
                studentOperations();
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
                studentOperations();
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving students: " + e.getMessage());
        }
    }


    public void quiz() throws SQLException {
        System.out.println("Select Your Preferred Domain:");
        System.out.println("1)Machine Learning");
        System.out.println("2)Algebra");
        System.out.println("3)Calculus");
        System.out.println("4)Physics");
        System.out.println("5)Chemistry");

        try{
            int choice=scanner.nextByte();
            switch (choice){
                case 1:
                    MachineLearningQuiz();
                    break;
                case 2:
                    Algebra();
                    break;
                case 3:
                    Calculus();
                    break;
                case 4:
                    Physics();
                    break;
                case 5:
                    Chemistry();
                    break;
                default:
                    System.out.println("Invalid Choice:");
                    studentOperations();
            }
        }
        catch (SQLException E){
            System.out.println(E);
        }
    }

    private void Chemistry() throws SQLException {
        List<Quiz>listOfQuiz=studentService.chemistry();
        int count=1;
        int points=0;
        String option;
        Scanner s=new Scanner(System.in);
        for(Quiz list:listOfQuiz){
            System.out.println(count+")"+list.getQuestion_text());
            System.out.println("A) "+list.getOptionA());
            System.out.println("B) "+list.getOptionB());
            System.out.println("C) "+list.getOptionC());
            System.out.println("D) "+list.getOptionD());
            System.out.println("Enter Your Option:");
            option= String.valueOf(s.nextLine().charAt(0));
            if(option.equalsIgnoreCase(list.getCorrect_answer())){
                System.out.println("Yeah,You Got it Right");
                points+=10;
            }
            else{
                System.out.println("Oops,Wrong Answer.The Correct Option is "+list.getCorrect_answer());
            }
            count++;
        }
        if(points!=0){
            studentService.updatePoints(loggedinStudent.getUsername(),points);
        }
        System.out.println("Total Points Acquired:"+points);
        studentOperations();
    }


    public void Physics() throws SQLException {
        List<Quiz>listOfQuiz=studentService.physics();
        int count=1;
        int points=0;
        String option;
        Scanner s=new Scanner(System.in);

        for(Quiz list:listOfQuiz){
            System.out.println(count+")"+list.getQuestion_text());
            System.out.println("A) "+list.getOptionA());
            System.out.println("B) "+list.getOptionB());
            System.out.println("C) "+list.getOptionC());
            System.out.println("D) "+list.getOptionD());
            System.out.println("Enter Your Option:");
            option= String.valueOf(s.nextLine().charAt(0));
            if(option.equalsIgnoreCase(list.getCorrect_answer())){
                System.out.println("Yeah,You Got it Right");
                points+=10;
            }
            else{
                System.out.println("Oops,Wrong Answer.The Correct Option is "+list.getCorrect_answer());
            }
            count++;
        }
        if(points!=0){
            studentService.updatePoints(loggedinStudent.getUsername(),points);
        }
        System.out.println("Total Points Acquired:"+points);
        studentOperations();
    }
    public void Calculus() throws SQLException {
        List<Quiz>listOfQuiz=studentService.Calculus();
        int count=1;
        int points=0;
        String option;
        Scanner s=new Scanner(System.in);

        for(Quiz list:listOfQuiz){
            System.out.println(count+")"+list.getQuestion_text());
            System.out.println("A) "+list.getOptionA());
            System.out.println("B) "+list.getOptionB());
            System.out.println("C) "+list.getOptionC());
            System.out.println("D) "+list.getOptionD());
            System.out.println("Enter Your Option:");
            option= String.valueOf(s.nextLine().charAt(0));
            if(option.equalsIgnoreCase(list.getCorrect_answer())){
                System.out.println("Yeah,You Got it Right");
                points+=10;
            }
            else{
                System.out.println("Oops,Wrong Answer.The Correct Option is "+list.getCorrect_answer());
            }
            count++;
        }
        if(points!=0){
            studentService.updatePoints(loggedinStudent.getUsername(),points);
        }
        System.out.println("Total Points Acquired:"+points);
        studentOperations();
    }
    public void Algebra() throws SQLException {
        List<Quiz>listOfQuiz=studentService.Algebra();
        int count=1;
        int points=0;
        String option;

        for(Quiz list:listOfQuiz){
            System.out.println(count+")"+list.getQuestion_text());
            System.out.println("A) "+list.getOptionA());
            System.out.println("B) "+list.getOptionB());
            System.out.println("C) "+list.getOptionC());
            System.out.println("D) "+list.getOptionD());
            System.out.println("Enter Your Option:");
            option= String.valueOf(scanner.nextLine().charAt(0));
            if(option.equalsIgnoreCase(list.getCorrect_answer())){
                System.out.println("Yeah,You Got it Right");
                points+=10;
            }
            else{
                System.out.println("Oops,Wrong Answer.The Correct Option is "+list.getCorrect_answer());
            }
            count++;
        }
        if(points!=0){
            studentService.updatePoints(loggedinStudent.getUsername(),points);
        }
        System.out.println("Total Points Acquired:"+points);
        studentOperations();
    }
    public void MachineLearningQuiz() throws SQLException {
        List<Quiz>listOfQuiz=studentService.MachineLearningQuiz();
        int count=1;
        int points=0;
        String option;
        Scanner s=new Scanner(System.in);

        for(Quiz list:listOfQuiz){
            System.out.println(count+")"+list.getQuestion_text());
            System.out.println("A) "+list.getOptionA());
            System.out.println("B) "+list.getOptionB());
            System.out.println("C) "+list.getOptionC());
            System.out.println("D) "+list.getOptionD());
            System.out.println("Enter Your Option:");
            option= String.valueOf(s.nextLine().charAt(0));
            if(option.equalsIgnoreCase(list.getCorrect_answer())){
                System.out.println("Yeah,You Got it Right");
                points+=10;
            }
            else{
                System.out.println("Oops,Wrong Answer.The Correct Option is "+list.getCorrect_answer());
            }
            count++;
        }
        if(points!=0){
            studentService.updatePoints(loggedinStudent.getUsername(),points);
        }
        System.out.println("Total Points Acquired:"+points);
        studentOperations();
    }
    public void searchBook() {
        Scanner scanner=new Scanner(System.in);
        String title;
        boolean found=false;
        Book book = null;
        System.out.println("Enter the book title:");
        title=scanner.nextLine();
        List<Book> books=studentService.getAllBooks();
        for(Book booki:books){
            if(booki.getTitle().equalsIgnoreCase(title)){
                found=true;
                book=booki;
            }
        }
        if(found){
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Domain: " + book.getDomain());
            System.out.println("Points Required: " + book.getPointsRequired());
            System.out.println("Short Summary: " + book.getShortSummary());
            System.out.println("Long Summary: " + book.getLongSummary());
            studentOperations();
        }
        else{
            System.out.println("Book Not found");
            studentOperations();
        }
    }

    public void getReview() {
        String title;
        scanner.nextLine();
        System.out.println("Enter the title of the book:");
        title=scanner.nextLine();
        boolean found=false;
        List<Book> books=studentService.getAllBooks();
        for(Book book:books){
            if(book.getTitle().equalsIgnoreCase(title)){
                found=true;
            }
        }
        if(found){
            List<Review>reviews=studentService.getReviews(title);
            for(Review review:reviews){
                System.out.println("Book Title:"+review.getBookTitle());
                System.out.println("Reviewer Name:"+review.getReviewerName());
                System.out.println("Content:"+review.getReviewText());
                System.out.println("Rating:"+review.getRating());
                System.out.println("------------------");
            }
            studentOperations();
        }
        else{
            System.out.println("Book Not found");
            studentOperations();
        }

    }

    public void addReview() throws SQLException {
        String title;
        boolean canAdd=false;
        boolean found=false;
        scanner.nextLine();
        System.out.println("Enter the book title:");
        title=scanner.nextLine();
        List<Book> books=studentService.getAllBooks();
        for(Book book:books){
            if(book.getTitle().equalsIgnoreCase(title)){
                found=true;
                canAdd=studentService.addReview(loggedinStudent.getUsername(),title);
            }
        }
        if(canAdd && found){
            System.out.println("Review Added Successfully");
            studentOperations();
        }
        else{
            System.out.println();
            studentOperations();
        }
    }

    public void viewBooks(){
    List<Book> books=studentService.getAllBooks();
        System.out.println("1)View Books Category Wise");
        System.out.println("2)View All Books");
        System.out.println("3)View Books By Points Wise");
        System.out.println("4)View By Author Name");
        System.out.println("5)View By Likes");
        int ch=scanner.nextInt();
        if(ch==1){
            categoryWise();
        }
        else if(ch==2){
            viewAllBooks();
        }
        else if(ch==3){
            viewByPoints();
        }
        else if(ch==4){
            viewByAuthor();
        }
        else if(ch==5){
            viewByLikes();
        }
        else{
            System.out.println("Invalid Input");
            studentOperations();
        }
        studentOperations();
    }

    private void viewByLikes() {
        List<Book>books=studentService.getByLikes();
        if (books == null || books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("Available Books:");
            for (Book book : books) {
                System.out.println("Book ID:"+book.getBookId());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Domain: " + book.getDomain());
                System.out.println("Points Required: " + book.getPointsRequired());
                System.out.println("Short Summary: " + book.getShortSummary());
                System.out.println("Long Summary: " + book.getLongSummary());
                System.out.println("Book Copies:"+book.getCopies());
                System.out.println("Likes:"+book.getLikes());
                System.out.println("----------------------------------");
            }
            studentOperations();
        }

    }

    private void viewByAuthor() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the Author Name:");
        String name=scanner.nextLine();
        List<Book>books=studentService.getByAuthor(name);
        if (books == null || books.isEmpty()) {
            System.out.println("No books available.");
            studentOperations();
        } else {
            System.out.println("Available Books:");
            for (Book book : books) {
                System.out.println("Book ID:"+book.getBookId());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Domain: " + book.getDomain());
                System.out.println("Points Required: " + book.getPointsRequired());
                System.out.println("Short Summary: " + book.getShortSummary());
                System.out.println("Long Summary: " + book.getLongSummary());
                System.out.println("Book Copies:"+book.getCopies());
                System.out.println("----------------------------------");
            }
            studentOperations();
        }
    }

    private void viewByPoints() {
        List<Book>books=studentService.getByPoints();
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
                System.out.println("Book Copies:"+book.getCopies());
                System.out.println("----------------------------------");
            }
            studentOperations();
        }
    }

    private void viewAllBooks() {
        List<Book> books=studentService.getAllBooks();
            if (books == null || books.isEmpty()) {
            System.out.println("No books available.");
                studentOperations();
        } else {
            System.out.println("Available Books:");
            for (Book book : books) {
                System.out.println("Book ID:"+book.getBookId());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Domain: " + book.getDomain());
                System.out.println("Points Required: " + book.getPointsRequired());
                System.out.println("Short Summary: " + book.getShortSummary());
                System.out.println("Long Summary: " + book.getLongSummary());
                System.out.println("Book Copies:"+book.getCopies());
                System.out.println("----------------------------------");
            }
                studentOperations();
        }
    }

    private void categoryWise() {
        System.out.println("Select a category:");
        System.out.println("1. Fiction");
        System.out.println("2. Non-Fiction");
        System.out.println("3. Science & Technology");
        System.out.println("4. History");
        System.out.println("5. Literature & Classics");
        System.out.println("6. Children & Young Adult");
        System.out.println("7. Self-Help & Personal Development");
        int choice = scanner.nextInt();
        String category = getCategoryByChoice(choice);
        if (category != null) {
            List<Book> books = studentService.getBooksByCategory(category);
            if (books.isEmpty()) {
                System.out.println("No books found in this category.");
            } else {
                System.out.println("Books in category " + category + ":");
                for (Book book : books) {
                    System.out.println("Book ID:"+book.getBookId());
                    System.out.println("Title: " + book.getTitle());
                    System.out.println("Author: " + book.getAuthor());
                    System.out.println("Domain: " + book.getDomain());
                    System.out.println("Points Required: " + book.getPointsRequired());
                    System.out.println("Short Summary: " + book.getShortSummary());
                    System.out.println("Long Summary: " + book.getLongSummary());
                    System.out.println("Book Copies: "+book.getCopies());
                    System.out.println("----------------------------------");
                }
            }
        } else {
            System.out.println("Invalid choice. Please select a valid category.");
        }
    }


    private String getCategoryByChoice(int choice) {
        switch (choice) {
            case 1: return "Fiction";
            case 2: return "Non-Fiction";
            case 3: return "Science & Technology";
            case 4: return "History";
            case 5: return "Literature & Classics";
            case 6: return "Children & Young Adult";
            case 7: return "Self-Help & Personal Development";
            default: return null;
        }
    }
    public void likeBook() throws SQLException {
        String title;
        scanner.nextLine();
        System.out.println("Enter the title:");
        title=scanner.nextLine();
        boolean found=false;
        List<Book> books=studentService.getAllBooks();
        for(Book book:books){
            if(book.getTitle().equalsIgnoreCase(title)){
                found=true;
            }
        }
        if(found){
            studentService.likeBook(title);
            System.out.println("Book is liked");
        }
        else{
            System.out.println("Book Not Found");
        }
        studentOperations();
    }




    public void sendMailToAuthor() throws SQLException {
        AuthorController authorController=new AuthorController();
        showAuthors();
        scanner.nextLine();
        System.out.print("Enter author's name: ");
        String authorName = scanner.nextLine();
        Author author=authorController.getAuthorObject(authorName);
        System.out.print("Enter subject: ");
        String subject = scanner.nextLine();
        System.out.print("Enter your message: ");
        String message = scanner.nextLine();

        if(author!=null)
        {
            mailService.sendEmail(author, subject, message);
            studentOperations();
        }
        else {
            System.out.println("no email available for tha author");
            studentOperations();
        }

    }

    public Student getStudentObject(String studentName) {
        try {
            List<Student> students = studentService.getAllStudents();
            if (students.isEmpty()) {
                System.out.println("No students found.");
            }
            else
            {
                for (Student student : students) {
                    if(student.getUsername().equalsIgnoreCase(studentName))
                    {
                        return student;
                    }
                }
            }
        }
        catch (SQLException e) {
            System.out.println("An error occurred while retrieving students: " + e.getMessage());
        }
        return null;
    }
    public void showAuthors() throws SQLException {
        AuthorService authorService=new AuthorService();
        try {
            List<Author> authors = authorService.getAllAuthors();
            if (authors.isEmpty()) {
                System.out.println("No authors found.");
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

            }
        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving authors: " + e.getMessage());
        }
    }

}